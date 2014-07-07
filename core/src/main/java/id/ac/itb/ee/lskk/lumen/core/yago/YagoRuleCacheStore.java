package id.ac.itb.ee.lskk.lumen.core.yago;

import java.net.URL;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.gridgain.grid.GridException;
import org.gridgain.grid.cache.store.GridCacheLoadOnlyStoreAdapter;
import org.gridgain.grid.lang.GridBiTuple;
import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Row;
import org.odftoolkit.simple.table.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ceefour
 *
 */
public class YagoRuleCacheStore extends
		GridCacheLoadOnlyStoreAdapter<String, YagoRule, Row> {
			
	private static final Logger log = LoggerFactory
			.getLogger(YagoRuleCacheStore.class);

	@Override
	protected Iterator<Row> inputIterator(Object... args)
			throws GridException {
		URL yagoRulesOds = YagoRuleCacheStore.class.getResource("yago-rules.ods");
		log.info("Loading '{}'...", yagoRulesOds);
		try (SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(yagoRulesOds.openStream())) {
			Table table = doc.getTableList().get(0);
			log.info("Got {} rows in table {}", table.getRowCount(), table.getTableName());
			return table.getRowList().stream().skip(1).collect(Collectors.toList()).iterator();
		} catch (Exception e) {
			throw new GridException("Cannot read " + yagoRulesOds, e);
		}
	}

	@Override
	protected GridBiTuple<String, YagoRule> parse(Row row, Object... args) {
		final YagoRule rule = new YagoRule(row.getCellByIndex(0).getStringValue(), 
				row.getCellByIndex(1).getStringValue(), row.getCellByIndex(2).getStringValue(), 
				row.getCellByIndex(3).getStringValue(), row.getCellByIndex(4).getStringValue());
		log.debug("Added rule {}", rule);
		return new GridBiTuple<>(rule.property, rule);
	}

}
