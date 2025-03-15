package com.verma.sandeep.hospital.mate.iservice;

import java.util.List;

import com.verma.sandeep.hospital.mate.entity.Test;

public interface TestService {
	
	 Long addTest(Test test);
	 List<Test> getAllTests();
	 Test getTestById(Long id);
	 void updateOneTest(Test test);
	 void deleteTest(Long id);
	 List<Test> fetchByPatientId(Long patientId);

}
