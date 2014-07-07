package id.ac.itb.ee.lskk.lumen.core.yago;

import id.ac.itb.ee.lskk.lumen.core.LumenException;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.Nullable;
import javax.inject.Inject;

import org.gridgain.grid.Grid;
import org.gridgain.grid.GridException;
import org.gridgain.grid.GridFuture;
import org.gridgain.grid.cache.GridCache;
import org.gridgain.grid.cache.GridCacheTx;
import org.gridgain.grid.lang.GridCallable;
import org.gridgain.grid.lang.GridClosure;
import org.gridgain.grid.lang.GridReducer;
import org.gridgain.grid.util.typedef.F;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;
import com.google.common.base.Throwables;
import com.google.common.collect.Collections2;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder.ListMultimapBuilder;
import com.google.common.html.HtmlEscapers;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.ReadPreference;

/**
 * @author ceefour
 *
 */
public class YagoAnswerer {
	
	private static final Logger log = LoggerFactory
			.getLogger(YagoAnswerer.class);
	private static final MustacheFactory MF = new DefaultMustacheFactory();

	public static enum AnswerKind {
		OK,
		/**
		 * No question rule match.
		 */
		RULE_MISMATCH,
		/**
		 * No entity found for given label.
		 */
		MISSING_ENTITY,
		/**
		 * No fact found for given subject & property.
		 */
		MISSING_FACT
	}
	
	public static class Answer {
		private final AnswerKind kind;
		private String text;
		private String html;
		
		public Answer(AnswerKind kind, @Nullable String text, @Nullable String html) {
			super();
			this.kind = kind;
			if (html == null && text != null) {
				this.text = text;
				this.html = HtmlEscapers.htmlEscaper().escape(text);
			} else if (text == null && html != null) {
				this.text = Jsoup.parseBodyFragment(html).text();
				this.html = html;
			} else {
				this.text = text;
				this.html = html;
			}
		}
		
		/**
		 * @return the kind
		 */
		public AnswerKind getKind() {
			return kind;
		}

		/**
		 * @return the html
		 */
		public String getHtml() {
			return html;
		}

		/**
		 * @return the text
		 */
		public String getText() {
			return text;
		}

		@Override
		public String toString() {
			return kind == AnswerKind.OK ? text : kind + ": " + text;
		}
		
	}
	
	@Inject
	private Grid grid;
	@Inject
	private DB db;
	@Inject
	private YagoEntityByLabelCacheStore entityByLabelCacheStore;
	
