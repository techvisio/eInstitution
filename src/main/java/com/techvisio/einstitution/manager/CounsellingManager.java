package com.techvisio.einstitution.manager;

import com.techvisio.einstitution.beans.CounsellingDetail;

public interface CounsellingManager {


	public CounsellingDetail getCounsellingDetail(String fileNo);
	public void addCounsellingDetail(CounsellingDetail counsellingDetail);
	public void updateCounsellingDetail(CounsellingDetail counsellingDetail);
	public void deleteConsultantDtl(String fileNo);
	
	
}
