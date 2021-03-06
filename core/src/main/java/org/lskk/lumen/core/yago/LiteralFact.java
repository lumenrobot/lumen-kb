package org.lskk.lumen.core.yago;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.javamoney.moneta.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import javax.money.Monetary;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	private static final MonetaryAmountFormat MONEY_EN = MonetaryFormats.getAmountFormat(ENGLISH);
	private static final MonetaryAmountFormat MONEY_ID = MonetaryFormats.getAmountFormat(INDONESIAN);
	private static final Pattern YEAR_MONTH = Pattern.compile("^([0-9]+-[0-9]+)");
	private static final Pattern YEAR_ONLY = Pattern.compile("^([0-9]+)");
	
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
			Money money = Money.of(new BigDecimal(literal.toString()), Monetary.getCurrency("USD"));
			objectText_en = MONEY_EN.format(money);
		} else if ("euro".equals(unit)) {
			Money money = Money.of(new BigDecimal(literal.toString()), Monetary.getCurrency("EUR"));
			objectText_en = MONEY_EN.format(money);
		} else if ("yagoMonetaryValue".equals(unit)) {
			// TODO: currency
			objectText_en = EN_NUM.format(literal);
		} else if (literal instanceof Number) {
			objectText_en = EN_NUM.format(literal) + " " + unit;
		} else if ("xsd:date".equals(unit)) {
			final String literalText = (String) literal;
			String dateStr_en = literalText;
			try {
				LocalDate localDate = LocalDate.parse(literalText, DateTimeFormatter.ofPattern("uuu-M-d"));
				dateStr_en = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(ENGLISH));
			} catch (Exception e) {
				Matcher yearMonthMatcher = YEAR_MONTH.matcher(literalText);
				Matcher yearMatcher = YEAR_ONLY.matcher(literalText);
				if (yearMonthMatcher.matches()) {
					YearMonth yearMonth = YearMonth.parse(yearMonthMatcher.group(1));
					dateStr_en = yearMonth.format(DateTimeFormatter.ofPattern("LLL uuu", ENGLISH));
				} else if (yearMatcher.matches()) {
					Year year = Year.parse(yearMatcher.group(1));
					dateStr_en = "year " + year.format(DateTimeFormatter.ofPattern("uuu", ENGLISH));
				}
			}
			objectText_en = dateStr_en;
		} else {
			objectText_en = literal + " " + unit;
		}
		StringWriter sw_en = new StringWriter();
		MF.compile(new StringReader(answerTemplate_en), "en")
			.run(sw_en, ImmutableList.of(ImmutableMap.of("subject", subjectLabel, "object", objectText_en)));
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
			Money money = Money.of(new BigDecimal(literal.toString()), Monetary.getCurrency("USD"));
			objectText_id = MONEY_ID.format(money);
		} else if ("euro".equals(unit)) {
			Money money = Money.of(new BigDecimal(literal.toString()), Monetary.getCurrency("EUR"));
			objectText_id = MONEY_ID.format(money);
		} else if ("yagoMonetaryValue".equals(unit)) {
			// TODO: currency
			objectText_id = ID_NUM.format(literal);
		} else if (literal instanceof Number) {
			objectText_id = ID_NUM.format(literal) + " " + unit;
		} else if ("xsd:date".equals(unit)) {
			final String literalText = (String) literal;
			String dateStr_id = literalText;
			try {
				LocalDate localDate = LocalDate.parse(literalText, DateTimeFormatter.ofPattern("uuu-M-d"));
				dateStr_id = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(INDONESIAN));
			} catch (Exception e) {
				Matcher yearMonthMatcher = YEAR_MONTH.matcher(literalText);
				Matcher yearMatcher = YEAR_ONLY.matcher(literalText);
				if (yearMonthMatcher.matches()) {
					YearMonth yearMonth = YearMonth.parse(yearMonthMatcher.group(1));
					dateStr_id = yearMonth.format(DateTimeFormatter.ofPattern("LLL uuu", INDONESIAN));
				} else if (yearMatcher.matches()) {
					Year year = Year.parse(yearMatcher.group(1));
					dateStr_id = "tahun " + year.format(DateTimeFormatter.ofPattern("uuu", INDONESIAN));
				}
			}
			objectText_id = dateStr_id;
		} else {
			objectText_id = literal + " " + unit;
		}
		StringWriter sw_id = new StringWriter();
		MF.compile(new StringReader(answerTemplate_id), "id")
			.run(sw_id, ImmutableList.of( ImmutableMap.of("subject", subjectLabel, "object", objectText_id) ));
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