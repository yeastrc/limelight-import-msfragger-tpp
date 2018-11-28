package org.yeastrc.limelight.xml.msfragger_tpp.objects;

import java.math.BigDecimal;
import java.util.Map;

public class TPPPSM {

	private BigDecimal xCorr;
	private BigDecimal deltaCn;
	private BigDecimal eValue;
	private BigDecimal ppmError;
	
	private BigDecimal peptideProphetProbability;
	private BigDecimal interProphetProbability;
	
	private int hitRank;
	
	
	private int scanNumber;
	private BigDecimal precursorNeutralMass;
	private int charge;
	private BigDecimal retentionTime;
	
	private String peptideSequence;
	
	private Map<Integer,BigDecimal> modifications;
	
	private BigDecimal peptideProphetFDR;
	private BigDecimal interProphetFDR;

    public BigDecimal getPpmError() {
        return ppmError;
    }

    public void setPpmError(BigDecimal ppmError) {
        this.ppmError = ppmError;
    }

    public BigDecimal getxCorr() {
		return xCorr;
	}

	public void setxCorr(BigDecimal xCorr) {
		this.xCorr = xCorr;
	}

	public BigDecimal getDeltaCn() {
		return deltaCn;
	}

	public void setDeltaCn(BigDecimal deltaCn) {
		this.deltaCn = deltaCn;
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
