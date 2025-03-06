package com.verma.sandeep.hospital.mate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verma.sandeep.hospital.mate.entity.Patient;
import com.verma.sandeep.hospital.mate.exception.PatientsNotFoundException;
import com.verma.sandeep.hospital.mate.repository.PatientRepository;

@Service
public class PatientServiceImpl implements IPatientService {
	
	@Autowired
	private PatientRepository patRepo;

	@Override
	public Long savePatient(Patient pat) {
		return patRepo.save(pat).getId();
	}

	@Override
	public List<Patient> getAllPatients() {
		return patRepo.findAll();
	}

	@Override
	public void remove(Long id) {
		patRepo.delete(getOnePatient(id));

	}

	@Override
	public Patient getOnePatient(Long id) {
		return patRepo.findById(id)
				                       .orElseThrow(()->new PatientsNotFoundException("Patient not exist"));
	}

	@Override
	public void updatePatient(Patient pat) {
		if(patRepo.existsById(pat.getId())) {
			patRepo.save(pat);
		}else {
			throw new PatientsNotFoundException("Patient not exist");
		}

	}

	@Override
	public Patient getOnePatient(String email) {
		return patRepo.findByEmail(email)
				                       .orElseThrow(()-> new PatientsNotFoundException("Patient not found"));
	}

	@Override
	public Long getPatientCount() {
		return patRepo.count();
	}

}
