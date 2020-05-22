package org.yeastrc.limelight.xml.msfragger_tpp.utils;

import org.yeastrc.limelight.xml.msfragger_tpp.objects.TPPPSM;
import org.yeastrc.limelight.xml.msfragger_tpp.objects.TPPReportedPeptide;

public class ReportedPeptideUtils {

	public static TPPReportedPeptide getTPPReportedPeptideForTPPPSM( TPPPSM psm ) throws Exception {
		
		TPPReportedPeptide rp = new TPPReportedPeptide();
		
		rp.setNakedPeptide( psm.getPeptideSequence() );
		rp.setMods( psm.getModifications() );
		rp.setReportedPeptideString( ModParsingUtils.getRoundedReportedPeptideString( psm.getPeptideSequence(), psm.getModifications() ));

		return rp;
	}

}
