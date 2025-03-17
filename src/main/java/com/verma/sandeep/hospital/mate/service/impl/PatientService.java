package com.verma.sandeep.hospital.mate.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.verma.sandeep.hospital.mate.entity.Patient;

public interface PatientService {
	
	public Long savePatient(Patient pat);
	//public List<Patient> getAllPatients();
	public Page<Patient> getAllPatients(Pageable pageable);
	public void remove(Long id);
	public Patient getOnePatient(Long id);
	public void updatePatient(Patient pat);
	public Patient getOnePatient(String userName);
	public Long getPatientCount();
	public Patient searchPatientByEmailOrMobile(String query);

}
