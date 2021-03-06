package org.lskk.lumen.core.yago;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nullable;

import org.gridgain.grid.Grid;
import org.gridgain.grid.cache.GridCache;

import com.google.common.collect.ListMultimap;

public class YagoLabel {
	
	public static GridCache<String, YagoLabel> cache(Grid grid) {
		return grid.cache("yagoLabel");
	}

	public static GridCache<String, ListMultimap<String, String>> entityByLabelCache(
			Grid grid) {
		return grid.cache("yagoEntityByLabel");
	}

	@Nullable
	private String prefLabel;
	@Nullable
	private Set<String> labels;
	@Nullable
	private Set<String> preferredMeaningLabels;
	@Nullable
	private String givenName;
	@Nullable
	private String familyName;
	
	public YagoLabel() {
		super();
	}

	public YagoLabel(String prefLabel, Set<String> labels, @Nullable Set<String> preferredMeaningLabels, String givenName, String familyName) {
		super();
		this.prefLabel = prefLabel;
		this.labels = labels;
		this.preferredMeaningLabels = preferredMeaningLabels;
		this.givenName = givenName;
		this.familyName = familyName;
	}
	
	@Nullable
	public String getPrefLabel() {
		return prefLabel;
	}
	
	public void setPrefLabel(String prefLabel) {
		this.prefLabel = prefLabel;
	}
	
	@Nullable
	public Set<String> getLabels() {
		return labels;
	}
	
	public void addLabel(String labelText) {
		if (labels == null) {
			labels = new HashSet<>();
		}
		labels.add(labelText);
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
	
	@Nullable
	public String getGivenName() {
		return givenName;
	}
	
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	
	@Nullable
	public String getFamilyName() {
		return familyName;
	}
	
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "YagoLabel ["
				+ (prefLabel != null ? "prefLabel=" + prefLabel + ", " : "")
				+ (labels != null ? "labels=" + labels + ", " : "")
				+ (preferredMeaningLabels != null ? "preferredMeaningLabels="
						+ preferredMeaningLabels + ", " : "")
				+ (givenName != null ? "givenName=" + givenName + ", " : "")
				+ (familyName != null ? "familyName=" + familyName : "") + "]";
	}

}
