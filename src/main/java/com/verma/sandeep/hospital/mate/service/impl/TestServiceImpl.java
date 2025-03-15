package com.verma.sandeep.hospital.mate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verma.sandeep.hospital.mate.entity.Test;
import com.verma.sandeep.hospital.mate.exception.TestNotFoundException;
import com.verma.sandeep.hospital.mate.iservice.TestService;
import com.verma.sandeep.hospital.mate.repository.TestRepository;

@Service
public class TestServiceImpl implements TestService {
	
	@Autowired
	private TestRepository testRepo;

	@Override
	public Long addTest(Test test) {
		return  testRepo.save(test).getTid();
	}

	@Override
	public List<Test> fetchByPatientId(Long patientId) {
		return testRepo.findByPatientId(patientId);
	}

	@Override
	public List<Test> getAllTests() {
		return testRepo.findAll();
	}

	@Override
	public Test getTestById(Long id) {
		return testRepo.findById(id)
				                       .orElseThrow(()->new TestNotFoundException("Test not Found"));
	}
	
	public void updateOneTest(Test test) {
		if(testRepo.existsById(test.getTid()))
			testRepo.save(test);
		else
			throw new TestNotFoundException("Test not found");
	}

	@Override
	public void deleteTest(Long id) {
		testRepo.delete(getTestById(id));
		
	}

}
