package com.verma.sandeep.hospital.mate.iservice;

import java.util.List;

import com.verma.sandeep.hospital.mate.dto.MedicosDTO;

public interface MedicosService {
	
	Long saveMedicos(MedicosDTO medicosDTO);
	List<MedicosDTO> getAllMedicos();
	MedicosDTO getMedicosById(Long id);
	void updateOneMedicos(MedicosDTO medicosDTO);
	void deleteMedicos(Long id);
	//Fetching the prescriptions of a patient
	List<MedicosDTO> getByPatientId(Long patientId);
	//Total cost of prescribed medicines
	Long getTotalCost(Long patientId);

}
