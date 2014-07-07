package id.ac.itb.ee.lskk.lumen.core.yago;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import org.gridgain.grid.GridException;
import org.gridgain.grid.cache.store.GridCacheLoadOnlyStoreAdapter;
import org.gridgain.grid.lang.GridBiTuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

/**
 * @author ceefour
 * @deprecated Use {@link YagoRuleCacheStore}.
 */
@Deprecated
public class TsvYagoRuleCacheStore extends
		GridCacheLoadOnlyStoreAdapter<String, YagoRule, String[]> {
			
	private static final Logger log = LoggerFactory
			.getLogger(TsvYagoRuleCacheStore.class);

	@Override
	protected Iterator<String[]> inputIterator(Object... args)
			throws GridException {
		URL yagoRulesTsv = TsvYagoRuleCacheStore.class.getResource("yago-rules.tsv");
		log.info("Loading '{}'...", yagoRulesTsv);
		// LibreOffice doesn't use \ as escape character
		try (CSVReader reader = new CSVReader(
				new InputStreamReader(yagoRulesTsv.openStream(), StandardCharsets.UTF_8),
					'\t', CSVWriter.DEFAULT_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER)) {
			reader.readNext(); // skip heading
			return reader.readAll().iterator();
		} catch (IOException e) {
			throw new GridException("Cannot read " + yagoRulesTsv, e);
		}
	}

	@Override
	protected GridBiTuple<String, YagoRule> parse(String[] row, Object... args) {
		final YagoRule rule = new YagoRule(row[0], row[1], row[2], row[3], row[4]);
		log.debug("Added rule {}", rule);
		return new GridBiTuple<>(rule.property, rule);
	}

}
