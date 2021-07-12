/*
 * Original author: Michael Riffle <mriffle .at. uw.edu>
 *                  
 * Copyright 2018 University of Washington - Seattle, WA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.yeastrc.limelight.xml.msfragger_tpp.reader;

import net.systemsbiology.regis_web.pepxml.MsmsPipelineAnalysis;
import net.systemsbiology.regis_web.pepxml.MsmsPipelineAnalysis.MsmsRunSummary;
import net.systemsbiology.regis_web.pepxml.MsmsPipelineAnalysis.MsmsRunSummary.SpectrumQuery;
import net.systemsbiology.regis_web.pepxml.MsmsPipelineAnalysis.MsmsRunSummary.SpectrumQuery.SearchResult;
import net.systemsbiology.regis_web.pepxml.MsmsPipelineAnalysis.MsmsRunSummary.SpectrumQuery.SearchResult.SearchHit;
import org.yeastrc.limelight.xml.msfragger_tpp.objects.MSFraggerParameters;
import org.yeastrc.limelight.xml.msfragger_tpp.objects.TPPPSM;
import org.yeastrc.limelight.xml.msfragger_tpp.objects.TPPReportedPeptide;
import org.yeastrc.limelight.xml.msfragger_tpp.objects.TPPResults;
import org.yeastrc.limelight.xml.msfragger_tpp.utils.ReportedPeptideUtils;
import org.yeastrc.limelight.xml.msfragger_tpp.utils.TPPParsingUtils;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael Riffle
 * @date Feb 21, 2018
 *
 */
public class TPPResultsParser {

	public static TPPResults getTPPResults( File pepXMLFile, MSFraggerParameters params, boolean isOpenMod ) throws Throwable {

		Map<TPPReportedPeptide,Map<Integer,TPPPSM>> resultMap = new HashMap<>();
				
		MsmsPipelineAnalysis msAnalysis = null;
		try {
			msAnalysis = TPPParsingUtils.getMSmsPipelineAnalysis( pepXMLFile );
		} catch( Throwable t ) {
			System.err.println( "Got an error parsing the pep XML file. Error: " + t.getMessage() );
			throw t;
		}

		if(!TPPParsingUtils.getHasPeptideProphetData(msAnalysis)) {
			String message = "PeptideProphet was not run. This converter only works for MSFragger + TPP data.\n";
			message += "Please see our other MSFragger converters to convert native MSFragger output:\n";
			message += "https://github.com/yeastrc?q=limelight-import-msfragger&type=&language=&sort=";

			throw new Exception(message);
		}
		
		
		TPPResults results = new TPPResults();
		results.setPeptidePSMMap( resultMap );
		
		results.setTppVersion( TPPParsingUtils.getTPPVersionFromXML( msAnalysis ) );
		results.setMsFraggerVersion( TPPParsingUtils.getMSFraggerVersionFromXML( msAnalysis ) );
		
		results.setHasIProphetResults( TPPParsingUtils.getHasIProphetData( msAnalysis ) );
		results.setHasPTMProphetResults( TPPParsingUtils.getHasPTMProphetData( msAnalysis ) );
		
		for( MsmsRunSummary runSummary : msAnalysis.getMsmsRunSummary() ) {
			for( SpectrumQuery spectrumQuery : runSummary.getSpectrumQuery() ) {
				
				int charge = TPPParsingUtils.getChargeFromSpectrumQuery( spectrumQuery );
				int scanNumber = TPPParsingUtils.getScanNumberFromSpectrumQuery( spectrumQuery );
				BigDecimal neutralMass = TPPParsingUtils.getNeutralMassFromSpectrumQuery( spectrumQuery );
				BigDecimal retentionTime = TPPParsingUtils.getRetentionTimeFromSpectrumQuery( spectrumQuery );
				
				for( SearchResult searchResult : spectrumQuery.getSearchResult() ) {
					for( SearchHit searchHit : searchResult.getSearchHit() ) {
						
						// do not include decoy hits
						if( TPPParsingUtils.searchHitIsDecoy( searchHit, params.getDecoyPrefix() ) ) {
							continue;
						}
						
						TPPPSM psm = null;
						
						try {
							
							psm = TPPParsingUtils.getPsmFromSearchHit( searchHit, charge, scanNumber, neutralMass, retentionTime, params, isOpenMod, results.isHasPTMProphetResults() );
							
						} catch( Throwable t) {
							
							System.err.println( "Error reading PSM from pepXML. Error: " + t.getMessage() );
							throw t;
							
						}
						
						if( psm != null ) {
							TPPReportedPeptide tppRp = ReportedPeptideUtils.getTPPReportedPeptideForTPPPSM( psm );
							
							if( !results.getPeptidePSMMap().containsKey( tppRp ) )
								results.getPeptidePSMMap().put( tppRp, new HashMap<>() );
							
							results.getPeptidePSMMap().get( tppRp ).put( psm.getScanNumber(), psm );
						}
					}
				}
			}
		}
		
		return results;
	}
	
}
