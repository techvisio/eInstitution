package com.techvisio.einstitution.db.impl;

import com.techvisio.einstitution.beans.AdmissionInquiry;
import com.techvisio.einstitution.beans.CounsellingDetail;
import com.techvisio.einstitution.db.CounsellingDao;
import com.techvisio.einstitution.db.InquiryDao;
import com.techvisio.einstitution.manager.CounsellingManager;
import com.techvisio.einstitution.util.ContextProvider;

public class CounsellingManagerImpl implements CounsellingManager {

	CounsellingDao counsellingDao=ContextProvider.getContext().getBean(CounsellingDao.class);
	
	public CounsellingDetail getCounsellingDetail(String fileNo) {

		CounsellingDetail counsellingDetail=null;
		
		counsellingDetail=counsellingDao.getCounsellingDetail(fileNo);
		
		return counsellingDetail;
	}

	public void addCounsellingDetail(CounsellingDetail counsellingDetail) {

		counsellingDao.addCounsellingDetail(counsellingDetail);
	}

	public void updateCounsellingDetail(CounsellingDetail counsellingDetail) {

		counsellingDao.updateCounsellingDetail(counsellingDetail);
	}

	public void deleteConsultantDtl(String fileNo) {

		counsellingDao.deleteConsultantDtl(fileNo);
	}

	
}
