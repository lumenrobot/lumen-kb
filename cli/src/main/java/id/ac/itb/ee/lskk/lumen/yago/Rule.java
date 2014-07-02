package id.ac.itb.ee.lskk.lumen.yago;

public class Rule {
	final String property;
	final String questionTemplate_en;
	final String questionTemplate_id;
	final String answerTemplate_en;
	final String answerTemplate_id;
	
	public Rule(String property, String questionTemplate_en,
			String questionTemplate_id, String answerTemplate_en,
			String answerTemplate_id) {
		super();
		this.property = property;
		this.questionTemplate_en = questionTemplate_en;
		this.questionTemplate_id = questionTemplate_id;
		this.answerTemplate_en = answerTemplate_en;
		this.answerTemplate_id = answerTemplate_id;
	}
	
	/**
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * @return the questionTemplate_en
	 */
	public String getQuestionTemplate_en() {
		return questionTemplate_en;
	}

	/**
	 * @return the questionTemplate_id
	 */
	public String getQuestionTemplate_id() {
		return questionTemplate_id;
	}

	/**
	 * @return the answerTemplate_en
	 */
	public String getAnswerTemplate_en() {
		return answerTemplate_en;
	}

	/**
	 * @return the answerTemplate_id
	 */
	public String getAnswerTemplate_id() {
		return answerTemplate_id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Rule ["
				+ (property != null ? "property=" + property + ", " : "")
				+ (questionTemplate_en != null ? "questionTemplate_en="
						+ questionTemplate_en + ", " : "")
				+ (questionTemplate_id != null ? "questionTemplate_id="
						+ questionTemplate_id + ", " : "")
				+ (answerTemplate_en != null ? "answerTemplate_en="
						+ answerTemplate_en + ", " : "")
				+ (answerTemplate_id != null ? "answerTemplate_id="
						+ answerTemplate_id : "") + "]";
	}
	
}