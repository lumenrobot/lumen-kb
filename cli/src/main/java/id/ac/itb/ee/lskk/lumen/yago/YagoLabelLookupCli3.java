package id.ac.itb.ee.lskk.lumen.yago;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.gridgain.grid.Grid;
import org.gridgain.grid.GridException;
import org.gridgain.grid.GridGain;
import org.gridgain.grid.cache.GridCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ListMultimap;

public class YagoLabelLookupCli3 {

	private static final Logger log = LoggerFactory
			.getLogger(YagoLabelLookupCli3.class);
	
	public static void main(String[] args) throws FileNotFoundException, IOException, GridException {
		String upLabel = "Muhammad";
		
		try (Grid grid = GridGain.start(YagoLabelLookupCli3.class.getResource("yago.gridgain.xml"))) {
			GridCache<String, ListMultimap<String, String>> entityByLabelCache = grid.cache("yagoEntityByLabel");
//			labelCache.queries().createScanQuery(null).execute((GridClosure<Entry<String, String>, String>) (it) ->
//				it.getValue().equalsIgnoreCase(upLabel) ? it.getKey() : null);
			log.info("Finding resource for label '{}'...", upLabel);
//			entityByLabelCache.removex(upLabel);
			ListMultimap<String, String> entities = entityByLabelCache.get(upLabel);
			log.info("Found for {}: {}", upLabel, entities);
		}
		
	}

}
