package id.ac.itb.ee.lskk.lumen.yago;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.gridgain.grid.Grid;
import org.gridgain.grid.GridException;
import org.gridgain.grid.GridGain;
import org.gridgain.grid.cache.GridCache;
import org.gridgain.grid.lang.GridClosure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * Import YAGO labels to MongoDB.
 * 
 * <pre>
 * &gt; db.label.find().limit(10)
 * { "_id" : "Elwood_Engel", "f" : "Engel" }
 * { "_id" : "Vlaško_Polje", "l" : [ "Vlasko Polje" ] }
 * { "_id" : "Gargilesse-Dampierre", "m" : [ "Gargilesse-Dampierre" ] }
 * { "_id" : "Horseshoe_Falls_(Wales)", "l" : [ "Horseshoe Falls (Wales)" ] }
 * { "_id" : "Jerome_H._Remick", "f" : "Remick" }
 * { "_id" : "Vadim_Pankratov", "l" : [ "Vadim Pankratov" ] }
 * { "_id" : "Iori_Nomizu", "m" : [ "Iori Nomizu" ] }
 * { "_id" : "Sturt_County", "l" : [ "Sturt County, New South Wales" ] }
 * { "_id" : "Lazăr_Șăineanu", "l" : [ "Șeineanu", "Lazăr Schein", "Lazăr Shain" ] }
 * { "_id" : "Rajiv_Mishra", "l" : [ "Rajiv Mishra" ] }
 * </pre> 
 * 
 * <pre>
 * &gt; db.label.find({m: {$size: 2}}).limit(10)
 * { "_id" : "wordnet_finality_104754440", "m" : [ "conclusiveness", "finality" ] }
 * { "_id" : "wordnet_safety_blitz_100976085", "m" : [ "blitz", "safety blitz" ] }
 * { "_id" : "wordnet_olecranon_105593654", "m" : [ "olecranon process", "olecranon" ] }
 * { "_id" : "wordnet_short_sale_100079908", "m" : [ "short sale", "short selling" ] }
 * { "_id" : "wordnet_slice_100572043", "m" : [ "slicing", "fade" ] }
 * { "_id" : "wordnet_system_call_106587217", "m" : [ "system call", "supervisor call instruction" ] }
 * { "_id" : "wordnet_gyrfalcon_101611800", "m" : [ "gyrfalcon", "gerfalcon" ] }
 * { "_id" : "wordnet_eastern_chipmunk_102359915", "m" : [ "striped squirrel", "eastern chipmunk" ] }
 * { "_id" : "wordnet_mesoderm_101464077", "m" : [ "mesoblast", "mesoderm" ] }
 * { "_id" : "wordnet_beach_wagon_102814533", "m" : [ "station waggon", "beach wagon" ] }
 * </pre>
 * 
 * _id: concept ID
 * p: skos:prefLabel
 * l: [rdfs:label1, ...]
 * m (preferredMeaningLabels): [preferredMeaningLabel1, preferredMeaningLabel2, ...]
 * g: givenName
 * f: familyName
 * 
 * Priorities:
 * 
 * skos:prefLabel --> for given concept, this is the best label
 * rdfs:label
 * isPreferredMeaningOf --> for given label, this is the best concept
 * hasGivenName
 * hasFamilyName
 * 
 * @author ceefour
 */
public class YagoLabelsToMongo {

	private static final Logger log = LoggerFactory
			.getLogger(YagoLabelsToMongo.class);
	public static Pattern BRACKETS = Pattern.compile("\\<(.+)\\>");
	public static Pattern ENG = Pattern.compile("\\\"(.+)\\\"@eng");
	
	public static String removeBrackets(String input) {
		Matcher matcher = BRACKETS.matcher(input);
		if (matcher.matches()) {
			return matcher.group(1);
		} else {
			return input;
		}
	}
	
	/**
	 * @param args yagoFacts.tsv file
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws GridException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, GridException {
		Preconditions.checkArgument(args.length == 1, "Usage: yagolabels2mongo path/to/yagoLabels.ttl");
		File yagoLabelsTsvFile = new File(args[0]);
		log.info("Importing '{}'...", yagoLabelsTsvFile);
		MongoClient mongo = new MongoClient(new MongoClientURI("mongodb://localhost/"));
		DB db = mongo.getDB("yago_dev");
		DBCollection labelColl = db.getCollection("label");
		log.info("Dropping {} collection...", labelColl.getName());
		labelColl.drop();
		log.info("{} collection dropped", labelColl.getName());
		
		try (Grid grid = GridGain.start(AnswerYagoFactTests.class.getResource("yago.gridgain.xml"))) {
			GridCache<String, YagoLabel> labelCache = grid.cache("yagoLabel");
			labelCache.reloadAll();
			
			GridClosure<String[], Void> rowProcessor = (GridClosure<String[], Void>) (it) -> {
				Matcher matcher = ENG.matcher(it[3]);
				if (!matcher.matches()) {
					return null;
				}
				final String labelText = matcher.group(1);
				final String resName = removeBrackets(it[1]);
				String labelProperty = removeBrackets(it[2]); // skos:prefLabel, rdfs:label, isPreferredMeaningOf, hasFamilyName
				
				try {
					Preconditions.checkState(labelCache.lock(resName, 30000),
							"Cannot lock '%s'", resName);
					try {
						YagoLabel label = labelCache.get(resName);

						if (label == null) {
							label = new YagoLabel();
//							entityCount++;
						}

						switch (labelProperty) {
						case "skos:prefLabel":
							label.setPrefLabel(labelText);
							break;
						case "rdfs:label":
							label.addLabel(labelText);
							break;
						case "hasGivenName":
							label.setGivenName(labelText);
							break;
						case "hasFamilyName":
							label.setFamilyName(labelText);
							break;
						case "isPreferredMeaningOf":
							label.addPreferredMeaning(labelText);
							break;
						case "hasGloss": // "(especially boxing) equipment that protects an athlete's mouth"
							return null;
						default:
							throw new RuntimeException("Unknown label property: " + labelProperty);
						}
						
						labelCache.putx(resName, label);
						//labelCount++;
					} finally {
						labelCache.unlock(resName);
					}
				} catch (Exception e) {
					Throwables.propagate(e);
				}
				return null;
			};
			
			int rowCount = 0;
//			int labelCount = 0;
//			int entityCount = 0;
			log.info("Loading '{}'...", yagoLabelsTsvFile);
			try (CSVReader reader = new CSVReader(new FileReader(yagoLabelsTsvFile), '\t', CSVWriter.NO_QUOTE_CHARACTER)) {
				while (true) {
					String[] row = reader.readNext();
					if (row == null || row.length == 0) {
						break;
					}
					
					grid.compute().apply(rowProcessor, row);
					
					rowCount++;
					if (rowCount % 100000 == 0) {
						log.info("{} rows queued...", rowCount);
					}
					
//					BasicDBObject dbo = new BasicDBObject(ImmutableMap.of(
//							"_id", resName,
//							"l", label));
//					try {
//						labelColl.insert(dbo);
//						labelCount++;
//					} catch (Exception e) {
//						log.trace("Not adding duplicate " + resName + ": " + label, e);
//					}
//					if (labelCount % 10000 == 0) {
//						log.info("Inserted {} labels for {} entities", labelCount, entityCount);
//					}
				}
			}
//			log.info("Inserted {} labels for {} entities", labelCount, entityCount);
		}
		
	}

}
