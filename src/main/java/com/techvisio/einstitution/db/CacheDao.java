package com.techvisio.einstitution.db;

import java.util.List;

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

public interface CacheDao {

	public List<State> getState();

	public List<Branch> getBranch();

	public List<Course> getCourse();

	public List<CasteCategory> getCatagory();

	public List<FeeHead> getFeeHead();

	public List<CounsellingBody> getCounsellingBody();

	public List<Subject> getSubject();

	public List<Qualification> getQualification();

	public List<QuotaCode> getQuotaCode();

	public List<Consultant> getConsultant();
}