package id.ac.itb.ee.lskk.lumen.yago;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.RandomUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.money.format.MoneyFormatter;
import org.joda.money.format.MoneyFormatterBuilder;
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

	private static final Locale ENGLISH = Locale.forLanguageTag("en-US");
	private static final Locale INDONESIAN = Locale.forLanguageTag("id-ID");
	private static final Logger log = LoggerFactory
			.getLogger(GenerateRandomYagoFactTests.class);
	private static final boolean LOOKUP_LABEL = true;
	private static final NumberFormat EN_NUM = NumberFormat.getNumberInstance(ENGLISH);
	private static final NumberFormat ID_NUM = NumberFormat.getNumberInstance(INDONESIAN);
	private static final NumberFormat EN_PCT = NumberFormat.getPercentInstance(ENGLISH);
	private static final NumberFormat ID_PCT = NumberFormat.getPercentInstance(INDONESIAN);
	private static final MoneyFormatter MONEY_EN = new MoneyFormatterBuilder().appendCurrencySymbolLocalized().appendAmountLocalized().toFormatter(ENGLISH);
	private static final MoneyFormatter MONEY_ID = new MoneyFormatterBuilder().appendCurrencySymbolLocalized().appendAmountLocalized().toFormatter(INDONESIAN);
	
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
		DBCollection literalFactColl = db.getCollection("literalFact");
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
			
			// Attempt 1 : Fact
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
					continue;
				} else {
					log.trace("No facts match for {} using {}", rule, queryObj);
				}
			}
			
			// Attempt 2 : Literal Fact
			try (DBCursor cursor = literalFactColl.find(queryObj, new BasicDBObject(ImmutableMap.of("s", true, "l", true, "u", true)))) {
				int foundCount = cursor.count();
				if (foundCount >= 1) {
					int factIdx = RandomUtils.nextInt(0, foundCount);
					DBObject factObj = cursor.skip(factIdx).limit(1).next();
					final String subjectName = (String) factObj.get("s");
					final Object literal = factObj.get("l");
					String unit = (String) factObj.get("u");
					String subjectLabel;
					if (LOOKUP_LABEL) {
						try {
							subjectLabel = (String) labelColl.findOne(new BasicDBObject("_id", subjectName), new BasicDBObject("l", true)).get("l");
						} catch (Exception e) {
							// Usually is website URI
							log.trace("No label for subject " + subjectName, e);
							subjectLabel = subjectName;
						}
					} else {
						subjectLabel = subjectName;
					}
					LiteralFact literalFact = new LiteralFact(subjectName, subjectLabel, rule.getProperty(), literal, unit);
					log.debug("Got literal fact #{} of {}: {}", factIdx, foundCount, literalFact);
					if (unit == null) {
						System.out.println(String.format(rule.getQuestionTemplate_en(), literalFact.getSubjectLabel()));
						System.out.println("  " + String.format(rule.getAnswerTemplate_en(), literalFact.getSubjectLabel(), literalFact.getLiteral()));
						System.out.println(String.format(rule.getQuestionTemplate_id(), literalFact.getSubjectLabel()));
						System.out.println("  " + String.format(rule.getAnswerTemplate_id(), literalFact.getSubjectLabel(), literalFact.getLiteral()));
					} else if ("yago0to100".equals(unit)) {
						System.out.println(String.format(rule.getQuestionTemplate_en(), literalFact.getSubjectLabel()));
						System.out.println("  " + String.format(rule.getAnswerTemplate_en(), literalFact.getSubjectLabel(),
								EN_PCT.format(Double.parseDouble(literalFact.getLiteral().toString()) / 100) ));
						System.out.println(String.format(rule.getQuestionTemplate_id(), literalFact.getSubjectLabel()));
						System.out.println("  " + String.format(rule.getAnswerTemplate_id(), literalFact.getSubjectLabel(), 
								ID_PCT.format(Double.parseDouble(literalFact.getLiteral().toString())  / 100) ));
					} else if ("xsd:nonNegativeInteger".equals(unit)) {
						System.out.println(String.format(rule.getQuestionTemplate_en(), literalFact.getSubjectLabel()));
						System.out.println("  " + String.format(rule.getAnswerTemplate_en(), literalFact.getSubjectLabel(),
								EN_NUM.format(literalFact.getLiteral()) ));
						System.out.println(String.format(rule.getQuestionTemplate_id(), literalFact.getSubjectLabel()));
						System.out.println("  " + String.format(rule.getAnswerTemplate_id(), literalFact.getSubjectLabel(), 
								ID_NUM.format(literalFact.getLiteral()) ));
					} else if ("degrees".equals(unit)) {
						System.out.println(String.format(rule.getQuestionTemplate_en(), literalFact.getSubjectLabel()));
						System.out.println("  " + String.format(rule.getAnswerTemplate_en(), literalFact.getSubjectLabel(),
								EN_NUM.format(literalFact.getLiteral()) + "°"));
						System.out.println(String.format(rule.getQuestionTemplate_id(), literalFact.getSubjectLabel()));
						System.out.println("  " + String.format(rule.getAnswerTemplate_id(), literalFact.getSubjectLabel(), 
								ID_NUM.format(literalFact.getLiteral()) + "°"));
					} else if ("dollar".equals(unit)) {
						Money money = Money.of(CurrencyUnit.USD, new BigDecimal(literalFact.getLiteral().toString()));
						System.out.println(String.format(rule.getQuestionTemplate_en(), literalFact.getSubjectLabel()));
						System.out.println("  " + String.format(rule.getAnswerTemplate_en(), literalFact.getSubjectLabel(),
								MONEY_EN.print(money) ));
						System.out.println(String.format(rule.getQuestionTemplate_id(), literalFact.getSubjectLabel()));
						System.out.println("  " + String.format(rule.getAnswerTemplate_id(), literalFact.getSubjectLabel(), 
								MONEY_ID.print(money) ));
					} else if ("euro".equals(unit)) {
						Money money = Money.of(CurrencyUnit.EUR, new BigDecimal(literalFact.getLiteral().toString()));
						System.out.println(String.format(rule.getQuestionTemplate_en(), literalFact.getSubjectLabel()));
						System.out.println("  " + String.format(rule.getAnswerTemplate_en(), literalFact.getSubjectLabel(),
								MONEY_EN.print(money) ));
						System.out.println(String.format(rule.getQuestionTemplate_id(), literalFact.getSubjectLabel()));
						System.out.println("  " + String.format(rule.getAnswerTemplate_id(), literalFact.getSubjectLabel(), 
								MONEY_ID.print(money) ));
					} else if ("yagoMonetaryValue".equals(unit)) {
						// TODO: currency
						System.out.println(String.format(rule.getQuestionTemplate_en(), literalFact.getSubjectLabel()));
						System.out.println("  " + String.format(rule.getAnswerTemplate_en(), literalFact.getSubjectLabel(),
								EN_NUM.format(literalFact.getLiteral()) ));
						System.out.println(String.format(rule.getQuestionTemplate_id(), literalFact.getSubjectLabel()));
						System.out.println("  " + String.format(rule.getAnswerTemplate_id(), literalFact.getSubjectLabel(), 
								ID_NUM.format(literalFact.getLiteral()) ));
					} else if (literal instanceof Number) {
						System.out.println(String.format(rule.getQuestionTemplate_en(), literalFact.getSubjectLabel()));
						System.out.println("  " + String.format(rule.getAnswerTemplate_en(), literalFact.getSubjectLabel(),
								EN_NUM.format(literalFact.getLiteral()) + " " + literalFact.getUnit() ));
						System.out.println(String.format(rule.getQuestionTemplate_id(), literalFact.getSubjectLabel()));
						System.out.println("  " + String.format(rule.getAnswerTemplate_id(), literalFact.getSubjectLabel(), 
								ID_NUM.format(literalFact.getLiteral()) + " " + literalFact.getUnit() ));
					} else if ("xsd:date".equals(unit)) {
						String dateStr_en = (String) literalFact.getLiteral();
						String dateStr_id = (String) literalFact.getLiteral();
						try {
							LocalDate localDate = LocalDate.parse((String) literalFact.getLiteral());
							dateStr_en = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(ENGLISH));
							dateStr_id = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(INDONESIAN));
						} catch (Exception e) {
							Year year = Year.parse(((String) literalFact.getLiteral()).substring(0, 4));
							dateStr_en = "year " + year.toString();
							dateStr_id = "tahun " + year.toString();
						}
						System.out.println(String.format(rule.getQuestionTemplate_en(), literalFact.getSubjectLabel()));
						System.out.println("  " + String.format(rule.getAnswerTemplate_en(), literalFact.getSubjectLabel(), dateStr_en ));
						System.out.println(String.format(rule.getQuestionTemplate_id(), literalFact.getSubjectLabel()));
						System.out.println("  " + String.format(rule.getAnswerTemplate_id(), literalFact.getSubjectLabel(), dateStr_id ));
					} else {
						System.out.println(String.format(rule.getQuestionTemplate_en(), literalFact.getSubjectLabel()));
						System.out.println("  " + String.format(rule.getAnswerTemplate_en(), literalFact.getSubjectLabel(), literalFact.getLiteral() + " " + literalFact.getUnit() ));
						System.out.println(String.format(rule.getQuestionTemplate_id(), literalFact.getSubjectLabel()));
						System.out.println("  " + String.format(rule.getAnswerTemplate_id(), literalFact.getSubjectLabel(), literalFact.getLiteral() + " " + literalFact.getUnit() ));
					}
					testCount++;
					continue;
				} else {
					log.warn("No facts nor literal facts match for {} using {}", rule, queryObj);
					continue;
				}
			}
			
		}
	}

}
