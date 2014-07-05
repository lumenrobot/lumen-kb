package id.ac.itb.ee.lskk.lumen.yago;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.gridgain.grid.Grid;
import org.gridgain.grid.GridException;
import org.gridgain.grid.GridFuture;
import org.gridgain.grid.GridGain;
import org.gridgain.grid.cache.GridCache;
import org.gridgain.grid.lang.GridCallable;
import org.gridgain.grid.lang.GridClosure;
import org.gridgain.grid.lang.GridReducer;
import org.gridgain.grid.util.typedef.F;
import org.joda.money.format.MoneyFormatter;
import org.joda.money.format.MoneyFormatterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;
import com.google.common.collect.Collections2;

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

	private static final Locale ENGLISH = Locale.forLanguageTag("en-US");
	private static final Locale INDONESIAN = Locale.forLanguageTag("id-ID");
	private static final Logger log = LoggerFactory
			.getLogger(AnswerYagoFactTests.class);
	private static final boolean LOOKUP_LABEL = true;
	private static final NumberFormat EN_NUM = NumberFormat.getNumberInstance(ENGLISH);
	private static final NumberFormat ID_NUM = NumberFormat.getNumberInstance(INDONESIAN);
	private static final NumberFormat EN_PCT = NumberFormat.getPercentInstance(ENGLISH);
	private static final NumberFormat ID_PCT = NumberFormat.getPercentInstance(INDONESIAN);
	private static final MoneyFormatter MONEY_EN = new MoneyFormatterBuilder().appendCurrencySymbolLocalized().appendAmountLocalized().toFormatter(ENGLISH);
	private static final MoneyFormatter MONEY_ID = new MoneyFormatterBuilder().appendCurrencySymbolLocalized().appendAmountLocalized().toFormatter(INDONESIAN);
	
	/**
	 * @param args yagoFacts.tsv file
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws GridException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, GridException {
		String msg = "Where was Michael Jackson born?";
		Pattern testPattern = Pattern.compile("Where was (?<subject>.+) born\\?", Pattern.CASE_INSENSITIVE);
		log.info("Test pattern matches? {}", testPattern.matcher(msg).matches());
		
		try (Grid grid = GridGain.start(AnswerYagoFactTests.class.getResource("yago.gridgain.xml"))) {
			
			GridCache<String, YagoRule> cache = grid.cache("yagoRules");
			cache.loadCache(null, 0);
			cache.forceRepartition().get();
			grid.compute().broadcast(new Runnable() {
				@Override
				public void run() {
					try {
						log.info("Cache formerly has size={} offheap={} overflow={}",
								cache.size(), cache.offHeapEntriesCount(), cache.overflowSize());
					} catch (GridException e) {
						log.error("Cannot get overflow size", e);
					}
				}
			}).get();

			log.info("wasBornIn is {}", cache.get("wasBornIn"));
			grid.compute().apply(new GridClosure<String, String>() {
				@Override
				public String apply(String e) {
					log.info("Found {} YAGO rules", cache.size());
					Set<String> ruleIds = cache.keySet();
//					Collection<GridFuture<Matcher>> matchers = Collections2.transform(ruleIds, (ruleId) -> 
//						grid.compute().<Matcher>affinityCall(cache.name(), ruleId, (GridCallable<Matcher>) () -> {
//							final YagoRule rule = cache.get(ruleId);
//							log.info("Processing rule #{} {}", ruleId, rule);
//							Pattern pattern = Pattern.compile(rule.questionPattern_en, Pattern.CASE_INSENSITIVE);
//							Matcher matcher = pattern.matcher(msg);
//							if (matcher.matches()) {
//								return matcher;
//							} else {
//								return null;
//							}
//						}) );
					Collection<GridFuture<MatchedYagoRule>> matchers = Collections2.transform(ruleIds, (ruleId) ->
						grid.compute().affinityCall(cache.name(), ruleId, 
							new GridCallable<MatchedYagoRule>() {
								@Override
								public MatchedYagoRule call()
										throws Exception {
									final YagoRule rule = cache.get(ruleId);
									Pattern pattern = Pattern.compile(rule.questionPattern_en, Pattern.CASE_INSENSITIVE);
									Matcher matcher = pattern.matcher(msg);
									if (matcher.matches()) {
										log.info("MATCH {} Processing rule #{} {}", matcher, ruleId, rule.property);
										return new MatchedYagoRule(rule, matcher.group("subject"));
									} else {
										log.info("not match Processing rule #{} {}", ruleId, rule.property);
										return null;
									}
								}
							}) );
					try {
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
							log.info("Subject: {}", foundMatcher);
						}
					} catch (GridException e1) {
						Throwables.propagate(e1);
					}
//					cache.forEach(new GridInClosure<GridCacheEntry<Integer,YagoRule>>() {
//						@Override
//						public void apply(GridCacheEntry<Integer, YagoRule> e) {
//							log.info("Processing rule #{} {}", e.getKey(), e.getValue());
//						}
//					});
					return null;
				}
			}, msg).get();
			
//			MongoClient mongo = new MongoClient(new MongoClientURI("mongodb://localhost/"));
//			DB db = mongo.getDB("yago_dev");
//			DBCollection factColl = db.getCollection("fact");
//			DBCollection literalFactColl = db.getCollection("literalFact");
//			DBCollection labelColl = db.getCollection("label");
			
		}
		
	}

}
