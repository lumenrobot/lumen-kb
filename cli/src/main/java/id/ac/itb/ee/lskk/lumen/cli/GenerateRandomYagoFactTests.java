package id.ac.itb.ee.lskk.lumen.cli;

import id.ac.itb.ee.lskk.lumen.yago.Fact;
import id.ac.itb.ee.lskk.lumen.yago.Rule;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import jline.internal.InputStreamReader;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVReader;

import com.google.common.collect.ImmutableMap;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * Generate random YAGO fact tests.
 * @author ceefour
 */
public class GenerateRandomYagoFactTests {

	private static final Logger log = LoggerFactory
			.getLogger(GenerateRandomYagoFactTests.class);
	private static final boolean LOOKUP_LABEL = true;
	
	/**
	 * @param args yagoFacts.tsv file
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		URL yagoPropertiesTsv = GenerateRandomYagoFactTests.class.getResource("yago-properties.tsv");
		log.info("Loading '{}'...", yagoPropertiesTsv);
		MongoClient mongo = new MongoClient(new MongoClientURI("mongodb://localhost/"));
		DB db = mongo.getDB("yago_dev");
		DBCollection factColl = db.getCollection("fact");
		DBCollection labelColl = db.getCollection("label");
		List<Rule> rules = new ArrayList<>();
		try (CSVReader reader = new CSVReader(new InputStreamReader(yagoPropertiesTsv.openStream(), StandardCharsets.UTF_8), '\t')) {
			reader.readNext(); // skip heading
			while (true) {
				String[] row = reader.readNext();
				if (row == null) {
					break;
				}
				final Rule rule = new Rule(row[0], row[1], row[2], row[3], row[4]);
				rules.add(rule);
				log.debug("Added rule: {}", rule);
			}
		}
		log.info("Loaded {} rules", rules.size());
		
		int testCount = 0;
		while (testCount < 10) {
			int ruleIdx = RandomUtils.nextInt(0, rules.size());
			Rule rule = rules.get(ruleIdx);
			final BasicDBObject queryObj = new BasicDBObject("p", rule.getProperty());
			try (DBCursor cursor = factColl.find(queryObj, new BasicDBObject(ImmutableMap.of("s", true, "o", true)))) {
				int foundCount = cursor.count();
				if (foundCount >= 1) {
					int factIdx = RandomUtils.nextInt(0, foundCount);
					DBObject factObj = cursor.skip(factIdx).limit(1).next();
					final Fact fact;
					final String subjectName = (String) factObj.get("s");
					final String objectName = (String) factObj.get("o");
					if (LOOKUP_LABEL) {
						String subjectLabel;
						try {
							subjectLabel = (String) labelColl.findOne(new BasicDBObject("_id", subjectName), new BasicDBObject("l", true)).get("l");
						} catch (Exception e) {
							// Usually is website URI
							log.trace("No label for subject " + subjectName, e);
							subjectLabel = subjectName;
						}
						String objectLabel;
						try {
							objectLabel = (String) labelColl.findOne(new BasicDBObject("_id", objectName), new BasicDBObject("l", true)).get("l");
						} catch (Exception e) {
							// Usually is website URI
							log.trace("No label for object " + objectName, e);
							objectLabel = objectName;
						}
						fact = new Fact(subjectName, subjectLabel, rule.getProperty(), objectName, objectLabel);
					} else {
						fact = new Fact(subjectName, rule.getProperty(), objectName);
					}
					log.debug("Got fact #{} of {}: {}", factIdx, foundCount, fact);
					System.out.println(String.format(rule.getQuestionTemplate_en(), fact.getSubjectLabel()));
					System.out.println("  " + String.format(rule.getAnswerTemplate_en(), fact.getSubjectLabel(), fact.getObjectLabel()));
					System.out.println(String.format(rule.getQuestionTemplate_id(), fact.getSubjectLabel()));
					System.out.println("  " + String.format(rule.getAnswerTemplate_id(), fact.getSubjectLabel(), fact.getObjectLabel()));
					testCount++;
				} else {
					log.warn("No facts match for {} using {}", rule, queryObj);
					continue;
				}
			}
		}
	}

}