	public Answer answer(Locale locale, String inputMsg) {
		try {
			GridCache<String, YagoRule> ruleCache = YagoRule.cache(grid);
			
			if (ruleCache.isEmpty()) {
				log.info("Loading rules...");
				ruleCache.loadCache(null, 0);
				log.info("Found {} cached YAGO rules in my node", ruleCache.size());
			}
			Set<String> ruleIds = FluentIterable.from(ruleCache.queries().createSqlFieldsQuery("SELECT property FROM YagoRule").execute().get())
					.<String>transform((it) -> (String) it.iterator().next()).toSet();
			log.debug("{} Rule IDs to process: {}", ruleIds.size(), ruleIds);
			
			MatchedYagoRule foundMatcher = grid.compute().apply(new GridClosure<String, MatchedYagoRule>() {
				@Override
				public MatchedYagoRule apply(String e) {
					try {
//					cache.queries().createScanQuery(null).execute(new GridClosure<E, R>, args)
//					Set<String> ruleIds = cache.keySet();
//					Collection<GridFuture<Matcher>> matchers = Collections2.transform(ruleIds, (ruleId) -> 
//						grid.compute().<Matcher>affinityCall(cache.name(), ruleId, (GridCallable<Matcher>) () -> {
//							final YagoRule rule = cache.get(ruleId);
//							log.info("Processing rule #{} {}", ruleId, rule);
//							Pattern pattern = Pattern.compile(rule.getQuestionPattern_en(), Pattern.CASE_INSENSITIVE);
//							Matcher matcher = pattern.matcher(msg);
//							if (matcher.matches()) {
//								return matcher;
//							} else {
//								return null;
//							}
//						}) );
						Collection<GridFuture<MatchedYagoRule>> matchers = Collections2.transform(ruleIds, (ruleId) ->
							grid.compute().affinityCall(ruleCache.name(), ruleId, 
								new GridCallable<MatchedYagoRule>() {
									@Override
									public MatchedYagoRule call()
											throws Exception {
										final YagoRule rule = ruleCache.get(ruleId);
										Pattern pattern_en = Pattern.compile(rule.getQuestionPattern_en(), Pattern.CASE_INSENSITIVE);
										Matcher matcher_en = pattern_en.matcher(inputMsg);
										if (matcher_en.matches()) {
											log.info("MATCH English {} Processing rule {} {}", matcher_en, ruleId, rule.getQuestionPattern_en());
											return new MatchedYagoRule(rule, matcher_en.group("subject"));
										} else {
											Pattern pattern_id = Pattern.compile(rule.getQuestionPattern_id(), Pattern.CASE_INSENSITIVE);
											Matcher matcher_id = pattern_id.matcher(inputMsg);
											if (matcher_id.matches()) {
												log.info("MATCH Indonesian {} Processing rule {} {}", matcher_id, ruleId, rule.getQuestionPattern_en());
												return new MatchedYagoRule(rule, matcher_id.group("subject"));
											} else {
												log.info("not match Processing rule {} {}", ruleId, rule.getQuestionPattern_en());
												return null;
											}
										}
									}
								}) );
						MatchedYagoRule foundMatcher = F.awaitAll(0, new GridReducer<MatchedYagoRule, MatchedYagoRule>() {
							MatchedYagoRule obj;
							@Override
							public boolean collect(MatchedYagoRule e) {
								if (e != null) {
									obj = e;
									return false;
								} else {
									return true;
								}
							};
							@Override
							public MatchedYagoRule reduce() {
								return obj;
							};
						}, matchers);
						log.info("Found matcher: {}", foundMatcher);
						if (foundMatcher != null) {
							return foundMatcher;
						}
					} catch (GridException e1) {
						Throwables.propagate(e1);
					}
					return null;
//				cache.forEach(new GridInClosure<GridCacheEntry<Integer,YagoRule>>() {
//					@Override
//					public void apply(GridCacheEntry<Integer, YagoRule> e) {
//						log.info("Processing rule #{} {}", e.getKey(), e.getValue());
//					}
//				});
				}
			}, inputMsg).get();

			GridCache<String, YagoLabel> labelCache = YagoLabel.cache(grid);
			GridCache<String, ListMultimap<String, String>> entityByLabelCache = YagoLabel.entityByLabelCache(grid);

			if (foundMatcher != null) {
				log.info("Subject: {} from {}", foundMatcher.getSubject(), foundMatcher);
				
				DBCollection factColl = db.getCollection("fact");
				DBCollection literalFactColl = db.getCollection("literalFact");
//			DBCollection labelColl = db.getCollection("label");
				log.debug("Finding resource for label '{}'...", foundMatcher.getSubject());
				
				@Nullable
				ListMultimap<String, String> foundEntities;
				try (GridCacheTx tx = entityByLabelCache.txStart()) {
					foundEntities = entityByLabelCache.get(foundMatcher.getSubject().toLowerCase());
					if (foundEntities == null) {
						foundEntities = entityByLabelCacheStore.load(null, foundMatcher.getSubject().toLowerCase());
						if (foundEntities == null) {
							foundEntities = ListMultimapBuilder.hashKeys().arrayListValues().build();
							foundEntities.put("_", null);
						}
						entityByLabelCache.putx(foundMatcher.getSubject().toLowerCase(), foundEntities);
					}
					tx.commit();
				}
				@Nullable
				String resId = Iterables.getFirst(foundEntities.get("_"), null);
				
				if (resId != null) {
					log.debug("Found resource '{}' for label '{}'.", resId, foundMatcher.getSubject());
					
					DBObject literalFactObj = literalFactColl.findOne(new BasicDBObject( ImmutableMap.of("s", resId, "p", foundMatcher.getRule().getProperty()) ),
							new BasicDBObject( ImmutableMap.of("_id", false, "l", true, "u", true)), ReadPreference.secondaryPreferred());
					if (literalFactObj != null) {
						final Object literalObj = literalFactObj.get("l");
						final String unit = (String) literalFactObj.get("u");
//					LiteralFact literalFact = new LiteralFact(resId, foundMatcher.getSubject(), foundMatcher.getRule().property, literalObj, unit);
						switch (locale.getLanguage()) {
						case "en":
							String answer_en = LiteralFact.formatLiteralFact_en(foundMatcher.getRule().getAnswerTemplateHtml_en(), foundMatcher.getSubject(), literalObj, unit);  
							log.info("English: {}", answer_en);
							return new Answer(AnswerKind.OK, null, answer_en.toString());
						case "in":
							String answer_id = LiteralFact.formatLiteralFact_id(foundMatcher.getRule().getAnswerTemplateHtml_id(), foundMatcher.getSubject(), literalObj, unit);
							log.info("Indonesian: {}", answer_id);
							return new Answer(AnswerKind.OK, null, answer_id.toString());
						default:
							throw new IllegalArgumentException("Unsupported language: " + locale.getLanguage());
						}
					} else {
						DBObject factObj = factColl.findOne(new BasicDBObject( ImmutableMap.of("s", resId, "p", foundMatcher.getRule().getProperty()) ),
								new BasicDBObject( ImmutableMap.of("_id", false, "o", true)), ReadPreference.secondaryPreferred());
						if (factObj != null) {
							final String factId = (String) factObj.get("o");
							YagoLabel objectLabel = labelCache.get(factId);
							String objectText;
							if (objectLabel != null) {
								if (objectLabel.getPrefLabel() != null) {
									objectText = objectLabel.getPrefLabel();
								} else if (objectLabel.getLabels() != null) {
									objectText = objectLabel.getLabels().iterator().next();
								} else {
									objectText = factId;
								}
							} else {
								objectText = factId;
							}
							switch (locale.getLanguage()) {
							case "en":
								final StringWriter sw_en = new StringWriter();
								MF.compile(new StringReader(foundMatcher.getRule().getAnswerTemplateHtml_en()), "en").run(sw_en, 
										new Object[] { ImmutableMap.of("subject", foundMatcher.getSubject(), "object", objectText) });
								log.info("English: {}", sw_en);
								return new Answer(AnswerKind.OK, null, sw_en.toString());
							case "in":
								final StringWriter sw_id = new StringWriter();
								MF.compile(new StringReader(foundMatcher.getRule().getAnswerTemplateHtml_id()), "id").run(sw_id, 
										new Object[] { ImmutableMap.of("subject", foundMatcher.getSubject(), "object", objectText) });
								log.info("Indonesian: {}", sw_id);
								return new Answer(AnswerKind.OK, null, sw_id.toString());
							default:
								throw new IllegalArgumentException("Unsupported language: " + locale.getLanguage());
							}
						} else {
							final Answer answer = new Answer(AnswerKind.MISSING_FACT, 
									String.format("Matched rule, tried finding '%s' for '%s' (%s) but no fact found.",
											foundMatcher.rule.getProperty(), resId, foundMatcher.getSubject()), null);
							log.info("{}", answer);
							return answer;
						}
					}
					
				} else {
					final Answer answer = new Answer(AnswerKind.MISSING_ENTITY, 
							String.format("Matched rule %s but no matching entity for label '%s'", foundMatcher.rule.getProperty(), foundMatcher.getSubject()), null);
					log.info("{}", answer);
					return answer;
				}
			} else {
				final Answer answer = new Answer(AnswerKind.RULE_MISMATCH, 
						String.format("No match from %s question rules: %s", ruleIds.size(), ruleIds.stream().collect(Collectors.joining(", "))), null);
				log.info("{}", answer);
				return answer;
			}
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (IllegalStateException | GridException e) {
			throw new LumenException(e, "Cannot answer: %s", inputMsg);
		}
	}

}
