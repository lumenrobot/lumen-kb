package id.ac.itb.ee.lskk.lumen.yago;

import java.net.UnknownHostException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.gridgain.grid.GridException;
import org.gridgain.grid.cache.GridCacheTx;
import org.gridgain.grid.cache.store.GridCacheStoreAdapter;
import org.gridgain.grid.lang.GridBiInClosure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.MultimapBuilder.ListMultimapBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * Key: label (lowercase)
 * Value: Map( type => [entity IDs in order of preference] )
 * the wildcard type is '_' (to make it store well in MongoDB or Cassandra, if wanted)
 * @author ceefour
 */
public class YagoEntityByLabelCacheStore extends GridCacheStoreAdapter<String, ListMultimap<String, String>> {
	
	private static final Logger log = LoggerFactory
			.getLogger(YagoEntityByLabelCacheStore.class);
	private final DBCollection labelColl;
	
	public YagoEntityByLabelCacheStore() throws UnknownHostException {
		super();
		MongoClient mongo = new MongoClient(new MongoClientURI("mongodb://localhost/"));
		DB db = mongo.getDB("yago_dev");
		labelColl = db.getCollection("label");
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
			GridBiInClosure<String, ListMultimap<String, String>> c) throws GridException {
		ImmutableMap<? extends String, ListMultimap<String, String>> resultMap = Maps.toMap(keys,
				(it) -> ListMultimapBuilder.<String, String>hashKeys().arrayListValues().build());
		
		String quoteds = Joiner.on('|').join( keys.stream().map((it) -> Pattern.quote(it)).collect(Collectors.toList()) );
		Pattern pattern = Pattern.compile("^(" + quoteds + ")$", Pattern.CASE_INSENSITIVE);
		
		ImmutableList<ImmutableMap<String, Pattern>> crits = ImmutableList.of(
				ImmutableMap.of("p", pattern),
				ImmutableMap.of("l", pattern),
				ImmutableMap.of("m", pattern),
				ImmutableMap.of("g", pattern),
				ImmutableMap.of("f", pattern) );
		BasicDBObject crit = new BasicDBObject("$or", crits);
		log.debug("Finding {} : {}", keys, crit);
		try (DBCursor cursor = labelColl.find(crit)) {
			for (DBObject dbo : cursor) {
				String entityId = (String) dbo.get("_id");
				final YagoLabel label = toYagoLabel(dbo);
				for (String key : keys) {
					final ListMultimap<String, String> labelResult = resultMap.get(key);
					if (label.getPreferredMeaningLabels() != null && label.getPreferredMeaningLabels().stream().anyMatch( (it) -> it.equalsIgnoreCase(key) )) {
						log.debug("{} isPreferredMeaningOf {}", entityId, key);
						// if has isPreferredMeaningOf, then this entity is prioritized for this label
						labelResult.get("_").remove(entityId);
						labelResult.get("_").add(0, entityId);
					} else if (!labelResult.get("_").contains(entityId)) {
						if (StringUtils.equalsIgnoreCase(key, label.getPrefLabel()) ||
								label.getLabels().stream().anyMatch( (it) -> it.equalsIgnoreCase(key) ) ||
								StringUtils.equalsIgnoreCase(key, label.getGivenName()) ||
								StringUtils.equalsIgnoreCase(key, label.getFamilyName()) ) {
							labelResult.get("_").add(entityId);
						}
					}
				}
			}
		}
		
		resultMap.forEach(c::apply);
	}
	
	@Override
	public ListMultimap<String, String> load(GridCacheTx tx, String key) throws GridException {
		AtomicReference<ListMultimap<String, String>> result = new AtomicReference<>(); 
		loadAll(tx, ImmutableList.of(key), (k, v) -> result.set(v));
		return result.get();
//		DBObject dbo = labelColl.findOne(new BasicDBObject("_id", key), new BasicDBObject(ImmutableMap.of("_id", false)));
//		if (dbo != null) {
//			return toYagoLabel(dbo);
//		} else {
//			return null;
//		}
	}

	@Override
	public void put(GridCacheTx tx, String key, ListMultimap<String, String> val)
			throws GridException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(GridCacheTx tx, String key) throws GridException {
		// TODO Auto-generated method stub
		
	}
	
}
