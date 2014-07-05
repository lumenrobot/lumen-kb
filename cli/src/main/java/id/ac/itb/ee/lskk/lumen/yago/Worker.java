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

			GridCache<String, YagoRule> cache = grid.cache("yagoRules");
			cache.loadCache(null, 0);
			
			Thread.currentThread().join();
		}
	}

}
