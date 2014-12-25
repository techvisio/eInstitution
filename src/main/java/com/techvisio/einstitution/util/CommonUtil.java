package com.techvisio.einstitution.util;

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


}
