package com.verma.sandeep.hospital.mate.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verma.sandeep.hospital.mate.constant.WardStatus;
import com.verma.sandeep.hospital.mate.dto.WardDTO;
import com.verma.sandeep.hospital.mate.entity.Doctor;
import com.verma.sandeep.hospital.mate.entity.Patient;
import com.verma.sandeep.hospital.mate.entity.Ward;
import com.verma.sandeep.hospital.mate.exception.DoctorNotFoundException;
import com.verma.sandeep.hospital.mate.exception.WardNotFoundException;
import com.verma.sandeep.hospital.mate.iservice.WardService;
import com.verma.sandeep.hospital.mate.repository.DoctorRepository;
import com.verma.sandeep.hospital.mate.repository.PatientRepository;
import com.verma.sandeep.hospital.mate.repository.WardRepository;

@Service
public class WardServiceImpl implements WardService {
	
	@Autowired
	private WardRepository wardRepo;
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private PatientRepository patientRepo;

	@Override
	public Long allocateWard(WardDTO wardDTO) {
		Ward ward=mapToEntity(wardDTO);
		return wardRepo.save(ward).getWid();
	}
	
	//Add more patient in same ward
	public void addPatientsToWard(Long wardId, List<Long> patientIds) {
		Ward ward=mapToEntity(getWardById(wardId));
		if(ward.getAvailableBeds()==0){
			throw new WardNotFoundException("No available beds in this ward.");
		}
		
		List<Patient> newPatients = patientRepo.findAllById(patientIds);
		int availableBeds = ward.getAvailableBeds();
		
		for (Patient patient : newPatients) {
	        if (availableBeds > 0) {
	            ward.getPatients().add(patient);
	            availableBeds--;
	        } else {
	        	ward.setStatus(WardStatus.FULL.name());
	            break;
	        }
	    }

	    ward.setAvailableBeds(availableBeds);
	    wardRepo.save(ward);
	}

	@Override
	public List<WardDTO> getAllWards() {
		return wardRepo.findAll()
				                          .stream()
				                          .map(ward->mapToDTO(ward))
				                          .collect(Collectors.toList());
	}

	@Override
	public WardDTO getWardById(Long id) {
		Ward ward=wardRepo.findById(id)
				                          .orElseThrow(()->new WardNotFoundException("Ward not exist"));
		return mapToDTO(ward);
	}

	@Override
	public void deleteWard(Long id) {
		wardRepo.delete(mapToEntity(getWardById(id)));

	}

	@Override
	public void updateOneWard(WardDTO wardDTO) {
		Ward ward=mapToEntity(wardDTO);
		if(wardRepo.existsById(ward.getWid()))
			wardRepo.save(ward);
		else 
			throw new WardNotFoundException("Ward not exist");
		
	}
	
	// DTO to Entity
    private Ward mapToEntity(WardDTO dto) {
    	
    	Ward ward=new Ward();
    	ward.setWid(dto.getWid());
    	ward.setWardName(dto.getWardName());
    	ward.setType(dto.getType());
    	ward.setCapacity(dto.getCapacity());
    	ward.setAvailableBeds(dto.getAvailableBeds());
    	ward.setStatus(dto.getStatus());
    	ward.setFloorNumber(dto.getFloorNumber());
    	ward.setDescription(dto.getDescription());
    	
    	if(dto.getDoctorId()!=null) {
    		Doctor doctor=doctorRepository.findById(dto.getDoctorId())
    				                                                      .orElseThrow(()-> new DoctorNotFoundException("Doctor not found"));
    		ward.setDoctor(doctor);
    	}
    	
    	if(dto.getPatientIds()!=null) {
    		List<Patient> patients=patientRepo.findAllById(dto.getPatientIds());	
    		ward.setPatients(patients);
    	}
    	
    	return ward;
    }
    
 // Entity to DTO
    private WardDTO mapToDTO(Ward ward) {
    	return new WardDTO(
    			ward.getWid(),
    			ward.getWardName(),
    			ward.getType(),
    			ward.getCapacity(),
    			ward.getAvailableBeds(),
    			ward.getStatus(),
    			ward.getFloorNumber(),
    			ward.getDescription(),
    			ward.getDoctor() != null ? ward.getDoctor().getId() : null,
    		    ward.getPatients() != null ? ward.getPatients()
    		    		                                                       .stream()
    		    		                                                       .map(Patient::getId)
    		    		                                                       .collect(Collectors.toList()): new ArrayList<>()
    		   );
    	
    }

}
