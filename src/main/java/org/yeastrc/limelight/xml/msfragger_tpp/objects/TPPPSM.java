package org.yeastrc.limelight.xml.msfragger_tpp.objects;

import java.math.BigDecimal;
import java.util.Map;

public class TPPPSM {

	private BigDecimal hyperScore;
	private BigDecimal nextScore;
	private BigDecimal eValue;
	private BigDecimal massDiff;
	private OpenModification openModification;

	public OpenModification getOpenModification() {
		return openModification;
	}

	public void setOpenModification(OpenModification openModification) {
		this.openModification = openModification;
	}

	private BigDecimal peptideProphetProbability;
	private BigDecimal interProphetProbability;

	private BigDecimal positionProbability;		// from PTM prophet
	private String ptmProphetPeptideString;		// from PTM prophet

	private int hitRank;
	
	private int scanNumber;
	private BigDecimal precursorNeutralMass;
	private int charge;
	private BigDecimal retentionTime;
	
	private String peptideSequence;
	
	private Map<Integer,BigDecimal> modifications;
	
	private BigDecimal peptideProphetFDR;
	private BigDecimal interProphetFDR;

	public String getPtmProphetPeptideString() {
		return ptmProphetPeptideString;
	}

	public void setPtmProphetPeptideString(String ptmProphetPeptideString) {
		this.ptmProphetPeptideString = ptmProphetPeptideString;
	}

	public BigDecimal getHyperScore() {
		return hyperScore;
	}

	public void setHyperScore(BigDecimal hyperScore) {
		this.hyperScore = hyperScore;
	}

	public BigDecimal getNextScore() {
		return nextScore;
	}

	public void setNextScore(BigDecimal nextScore) {
		this.nextScore = nextScore;
	}

	public BigDecimal getMassDiff() {
		return massDiff;
	}

	public void setMassDiff(BigDecimal massDiff) {
		this.massDiff = massDiff;
	}

	public BigDecimal getPositionProbability() {
		return positionProbability;
	}

	public void setPositionProbability(BigDecimal positionProbability) {
		this.positionProbability = positionProbability;
	}

	public BigDecimal geteValue() {
		return eValue;
	}

	public void seteValue(BigDecimal eValue) {
		this.eValue = eValue;
	}

	public BigDecimal getPeptideProphetProbability() {
		return peptideProphetProbability;
	}

	public void setPeptideProphetProbability(BigDecimal peptideProphetProbability) {
		this.peptideProphetProbability = peptideProphetProbability;
	}

	public BigDecimal getInterProphetProbability() {
		return interProphetProbability;
	}

	public void setInterProphetProbability(BigDecimal interProphetProbability) {
		this.interProphetProbability = interProphetProbability;
	}

	public int getHitRank() {
		return hitRank;
	}

	public void setHitRank(int hitRank) {
		this.hitRank = hitRank;
	}

	public int getScanNumber() {
		return scanNumber;
	}

	public void setScanNumber(int scanNumber) {
		this.scanNumber = scanNumber;
	}

	public BigDecimal getPrecursorNeutralMass() {
		return precursorNeutralMass;
	}

	public void setPrecursorNeutralMass(BigDecimal precursorNeutralMass) {
		this.precursorNeutralMass = precursorNeutralMass;
	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}

	public BigDecimal getRetentionTime() {
		return retentionTime;
	}

	public void setRetentionTime(BigDecimal retentionTime) {
		this.retentionTime = retentionTime;
	}

	public String getPeptideSequence() {
		return peptideSequence;
	}

	public void setPeptideSequence(String peptideSequence) {
		this.peptideSequence = peptideSequence;
	}

	public Map<Integer, BigDecimal> getModifications() {
		return modifications;
	}

	public void setModifications(Map<Integer, BigDecimal> modifications) {
		this.modifications = modifications;
	}

	public BigDecimal getPeptideProphetFDR() {
		return peptideProphetFDR;
	}

	public void setPeptideProphetFDR(BigDecimal peptideProphetFDR) {
		this.peptideProphetFDR = peptideProphetFDR;
	}

	public BigDecimal getInterProphetFDR() {
		return interProphetFDR;
	}

	public void setInterProphetFDR(BigDecimal interProphetFDR) {
		this.interProphetFDR = interProphetFDR;
	}

}
