package com.verma.sandeep.hospital.mate.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.verma.sandeep.hospital.mate.dto.PatientRequestDTO;
import com.verma.sandeep.hospital.mate.dto.PatientResponseDTO;

public interface PatientService {
	
	Long savePatient(PatientRequestDTO pat);
	//public List<Patient> getAllPatients();
	Page<PatientResponseDTO> getAllPatients(Pageable pageable);
	void remove(Long id);
	PatientResponseDTO getOnePatient(Long id);
	void updatePatient(Long id,PatientRequestDTO patRequestDTO);
	PatientResponseDTO getOnePatient(String email);
	Long getPatientCount();
	PatientResponseDTO searchPatientByEmailOrMobile(String query);

}
