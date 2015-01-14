package com.techvisio.einstitution.manager;

import com.techvisio.einstitution.beans.HostelAllocation;

public interface HostelAllocationManger {
	public HostelAllocation getHostelAllocation(String fileNo);
	public void addHostelAllocation(HostelAllocation hostelAllocation);
	public void updateHostelAllocation(HostelAllocation hostelAllocation);
	public void deleteHostelAllocation( String fileNo);
}
