package com.techvisio.einstitution.manager.impl;

import com.techvisio.einstitution.beans.HostelAllocation;
import com.techvisio.einstitution.db.HostelDao;
import com.techvisio.einstitution.manager.HostelAllocationManger;
import com.techvisio.einstitution.util.ContextProvider;

public class HostelAllocationManagerImpl implements HostelAllocationManger{
	HostelDao hostelDao = ContextProvider.getContext().getBean(HostelDao.class);

	public HostelAllocation getHostelAllocation(String fileNo) {
		HostelAllocation h = hostelDao.getHostelAllocation("fileNo");
		return h;
	}

	public void addHostelAllocation(HostelAllocation hostelAllocation) {
		hostelDao.addHostelAllocation(hostelAllocation);
	}

	public void updateHostelAllocation(HostelAllocation hostelAllocation) {
		hostelDao.updateHostelAllocation(hostelAllocation);
	}

	public void deleteHostelAllocation(String fileNo) {
		hostelDao.deleteHostelAllocation("fileNo");
	}

}
