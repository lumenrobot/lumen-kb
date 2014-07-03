package id.ac.itb.ee.lskk.lumen.yago;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * Import YAGO labels to MongoDB.
 * 
 * <pre>
 * &gt; db.label.find().limit(10)
 * { "_id" : "Dr._Kuno_Struck_House", "l" : "Dr. Kuno Struck House" }
 * { "_id" : "Duarte_Coelho_Pereira", "l" : "Duarte Coelho Pereira" }
 * { "_id" : "The_Still_Alarm", "l" : "The Still Alarm" }
 * { "_id" : "Smoky_Robin", "l" : "Smoky Robin" }
 * { "_id" : "K._Natwar_Singh", "l" : "Natwar Singh" }
 * { "_id" : "Wendy_O._Williams", "l" : "Wendy o" }
 * { "_id" : "Big_tent", "l" : "Big tent" }
 * { "_id" : "Omalanthus_stokesii", "l" : "Omalanthus stokesii" }
 * { "_id" : "Cython", "l" : "Cython" }
 * { "_id" : "Rajiv_Mishra", "l" : "Rajiv Mishra" }
 * </pre>
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
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Preconditions.checkArgument(args.length == 1, "Usage: yagolabels2mongo path/to/yagoLabels.ttl");
		File yagoFactLabelsTsvFile = new File(args[0]);
		log.info("Importing '{}'...", yagoFactLabelsTsvFile);
		MongoClient mongo = new MongoClient(new MongoClientURI("mongodb://localhost/"));
		DB db = mongo.getDB("yago_dev");
		DBCollection labelColl = db.getCollection("label");
		log.info("Dropping {} collection...", labelColl.getName());
		labelColl.drop();
		log.info("{} collection dropped", labelColl.getName());
		int labelCount = 0;
		try (CSVReader reader = new CSVReader(new FileReader(yagoFactLabelsTsvFile), '\t', CSVWriter.NO_QUOTE_CHARACTER)) {
			while (true) {
				String[] row = reader.readNext();
				if (row == null || row.length == 0) {
					break;
				}
				Matcher matcher = ENG.matcher(row[3]);
				if (!matcher.matches()) {
					continue;
				}
				final String label = matcher.group(1);
				final String resName = removeBrackets(row[1]);
				BasicDBObject dbo = new BasicDBObject(ImmutableMap.of(
						"_id", resName,
						"l", label));
				try {
					labelColl.insert(dbo);
					labelCount++;
				} catch (Exception e) {
					log.trace("Not adding duplicate " + resName + ": " + label, e);
				}
				if (labelCount % 10000 == 0) {
					log.info("Inserted {} labels", labelCount);
				}
			}
		}
		log.info("Inserted {} labels", labelCount);
	}

}
