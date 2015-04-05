package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.core.RowMapper;

import com.techvisio.einstitution.beans.Branch;
import com.techvisio.einstitution.beans.CasteCategory;
import com.techvisio.einstitution.beans.CodeMapping;
import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.CounsellingBody;
import com.techvisio.einstitution.beans.Course;
import com.techvisio.einstitution.beans.FeeDetail;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.Qualification;
import com.techvisio.einstitution.beans.QuotaCode;
import com.techvisio.einstitution.beans.Semester;
import com.techvisio.einstitution.beans.State;
import com.techvisio.einstitution.beans.Subject;
import com.techvisio.einstitution.db.CacheDao;
import com.techvisio.einstitution.util.CommonUtil;


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
				branch.setId(CommonUtil.getLongValue(rs.getLong("Id")));
				branch.setCourseId(CommonUtil.getLongValue(rs.getLong("Course_Id")));

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
				course.setId(CommonUtil.getLongValue(rs.getLong("Id")));
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

				category.setId(CommonUtil.getLongValue(rs.getLong("Id")));
				category.setCategoryName(rs.getString("Category"));
				return category;
			}
		});

		return casteCategories;
	}
	
	public List<CounsellingBody> getCounsellingBody(){

		String getCounsellingQuery=masterQueryProps.getProperty("getCounselling");

		List<CounsellingBody> counsellingBodies =new ArrayList<CounsellingBody>(); 

		counsellingBodies = getNamedParamJdbcTemplate().query(getCounsellingQuery, new RowMapper<CounsellingBody>() {

			public CounsellingBody mapRow(ResultSet rs, int rowNum) throws SQLException {

				CounsellingBody body = new CounsellingBody();
				body.setId(CommonUtil.getLongValue(rs.getLong("Id")));
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
				subject.setId(CommonUtil.getLongValue(rs.getLong("Subject_Id")));
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
				qualification.setId(CommonUtil.getLongValue(rs.getLong("Id")));
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
				code.setId(CommonUtil.getLongValue(rs.getLong("Id")));
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
				state.setId(CommonUtil.getLongValue(rs.getLong("Id")));
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
				consultant.setConsultantId(CommonUtil.getLongValue(rs.getLong("Id")));
				consultant.setName(rs.getString("Name"));
				return consultant;
			}
		});

		return consultants;
	}


	public List<FeeDetail> getFeeDetailMaster() {
		String getFeeDetailQuery=masterQueryProps.getProperty("getFeeDetailMaster");
		List<FeeDetail> feeDetails = new ArrayList<FeeDetail>();
		feeDetails = getNamedParamJdbcTemplate().query(getFeeDetailQuery, new RowMapper<FeeDetail>(){

			public FeeDetail mapRow(ResultSet rs, int rowNum)throws SQLException {
				FeeDetail detail = new FeeDetail();
				detail.setBranch(CommonUtil.getLongValue(rs.getLong("BRANCH")));
				detail.setCourse(CommonUtil.getLongValue(rs.getLong("COURSE")));
				detail.setFeeAmount(rs.getDouble("FEE_AMOUNT"));
				detail.setFeeHeadId(CommonUtil.getLongValue(rs.getLong("FEE_HEAD_ID")));
				detail.setSemester(rs.getInt("SEMESTER"));
				return detail;
			}
			
		});
		return feeDetails;
	}


	public List<FeeDiscountHead> getFeeDiscountHeadMaster() {
		String getFeeDiscountHeadQuery=masterQueryProps.getProperty("getFeeDiscountHeadMaster");
		List<FeeDiscountHead> feeDiscountHeads = new ArrayList<FeeDiscountHead>();
		feeDiscountHeads = getNamedParamJdbcTemplate().query(getFeeDiscountHeadQuery, new RowMapper<FeeDiscountHead>(){

			public FeeDiscountHead mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				FeeDiscountHead feeDiscountHead = new FeeDiscountHead();
				feeDiscountHead.setHeadId(CommonUtil.getLongValue(rs.getLong("Head_Id")));
				feeDiscountHead.setHead(rs.getString("Head"));
				feeDiscountHead.setParentId(CommonUtil.getLongValue(rs.getLong("Parent_Type_Id")));
				feeDiscountHead.setTransactionType(rs.getString("Transaction_Type"));
				feeDiscountHead.setReoccurring(rs.getBoolean("isReoccurring"));
				feeDiscountHead.setRefundType(rs.getString("Refund_Type"));

				return feeDiscountHead;
			}
			
		});
		
		
		return feeDiscountHeads;
	}


	public List<Semester> getSemester(){

		String getSemesterQuery=masterQueryProps.getProperty("getSemester");

		List<Semester> semesters =new ArrayList<Semester>(); 

		semesters = getNamedParamJdbcTemplate().query(getSemesterQuery, new RowMapper<Semester>() {

			public Semester mapRow(ResultSet rs, int rowNum) throws SQLException {

				Semester semester = new Semester();
				
				semester.setCourseId(CommonUtil.getLongValue(rs.getLong("Course_Id")));
				semester.setSemester(rs.getString("Semester"));
				semester.setId(CommonUtil.getLongValue(rs.getLong("Id")));
				return semester;
			}
		});

		return semesters;
	}
	
	@Override
	public List<CodeMapping> getCodeMapping(){

		String getSemesterQuery=masterQueryProps.getProperty("getSemester");

		List<CodeMapping> codeMappings =new ArrayList<CodeMapping>(); 

		codeMappings = getNamedParamJdbcTemplate().query(getSemesterQuery, new RowMapper<CodeMapping>() {

			public CodeMapping mapRow(ResultSet rs, int rowNum) throws SQLException {

				CodeMapping codeMapping = new CodeMapping();
				
				codeMapping.setName(rs.getString("Name"));
         		codeMapping.setCode(rs.getString("Code"));
		        codeMapping.setDescription(rs.getString("Description"));
				
		        return codeMapping;
			}
		});

		return codeMappings;
	}

}
