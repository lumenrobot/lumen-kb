package id.ac.itb.ee.lskk.lumen.yago;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nullable;

public class YagoLabel {
	
	@Nullable
	private String prefLabel;
	@Nullable
	private String label;
	@Nullable
	private Set<String> preferredMeaningLabels;
	@Nullable
	private String givenName;
	@Nullable
	private String familyName;
	
	public YagoLabel() {
		super();
	}

	public YagoLabel(String prefLabel, String label, @Nullable Set<String> preferredMeaningLabels, String givenName, String familyName) {
		super();
		this.prefLabel = prefLabel;
		this.label = label;
		this.preferredMeaningLabels = preferredMeaningLabels;
		this.givenName = givenName;
		this.familyName = familyName;
	}
	
	public String getPrefLabel() {
		return prefLabel;
	}
	
	public void setPrefLabel(String prefLabel) {
		this.prefLabel = prefLabel;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the preferredMeaningLabels
	 */
	@Nullable
	public Set<String> getPreferredMeaningLabels() {
		return preferredMeaningLabels;
	}

	public void addPreferredMeaning(String labelText) {
		if (preferredMeaningLabels == null) {
			preferredMeaningLabels = new HashSet<>();
		}
		preferredMeaningLabels.add(labelText);
	}
	
	public String getGivenName() {
		return givenName;
	}
	
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	
	public String getFamilyName() {
		return familyName;
	}
	
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

}
