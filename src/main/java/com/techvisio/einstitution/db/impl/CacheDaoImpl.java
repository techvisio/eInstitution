package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.core.RowMapper;

import com.techvisio.einstitution.beans.Branch;
import com.techvisio.einstitution.beans.CasteCategory;
import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.CounsellingBody;
import com.techvisio.einstitution.beans.Course;
import com.techvisio.einstitution.beans.FeeHead;
import com.techvisio.einstitution.beans.Qualification;
import com.techvisio.einstitution.beans.QuotaCode;
import com.techvisio.einstitution.beans.State;
import com.techvisio.einstitution.beans.Subject;
import com.techvisio.einstitution.db.CacheDao;


public class CacheDaoImpl extends BaseDao implements CacheDao {


	private Properties masterQueryProps;

	public void setmasterQueryProps(Properties masterQueryProps) {

		this.masterQueryProps = masterQueryProps;
	}

	
	public List<Branch> getBranch(){  

		String getBranchQuery=masterQueryProps.getProperty("getCourseBranch");

		List<Branch> branchs=new ArrayList<Branch>();

		branchs= getNamedParamJdbcTemplate().query(getBranchQuery, new RowMapper<Branch>() {

			public Branch mapRow(ResultSet rs, int rowNum) throws SQLException {

				Branch branch=new Branch();  
				branch.setBranchName(rs.getString("Branch"));  
				branch.setId(rs.getLong("Id"));
				branch.setCourseId(rs.getLong("Course_Id"));

				return branch;
			}
		});		
		return branchs;
	}

	
	public List<Course> getCourse(){

		String getCourseQuery=masterQueryProps.getProperty("getCourse");

		List<Course> courses =new ArrayList<Course>(); 

		courses = getNamedParamJdbcTemplate().query(getCourseQuery, new RowMapper<Course>() {

			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

				Course course = new Course();
				course.setId(rs.getLong("Id"));
				course.setCourse(rs.getString("Course"));
				course.setCourseType(rs.getString("Course_type"));
				return course;
			}
		});


		return courses;

	}

	
	public List<CasteCategory> getCatagory(){

		String getCategoryQuery=masterQueryProps.getProperty("getCasteCategory");

		List<CasteCategory> casteCategories =new ArrayList<CasteCategory>(); 

		casteCategories = getNamedParamJdbcTemplate().query(getCategoryQuery, new RowMapper<CasteCategory>() {

			public CasteCategory mapRow(ResultSet rs, int rowNum) throws SQLException {

				CasteCategory category = new CasteCategory();

				category.setId(rs.getLong("Id"));
				category.setCategoryName(rs.getString("Category"));
				return category;
			}
		});

		return casteCategories;
	}

	
	public List<FeeHead> getFeeHead(){

		String getFeeHeadQuery=masterQueryProps.getProperty("getFeeHead");

		List<FeeHead> feeHeads =new ArrayList<FeeHead>(); 

		feeHeads = getNamedParamJdbcTemplate().query(getFeeHeadQuery, new RowMapper<FeeHead>() {

			public FeeHead mapRow(ResultSet rs, int rowNum) throws SQLException {

				FeeHead feeHead = new FeeHead();
				feeHead.setId(rs.getLong("Id"));
				feeHead.setFeeHead(rs.getString("Fee_Head"));
				return feeHead;
			}
		});

		return feeHeads;
	}

	
	public List<CounsellingBody> getCounsellingBody(){

		String getCounsellingQuery=masterQueryProps.getProperty("getCounselling");

		List<CounsellingBody> counsellingBodies =new ArrayList<CounsellingBody>(); 

		counsellingBodies = getNamedParamJdbcTemplate().query(getCounsellingQuery, new RowMapper<CounsellingBody>() {

			public CounsellingBody mapRow(ResultSet rs, int rowNum) throws SQLException {

				CounsellingBody body = new CounsellingBody();
				body.setId(rs.getLong("Id"));
				body.setCousellingBody(rs.getString("Counselling_Body"));
				return body;
			}
		});

		return counsellingBodies;
	}

	
	public List<Subject> getSubject(){

		String getSubjectQuery=masterQueryProps.getProperty("getSubject");

		List<Subject> subjects =new ArrayList<Subject>(); 

		subjects = getNamedParamJdbcTemplate().query(getSubjectQuery, new RowMapper<Subject>() {

			public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {

				Subject subject = new Subject();
				subject.setId(rs.getLong("Subject_Id"));
				subject.setSubjectName(rs.getString("Subject"));
				return subject;
			}
		});

		return subjects;
	}

	
	public List<Qualification> getQualification(){

		String getQualificationQuery=masterQueryProps.getProperty("getQualification");

		List<Qualification> qualifications =new ArrayList<Qualification>(); 

		qualifications = getNamedParamJdbcTemplate().query(getQualificationQuery, new RowMapper<Qualification>() {

			public Qualification mapRow(ResultSet rs, int rowNum) throws SQLException {

				Qualification qualification = new Qualification();
				qualification.setId(rs.getLong("Id"));
				qualification.setQulaifyingExam(rs.getString("QualifyingExam"));
				return qualification;
			}
		});

		return qualifications;
	}

	
	public List<QuotaCode> getQuotaCode(){

		String getQuotaQuery=masterQueryProps.getProperty("getQuotaCode");

		List<QuotaCode> quotaCodes =new ArrayList<QuotaCode>(); 

		quotaCodes = getNamedParamJdbcTemplate().query(getQuotaQuery, new RowMapper<QuotaCode>() {

			public QuotaCode mapRow(ResultSet rs, int rowNum) throws SQLException {

				QuotaCode code = new QuotaCode();
				code.setId(rs.getLong("Id"));
				code.setDescription(rs.getString("Description"));
				code.setCode(rs.getString("Code"));
				return code;
			}
		});

		return quotaCodes;
	}

	
	public List<State> getState(){

		String getStateQuery=masterQueryProps.getProperty("getState");

		List<State> states =new ArrayList<State>(); 

		states = getNamedParamJdbcTemplate().query(getStateQuery, new RowMapper<State>() {

			public State mapRow(ResultSet rs, int rowNum) throws SQLException {

				State state = new State();
				state.setId(rs.getLong("Id"));
				state.setStateName(rs.getString("State"));
				return state;
			}
		});

		return states;
	}

	public List<Consultant> getConsultant(){

		String getConsultantQuery=masterQueryProps.getProperty("getConsultant");

		List<Consultant> consultants =new ArrayList<Consultant>(); 

		consultants = getNamedParamJdbcTemplate().query(getConsultantQuery, new RowMapper<Consultant>() {

			public Consultant mapRow(ResultSet rs, int rowNum) throws SQLException {

				Consultant consultant = new Consultant();
				consultant.setConsultantId(rs.getLong("Id"));
				consultant.setName(rs.getString("Name"));
				return consultant;
			}
		});

		return consultants;
	}

}
