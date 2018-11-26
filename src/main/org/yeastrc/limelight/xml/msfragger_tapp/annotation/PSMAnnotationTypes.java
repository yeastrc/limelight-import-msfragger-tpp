package org.yeastrc.limelight.xml.msfragger_tapp.annotation;

import org.yeastrc.limelight.limelight_import.api.xml_dto.FilterDirectionType;
import org.yeastrc.limelight.limelight_import.api.xml_dto.FilterablePsmAnnotationType;
import org.yeastrc.limelight.xml.msfragger_tapp.constants.Constants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class PSMAnnotationTypes {

	// comet scores
	// magnum scores
	public static final String MAGNUM_ANNOTATION_TYPE_EVALUE = "E-Value";
	public static final String MAGNUM_ANNOTATION_TYPE_SCORE = "Score";
	public static final String MAGNUM_ANNOTATION_TYPE_DSCORE = "dScore";
	public static final String MAGNUM_ANNOTATION_TYPE_PPMERROR = "PPM Error";
	
	// PeptideProphet scores
	public static final String PPROPHET_ANNOTATION_TYPE_SCORE = "Probability Score";
	public static final String PPROPHET_ANNOTATION_TYPE_FDR = "Calculated FDR";
	
	// InterProphet scores
	public static final String IPROPHET_ANNOTATION_TYPE_SCORE = "Probability Score";
	public static final String IPROPHET_ANNOTATION_TYPE_FDR = "Calculated FDR";
	
	public static List<FilterablePsmAnnotationType> getFilterablePsmAnnotationTypes( String programName ) {
		List<FilterablePsmAnnotationType> types = new ArrayList<FilterablePsmAnnotationType>();

		if( programName.equals( Constants.PROGRAM_NAME_MAGNUM ) ) {

			{
				FilterablePsmAnnotationType type = new FilterablePsmAnnotationType();
				type.setName( MAGNUM_ANNOTATION_TYPE_EVALUE );
				type.setDescription( "Expect value" );
				type.setFilterDirection( FilterDirectionType.BELOW );

				types.add( type );
			}

			{
				FilterablePsmAnnotationType type = new FilterablePsmAnnotationType();
				type.setName( MAGNUM_ANNOTATION_TYPE_SCORE );
				type.setDescription( "Cross-correlation coefficient" );
				type.setFilterDirection( FilterDirectionType.ABOVE );

				types.add( type );
			}

			{
				FilterablePsmAnnotationType type = new FilterablePsmAnnotationType();
				type.setName( MAGNUM_ANNOTATION_TYPE_DSCORE );
				type.setDescription( "Difference between the XCorr of this PSM and the next best PSM (with a dissimilar peptide)" );
				type.setFilterDirection( FilterDirectionType.ABOVE );

				types.add( type );
			}

			{
				FilterablePsmAnnotationType type = new FilterablePsmAnnotationType();
				type.setName( MAGNUM_ANNOTATION_TYPE_PPMERROR );
				type.setDescription( "PPM Error, as calculated by " + Constants.PROGRAM_NAME_MAGNUM );
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
