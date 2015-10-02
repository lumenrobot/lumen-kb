package org.lskk.lumen.yago;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Map.Entry;

import org.gridgain.grid.Grid;
import org.gridgain.grid.GridException;
import org.gridgain.grid.GridGain;
import org.gridgain.grid.cache.GridCache;
import org.gridgain.grid.lang.GridReducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YagoLabelLookupCli {

	private static final Logger log = LoggerFactory
			.getLogger(YagoLabelLookupCli.class);

	public static void main(String[] args) throws FileNotFoundException, IOException, GridException {
		String upLabel = "Muhammad";
		
		try (Grid grid = GridGain.start(YagoLabelLookupCli.class.getResource("yago.gridgain.xml"))) {
			GridCache<String, String> labelCache = grid.cache("yagoLabel");
//			labelCache.queries().createScanQuery(null).execute((GridClosure<Entry<String, String>, String>) (it) ->
//				it.getValue().equalsIgnoreCase(upLabel) ? it.getKey() : null);
			log.info("Finding resource for label '{}'...", upLabel);
			Collection<String> founds = labelCache.queries().createScanQuery(null).execute(new GridReducer<Entry<String, String>, String>() {
				String id;
				
				@Override
				public boolean collect(Entry<String, String> e) {
					if (e.getValue().equalsIgnoreCase(upLabel)) {
						id = e.getKey();
						return false;
					} else {
						return true;
					}
				}
				
				@Override
				public String reduce() {
					return id;
				}
			}).get();
			
			log.info("Found for {}: {}", upLabel, founds);
		}
		
	}

}
