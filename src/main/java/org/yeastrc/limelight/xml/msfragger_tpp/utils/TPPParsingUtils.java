package org.yeastrc.limelight.xml.msfragger_tpp.utils;

import net.systemsbiology.regis_web.pepxml.*;
import net.systemsbiology.regis_web.pepxml.ModInfoDataType.ModAminoacidMass;
import net.systemsbiology.regis_web.pepxml.MsmsPipelineAnalysis.AnalysisSummary;
import net.systemsbiology.regis_web.pepxml.MsmsPipelineAnalysis.MsmsRunSummary;
import net.systemsbiology.regis_web.pepxml.MsmsPipelineAnalysis.MsmsRunSummary.SearchSummary;
import net.systemsbiology.regis_web.pepxml.MsmsPipelineAnalysis.MsmsRunSummary.SpectrumQuery;
import net.systemsbiology.regis_web.pepxml.MsmsPipelineAnalysis.MsmsRunSummary.SpectrumQuery.SearchResult.SearchHit;
import net.systemsbiology.regis_web.pepxml.MsmsPipelineAnalysis.MsmsRunSummary.SpectrumQuery.SearchResult.SearchHit.AnalysisResult;
import org.yeastrc.limelight.xml.msfragger_tpp.constants.MassConstants;
import org.yeastrc.limelight.xml.msfragger_tpp.objects.MSFraggerParameters;
import org.yeastrc.limelight.xml.msfragger_tpp.objects.OpenModification;
import org.yeastrc.limelight.xml.msfragger_tpp.objects.TPPPSM;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static java.lang.Math.toIntExact;

public class TPPParsingUtils {

	/**
	 * Attempt to get the comet version from the pepXML file. Returns "Unknown" if not found.
	 * 
	 * @param msAnalysis
	 * @return
	 */
	public static String getMSFraggerVersionFromXML(MsmsPipelineAnalysis msAnalysis ) {
		
		for( MsmsRunSummary runSummary : msAnalysis.getMsmsRunSummary() ) {
			for( SearchSummary searchSummary : runSummary.getSearchSummary() ) {

				if( 	searchSummary.getSearchEngine() != null &&
						searchSummary.getSearchEngine().value() != null &&
						searchSummary.getSearchEngine().value().equals( "X! Tandem" ) ) {	// interesting, they report as X! Tandem?

					return searchSummary.getSearchEngineVersion();
				}
			
			}
		}
		
		return "Unknown";
	}
	
	/**
	 * Attempt to get the TPP version from the pepXML. Returns "Unknown" if not found.
	 * 
	 * @param msAnalysis
	 * @return
	 */
	public static String getTPPVersionFromXML( MsmsPipelineAnalysis msAnalysis ) {
		
		for( AnalysisSummary analysisSummary : msAnalysis.getAnalysisSummary() ) {
			
			for( Object o : analysisSummary.getAny() ) {
			
				// if iProphet was run, get version from its summary
				try {
					InterprophetSummary pps = (InterprophetSummary)o;
					return pps.getVersion();

				} catch( Throwable t ) { ; }
				
				// if iProphet was not run, get version from peptideprophet summary
				try {
					PeptideprophetSummary pps = (PeptideprophetSummary)o;
					return pps.getVersion();

				} catch( Throwable t ) { ; }
				
			}
		}
		
		return "Unknown";
	}
	
	/**
	 * Return true if the results can be expected to have iProphet data, false otherwise.
	 * 
	 * @param msAnalysis
	 * @return
	 */
	public static boolean getHasIProphetData( MsmsPipelineAnalysis msAnalysis ) {
		
		for( AnalysisSummary analysisSummary : msAnalysis.getAnalysisSummary() ) {
			
			for( Object o : analysisSummary.getAny() ) {

				if( o instanceof InterprophetSummary ) {
					return true;
				}
				
			}
		}
		
		return false;
	}

	/**
	 * Return true if the results can be expected to have PTMProphet data, false otherwise.
	 *
	 * @param msAnalysis
	 * @return
	 */
	public static boolean getHasPTMProphetData( MsmsPipelineAnalysis msAnalysis ) {

		for( AnalysisSummary analysisSummary : msAnalysis.getAnalysisSummary() ) {

			for( Object o : analysisSummary.getAny() ) {

				if( o instanceof PtmprophetSummary) {
					return true;
				}

			}
		}

		return false;
	}
	

