package id.ac.itb.ee.lskk.lumen.yago;

import id.ac.itb.ee.lskk.lumen.core.LumenConfig;
import id.ac.itb.ee.lskk.lumen.core.yago.YagoLabel;
import id.ac.itb.ee.lskk.lumen.core.yago.YagoRule;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.gridgain.grid.Grid;
import org.gridgain.grid.GridException;
import org.gridgain.grid.cache.GridCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.google.common.collect.ListMultimap;

public class Worker {

	private static final Logger log = LoggerFactory
			.getLogger(Worker.class);
	
	public static void main(String[] args) throws FileNotFoundException, IOException, GridException, InterruptedException {
		try (AnnotationConfigApplicationContext appCtx = new AnnotationConfigApplicationContext(LumenConfig.class)) {
			Grid grid = appCtx.getBean(Grid.class);
			
			GridCache<String, YagoRule> ruleCache = YagoRule.cache(grid);
			if (ruleCache.isEmpty()) {
				log.info("Loading rules...");
				ruleCache.loadCache(null, 0);
			}
			log.info("For yagoRule, I have {} primary out of {} entries + {} swap", 
					ruleCache.primarySize(), ruleCache.size(), ruleCache.swapKeys());
			
			GridCache<String, YagoLabel> labelCache = YagoLabel.cache(grid);
//			if (labelCache.isEmpty()) {
//				log.info("Loading labels...");
//				labelCache.loadCache(null, 0);
//			}
			log.info("For yagoLabel, I have {} primary out of {} entries + {} swap", 
					labelCache.primarySize(), labelCache.size(), labelCache.swapKeys());
			
			GridCache<String, ListMultimap<String, String>> entityByLabelCache = YagoLabel.entityByLabelCache(grid);
//			if (labelCache.isEmpty()) {
//				log.info("Loading labels...");
//				labelCache.loadCache(null, 0);
//			}
			log.info("For entityByLabel, I have {} primary out of {} entries + {} swap", 
					entityByLabelCache.primarySize(), entityByLabelCache.size(), entityByLabelCache.swapKeys());
			
			Thread.currentThread().join();
		}
	}

}
