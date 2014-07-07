package id.ac.itb.ee.lskk.lumen.core.yago;

import org.gridgain.grid.Grid;
import org.gridgain.grid.cache.GridCache;
import org.gridgain.grid.cache.query.GridCacheQuerySqlField;

public class YagoRule {
	
	@GridCacheQuerySqlField(index=true)
	String property;
	String questionPattern_en;
	String questionPattern_id;
	String answerTemplateHtml_en;
	String answerTemplateHtml_id;
	
	public static GridCache<String, YagoRule> cache(Grid grid) {
		return grid.cache("yagoRule");
	}
	
	public YagoRule(String property, String questionPattern_en,
			String questionPattern_id, String answerTemplateHtml_en,
			String answerTemplateHtml_id) {
		super();
		this.property = property;
		this.questionPattern_en = questionPattern_en;
		this.questionPattern_id = questionPattern_id;
		this.answerTemplateHtml_en = answerTemplateHtml_en;
		this.answerTemplateHtml_id = answerTemplateHtml_id;
	}

	/**
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * @return the questionPattern_en
	 */
	public String getQuestionPattern_en() {
		return questionPattern_en;
	}

	/**
	 * @return the questionPattern_id
	 */
	public String getQuestionPattern_id() {
		return questionPattern_id;
	}

	/**
	 * @return the answerTemplateHtml_en
	 */
	public String getAnswerTemplateHtml_en() {
		return answerTemplateHtml_en;
	}

	/**
	 * @return the answerTemplateHtml_id
	 */
	public String getAnswerTemplateHtml_id() {
		return answerTemplateHtml_id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "YagoRule [property=" + property + ", questionPattern_en="
				+ questionPattern_en + ", questionPattern_id="
				+ questionPattern_id + ", answerTemplateHtml_en="
				+ answerTemplateHtml_en + ", answerTemplateHtml_id="
				+ answerTemplateHtml_id + "]";
	}
	
}