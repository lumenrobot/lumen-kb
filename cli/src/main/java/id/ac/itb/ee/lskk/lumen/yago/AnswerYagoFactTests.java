package id.ac.itb.ee.lskk.lumen.yago;

import id.ac.itb.ee.lskk.lumen.core.impl.LumenConfig;
import id.ac.itb.ee.lskk.lumen.core.yago.LiteralFact;
import id.ac.itb.ee.lskk.lumen.core.yago.MatchedYagoRule;
import id.ac.itb.ee.lskk.lumen.core.yago.YagoEntityByLabelCacheStore;
import id.ac.itb.ee.lskk.lumen.core.yago.YagoLabel;
import id.ac.itb.ee.lskk.lumen.core.yago.YagoRule;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

import org.gridgain.grid.Grid;
import org.gridgain.grid.GridException;
import org.gridgain.grid.GridFuture;
import org.gridgain.grid.cache.GridCache;
import org.gridgain.grid.cache.GridCacheTx;
import org.gridgain.grid.lang.GridCallable;
import org.gridgain.grid.lang.GridClosure;
import org.gridgain.grid.lang.GridReducer;
import org.gridgain.grid.lang.GridRunnable;
import org.gridgain.grid.util.typedef.F;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;
import com.google.common.base.Throwables;
import com.google.common.collect.Collections2;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder.ListMultimapBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.ReadPreference;

/**
 * Answer YAGO fact tests.
 * 
 * For other GridGain nodes:
 * 
 * bin/ggstart.sh ~/git/lumen-kb/cli/src/main/resources/id/ac/itb/ee/lskk/lumen/yago/yago.gridgain.xml
 * 
 * @author ceefour
 */
public class AnswerYagoFactTests {

	private static final Logger log = LoggerFactory
			.getLogger(AnswerYagoFactTests.class);
	private static final MustacheFactory MF = new DefaultMustacheFactory();
	
	/**
	 * @param args yagoFacts.tsv file
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws GridException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, GridException, InterruptedException {
//		String msg = "Where was Michael Jackson born?";
//		String msg = "Di mana Muhammad dilahirkan?";
		String msg = "Dmna Michael Jackson lahir";
//		String msg = "berapa populasi Indonesia";
//		String msg = "kapan Lady gaga lahir";
//		String msg = "Kapan Michael Jackson dilahirkan?";
//		Pattern testPattern = Pattern.compile("Where was (?<subject>.+) born\\?", Pattern.CASE_INSENSITIVE);
//		log.info("Test pattern matches? {}", testPattern.matcher(msg).matches());
		
		try (AnnotationConfigApplicationContext appCtx = new AnnotationConfigApplicationContext(LumenConfig.class)) {
			Grid grid = appCtx.getBean(Grid.class);
			DB db = appCtx.getBean(DB.class);
			
			GridCache<String, YagoRule> ruleCache = YagoRule.cache(grid);
			if (ruleCache.isEmpty()) {
				log.info("Loading rules...");
				ruleCache.loadCache(null, 0);
			}
			grid.compute().broadcast(new Runnable() {
				@Override
				public void run() {
					try {
						log.info("Cache formerly has size={} offheap={} overflow={}",
								ruleCache.size(), ruleCache.offHeapEntriesCount(), ruleCache.overflowSize());
					} catch (GridException e) {
						log.error("Cannot get overflow size", e);
					}
				}
			}).get();

			log.info("wasBornIn is {}", ruleCache.get("wasBornIn"));
			
			log.info("Found {} YAGO rules", ruleCache.size());
			Set<String> ruleIds = FluentIterable.from(ruleCache.queries().createSqlFieldsQuery("SELECT property FROM YagoRule").execute().get())
					.<String>transform((it) -> (String) it.iterator().next()).toSet();
			log.info("{} Rule IDs to process: {}", ruleIds.size(), ruleIds);
			
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
										Matcher matcher_en = pattern_en.matcher(msg);
										if (matcher_en.matches()) {
											log.info("MATCH English {} Processing rule {} {}", matcher_en, ruleId, rule.getQuestionPattern_en());
											return new MatchedYagoRule(rule, matcher_en.group("subject"));
										} else {
											Pattern pattern_id = Pattern.compile(rule.getQuestionPattern_id(), Pattern.CASE_INSENSITIVE);
											Matcher matcher_id = pattern_id.matcher(msg);
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
//					cache.forEach(new GridInClosure<GridCacheEntry<Integer,YagoRule>>() {
//						@Override
//						public void apply(GridCacheEntry<Integer, YagoRule> e) {
//							log.info("Processing rule #{} {}", e.getKey(), e.getValue());
//						}
//					});
				}
			}, msg).get();

			GridCache<String, YagoLabel> labelCache = YagoLabel.cache(grid);
			YagoEntityByLabelCacheStore entityByLabelCacheStore = appCtx.getBean(YagoEntityByLabelCacheStore.class);
			GridCache<String, ListMultimap<String, String>> entityByLabelCache = YagoLabel.entityByLabelCache(grid);

			if (foundMatcher != null) {
				log.info("Subject: {} from {}", foundMatcher.getSubject(), foundMatcher);
				
				DBCollection factColl = db.getCollection("fact");
				DBCollection literalFactColl = db.getCollection("literalFact");
//				DBCollection labelColl = db.getCollection("label");
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
//						LiteralFact literalFact = new LiteralFact(resId, foundMatcher.getSubject(), foundMatcher.getRule().property, literalObj, unit);
						String answer_en = LiteralFact.formatLiteralFact_en(foundMatcher.getRule().getAnswerTemplateHtml_en(), foundMatcher.getSubject(), literalObj, unit);  
						String answer_id = LiteralFact.formatLiteralFact_id(foundMatcher.getRule().getAnswerTemplateHtml_id(), foundMatcher.getSubject(), literalObj, unit);
						log.info("English: {}", answer_en);
						log.info("Indonesian: {}", answer_id);
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
							final StringWriter sw_en = new StringWriter();
							MF.compile(new StringReader(foundMatcher.getRule().getAnswerTemplateHtml_en()), "en").run(sw_en, 
									new Object[] { ImmutableMap.of("subject", foundMatcher.getSubject(), "object", objectText) });
							final StringWriter sw_id = new StringWriter();
							MF.compile(new StringReader(foundMatcher.getRule().getAnswerTemplateHtml_id()), "id").run(sw_id, 
									new Object[] { ImmutableMap.of("subject", foundMatcher.getSubject(), "object", objectText) });
							log.info("English: {}", sw_en);
							log.info("Indonesian: {}", sw_id);
						} else {
							log.info("I don't know");
						}
					}
					
				} else {
					log.info("No resource for label '{}'.", foundMatcher.getSubject());
				}
			} else {
				log.info("NOT FOUND!");
			}
			
			grid.compute().broadcast((GridRunnable) () -> {
				try {
					log.info("For yagoRule, I have {} primary out of {} entries + {} swap", 
							ruleCache.primarySize(), ruleCache.size(), ruleCache.swapKeys());
					log.info("For yagoLabel, I have {} primary out of {} entries + {} swap", 
							labelCache.primarySize(), labelCache.size(), labelCache.swapKeys());
					log.info("For entityByLabel, I have {} primary out of {} entries + {} swap", 
							entityByLabelCache.primarySize(), entityByLabelCache.size(), entityByLabelCache.swapKeys());
				} catch (Exception e) {
					log.error("swapKeys error", e);
				}
			});

		}
		
	}

}