	/**
	 * Return true if this searchHit is a decoy. This means that it only matches
	 * decoy proteins.
	 * 
	 * @param searchHit
	 * @return
	 */
	public static boolean searchHitIsDecoy( SearchHit searchHit, String decoyPrefix ) {
		
		String protein = searchHit.getProtein();
		if( protein.startsWith( decoyPrefix ) ) {
			
			if( searchHit.getAlternativeProtein() != null ) {
				for( AltProteinDataType ap : searchHit.getAlternativeProtein() ) {
					if( !ap.getProtein().startsWith( decoyPrefix ) ) {
						return false;
					}
				}
			}
			
			return true;			
		}
		
		return false;
	}
	
	/**
	 * Return the top-most parent element of the pepXML file as a JAXB object.
	 * 
	 * @param file
	 * @return
	 * @throws Throwable
	 */
	public static MsmsPipelineAnalysis getMSmsPipelineAnalysis( File file ) throws Throwable {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(MsmsPipelineAnalysis.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		MsmsPipelineAnalysis msAnalysis = (MsmsPipelineAnalysis)jaxbUnmarshaller.unmarshal( file );
		
		return msAnalysis;
	}
	
	/**
	 * Get the retention time from the spectrumQuery JAXB object
	 * 
	 * @param spectrumQuery
	 * @return
	 */
	public static BigDecimal getRetentionTimeFromSpectrumQuery( SpectrumQuery spectrumQuery ) {
		return spectrumQuery.getRetentionTimeSec();
	}
	
	/**
	 * Get the neutral mass from the spectrumQuery JAXB object
	 * 
	 * @param spectrumQuery
	 * @return
	 */
	public static BigDecimal getNeutralMassFromSpectrumQuery( SpectrumQuery spectrumQuery ) {
		return spectrumQuery.getPrecursorNeutralMass();
	}
	
	/**
	 * Get the scan number from the spectrumQuery JAXB object
	 * 
	 * @param spectrumQuery
	 * @return
	 */
	public static int getScanNumberFromSpectrumQuery( SpectrumQuery spectrumQuery ) {
		return toIntExact( spectrumQuery.getStartScan() );
	}
	
	/**
	 * Get the charge from the spectrumQuery JAXB object
	 * 
	 * @param spectrumQuery
	 * @return
	 */
	public static int getChargeFromSpectrumQuery( SpectrumQuery spectrumQuery ) {
		return spectrumQuery.getAssumedCharge().intValue();
	}

	/**
	 * Get a TPPPSM (psm object) from the supplied searchHit JAXB object.
	 *
	 * @param searchHit
	 * @param charge
	 * @param scanNumber
	 * @param obsMass
	 * @param retentionTime
	 * @return
	 * @throws Throwable
	 */
	public static TPPPSM getPsmFromSearchHit(
			SearchHit searchHit,
			int charge,
			int scanNumber,
			BigDecimal obsMass,
			BigDecimal retentionTime,
			MSFraggerParameters params,
			boolean isOpenMod,
			boolean isPTMProphet ) throws Throwable {
				
		TPPPSM psm = new TPPPSM();
		
		psm.setCharge( charge );
		psm.setScanNumber( scanNumber );
		psm.setPrecursorNeutralMass( obsMass );
		psm.setRetentionTime( retentionTime );
		
		psm.setPeptideSequence( searchHit.getPeptide() );
		
		psm.setHyperScore( getScoreForType( searchHit, "hyperscore" ) );
		psm.setNextScore( getScoreForType( searchHit, "nextscore" ) );
		psm.seteValue( getScoreForType( searchHit, "expect" ) );

		psm.setMassDiff( getMassDiffForSearchHit( searchHit ) );

		psm.setPeptideProphetProbability( getPeptideProphetProbabilityForSearchHit( searchHit ) );
		
		
		if( psm.getPeptideProphetProbability() == null ) {
			return null;
		}
		
		// this will set this to null if this was not an iProphet run
		psm.setInterProphetProbability( getInterProphetProbabilityForSearchHit( searchHit ) );

		
		try {
			psm.setModifications( getModificationsForSearchHit( searchHit, params, isOpenMod, isPTMProphet ) );
		} catch( Throwable t ) {
			
			System.err.println( "Error getting mods for PSM. Error was: " + t.getMessage() );
			throw t;
		}

		try {
			psm.setOpenModification( getOpenModificationForSearchHit( searchHit, isOpenMod, isPTMProphet ) );
		} catch( Throwable t ) {

			System.err.println( "Error getting open mod information for PSM. Error was: " + t.getMessage() );
			throw t;
		}
		
		return psm;
	}

	/**
	 * Get a PeptideProphet probability from the supplied searchHit JAXB object
	 *
	 * @param searchHit
	 * @return
	 * @throws Exception
	 */
	public static BigDecimal getPeptideProphetProbabilityForSearchHit( SearchHit searchHit ) throws Exception {
		
		
		for( AnalysisResult ar : searchHit.getAnalysisResult() ) {
			if( ar.getAnalysis().equals( "peptideprophet" ) ) {
				
				for( Object o : ar.getAny() ) {
					
					try {
						
						PeptideprophetResult ppr = (PeptideprophetResult)o;
						return ppr.getProbability();
						
					} catch( Throwable t ) {
						
					}
					
				}
				
			}
		}
		
		return null;
	}

	/**
	 * Get a InterProphet probability from the supplied searchHit JAXB object
	 *
	 * @param searchHit
	 * @return
	 * @throws Exception
	 */
	public static BigDecimal getInterProphetProbabilityForSearchHit( SearchHit searchHit ) throws Exception {
		
		
		for( AnalysisResult ar : searchHit.getAnalysisResult() ) {
			if( ar.getAnalysis().equals( "interprophet" ) ) {
				
				for( Object o : ar.getAny() ) {
					
					try {
						
						InterprophetResult ppr = (InterprophetResult)o;
						return ppr.getProbability();
						
					} catch( Throwable t ) {
						
					}
					
				}
				
			}
		}
		
		return null;
	}


	/**
	 * Get the requested score from the searchHit JAXB object
	 *
	 * @param searchHit
	 * @param type
	 * @return
	 * @throws Throwable
	 */
	public static BigDecimal getScoreForType( SearchHit searchHit, String type ) throws Throwable {
		
		for( NameValueType searchScore : searchHit.getSearchScore() ) {
			if( searchScore.getName().equals( type ) ) {
				
				return new BigDecimal( searchScore.getValueAttribute() );
			}
		}
		
		throw new Exception( "Could not find a score of name: " + type + " for PSM..." );		
	}

	/**
	 * Get the variable modifications from the supplied searchHit JAXB object
	 *
	 * @param searchHit
	 * @return
	 * @throws Throwable
	 */
	public static Map<Integer, BigDecimal> getModificationsForSearchHit( SearchHit searchHit, MSFraggerParameters params, boolean isOpenMod, boolean isPTMProphet ) throws Throwable {
		
		Map<Integer, BigDecimal> modMap = new HashMap<>();

		String peptide = searchHit.getPeptide();
		if( peptide == null || peptide.length() < 1 ) {
			throw new Exception( "searchHit had no peptide: " + searchHit );
		}

		ModInfoDataType mofo = searchHit.getModificationInfo();
		if( mofo != null ) {
			for( ModAminoacidMass mod : mofo.getModAminoacidMass() ) {

				int position = mod.getPosition().intValueExact();

				String aminoAcid = String.valueOf( peptide.charAt( position - 1 ) );

				if( !MassConstants.AMINO_ACID_MASSES.containsKey( aminoAcid ) ) {
					throw new Exception( "Could not find mass for amino acid: " + aminoAcid );
				}

				BigDecimal modMass = BigDecimal.valueOf( mod.getMass() ).subtract( MassConstants.AMINO_ACID_MASSES.get( aminoAcid ) );

				modMass = modMass.setScale( 4, RoundingMode.HALF_UP );	// round the mod mass to 4 decimal places

				if( !isModStaticMod( aminoAcid, modMass, params ) ) {
					if( !isOpenMod(searchHit, modMass, position, isOpenMod, isPTMProphet)) {
						modMap.put(position, modMass);
					}
				}
			}
		}
		
		return modMap;
	}

	private static OpenModification getOpenModificationForSearchHit(SearchHit searchHit, boolean isOpenMod, boolean isPTMProphet) throws Throwable {

		if(!isOpenMod) { return null; }

		BigDecimal mass = getMassDiffForSearchHit( searchHit );
		Collection<Integer> positions = null;

		if(isPTMProphet) {
			positions = getPTMProphetPositionsForSearchHIt(searchHit);
		}

		return new OpenModification(mass, positions);
	}

	/**
	 * Get all positions associated with the best probability calculated by PTM Prophet
	 *
	 * @param searchHit
	 * @return
	 * @throws Throwable
	 */
	private static Collection<Integer> getPTMProphetPositionsForSearchHIt(SearchHit searchHit) throws Throwable {

		Collection<Integer> positions = null;

		for(AnalysisResult analysisResult : searchHit.getAnalysisResult()) {

			if( analysisResult.getAnalysis().equals( "ptmprophet")) {

				for( Object o : analysisResult.getAny() ) {

					try {

						PtmprophetResult ptmprophetResult = (PtmprophetResult)o;

						// only consider mass diff-derived PTM localizations
						if(!ptmprophetResult.getPtm().equals("MASSDIFF")) {
							continue;
						}

						BigDecimal bestScore = BigDecimal.ZERO;

						for(PtmprophetResult.ModAminoacidProbability modAminoacidProbability : ptmprophetResult.getModAminoacidProbability()) {

							if(modAminoacidProbability.getProbability().compareTo(bestScore) > 0) {
								bestScore = modAminoacidProbability.getProbability();
								positions = new ArrayList<>();
								positions.add(modAminoacidProbability.getPosition().intValueExact());
							} else if(modAminoacidProbability.getProbability().equals(bestScore)) {
								positions.add(modAminoacidProbability.getPosition().intValueExact());
							}

						}

					} catch( Throwable t ) {

					}
				}
			}
		}

		return positions;
	}

	/**
	 * Make a best guess about whether the reported mod has been made by PTMProphet using the mass diff.
	 * Note to whomever is reading this: it'd be nice if PTMProphet indicated this, itself.
	 *
	 * Strategy: if the modded position is in the list of positions with the highest probability reported by PTMProphet,
	 * AND if the mod mass is equal to the mass diff of the PSM (to 4 decimal places), assume this mod was created by
	 * PTMProphet and is an open mod
	 *
	 * @param searchHit
	 * @param modMass
	 * @param position
	 * @return
	 */
	private static boolean isOpenMod(SearchHit searchHit, BigDecimal modMass, int position, boolean isOpenMod, boolean isPTMProphet) throws Throwable {
		if( !isOpenMod ) { return false; }
		if( !isPTMProphet ) { return false; }

		Collection<Integer> ptmProphetPositions = getPTMProphetPositionsForSearchHIt(searchHit);
		if(ptmProphetPositions != null) {
			if(ptmProphetPositions.contains(position)) {
				BigDecimal massDiff = getMassDiffForSearchHit(searchHit).setScale(4, RoundingMode.HALF_UP);
				modMass = modMass.setScale(4, RoundingMode.HALF_UP);

				return massDiff.equals(modMass);
			}
		}

		return false;
	}

	private static boolean isModStaticMod(String aminoAcid, BigDecimal modMass, MSFraggerParameters params ) {

		if( params.getStaticMods() == null || params.getStaticMods().size() < 1 ) {
			return false;
		}

		if( !params.getStaticMods().containsKey( aminoAcid.charAt( 0 ) ) ) {
			return false;
		}

		// round to two decimal places and compare
		BigDecimal testMass = modMass.setScale( 2, RoundingMode.HALF_UP );
		BigDecimal paramMass = BigDecimal.valueOf( params.getStaticMods().get( aminoAcid.charAt( 0 ) ) ).setScale( 2, RoundingMode.HALF_UP );

		return testMass.equals( paramMass );
	}

	/**
	 * Get the mass diff reported for the search hit
	 *
	 * @param searchHit
	 * @return
	 * @throws Throwable
	 */
	public static BigDecimal getMassDiffForSearchHit( SearchHit searchHit ) throws Throwable {

		return searchHit.getMassdiff();

	}

	
	
}
