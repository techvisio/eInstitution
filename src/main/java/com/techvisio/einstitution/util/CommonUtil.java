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
import com.techvisio.einstitution.beans.CounsellingDetail;
import com.techvisio.einstitution.beans.FieldDesc;
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

		if (studentDetail.getCounsellingDtl() != null) {

			for (CounsellingDetail counsellingDetail : studentDetail.getCounsellingDtl()) {

				counsellingDetail.setFileNo(fileNo);

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
	
	public static List<FieldDesc> createJSONfordynamicUI() throws NoSuchFieldException, SecurityException{
		Field[] fields=StudentDetail.class.getDeclaredFields();
		List<FieldDesc> finalFields=new ArrayList<FieldDesc>();
		for(Field field:fields){
			FieldDesc desc=new FieldDesc();
			desc.setId(field.getName());
			String type=null;
			DynamicProperties annotation=field.getAnnotation(DynamicProperties.class);
			if(annotation !=null){
			String title=annotation.title();
			String[] validValues=annotation.validValues();
			String masterDataCode=annotation.masterDataCode();
			type="text";
			if(annotation.type() != null && annotation.type().trim() != ""){
				type=annotation.type();
			}
			desc.setType(type);
			desc.setTitle(title);
			desc.setValidValue(validValues);
			desc.setMasterDataCode(masterDataCode);
			}
			else{
				continue;
			}
					
			desc.setMandatoryInd(false);
			finalFields.add(desc);
		}
		return finalFields;
	}
	

	public static Long getLongValue(long long1) {
		if(long1 == 0){
		return null;
		}
		else{
			return long1;
		}
	}
	

}
