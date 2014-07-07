package id.ac.itb.ee.lskk.lumen.yago;

import id.ac.itb.ee.lskk.lumen.core.LumenConfig;
import id.ac.itb.ee.lskk.lumen.core.yago.YagoAnswerer;
import id.ac.itb.ee.lskk.lumen.core.yago.YagoAnswerer.Answer;
import id.ac.itb.ee.lskk.lumen.core.yago.YagoLabel;
import id.ac.itb.ee.lskk.lumen.core.yago.YagoRule;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.gridgain.grid.Grid;
import org.gridgain.grid.GridException;
import org.gridgain.grid.cache.GridCache;
import org.gridgain.grid.lang.GridRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.google.common.collect.ListMultimap;

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
//		String msg = "Dmna Michael Jackson lahir";
//		String msg = "kapan Muhammad lahir";
		String msg = "berapa pdb Indonesia";
//		String msg = "berapa populasi Indonesia";
//		String msg = "kapan Lady gaga lahir";
//		String msg = "Kapan Michael Jackson dilahirkan?";
//		Pattern testPattern = Pattern.compile("Where was (?<subject>.+) born\\?", Pattern.CASE_INSENSITIVE);
//		log.info("Test pattern matches? {}", testPattern.matcher(msg).matches());
		
		try (AnnotationConfigApplicationContext appCtx = new AnnotationConfigApplicationContext(LumenConfig.class)) {
			Grid grid = appCtx.getBean(Grid.class);
			
			GridCache<String, YagoRule> ruleCache = YagoRule.cache(grid);
			GridCache<String, YagoLabel> labelCache = YagoLabel.cache(grid);
			GridCache<String, ListMultimap<String, String>> entityByLabelCache = YagoLabel.entityByLabelCache(grid);
			
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
			
			YagoAnswerer answerer = appCtx.getBean(YagoAnswerer.class);
			Answer answer_en = answerer.answer(LumenConfig.ENGLISH, msg);
			Answer answer_id = answerer.answer(LumenConfig.INDONESIAN, msg);
			log.info("English: {}", answer_en);
			log.info("Indonesian: {}", answer_id);
			
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
