package org.yeastrc.limelight.xml.msfragger_tpp.objects;

import java.util.Map;

public class TPPResults {

	private boolean hasIProphetResults;
	private boolean hasPTMProphetResults;
	private boolean hasSubSearches = false;
	private String msFraggerVersion;
	private String tppVersion;
	private Map<TPPReportedPeptide, Map<Integer, TPPPSM>> peptidePSMMap;
	
	/**
	 * @return the msFraggerVersion
	 */
	public String getMsFraggerVersion() {
		return msFraggerVersion;
	}
	/**
	 * @param msFraggerVersion the msFraggerVersion to set
	 */
	public void setMsFraggerVersion(String msFraggerVersion) {
		this.msFraggerVersion = msFraggerVersion;
	}
	/**
	 * @return the peptidePSMMap
	 */
	public Map<TPPReportedPeptide, Map<Integer, TPPPSM>> getPeptidePSMMap() {
		return peptidePSMMap;
	}
	/**
	 * @param peptidePSMMap the peptidePSMMap to set
	 */
	public void setPeptidePSMMap(Map<TPPReportedPeptide, Map<Integer, TPPPSM>> peptidePSMMap) {
		this.peptidePSMMap = peptidePSMMap;
	}
	public String getTppVersion() {
		return tppVersion;
	}
	public void setTppVersion(String tppVersion) {
		this.tppVersion = tppVersion;
	}
	public boolean isHasIProphetResults() {
		return hasIProphetResults;
	}
	public void setHasIProphetResults(boolean hasIProphetResults) {
		this.hasIProphetResults = hasIProphetResults;
	}

	public boolean isHasPTMProphetResults() {
		return hasPTMProphetResults;
	}

	public void setHasPTMProphetResults(boolean hasPTMProphetResults) {
		this.hasPTMProphetResults = hasPTMProphetResults;
	}

	public boolean isHasSubSearches() {
		return hasSubSearches;
	}

	public void setHasSubSearches(boolean hasSubSearches) {
		this.hasSubSearches = hasSubSearches;
	}
}
