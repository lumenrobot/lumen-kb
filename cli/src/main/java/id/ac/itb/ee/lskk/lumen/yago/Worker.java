package id.ac.itb.ee.lskk.lumen.yago;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.gridgain.grid.Grid;
import org.gridgain.grid.GridException;
import org.gridgain.grid.GridGain;
import org.gridgain.grid.cache.GridCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Worker {

	private static final Logger log = LoggerFactory
			.getLogger(Worker.class);
	
	public static void main(String[] args) throws FileNotFoundException, IOException, GridException, InterruptedException {
		try (Grid grid = GridGain.start(Worker.class.getResource("yago.gridgain.xml"))) {

			GridCache<String, YagoRule> ruleCache = grid.cache("yagoRules");
			if (ruleCache.isEmpty()) {
				log.info("Loading rules...");
				ruleCache.loadCache(null, 0);
			}
			log.info("For yagoRule, I have {} primary out of {} entries + {} swap", 
					ruleCache.primarySize(), ruleCache.size(), ruleCache.swapKeys());
			
			GridCache<String, String> labelCache = grid.cache("yagoLabel");
			if (labelCache.isEmpty()) {
				log.info("Loading labels...");
				labelCache.loadCache(null, 0);
			}
			log.info("For yagoLabel, I have {} primary out of {} entries + {} swap", 
					labelCache.primarySize(), labelCache.size(), labelCache.swapKeys());
			
			Thread.currentThread().join();
		}
	}

}
