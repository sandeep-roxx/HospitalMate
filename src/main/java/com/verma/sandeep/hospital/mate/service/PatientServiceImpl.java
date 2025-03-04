package com.verma.sandeep.hospital.mate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verma.sandeep.hospital.mate.entity.Patient;
import com.verma.sandeep.hospital.mate.repository.PatientRepository;

@Service
public class PatientServiceImpl implements IPatientService {
	
	@Autowired
	private PatientRepository patRepo;

	@Override
	public Long savePatient(Patient pat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> getAllPatients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Patient getOnePatient(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePatient(Patient pat) {
		// TODO Auto-generated method stub

	}

	@Override
	public Patient getOnePatient(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getPatientCount() {
		// TODO Auto-generated method stub
		return null;
	}

}
