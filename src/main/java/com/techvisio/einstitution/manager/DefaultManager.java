package com.techvisio.einstitution.manager;

import org.springframework.stereotype.Component;

@Component
public interface DefaultManager {

	Long getDefaultCentre();

	Long getDefaultShift();

	Long getDefaultBatch(Long courseId);

	Long getDefaultAcademicYear();

	Long getDefaultSession(Long courseId);

}
