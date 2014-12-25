package com.techvisio.einstitution.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.db.AdmissionDao;
import com.techvisio.einstitution.db.InquiryDao;
import com.techvisio.einstitution.manager.AdmissionManager;

public class AdmissionManagerImpl implements AdmissionManager {

	ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-config/Application-context.xml");
	AdmissionDao admissionDao=ctx.getBean(AdmissionDao.class);
	public void getStudentDetail(StudentDetail studentDetail) {

		admissionDao.getStudentDtl(studentDetail);

	}

	public void addStudentDetail(StudentDetail studentDetail) {

		admissionDao.addStudentDtl(studentDetail);

	}

	public void updateStudentDetail(StudentDetail studentDetail) {

		admissionDao.updateStudentDtl(studentDetail);
	}

	public void deleteStudentDetail(StudentDetail studentDetail) {

		admissionDao.deleteSudentDtl(studentDetail);
	}

}
