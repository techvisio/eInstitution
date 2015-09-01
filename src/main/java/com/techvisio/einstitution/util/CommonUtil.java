package com.techvisio.einstitution.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techvisio.einstitution.beans.Address;
import com.techvisio.einstitution.beans.AdmissionDiscount;
import com.techvisio.einstitution.beans.AdmissnConsltntDtl;
import com.techvisio.einstitution.beans.ApplicableFeeCriteria;
import com.techvisio.einstitution.beans.Counselling;
import com.techvisio.einstitution.beans.FieldDesc;
import com.techvisio.einstitution.beans.QualificationSubject;
import com.techvisio.einstitution.beans.Student;
import com.techvisio.einstitution.beans.StudentAcademic;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentDocument;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.beans.TaskAndFollowUp;

public class CommonUtil {

	public static void propogateIdentifiertoAdmission(
			Student studentDetail) {

		Long fileNo = studentDetail.getFileNo();
		if (studentDetail.getAcademicDtl() != null) {
			for (StudentAcademic studentAcademicDetail : studentDetail
					.getAcademicDtl()) {
				studentAcademicDetail.setFileNo(fileNo);
			}
		}

		if (studentDetail.getDiscountDtl() != null) {
			for (AdmissionDiscount admissionDiscountDtl : studentDetail
					.getDiscountDtl()) {
				admissionDiscountDtl.setFileNo(fileNo);
			}
		}

		if (studentDetail.getAddressDtl() != null) {
			for (Address addressDetail : studentDetail.getAddressDtl()) {
				addressDetail.setFileNo(fileNo);
			}
		}

		if(studentDetail.getConsultantDetail() != null){
			for(AdmissnConsltntDtl admissnConsltntDtl:studentDetail.getConsultantDetail()){
				admissnConsltntDtl.setFileNo(fileNo);
			}
		}

		if(studentDetail.getScholarship() != null){

			studentDetail.getScholarship().setFileNo(fileNo);
		}

		if (studentDetail.getCounsellingDtl() != null) {
			for (Counselling counsellingDetail : studentDetail.getCounsellingDtl()) {
				counsellingDetail.setFileNo(fileNo);
			}
		}
		
		if(studentDetail.getStudentBasics()!=null){
			studentDetail.getStudentBasics().setFileNo(fileNo);
		}
	}

	public static void propogateIdentifierToQualification(StudentAcademic academicDetail){

		Long fileNo=academicDetail.getFileNo();

		if(academicDetail.getQualificationSubDtl() != null){

			for(QualificationSubject qualificationSubjectDtl:academicDetail.getQualificationSubDtl()){

				qualificationSubjectDtl.setFileNo(fileNo);
				qualificationSubjectDtl.setQualification(academicDetail.getQualification());
			}
		}
	}

	public static void propogateFileNoTofeeStaging(Long fileNo, List<StudentFeeStaging> feeStagings){

		if(feeStagings != null){
			for(StudentFeeStaging studentFeeStaging : feeStagings){
				studentFeeStaging.setFileNo(fileNo);
			}
		}
	}

	public static Date removeTimeFromDate(Date date) {

		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static void propogateEntityIdToTaskAndFollowup(List<TaskAndFollowUp> taskAndFollowUps, Long entityId, String module){

		if(taskAndFollowUps !=null){
			for(TaskAndFollowUp taskAndFollowUp : taskAndFollowUps){
				taskAndFollowUp.setEntityId(entityId);
				taskAndFollowUp.setModule(module);
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
		Field[] fields=Student.class.getDeclaredFields();
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

	public static boolean isNullLongValue(Long val){
		return val==null||val.equals(0L);
	}

	public static ApplicableFeeCriteria getApplicableFeeCriteriaFromStudentBasicInfo(
			StudentBasicInfo basicInfo) {
		ApplicableFeeCriteria criteria=new ApplicableFeeCriteria();
		criteria.setBranchId(basicInfo.getBranch().getBranchId());
		criteria.setCentreId(basicInfo.getCentre().getCentreId());
		criteria.setCourseId(basicInfo.getCourse().getCourseId());
		criteria.setLateral(basicInfo.isLateral());
		criteria.setSessionId(basicInfo.getSession().getSessionId());
		criteria.setShiftId(basicInfo.getShift().getShiftId());
		return criteria;
	}

	public static Long getLongToObject(Object object) {
		if(object != null){

			String s = object.toString();
			return Long.valueOf(s);		}

		return null;
	}

	public static Integer getIntegerToObject(Object object) {
		if(object != null){

			String s = object.toString();
			return Integer.valueOf(s);	}

		return null;
	}

	public static void removeJunk(List<T> object, String property) {

		Iterator iterator = object.iterator();
		while(iterator.hasNext()){
			Object obj = iterator.next();
			Object value;
			try {
				value = BeanUtils.getProperty(obj, property);
			} 
			catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) 
			{
				e.printStackTrace();  
				throw new RuntimeException();			
			}
			
			if(value == null || StringUtils.isEmpty(value.toString())){
				iterator.remove();
			}
		}
	}
	
	public static List<StudentDocument> convertFromDocumentUI(Map<String,List<List<StudentDocument>>> docMap){
	    List<StudentDocument> studentDocuments = new ArrayList<StudentDocument>();
	    for(List<List<StudentDocument>>  docs:docMap.values()){
	    	for(List<StudentDocument> rootDocList : docs){
	    		studentDocuments.addAll(rootDocList);
	    	}
	    }
	    
		return studentDocuments;
	}
	
	public static Map<String,List<List<StudentDocument>>> getDocUIView(List<StudentDocument> docs){
		List<StudentDocument> documents=new ArrayList<StudentDocument>(docs);
		Map<String,List<List<StudentDocument>>> result=new HashMap<String, List<List<StudentDocument>>>();
		
		Map<String,List<StudentDocument>> firstLevelList=new HashMap<String, List<StudentDocument>>();
		for(StudentDocument doc:documents){
			String docType=doc.getDocument().getDocumentType();
			if(firstLevelList.get(docType)==null){
				firstLevelList.put(docType, new ArrayList<StudentDocument>());
			}
			firstLevelList.get(docType).add(doc);
		}
		
		for(String docType:firstLevelList.keySet()){
			List<List<StudentDocument>> studentDoc=new ArrayList<List<StudentDocument>>();
			List<StudentDocument> originalList=firstLevelList.get(docType);
			List<StudentDocument> innerList=new ArrayList<StudentDocument>();
			for(int i=0;i<originalList.size();i++){
				if(i%2==0){
					innerList=new ArrayList<StudentDocument>();
				}
				innerList.add(originalList.get(i));
				
				if(i%2!=0||i==originalList.size()-1){
					studentDoc.add(innerList);
				}
			}
			result.put(docType, studentDoc);
		}
		return result;
	}
}