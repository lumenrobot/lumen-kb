package id.ac.itb.ee.lskk.lumen.core.yago;

public class Fact {
	final String subject;
	private final String property;
	final String object;
	private final String subjectLabel;
	private final String objectLabel;
	
	public Fact(String subject, String property, String object) {
		this(subject, subject, property, object, object);
	}

	public Fact(String subject, String subjectLabel, String property, String object, String objectLabel) {
		super();
		this.subject = subject;
		this.subjectLabel = subjectLabel;
		this.property = property;
		this.object = object;
		this.objectLabel = objectLabel;
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

	/**
	 * @return the object
	 */
	public String getObject() {
		return object;
	}
	
	public String getObjectLabel() {
		return objectLabel;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Fact ["
				+ (subject != null ? "subject=" + subject + ", " : "")
				+ (property != null ? "property=" + property + ", " : "")
				+ (object != null ? "object=" + object : "") + "]";
	}
	
}