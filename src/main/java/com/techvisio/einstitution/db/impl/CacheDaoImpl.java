package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.techvisio.einstitution.beans.Batch;
import com.techvisio.einstitution.beans.Branch;
import com.techvisio.einstitution.beans.CasteCategory;
import com.techvisio.einstitution.beans.Centre;
import com.techvisio.einstitution.beans.CodeMapping;
import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.CounsellingBody;
import com.techvisio.einstitution.beans.Course;
import com.techvisio.einstitution.beans.ApplicableFeeDetail;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.Qualification;
import com.techvisio.einstitution.beans.QuotaCode;
import com.techvisio.einstitution.beans.Section;
import com.techvisio.einstitution.beans.Semester;
import com.techvisio.einstitution.beans.Session;
import com.techvisio.einstitution.beans.Shift;
import com.techvisio.einstitution.beans.State;
import com.techvisio.einstitution.beans.Subject;
import com.techvisio.einstitution.db.CacheDao;
import com.techvisio.einstitution.util.CommonUtil;
import com.techvisio.einstitution.util.CustomLogger;

@Component
public class CacheDaoImpl extends BaseDao implements CacheDao {
	private static CustomLogger logger = CustomLogger.getLogger(CacheDaoImpl.class);

	@Autowired @Qualifier(value="masterQueryProps")
	private Properties masterQueryProps;

	public void setmasterQueryProps(Properties masterQueryProps) {

		this.masterQueryProps = masterQueryProps;
	}

	
	public List<Branch> getBranch(){  
		 logger.info("{} : Initializing cache for branch",this.getClass().getName());
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
		 logger.info("{} : Initializing cache for Course ",this.getClass().getName() );
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
		 logger.info("{} : Initializing cache for caste catagory",this.getClass().getName());
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
		 logger.info("{} : Initializing cache for counselling",this.getClass().getName());
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
		 logger.info("{} : Initializing cache for subjects",this.getClass().getName());
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
		 logger.info("{} : Initializing cache for qualifications",this.getClass().getName());
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
		 logger.info("{} : Initializing cache for quota codes",this.getClass().getName());
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
		 logger.info("{} : Initializing cache for States",this.getClass().getName());
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
		 logger.info("{} : Initializing cache for consultants",this.getClass().getName());
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


	public List<FeeDiscountHead> getFeeDiscountHeadMaster() {
		 logger.info("{} : Initializing cache for head master",this.getClass().getName());	
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
		 logger.info("{} : Getting semesters for course",this.getClass().getName());
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
		 logger.info("{} : Getting short forms for a particular course  ",this.getClass().getName());
		String getSemesterQuery=masterQueryProps.getProperty("getCodeMap");

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

	@Override
	public List<Batch> getBatch() {
		 logger.info("{} : Initializing cache for batches",this.getClass().getName());
		String getBatchQuery = masterQueryProps.getProperty("getBatch");
		List<Batch> batchs = new ArrayList<Batch>();
		batchs = getNamedParamJdbcTemplate().query(getBatchQuery, new RowMapper<Batch>(){

			@Override
			public Batch mapRow(ResultSet rs, int rowNum) throws SQLException {
				Batch batch = new Batch();
				batch.setBatch(rs.getString("Batch"));
				batch.setBatchId(CommonUtil.getLongValue(rs.getLong("Batch_Id")));
				batch.setCourseId(CommonUtil.getLongValue(rs.getLong("Course_Id")));
				batch.setNextBatchId(CommonUtil.getLongValue(rs.getLong("Next_Batch_Id")));
				batch.setPrevBatchId(CommonUtil.getLongValue(rs.getLong("Prev_Batch_Id")));
				return batch;
			}
			
		
			
		});
		return batchs;
	}


	@Override
	public List<Session> getSession() {
		 logger.info("{} : Initializing cache for sessions",this.getClass().getName());
		String getSessionQuery = masterQueryProps.getProperty("getSession");
		
		List<Session> sessions = new ArrayList<Session>();
		sessions = getNamedParamJdbcTemplate().query(getSessionQuery, new RowMapper<Session>(){

			@Override
			public Session mapRow(ResultSet rs, int rowNum) throws SQLException {
				Session session = new Session();
				session.setCourseId(CommonUtil.getLongValue(rs.getLong("Course_Id")));
				session.setNextSessionId(CommonUtil.getLongValue(rs.getLong("Next_Session_Id")));
				session.setPrevSessionId(CommonUtil.getLongValue(rs.getLong("Prev_Session_Id")));
				session.setSession(rs.getString("Session"));
				session.setSessionId(CommonUtil.getLongValue(rs.getLong("Session_Id")));
				return session;
			}
			
		});
		return sessions;
	}


	@Override
	public List<Centre> getCentre() {
		 logger.info("{} : Initializing cache for centres",this.getClass().getName());
		String getCentreQuery = masterQueryProps.getProperty("getCentre");
		
		List<Centre> centres = new ArrayList<Centre>();
		centres = getNamedParamJdbcTemplate().query(getCentreQuery, new RowMapper<Centre>(){

			@Override
			public Centre mapRow(ResultSet rs, int rowNum) throws SQLException {
				Centre centre = new Centre();
				centre.setCentreId(CommonUtil.getLongValue(rs.getLong("Centre_Id")));
				centre.setCentreName(rs.getString("Centre_Name"));
				return centre;
			}
			
		});
		return centres;
	}


	@Override
	public List<Shift> getShift() {
		 logger.info("{} : Initializing cache for shifts",this.getClass().getName());
		String getShiftQuery = masterQueryProps.getProperty("getShift");
		
		List<Shift> shifts = new ArrayList<Shift>();
		shifts = getNamedParamJdbcTemplate().query(getShiftQuery, new RowMapper<Shift>(){

			@Override
			public Shift mapRow(ResultSet rs, int rowNum) throws SQLException {
				Shift shift = new Shift();
				shift.setShiftId(CommonUtil.getLongValue(rs.getLong("Shift_Id")));
				shift.setShiftName(rs.getString("Shift_Name"));
				
				return shift;
			}
			
		});
		return shifts;
	}


	@Override
	public List<Section> getSection() {
		 logger.info("{} : Initializing cache for a particular courses",this.getClass().getName());
		String getSectionQuery = masterQueryProps.getProperty("getSection");
		
		List<Section> sections = new ArrayList<Section>();
		sections = getNamedParamJdbcTemplate().query(getSectionQuery, new RowMapper<Section>(){

			@Override
			public Section mapRow(ResultSet rs, int rowNum) throws SQLException {
				Section section = new Section();
				section.setBranchId(CommonUtil.getLongValue(rs.getLong("Branch_Id")));
				section.setCourseId(CommonUtil.getLongValue(rs.getLong("Course_Id")));
				section.setSection(rs.getString("Section"));
				section.setSectionId(CommonUtil.getLongValue(rs.getLong("Section_Id")));
				return section;
			}
			
		});
		return sections;
	}

}
