package id.ac.itb.ee.lskk.lumen.yago;

import javax.annotation.Nullable;

public class LiteralFact {
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