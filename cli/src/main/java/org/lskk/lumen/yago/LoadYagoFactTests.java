package org.lskk.lumen.yago;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.gridgain.grid.Grid;
import org.gridgain.grid.GridException;
import org.gridgain.grid.GridGain;
import org.gridgain.grid.cache.GridCache;
import org.lskk.lumen.core.yago.YagoRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Generate random YAGO fact tests.
 * @author ceefour
 */
public class LoadYagoFactTests {

	private static final Logger log = LoggerFactory
			.getLogger(LoadYagoFactTests.class);

	/**
	 * @param args yagoFacts.tsv file
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws GridException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, GridException {
		try (Grid grid = GridGain.start(AnswerYagoFactTests.class.getResource("yago.gridgain.xml"))) {
			GridCache<Integer, YagoRule> cache = grid.cache("yagoRules");
			grid.compute().broadcast((Runnable) () -> {
                try {
                    log.info("Cache formerly has size={} offheap={} overflow={}",
                            cache.size(), cache.offHeapEntriesCount(), cache.overflowSize());
                } catch (GridException e) {
                    log.error("Cannot get overflow size", e);
                }
            }).get();
			
			// load rules
//			try (GridDataLoader<Integer, YagoRule> loader = grid.dataLoader("yagoRules")) {
				URL yagoRulesTsv = LoadYagoFactTests.class.getResource("yago-rules.tsv");
				log.info("Loading '{}'...", yagoRulesTsv);
				int ruleIdx = 0;
				// LibreOffice doesn't use \ as escape character
				try (CSVReader reader = new CSVReader(
						new InputStreamReader(yagoRulesTsv.openStream(), StandardCharsets.UTF_8),
							'\t', CSVWriter.DEFAULT_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER)) {
					reader.readNext(); // skip heading
					while (true) {
						String[] row = reader.readNext();
						if (row == null) {
							break;
						}
						ruleIdx++;
						final YagoRule rule = new YagoRule(row[0], row[1], row[2], row[3], row[4]);
//						loader.addData(ruleIdx, rule);
						cache.put(ruleIdx, rule);
						log.debug("Added rule #{} {}", ruleIdx, rule);
					}
				}
				log.info("Loaded {} rules", ruleIdx);
//			}
			
			grid.compute().broadcast((Runnable) () -> {
                try {
                    log.info("Cache now has size={} offheap={} overflow={}",
                            cache.size(), cache.offHeapEntriesCount(), cache.overflowSize());
                } catch (GridException e) {
                    log.error("Cannot get overflow size", e);
                }
            }).get();
			
//			MongoClient mongo = new MongoClient(new MongoClientURI("mongodb://localhost/"));
//			DB db = mongo.getDB("yago_dev");
//			DBCollection factColl = db.getCollection("fact");
//			DBCollection literalFactColl = db.getCollection("literalFact");
//			DBCollection labelColl = db.getCollection("label");
			
		}
		
	}

}
