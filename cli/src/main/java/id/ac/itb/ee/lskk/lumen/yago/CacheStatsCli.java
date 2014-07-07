package id.ac.itb.ee.lskk.lumen.yago;

import id.ac.itb.ee.lskk.lumen.core.impl.LumenConfig;
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

public class CacheStatsCli {

	private static final Logger log = LoggerFactory
			.getLogger(CacheStatsCli.class);
	
	public static void main(String[] args) throws FileNotFoundException, IOException, GridException, InterruptedException {
		try (AnnotationConfigApplicationContext appCtx = new AnnotationConfigApplicationContext(LumenConfig.class)) {
			Grid grid = appCtx.getBean(Grid.class);
			
			GridCache<String, YagoRule> ruleCache = YagoRule.cache(grid);
			GridCache<String, YagoLabel> labelCache = YagoLabel.cache(grid);
			GridCache<String, ListMultimap<String, String>> entityByLabelCache = YagoLabel.entityByLabelCache(grid);
			
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
