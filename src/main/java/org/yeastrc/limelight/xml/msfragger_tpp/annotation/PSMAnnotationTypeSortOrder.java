package org.yeastrc.limelight.xml.msfragger_tpp.annotation;

import org.yeastrc.limelight.limelight_import.api.xml_dto.SearchAnnotation;
import org.yeastrc.limelight.xml.msfragger_tpp.constants.Constants;

import java.util.ArrayList;
import java.util.List;

public class PSMAnnotationTypeSortOrder {

	public static List<SearchAnnotation> getPSMAnnotationTypeSortOrder( boolean haveIProphetData ) {
		List<SearchAnnotation> annotations = new ArrayList<SearchAnnotation>();
		
		if( haveIProphetData ) {
			
			{
				SearchAnnotation annotation = new SearchAnnotation();
				annotation.setAnnotationName( PSMAnnotationTypes.IPROPHET_ANNOTATION_TYPE_FDR );
				annotation.setSearchProgram( Constants.PROGRAM_NAME_INTERPROPHET );
				annotations.add( annotation );
			}
			
			{
				SearchAnnotation annotation = new SearchAnnotation();
				annotation.setAnnotationName( PSMAnnotationTypes.IPROPHET_ANNOTATION_TYPE_SCORE );
				annotation.setSearchProgram( Constants.PROGRAM_NAME_INTERPROPHET );
				annotations.add( annotation );
			}
			
		} else {
		
		
			{
				SearchAnnotation annotation = new SearchAnnotation();
				annotation.setAnnotationName( PSMAnnotationTypes.PPROPHET_ANNOTATION_TYPE_FDR );
				annotation.setSearchProgram( Constants.PROGRAM_NAME_PEPTIDEPROPHET );
				annotations.add( annotation );
			}
			
			{
				SearchAnnotation annotation = new SearchAnnotation();
				annotation.setAnnotationName( PSMAnnotationTypes.PPROPHET_ANNOTATION_TYPE_SCORE );
				annotation.setSearchProgram( Constants.PROGRAM_NAME_PEPTIDEPROPHET );
				annotations.add( annotation );
			}
			
		}
		
		{
			SearchAnnotation annotation = new SearchAnnotation();
			annotation.setAnnotationName( PSMAnnotationTypes.MSFRAGGER_ANNOTATION_TYPE_EVALUE );
			annotation.setSearchProgram( Constants.PROGRAM_NAME_MSFRAGGER );
			annotations.add( annotation );
		}

		
		return annotations;
	}
}
