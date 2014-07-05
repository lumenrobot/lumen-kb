package id.ac.itb.ee.lskk.lumen.yago;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Locale;
import java.util.Map.Entry;

import org.gridgain.grid.Grid;
import org.gridgain.grid.GridException;
import org.gridgain.grid.GridGain;
import org.gridgain.grid.cache.GridCache;
import org.gridgain.grid.lang.GridReducer;
import org.joda.money.format.MoneyFormatter;
import org.joda.money.format.MoneyFormatterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Answer YAGO fact tests.
 * 
 * For other GridGain nodes:
 * 
 * bin/ggstart.sh ~/git/lumen-kb/cli/src/main/resources/id/ac/itb/ee/lskk/lumen/yago/yago.gridgain.xml
 * 
 * @author ceefour
 */
public class YagoLabelLookupCli {

	private static final Locale ENGLISH = Locale.forLanguageTag("en-US");
	private static final Locale INDONESIAN = Locale.forLanguageTag("id-ID");
	private static final Logger log = LoggerFactory
			.getLogger(YagoLabelLookupCli.class);
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
