package com.techvisio.einstitution.db;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.techvisio.einstitution.beans.Address;
import com.techvisio.einstitution.beans.Branch;
import com.techvisio.einstitution.beans.Course;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.Student;
import com.techvisio.einstitution.beans.StudentBasicInfo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config/Application-context.xml" })
public class AdmissionDaoTest {

	@Autowired
	AdmissionDao dao;
	
	@Test
	public void testaddAdmissionDao(){
		
		Student studentDetail=new Student();
        List<Address> addresses = new ArrayList<Address>();
        Address address=new Address();
        address.setAddressType("P");
        Course course=new Course();
        course.setCourseId(1L);
        course.setCourse("MBA");
        address.setCity("GHAZIABAD");
        address.setDistrict("GHAZIABAD");
        address.setHouseNo("258");
        address.setLocality("New Kot Gaon");
		//studentDetail.setFileNo(11L);
//		studentDetail.setEnrollNo("3");
//		studentDetail.setFirstName("Sandeep");
//		studentDetail.setLastName("Gusain");
//		studentDetail.setFatherName("Chaman Singh Gusain");
//		studentDetail.setMotherName("Beena Gusain");
//        studentDetail.setFatherOccupation("Service");
////		studentDetail.setCategoryId("3");
////		studentDetail.setCourseId("1");
////		studentDetail.setBranchId("4");
////		studentDetail.setDomicileState("2");
//		studentDetail.setCategoryId("5");
        addresses.add(address);
        studentDetail.setAddressDtl(addresses);
        studentDetail.setCourse(course);
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-config/Application-context.xml");
		AdmissionDao dao=ctx.getBean(AdmissionDao.class);
		dao.saveStudent(studentDetail);
	}
	
	@Test
	public void testGetStudent(){
		
		Student student = dao.getStudent(1L);
	
		System.out.println(student);
	}
	
	@Test
	public void testGetAddress(){
		
		List<Address> addresses = dao.getAddressDtl(2L);
	
		System.out.println(addresses);
	}
	

@Test
public void testSearch(){
	SearchCriteria criteria = new SearchCriteria();
	criteria.setEmailId("saurbh@gmail.com");
	List<StudentBasicInfo> basicInfos = dao.getStudentDtlBySearchCriteria(criteria);
	System.out.println("Data is:"+basicInfos);
}






}
  
