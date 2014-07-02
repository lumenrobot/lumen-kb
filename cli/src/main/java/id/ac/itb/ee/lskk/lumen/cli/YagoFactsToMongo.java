package id.ac.itb.ee.lskk.lumen.cli;

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

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

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
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Preconditions.checkArgument(args.length == 1, "Usage: yagofactstomongo path/to/yagoFacts.ttl");
		File yagoFactsTsvFile = new File(args[0]);
		log.info("Importing '{}'...", yagoFactsTsvFile);
		MongoClient mongo = new MongoClient(new MongoClientURI("mongodb://localhost/"));
		DB db = mongo.getDB("yago_dev");
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
