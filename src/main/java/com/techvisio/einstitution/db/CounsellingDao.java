package com.techvisio.einstitution.db;

import com.techvisio.einstitution.beans.CounsellingDetail;

public interface CounsellingDao {

	public CounsellingDetail getCounsellingDetail(String fileNo);
	public void addCounsellingDetail(CounsellingDetail counsellingDetail);
	public void updateCounsellingDetail(CounsellingDetail counsellingDetail);
	public void deleteConsultantDtl(String fileNo);
	
	
}
