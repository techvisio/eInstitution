package com.techvisio.einstitution.db;

import java.util.List;

import com.techvisio.einstitution.beans.Branch;
import com.techvisio.einstitution.beans.CasteCategory;
import com.techvisio.einstitution.beans.CounsellingBody;
import com.techvisio.einstitution.beans.Course;
import com.techvisio.einstitution.beans.FeeHead;
import com.techvisio.einstitution.beans.Qualification;
import com.techvisio.einstitution.beans.QuotaCode;
import com.techvisio.einstitution.beans.State;
import com.techvisio.einstitution.beans.Subject;

public interface CacheDao {

	List<State> getState();

	List<Branch> getBranch();

	List<Course> getCourse();

	List<CasteCategory> getCatagory();

	List<FeeHead> getFeeHead();

	List<CounsellingBody> getCounsellingBody();

	List<Subject> getSubject();

	List<Qualification> getQualification();

	List<QuotaCode> getQuotaCode();

}
