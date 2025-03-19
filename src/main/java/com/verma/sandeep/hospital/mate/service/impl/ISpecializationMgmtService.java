package com.verma.sandeep.hospital.mate.service.impl;

import java.util.List;
import java.util.Map;

import com.verma.sandeep.hospital.mate.dto.SpecializationRequestDTO;
import com.verma.sandeep.hospital.mate.dto.SpecializationResponseDTO;

public interface ISpecializationMgmtService {
	
	Long saveSpecialization(SpecializationRequestDTO specializationRequestDTO);
	List<SpecializationResponseDTO> getAllSpecializations();
	SpecializationResponseDTO getOneSpecialization(Long id);
	void removeSpecialization(Long id);
	void updateSpecialization(Long id,SpecializationRequestDTO specializationRequestDTO);
	Map<Long,String> getSpecIdAndName();
	

}
