package id.ac.itb.ee.lskk.lumen.yago;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
 * Import YAGO literal facts to MongoDB.
 * Imported 3,353,659 literal facts in 1.5 minutes.
 * 
 * <pre>
 * &gt; db.literalFact.find().limit(10)
 * { "_id" : ObjectId("53b48a4944ae4e969d12b225"), "s" : "Katherine_LaNasa", "p" : "wasBornOnDate", "l" : "1966-12-01", "u" : "xsd:date" }
 * { "_id" : ObjectId("53b48a4944ae4e969d12b226"), "s" : "Tirrel_Burton", "p" : "wasBornOnDate", "l" : "1930-##-##", "u" : "xsd:date" }
 * { "_id" : ObjectId("53b48a4944ae4e969d12b227"), "s" : "Julian_Prochnow", "p" : "wasBornOnDate", "l" : "1986-07-01", "u" : "xsd:date" }
 * { "_id" : ObjectId("53b48a4944ae4e969d12b228"), "s" : "Glen_Smith_(cricketer)", "p" : "wasBornOnDate", "l" : "1973-##-##", "u" : "xsd:date" }
 * { "_id" : ObjectId("53b48a4944ae4e969d12b229"), "s" : "Jack_Offer", "p" : "wasBornOnDate", "l" : "1908-##-##", "u" : "xsd:date" }
 * { "_id" : ObjectId("53b48a4944ae4e969d12b22a"), "s" : "Beacon_Towers", "p" : "wasCreatedOnDate", "l" : "1918-##-##", "u" : "xsd:date" }
 * { "_id" : ObjectId("53b48a4944ae4e969d12b22b"), "s" : "James_C._Shannon", "p" : "wasBornOnDate", "l" : "1896-07-21", "u" : "xsd:date" }
 * { "_id" : ObjectId("53b48a4944ae4e969d12b22c"), "s" : "Wolfpack_(video_game)", "p" : "happenedOnDate", "l" : "1990-##-##", "u" : "xsd:date" }
 * { "_id" : ObjectId("53b48a4944ae4e969d12b22d"), "s" : "Bout-du-Pont-de-Larn", "p" : "hasArea", "l" : 7630000, "u" : "m^2" }
 * { "_id" : ObjectId("53b48a4944ae4e969d12b22e"), "s" : "Passiflora_arbelaezii", "p" : "wasCreatedOnDate", "l" : "1957-##-##", "u" : "xsd:date" }
 * </pre>
 * 
 * @author ceefour
 */
public class YagoLiteralFactsToMongo {

	private static final Logger log = LoggerFactory
			.getLogger(YagoLiteralFactsToMongo.class);
	public static Pattern BRACKETS = Pattern.compile("\\<(.+)\\>");
	public static Pattern LITERAL = Pattern.compile("\\\"(.+)\\\"\\^\\^(.+)");
	private static NumberFormat NUMBER = NumberFormat.getNumberInstance(Locale.ENGLISH);
	
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
	 * @param args yagoLiteralFacts.tsv file
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Preconditions.checkArgument(args.length == 1, "Usage: yagoliteralfacts2mongo path/to/yagoLiteralFacts.ttl");
		File yagoLiteralFactsTsvFile = new File(args[0]);
		log.info("Importing '{}'...", yagoLiteralFactsTsvFile);
		MongoClient mongo = new MongoClient(new MongoClientURI("mongodb://localhost/"));
		DB db = mongo.getDB("yago_dev");
		DBCollection literalFactColl = db.getCollection("literalFact");
		log.info("Dropping {} collection...", literalFactColl.getName());
		literalFactColl.drop();
		log.info("{} collection dropped", literalFactColl.getName());
		int factCount = 0;
		try (CSVReader reader = new CSVReader(new FileReader(yagoLiteralFactsTsvFile), '\t', CSVWriter.NO_QUOTE_CHARACTER)) {
			List<DBObject> dbos = new ArrayList<>();
			while (true) {
				String[] row = reader.readNext();
				if (row == null || row.length == 0) {
					break;
				}
				final String subject = removeBrackets(row[1]);
				final String property = removeBrackets(row[2]);
				Matcher matcher = LITERAL.matcher(row[3]);
				final BasicDBObject dbo;
				if (matcher.matches()) {
					String unit = removeBrackets(matcher.group(2));
					Object literal = matcher.group(1);
					try {
						literal = Long.valueOf(matcher.group(1));
					} catch (NumberFormatException e) {
						try {
							literal = Double.valueOf(matcher.group(1));
						} catch (NumberFormatException e1) {
							// give up!
						}
					}
					dbo = new BasicDBObject(ImmutableMap.of(
							//"_id", removeBrackets(row[0]),
							"s", subject,
							"p", property,
							"l", literal,
							"u", unit));
				} else {
					// plain string
					String literal = row[3].substring(1, row[3].length() - 1).replace("\\", "");
					dbo = new BasicDBObject(ImmutableMap.of(
							//"_id", removeBrackets(row[0]),
							"s", subject,
							"p", property,
							"l", literal));
				}
				dbos.add(dbo);
				log.trace("Inserting {}...", dbo);
				factCount++;
				if (factCount % 10000 == 0) {
					insert(literalFactColl, dbos);
					dbos.clear();
					log.info("Inserted {} literal facts", NUMBER.format(factCount));
				}
			}
			if (!dbos.isEmpty()) {
				insert(literalFactColl, dbos);
			}
			dbos.clear();
			log.info("Inserted {} literal facts", NUMBER.format(factCount));
		}
		log.info("Imported {} literal facts", NUMBER.format(factCount));
		log.info("Ensuring index...");
		literalFactColl.ensureIndex(new BasicDBObject("p", 1));
		log.info("Index ensured.");
	}

}
