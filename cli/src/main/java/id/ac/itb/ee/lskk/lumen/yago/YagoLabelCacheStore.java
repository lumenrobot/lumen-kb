package id.ac.itb.ee.lskk.lumen.yago;

import id.ac.itb.ee.lskk.lumen.core.impl.LumenConfig;
import id.ac.itb.ee.lskk.lumen.core.yago.YagoLabel;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.gridgain.grid.GridException;
import org.gridgain.grid.cache.GridCacheTx;
import org.gridgain.grid.cache.store.GridCacheStoreAdapter;
import org.gridgain.grid.lang.GridBiInClosure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.ReadPreference;

/**
 * Key: resource ID
 * Value: resource's label (skos:prefLabel or rdfs:label)
 * @todo Resource label can be multiple, in order of preference. In complex cases, can even be multilingual.
 * @author ceefour
 */
public class YagoLabelCacheStore extends GridCacheStoreAdapter<String, YagoLabel> {
	
	private static final Logger log = LoggerFactory
			.getLogger(YagoLabelCacheStore.class);
	private DBCollection labelColl;
	
//	@GridSpringResource(resourceName="mongoDb")
	@Inject
	private DB db;
	
	@PostConstruct
	public void init() {
		// FIXME: better workaround? http://stackoverflow.com/questions/24606646/how-to-inject-a-dependency-bean-to-gridcachestore-implementation
		if (db == null) {
			db = Preconditions.checkNotNull(LumenConfig.MONGODB, "MongoDB instance required");
		}
		labelColl = db.getCollection("label");
	}
	
	/**
	 * You should never need to use this.
	 * @see org.gridgain.grid.cache.store.GridCacheStoreAdapter#loadCache(org.gridgain.grid.lang.GridBiInClosure, java.lang.Object[])
	 */
	@Override
	public void loadCache(GridBiInClosure<String, YagoLabel> clo, Object... args)
			throws GridException {
		super.loadCache(clo, args);
		final BasicDBObject crit = new BasicDBObject("_id", Pattern.compile("^M"));
		int cnt = (int) labelColl.count(crit);
		log.info("Loading {} labels...", cnt);
		try (DBCursor cursor = labelColl.find(crit)) {
			int applied = 0;
			for (DBObject dbo : cursor) {
				final YagoLabel label = toYagoLabel(dbo);
				clo.apply((String) dbo.get("_id"), label);
				applied++;
				if (applied % 50000 == 0) {
					log.debug("  [{}%] {} labels loaded, {} more to go...", applied * 100 / cnt, applied, cnt - applied); 
				}
			}
			log.info("Loaded {} labels.", cnt);
		}
	}

	protected YagoLabel toYagoLabel(DBObject dbo) {
		Set<String> labels = dbo.containsField("l") ? new HashSet<>((List<String>) dbo.get("l")) : null;
		Set<String> preferredMeaningLabels = dbo.containsField("m") ? new HashSet<>((List<String>) dbo.get("m")) : null;
		final YagoLabel label = new YagoLabel((String) dbo.get("p"), labels, preferredMeaningLabels,
				(String) dbo.get("g"), (String) dbo.get("f"));
		return label;
	}

	@Override
	public void loadAll(GridCacheTx tx, Collection<? extends String> keys,
			GridBiInClosure<String, YagoLabel> c) throws GridException {
		try (DBCursor cursor = labelColl.find(
				new BasicDBObject("_id", ImmutableMap.of("$in", keys))).setReadPreference(ReadPreference.primary())) {
			for (DBObject dbo : cursor) {
				final YagoLabel label = toYagoLabel(dbo);
				c.apply((String) dbo.get("_id"), label);
			}
		}
	}
	
	@Override
	public YagoLabel load(GridCacheTx tx, String key) throws GridException {
		DBObject dbo = labelColl.findOne(new BasicDBObject("_id", key), new BasicDBObject(ImmutableMap.of("_id", false)), 
				ReadPreference.primary());
		if (dbo != null) {
			return toYagoLabel(dbo);
		} else {
			return null;
		}
	}
	
	@Override
	public void putAll(GridCacheTx tx,
			Map<? extends String, ? extends YagoLabel> map)
			throws GridException {
		BulkWriteOperation bulk = labelColl.initializeUnorderedBulkOperation();
		for (Entry<? extends String, ? extends YagoLabel> entry : map.entrySet()) {
			BasicDBObject dbo = toDBObject(entry.getKey(), entry.getValue());
			bulk.find(new BasicDBObject("_id", entry.getKey())).upsert().replaceOne(dbo);
		}
		BulkWriteResult writeResult = bulk.execute();
		log.debug("Put {} documents: inserted={}, modified={}, upserted={}", 
				map.size(), writeResult.getInsertedCount(), writeResult.getModifiedCount(), writeResult.getUpserts().size());
	}

	@Override
	public void put(GridCacheTx tx, String key, YagoLabel val)
			throws GridException {
		BasicDBObject dbo = toDBObject(key, val);
		labelColl.update(new BasicDBObject("_id", key), dbo, true, false);
	}

	protected BasicDBObject toDBObject(String key, YagoLabel val) {
		BasicDBObject dbo = new BasicDBObject("_id", key);
		if (val.getPrefLabel() != null) {
			dbo.put("p", val.getPrefLabel());
		}
		if (val.getLabels() != null) {
			dbo.put("l", val.getLabels());
		}
		if (val.getPreferredMeaningLabels() != null) {
			dbo.put("m", val.getPreferredMeaningLabels());
		}
		if (val.getGivenName() != null) {
			dbo.put("g", val.getGivenName());
		}
		if (val.getFamilyName() != null) {
			dbo.put("f", val.getFamilyName());
		}
		return dbo;
	}

	@Override
	public void remove(GridCacheTx tx, String key) throws GridException {
		labelColl.remove(new BasicDBObject("_id", key));
	}
	
	@Override
	public void removeAll(GridCacheTx tx, Collection<? extends String> keys)
			throws GridException {
		labelColl.remove(new BasicDBObject("_id", ImmutableMap.of("$in", keys)));
	}

}
