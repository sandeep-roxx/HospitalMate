package com.verma.sandeep.hospital.mate.service;

import java.util.List;

import com.verma.sandeep.hospital.mate.entity.Patient;

public interface IPatientService {
	
	public Long savePatient(Patient pat);
	public List<Patient> getAllPatients();
	public void remove(Long id);
	public Patient getOnePatient(Long id);
	public void updatePatient(Patient pat);
	public Patient getOnePatient(String userName);
	public Long getPatientCount();

}
