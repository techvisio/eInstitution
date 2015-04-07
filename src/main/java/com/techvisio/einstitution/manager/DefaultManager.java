package com.techvisio.einstitution.manager;

public interface DefaultManager {

	Long getDefaultCentre();

	Long getDefaultShift();

	Long getDefaultBatch(Long courseId);

	Long getDefaultAcademicYear();

	Long getDefaultSession(Long courseId);

}
