package id.ac.itb.ee.lskk.lumen.core.yago;

public class MatchedYagoRule {
	
	YagoRule rule;
	String subject;
	
	public MatchedYagoRule(YagoRule rule, String subject) {
		super();
		this.rule = rule;
		this.subject = subject;
	}

	/**
	 * @return the rule
	 */
	public YagoRule getRule() {
		return rule;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MatchedYagoRule ["
				+ (rule != null ? "rule=" + rule + ", " : "")
				+ (subject != null ? "subject=" + subject : "") + "]";
	}
	
}
