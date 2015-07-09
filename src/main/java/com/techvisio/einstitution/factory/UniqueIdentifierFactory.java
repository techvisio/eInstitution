package com.techvisio.einstitution.factory;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techvisio.einstitution.beans.Branch;
import com.techvisio.einstitution.beans.Course;
import com.techvisio.einstitution.beans.Student;
import com.techvisio.einstitution.db.HostelDao;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.manager.impl.CacheManagerImpl;
import com.techvisio.einstitution.util.AppConstants;
import com.techvisio.einstitution.util.ContextProvider;

@Service
public class UniqueIdentifierFactory implements UniqueIdentifierGenerator {

		
			@Autowired
			CacheManager cacheManager ;
			
			public Long getUniqueIdentifierForAdmission() {

			    
				SequenceFactory sf=ContextProvider.getContext().getBean(SequenceFactory.class);
				
				Long fileNo = sf.getSequence(AppConstants.ADMISSION);
				
			    return fileNo;
				
				
			}
			
			public Long getUniqueIdentifierForEnquiry() {

				SequenceFactory sf=ContextProvider.getContext().getBean(SequenceFactory.class);
				
				Long Id = sf.getSequence(AppConstants.ENQUIRY);
				
			    return Id;
				
				
			}

			@Override
			public Long getUniqueIdentifierForTask() {

                SequenceFactory sf=ContextProvider.getContext().getBean(SequenceFactory.class);
				
				Long Id = sf.getSequence(AppConstants.TASK);
				
				
				return Id;
			}

			@Override
			public Long getUniqueIdentifierForConsultant() {

                SequenceFactory sf=ContextProvider.getContext().getBean(SequenceFactory.class);
				
				Long Id = sf.getSequence(AppConstants.CONSULTANT);
				
				return Id;
			}

			@Override
			public String getUniqueIdentifierForRegistration(Student studentDetail) {
				
				Course course = cacheManager.getCourseById(studentDetail.getCourse().getCourseId());
				String courseName = course.getCourse();
				
				Branch branch = cacheManager.getBranchById(studentDetail.getBranch().getBranchId());
				String branchName = branch.getBranchName();
				
				String courseCode = cacheManager.getCodeMappingByName(courseName);
				String branchCode = cacheManager.getCodeMappingByName(branchName);
				
				String registrationCode = studentDetail.getAcademicYear()+"_"+courseCode+"_"+branchCode;
				
				SequenceFactory sf=ContextProvider.getContext().getBean(SequenceFactory.class);
				
				Long Id = sf.getSequence(registrationCode);
				
				if(Id==0){
					throw new RuntimeException("Enable to generate registration no for code : "+ registrationCode);
				}
				
				String registrationNo=studentDetail.getAcademicYear()+"/"+courseCode+"/"+branchCode+"/"+Id;
				
				return registrationNo;
			}
	}
