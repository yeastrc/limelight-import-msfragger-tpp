package org.yeastrc.limelight.xml.msfragger_tpp.annotation;

import org.yeastrc.limelight.limelight_import.api.xml_dto.FilterDirectionType;
import org.yeastrc.limelight.limelight_import.api.xml_dto.FilterablePsmAnnotationType;
import org.yeastrc.limelight.xml.msfragger_tpp.constants.Constants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class PSMAnnotationTypes {

	// comet scores
	// magnum scores
	public static final String MSFRAGGER_ANNOTATION_TYPE_EVALUE = "E-Value";
	public static final String MSFRAGGER_ANNOTATION_TYPE_HYPERSCORE = "hyperscore";
	public static final String MSFRAGGER_ANNOTATION_TYPE_DELTASCORE = "deltascore";
	public static final String MSFRAGGER_ANNOTATION_TYPE_MASSDIFF = "Mass Diff";
	
	// PeptideProphet scores
	public static final String PPROPHET_ANNOTATION_TYPE_SCORE = "Probability Score";
	public static final String PPROPHET_ANNOTATION_TYPE_FDR = "Calculated FDR";
	
	// InterProphet scores
	public static final String IPROPHET_ANNOTATION_TYPE_SCORE = "Probability Score";
	public static final String IPROPHET_ANNOTATION_TYPE_FDR = "Calculated FDR";
	
	public static List<FilterablePsmAnnotationType> getFilterablePsmAnnotationTypes( String programName ) {
		List<FilterablePsmAnnotationType> types = new ArrayList<FilterablePsmAnnotationType>();

		if( programName.equals( Constants.PROGRAM_NAME_MSFRAGGER ) ) {

			{
				FilterablePsmAnnotationType type = new FilterablePsmAnnotationType();
				type.setName( MSFRAGGER_ANNOTATION_TYPE_EVALUE );
				type.setDescription( "Expect value" );
				type.setFilterDirection( FilterDirectionType.BELOW );

				types.add( type );
			}

			{
				FilterablePsmAnnotationType type = new FilterablePsmAnnotationType();
				type.setName( MSFRAGGER_ANNOTATION_TYPE_HYPERSCORE );
				type.setDescription( "Similar to scoring done in X! Tandem. See: https://www.ncbi.nlm.nih.gov/pmc/articles/PMC5409104/" );
				type.setFilterDirection( FilterDirectionType.ABOVE );

				types.add( type );
			}

			{
				FilterablePsmAnnotationType type = new FilterablePsmAnnotationType();
				type.setName( MSFRAGGER_ANNOTATION_TYPE_DELTASCORE );
				type.setDescription( "Difference in hyperscore between top and next hit." );
				type.setFilterDirection( FilterDirectionType.ABOVE );

				types.add( type );
			}

			{
				FilterablePsmAnnotationType type = new FilterablePsmAnnotationType();
				type.setName( MSFRAGGER_ANNOTATION_TYPE_MASSDIFF );
				type.setDescription( "Mass diff, as calculated by " + Constants.PROGRAM_NAME_MSFRAGGER );
				type.setFilterDirection( FilterDirectionType.ABOVE );

				types.add( type );
			}

		}

		else if( programName.equals( Constants.PROGRAM_NAME_PEPTIDEPROPHET ) ) {
			{
				FilterablePsmAnnotationType type = new FilterablePsmAnnotationType();
				type.setName( PPROPHET_ANNOTATION_TYPE_SCORE );
				type.setDescription( "PeptideProphet Probability Score" );
				type.setFilterDirection( FilterDirectionType.ABOVE );
	
				types.add( type );
			}
			
			{
				FilterablePsmAnnotationType type = new FilterablePsmAnnotationType();
				type.setName( PPROPHET_ANNOTATION_TYPE_FDR );
				type.setDescription( "Calculated FDR from PeptideProphet Probability Score" );
				type.setFilterDirection( FilterDirectionType.BELOW );
				type.setDefaultFilterValue( new BigDecimal( "0.01" ) );
	
				types.add( type );
			}
		}
		
		else if( programName.equals( Constants.PROGRAM_NAME_INTERPROPHET ) ) {
			{
				FilterablePsmAnnotationType type = new FilterablePsmAnnotationType();
				type.setName( IPROPHET_ANNOTATION_TYPE_SCORE );
				type.setDescription( "InterProphet Probability Score" );
				type.setFilterDirection( FilterDirectionType.ABOVE );
	
				types.add( type );
			}
			
			{
				FilterablePsmAnnotationType type = new FilterablePsmAnnotationType();
				type.setName( IPROPHET_ANNOTATION_TYPE_FDR );
				type.setDescription( "Calculated FDR from InterProphet Probability Score" );
				type.setFilterDirection( FilterDirectionType.BELOW );
				type.setDefaultFilterValue( new BigDecimal( "0.01" ) );
	
				types.add( type );
			}
		}
		
		return types;
	}
	
	
}
