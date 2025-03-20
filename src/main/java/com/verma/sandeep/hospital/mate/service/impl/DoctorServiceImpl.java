package com.verma.sandeep.hospital.mate.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.verma.sandeep.hospital.mate.constant.UserRole;
import com.verma.sandeep.hospital.mate.dto.DoctorRequestDTO;
import com.verma.sandeep.hospital.mate.dto.DoctorResponseDTO;
import com.verma.sandeep.hospital.mate.entity.Doctor;
import com.verma.sandeep.hospital.mate.entity.Specialization;
import com.verma.sandeep.hospital.mate.entity.User;
import com.verma.sandeep.hospital.mate.exception.DoctorNotFoundException;
import com.verma.sandeep.hospital.mate.exception.SpecializationNotFoundException;
import com.verma.sandeep.hospital.mate.repository.DoctorRepository;
import com.verma.sandeep.hospital.mate.repository.SpecializationRepository;
import com.verma.sandeep.hospital.mate.util.PasswordGeneratorUtil;

@Service
public class DoctorServiceImpl implements DoctorService {
	
	@Autowired
	private DoctorRepository docRepo;
	@Autowired
	private  SpecializationRepository specRepository;
	@Autowired
	private UserMgmtService userService;
	@Autowired
	private PasswordGeneratorUtil passwordGenerator;
	@Autowired
	private S3FileService s3FileService; 

	@Override
	public Long saveDoctor(DoctorRequestDTO doctorRequestDTO,
			                                       MultipartFile file) throws IOException 
	{
		Doctor doctor=mapToDoctor(doctorRequestDTO);
		// Upload file to S3 and get the URL
		 String fileUrl = s3FileService.uploadFile(file, "doctor-photos");
		 doctor.setFileUrl(fileUrl);
		 //Save the doctor details
		Long id=docRepo.save(doctor).getId();
		if(id!=null) {
			User user=new User();
			user.setName(doctor.getFirstName()+" "+doctor.getLastName());
			user.setEmail(doctor.getEmail());
			user.setPassword(passwordGenerator.generatePassword());
			user.setRole(UserRole.DOCTOR.name());
			userService.saveUser(user);
			//TODO : Email is pending
		}
		return id;
	}

	@Override
	public List<DoctorResponseDTO> getAllDoctor() {
		return docRepo.findAll()
				                        .stream()
				                        .map(doctor->mapToDoctorResponseDTO(doctor))
				                        .collect(Collectors.toList());
	}

	@Override
	public void removeDoctor(Long id) {
		docRepo.delete(mapToDoctor(getOneDoctor(id)));

	}

	@Override
	public DoctorResponseDTO getOneDoctor(Long id) {
		Doctor doctor= docRepo.findById(id)
				                        .orElseThrow(()->new DoctorNotFoundException("Docter not found"));
		return mapToDoctorResponseDTO(doctor);
	}

	@Override
	public void updateDoctor(Long id,DoctorRequestDTO doctorRequestDTO) {
		if(docRepo.existsById(id)) {
			Doctor doctor=mapToDoctor(doctorRequestDTO);
			doctor.setId(id);
			docRepo.save(doctor);
		}else {
			throw new DoctorNotFoundException("Doctor not exist "+id);
		}

	}

	@Override
	public List<DoctorResponseDTO> findDoctorBySpecId(Long specId) {
		return docRepo.findDoctorBySpecId(specId)
				                       .stream()
				                       .map(doctor->mapToDoctorResponseDTO(doctor))
				                       .collect(Collectors.toList());
	}

	@Override
	public Long getDoctorCount() {
		return docRepo.count();
	}

	//Get doctor by docId and SpecId
	@Override
	public DoctorResponseDTO findDoctorBySpecIdAndDoctorId(Long specId, Long doctorId) {
		Doctor doctor=docRepo.findDoctorBySpecIdAndDoctorId(specId, doctorId)
				                                      .orElseThrow(()-> new DoctorNotFoundException("Doctor not found "+doctorId));
		return mapToDoctorResponseDTO(doctor);
	}
	
	
	private DoctorResponseDTO mapToDoctorResponseDTO(Doctor doctor) {
        return new DoctorResponseDTO(
                doctor.getId(),
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getEmail(),
                doctor.getAddress(),
                doctor.getMobile(),
                doctor.getGender(),
                doctor.getNote(),
                doctor.getFileUrl(),
                doctor.getSpecialization().getId(),
                doctor.getCreatedAt(),
                doctor.getUpdatedAt()
        );
    }
	
	private Doctor mapToDoctor(DoctorResponseDTO responseDTO) {
		
		Specialization specialization=specRepository.findById(responseDTO.getSpecializationId())
                .orElseThrow(()->new SpecializationNotFoundException("Specialization not found"));
		Doctor doctor=new Doctor();
		doctor.setId(responseDTO.getId());
		doctor.setFirstName(responseDTO.getFirstName());
	    doctor.setLastName(responseDTO.getLastName());
	    doctor.setEmail(responseDTO.getEmail());
	    doctor.setAddress(responseDTO.getAddress());
	    doctor.setMobile(responseDTO.getMobile());
	    doctor.setGender(responseDTO.getGender());
	    doctor.setNote(responseDTO.getNote());
	    doctor.setFileUrl(responseDTO.getFileUrl());
	    doctor.setSpecialization(specialization);
	    doctor.setCreatedAt(responseDTO.getCreatedAt());
	    doctor.setUpdatedAt(responseDTO.getUpdatedAt());
	    
		return doctor;	
		
	}
	
private Doctor mapToDoctor(DoctorRequestDTO requestDTO) {
	
	Specialization specialization=specRepository.findById(requestDTO.getSpecializationId())
			                                                                           .orElseThrow(()->new SpecializationNotFoundException("Specialization not found"));
	Doctor doctor=new Doctor();
	doctor.setFirstName(requestDTO.getFirstName());
    doctor.setLastName(requestDTO.getLastName());
    doctor.setEmail(requestDTO.getEmail());
    doctor.setAddress(requestDTO.getAddress());
    doctor.setMobile(requestDTO.getMobile());
    doctor.setGender(requestDTO.getGender());
    doctor.setNote(requestDTO.getNote());
    doctor.setSpecialization(specialization);
	return doctor;
		
		
	}

}
