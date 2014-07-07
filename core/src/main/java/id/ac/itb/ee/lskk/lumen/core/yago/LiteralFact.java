package id.ac.itb.ee.lskk.lumen.core.yago;

import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Objects;

import javax.annotation.Nullable;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.money.format.MoneyFormatter;
import org.joda.money.format.MoneyFormatterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;
import com.google.common.collect.ImmutableMap;

public class LiteralFact {
	private static final MustacheFactory MF = new DefaultMustacheFactory();
	private static final Logger log = LoggerFactory
			.getLogger(LiteralFact.class);
	private static final Locale ENGLISH = Locale.forLanguageTag("en-US");
	private static final Locale INDONESIAN = Locale.forLanguageTag("id-ID");
	private static final NumberFormat EN_NUM = NumberFormat.getNumberInstance(ENGLISH);
	private static final NumberFormat ID_NUM = NumberFormat.getNumberInstance(INDONESIAN);
	private static final NumberFormat EN_PCT = NumberFormat.getPercentInstance(ENGLISH);
	private static final NumberFormat ID_PCT = NumberFormat.getPercentInstance(INDONESIAN);
	private static final MoneyFormatter MONEY_EN = new MoneyFormatterBuilder().appendCurrencySymbolLocalized().appendAmountLocalized().toFormatter(ENGLISH);
	private static final MoneyFormatter MONEY_ID = new MoneyFormatterBuilder().appendCurrencySymbolLocalized().appendAmountLocalized().toFormatter(INDONESIAN);
	
	final String subject;
	private final String property;
	final Object literal;
	private final String subjectLabel;
	@Nullable
	private final String unit;
	
	public LiteralFact(String subject, String subjectLabel, String property, Object literal, @Nullable String unit) {
		super();
		this.subject = subject;
		this.subjectLabel = subjectLabel;
		this.property = property;
		this.literal = literal;
		this.unit = unit;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	
	public String getSubjectLabel() {
		return subjectLabel;
	}

	/**
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}
	
	public Object getLiteral() {
		return literal;
	}
	
	@Nullable
	public String getUnit() {
		return unit;
	}

	public static String formatLiteralFact_en(String answerTemplate_en,
			String subjectLabel, final Object literal, String unit) throws NumberFormatException {
		final String objectText_en;
		if (unit == null) {
			objectText_en = Objects.toString(literal);
		} else if ("yago0to100".equals(unit)) {
			objectText_en = EN_PCT.format(Double.parseDouble(literal.toString()) / 100);
		} else if ("xsd:nonNegativeInteger".equals(unit)) {
			objectText_en = EN_NUM.format(literal);
		} else if ("degrees".equals(unit)) {
			objectText_en = EN_NUM.format(literal) + "°";
		} else if ("dollar".equals(unit)) {
			Money money = Money.of(CurrencyUnit.USD, new BigDecimal(literal.toString()));
			objectText_en = MONEY_EN.print(money);
		} else if ("euro".equals(unit)) {
			Money money = Money.of(CurrencyUnit.EUR, new BigDecimal(literal.toString()));
			objectText_en = MONEY_EN.print(money);
		} else if ("yagoMonetaryValue".equals(unit)) {
			// TODO: currency
			objectText_en = EN_NUM.format(literal);
		} else if (literal instanceof Number) {
			objectText_en = EN_NUM.format(literal) + " " + unit;
		} else if ("xsd:date".equals(unit)) {
			String dateStr_en = (String) literal;
			try {
				LocalDate localDate = LocalDate.parse((String) literal);
				dateStr_en = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(ENGLISH));
			} catch (Exception e) {
				Year year = Year.parse(((String) literal).substring(0, 4));
				dateStr_en = "year " + year.toString();
			}
			objectText_en = dateStr_en;
		} else {
			objectText_en = literal + " " + unit;
		}
		StringWriter sw_en = new StringWriter();
		MF.compile(new StringReader(answerTemplate_en), "en")
			.run(sw_en, new Object[] { ImmutableMap.of("subject", subjectLabel, "object", objectText_en) });
		return sw_en.toString();
	}

	public static String formatLiteralFact_id(String answerTemplate_id,
			String subjectLabel, final Object literal, String unit) throws NumberFormatException {
		final String objectText_id;
		if (unit == null) {
			objectText_id = Objects.toString(literal);
		} else if ("yago0to100".equals(unit)) {
			objectText_id = ID_PCT.format(Double.parseDouble(literal.toString())  / 100);
		} else if ("xsd:nonNegativeInteger".equals(unit)) {
			objectText_id = ID_NUM.format(literal);
		} else if ("degrees".equals(unit)) {
			objectText_id = ID_NUM.format(literal) + "°";
		} else if ("dollar".equals(unit)) {
			Money money = Money.of(CurrencyUnit.USD, new BigDecimal(literal.toString()));
			objectText_id = MONEY_ID.print(money);
		} else if ("euro".equals(unit)) {
			Money money = Money.of(CurrencyUnit.EUR, new BigDecimal(literal.toString()));
			objectText_id = MONEY_ID.print(money);
		} else if ("yagoMonetaryValue".equals(unit)) {
			// TODO: currency
			objectText_id = ID_NUM.format(literal);
		} else if (literal instanceof Number) {
			objectText_id = ID_NUM.format(literal) + " " + unit;
		} else if ("xsd:date".equals(unit)) {
			String dateStr_id = (String) literal;
			try {
				LocalDate localDate = LocalDate.parse((String) literal);
				dateStr_id = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(INDONESIAN));
			} catch (Exception e) {
				Year year = Year.parse(((String) literal).substring(0, 4));
				dateStr_id = "tahun " + year.toString();
			}
			objectText_id = dateStr_id;
		} else {
			objectText_id = literal + " " + unit;
		}
		StringWriter sw_id = new StringWriter();
		MF.compile(new StringReader(answerTemplate_id), "id")
			.run(sw_id, new Object[] { ImmutableMap.of("subject", subjectLabel, "object", objectText_id) });
		return sw_id.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LiteralFact ["
				+ (subject != null ? "subject=" + subject + ", " : "")
				+ (property != null ? "property=" + property + ", " : "")
				+ (literal != null ? "literal=" + literal + ", " : "")
				+ (subjectLabel != null ? "subjectLabel=" + subjectLabel + ", "
						: "") + (unit != null ? "unit=" + unit : "") + "]";
	}
	
}