package com.verma.sandeep.hospital.mate.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.verma.sandeep.hospital.mate.dto.DoctorRequestDTO;
import com.verma.sandeep.hospital.mate.dto.DoctorResponseDTO;


public interface DoctorService {
	
	Long saveDoctor(DoctorRequestDTO doctorRequestDTO,MultipartFile file)throws IOException;
	List<DoctorResponseDTO> getAllDoctor();
	void removeDoctor(Long id);
	DoctorResponseDTO getOneDoctor(Long id);
	void updateDoctor(Long id,DoctorRequestDTO doctorRequestDTO);
	List<DoctorResponseDTO> findDoctorBySpecId(Long specId);
	DoctorResponseDTO findDoctorBySpecIdAndDoctorId(Long specId, Long doctorId);
	Long getDoctorCount();

}
