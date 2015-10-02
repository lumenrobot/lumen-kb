package org.lskk.lumen.yago;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * Import YAGO facts to MongoDB. 4484914 facts in about 1.5 minutes.
 * 
 * <pre>
 * &gt; db.fact.find().limit(10)
 * { "_id" : "id_1bcczro_1s2_pc2dz5", "s" : "Lenílson_Porto_Peixoto", "p" : "isAffiliatedTo", "o" : "América_Futebol_Clube_(SP)" }
 * { "_id" : "id_jqyg1z_zkl_1xi58gi", "s" : "Jefferson_County,_Texas", "p" : "owns", "o" : "Jack_Brooks_Regional_Airport" }
 * { "_id" : "id_zw0wm0_1s2_1b1jycx", "s" : "Tom_Hartley", "p" : "isAffiliatedTo", "o" : "Sinn_Féin" }
 * { "_id" : "id_f258yz_1gi_15sq7rt", "s" : "Tony_Gibson", "p" : "created", "o" : "Ghetto_Blaster_(video_game)" }
 * { "_id" : "id_6z0jld_ice_qhtymo", "s" : "Mänttä-Vilppula", "p" : "hasWebsite", "o" : "http://www.manttavilppula.fi" }
 * { "_id" : "id_ha2a9a_1s2_4mobd4", "s" : "Luis_Carlos_Martín", "p" : "isAffiliatedTo", "o" : "Real_Murcia_Imperial" }
 * { "_id" : "id_tbw8rw_p3m_zkjp59", "s" : "William_I,_Duke_of_Bavaria", "p" : "hasGender", "o" : "male" }
 * { "_id" : "id_1e7i0ut_1gi_50uey2", "s" : "Paul_Redford", "p" : "created", "o" : "The_Portland_Trip" }
 * { "_id" : "id_c6ds23_1ul_q9yabg", "s" : "Jean-Claude_Darcheville", "p" : "playsFor", "o" : "Kavala_F.C." }
 * { "_id" : "id_1rx0dux_z7a_rhd6l5", "s" : "Kumamoto_Prefectural_Theater", "p" : "isLocatedIn", "o" : "Kumamoto_Prefecture" }
 * </pre>
 * 
 * @author ceefour
 */
public class YagoFactsToMongo {

	private static final Logger log = LoggerFactory
			.getLogger(YagoFactsToMongo.class);
	public static Pattern BRACKETS = Pattern.compile("\\<(.+)\\>");
	
	public static String removeBrackets(String input) {
		Matcher matcher = BRACKETS.matcher(input);
		if (matcher.matches()) {
			return matcher.group(1);
		} else {
			return input;
		}
	}
	
	public static void insert(DBCollection coll, List<DBObject> dbos) {
		coll.insert(dbos);
	}
	
	/**
	 * @param args yagoFacts.tsv file
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws IOException {
		Preconditions.checkArgument(args.length == 1, "Usage: yagofactstomongo path/to/yagoFacts.tsv");
		File yagoFactsTsvFile = new File(args[0]);
		log.info("Importing '{}'...", yagoFactsTsvFile);
		MongoClient mongo = new MongoClient(new MongoClientURI("mongodb://localhost/"));
		DB db = mongo.getDB("lumen_lumen_dev");
		DBCollection factColl = db.getCollection("fact");
		log.info("Dropping fact collection...");
		factColl.drop();
		log.info("Fact collection dropped");
		int factCount = 0;
		try (CSVReader reader = new CSVReader(new FileReader(yagoFactsTsvFile), '\t', CSVWriter.NO_QUOTE_CHARACTER)) {
			List<DBObject> dbos = new ArrayList<>();
			while (true) {
				String[] row = reader.readNext();
				if (row == null || row.length == 0) {
					break;
				}
				BasicDBObject dbo = new BasicDBObject(ImmutableMap.of(
						//"_id", removeBrackets(row[0]),
						"s", removeBrackets(row[1]),
						"p", removeBrackets(row[2]),
						"o", removeBrackets(row[3])));
				dbos.add(dbo);
				log.trace("Inserting {}...", dbo);
				factCount++;
				if (factCount % 10000 == 0) {
					insert(factColl, dbos);
					dbos.clear();
					log.info("Inserted {} facts", factCount);
				}
			}
			if (!dbos.isEmpty()) {
				insert(factColl, dbos);
			}
			dbos.clear();
			log.info("Inserted {} facts", factCount);
		}
		log.info("Imported {} facts", factCount);
		log.info("Creating index...");
		factColl.ensureIndex(new BasicDBObject("p", 1));
		log.info("Index created");
	}

}
