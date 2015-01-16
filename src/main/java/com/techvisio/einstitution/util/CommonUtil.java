package com.techvisio.einstitution.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techvisio.einstitution.beans.AddressDetail;
import com.techvisio.einstitution.beans.AdmissionDiscountDtl;
import com.techvisio.einstitution.beans.QualificationSubjectDtl;
import com.techvisio.einstitution.beans.StudentAcademicDetail;
import com.techvisio.einstitution.beans.StudentDetail;

public class CommonUtil {

	public static void propogateIdentifiertoAdmission(
			StudentDetail studentDetail) {

		String fileNo = studentDetail.getFileNo();
		if (studentDetail.getAcademicDtl() != null) {
			for (StudentAcademicDetail studentAcademicDetail : studentDetail
					.getAcademicDtl()) {
				studentAcademicDetail.setFileNo(fileNo);
			}
		}

		if (studentDetail.getDiscountDtl() != null) {

			for (AdmissionDiscountDtl admissionDiscountDtl : studentDetail
					.getDiscountDtl()) {

				admissionDiscountDtl.setFileNo(fileNo);
			}
		}

		if (studentDetail.getAddressDtl() != null) {

			for (AddressDetail addressDetail : studentDetail.getAddressDtl()) {

				addressDetail.setFileNo(fileNo);

			}
		}

	}



	public static void propogateIdentifierToQualification(StudentAcademicDetail academicDetail){
		
		String fileNo=academicDetail.getFileNo();
		
		if(academicDetail.getQualificationSubDtl() != null){
			
			for(QualificationSubjectDtl qualificationSubjectDtl:academicDetail.getQualificationSubDtl()){
				
				qualificationSubjectDtl.setFileNo(fileNo);
			}
		}
		
		
		
	}

	
	public static void convertJavatoJSON(Object bean){

		ObjectMapper mapper = new ObjectMapper();
	      try
	      {
	         mapper.writeValue(new File("java.json"), bean);
	      } catch (JsonGenerationException e)
	      {
	         e.printStackTrace();
	      } catch (JsonMappingException e)
	      {
	         e.printStackTrace();
	      } catch (IOException e)
	      {
	         e.printStackTrace();
	      }
	
	}
	
	public static void createJSONfordynamicUI() throws NoSuchFieldException, SecurityException{
		Field[] fields=StudentDetail.class.getDeclaredFields();
		List<FieldDesc> finalFields=new ArrayList<FieldDesc>();
//		for(Field field:fields){
//			FieldDesc desc=new FieldDesc();
//			desc.setId(field.getName());
//			String type=null;
//			DynamicProperty annotation=field.getAnnotation(DynamicProperty.class);
//			if(annotation !=null){
//			String title=annotation.description();
//			String[] validValues=annotation.validValues();
//			String masterDataCode=annotation.masterDataCode();
//			type="text";
//			if(annotation.type() != null && annotation.type().trim() != ""){
//				type=annotation.type();
//			}
//			desc.setType(type);
//			desc.setTitle(title);
//			desc.setValidValue(validValues);
//			desc.setMasterDataCode(masterDataCode);
//			}
//			else{
//				continue;
//			}
//					
//			desc.setMandatoryInd(false);
//			finalFields.add(desc);
//		}
		convertJavatoJSON(finalFields);
	}
	
	static class FieldDesc{
		private String id;
		private String type;
		private String Title;
		private String masterDataCode;
		private String[] validValue;
		private boolean mandatoryInd;
		private boolean visible;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public boolean isMandatoryInd() {
			return mandatoryInd;
		}
		public void setMandatoryInd(boolean mandatoryInd) {
			this.mandatoryInd = mandatoryInd;
		}
		public String getTitle() {
			return Title;
		}
		public void setTitle(String title) {
			Title = title;
		}
		public String[] getValidValue() {
			return validValue;
		}
		public void setValidValue(String[] validValue) {
			this.validValue = validValue;
		}
		public boolean isVisible() {
			return visible;
		}
		public void setVisible(boolean visible) {
			this.visible = visible;
		}
		public String getMasterDataCode() {
			return masterDataCode;
		}
		public void setMasterDataCode(String masterDataCode) {
			this.masterDataCode = masterDataCode;
		}
		
	}

}
