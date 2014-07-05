package id.ac.itb.ee.lskk.lumen.yago;

import java.net.UnknownHostException;
import java.util.regex.Pattern;

import org.gridgain.grid.GridException;
import org.gridgain.grid.cache.GridCacheTx;
import org.gridgain.grid.cache.store.GridCacheStoreAdapter;
import org.gridgain.grid.lang.GridBiInClosure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * Key: resource ID
 * Value: resource's label (skos:prefLabel or rdfs:label)
 * @todo Resource label can be multiple, in order of preference. In complex cases, can even be multilingual.
 * @author ceefour
 */
public class YagoLabelCacheStore extends GridCacheStoreAdapter<String, String> {
	
	private static final Logger log = LoggerFactory
			.getLogger(YagoLabelCacheStore.class);
	private final DBCollection labelColl;
	
	public YagoLabelCacheStore() throws UnknownHostException {
		super();
		MongoClient mongo = new MongoClient(new MongoClientURI("mongodb://localhost/"));
		DB db = mongo.getDB("yago_dev");
		labelColl = db.getCollection("label");
	}
	
	@Override
	public void loadCache(GridBiInClosure<String, String> clo, Object... args)
			throws GridException {
		super.loadCache(clo, args);
		final BasicDBObject crit = new BasicDBObject("_id", Pattern.compile("^M"));
		int cnt = (int) labelColl.count(crit);
		log.info("Loading {} labels...", cnt);
		try (DBCursor cursor = labelColl.find(crit)) {
			int applied = 0;
			for (DBObject dbo : cursor) {
				clo.apply((String) dbo.get("_id"), (String) dbo.get("l"));
				applied++;
				if (applied % 50000 == 0) {
					log.debug("  [{}%] {} labels loaded, {} more to go...", applied * 100 / cnt, applied, cnt - applied); 
				}
			}
			log.info("Loaded {} labels.", cnt);
		}
	}

	@Override
	public String load(GridCacheTx tx, String key) throws GridException {
		DBObject dbo = labelColl.findOne(new BasicDBObject("_id", key), new BasicDBObject(ImmutableMap.of("_id", false, "l", true)));
		if (dbo != null) {
			return (String) dbo.get("l");
		} else {
			return null;
		}
	}

	@Override
	public void put(GridCacheTx tx, String key, String val)
			throws GridException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void remove(GridCacheTx tx, String key) throws GridException {
		throw new UnsupportedOperationException();
	}

}
