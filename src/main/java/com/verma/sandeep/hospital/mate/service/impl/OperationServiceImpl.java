package com.verma.sandeep.hospital.mate.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verma.sandeep.hospital.mate.dto.OperationDTO;
import com.verma.sandeep.hospital.mate.entity.Doctor;
import com.verma.sandeep.hospital.mate.entity.Operation;
import com.verma.sandeep.hospital.mate.entity.Patient;
import com.verma.sandeep.hospital.mate.exception.OperationNotFoundException;
import com.verma.sandeep.hospital.mate.iservice.OperationService;
import com.verma.sandeep.hospital.mate.repository.OperationRepository;

import jakarta.transaction.Transactional;

@Service
public class OperationServiceImpl implements OperationService {
	
	@Autowired
	private OperationRepository operationRepo;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private PatientService patientService;
	
	
	@Override
	public Long createOperation(OperationDTO operationDTO) {
		Operation operation=convertToEntity(operationDTO);
		return operationRepo.save(operation).getOid();
	}

	@Override
	public List<OperationDTO> getAllOperations() {
		return operationRepo.findAll()
				                                    .stream()
				                                    .map(operation->convertToDTO(operation))
				                                    .collect(Collectors.toList());
	}

	@Override
	public OperationDTO getOperationById(Long id) {
		Operation operation=operationRepo.findById(id)
				                                                              .orElseThrow(()->new OperationNotFoundException("Operation not found with ID: " + id));
		return convertToDTO(operation);
	}

	@Override
	public void updateOperation(OperationDTO operationDTO) {
		Operation operation=convertToEntity(operationDTO);
		if(operationRepo.existsById(operation.getOid()))
			operationRepo.save(operation);
		else
			throw new OperationNotFoundException("Operation not found with ID: "+ operation.getOid());
	}

	@Override
	public void deleteOperation(Long id) {
		operationRepo.delete(convertToEntity(getOperationById(id)));

	}
	
	@Override
	@Transactional
	public void updateOperationStatus(Long operationId, String status) {
		operationRepo.updateOperationStatus(operationId, status);
		
	}
	
	@Override
	public String getOperationStatus(Long operationId) {
		return operationRepo.findOperationStatusById(operationId);
	}
	
	// Convert Entity to DTO
    private Operation convertToEntity(OperationDTO operationDTO) {
    	
    	Patient patient=patientService.getOnePatient(operationDTO.getPatientId());
    	Doctor doctor=doctorService.getOneDoctor(operationDTO.getDoctorId());
    	
    	Operation operation = new Operation();
    	 operation.setOid(operationDTO.getOid());
        operation.setOName(operationDTO.getOName());
        operation.setPatient(patient);
        operation.setDoctor(doctor);
        operation.setOperationDateTime(operationDTO.getOperationDateTime());
        operation.setStatus(operationDTO.getStatus());
        operation.setDescription(operationDTO.getDescription());
        operation.setOperationTheaterRoom(operationDTO.getOperationTheaterRoom());
        operation.setNotes(operationDTO.getNotes());
        return operation;
    	
    }
	
	// Convert Entity to DTO
    private OperationDTO convertToDTO(Operation operation) {
        return new OperationDTO(operation.getOid(),
        		                                            operation.getOName(),
        		                                            operation.getPatient().getId(),
        		                                            operation.getDoctor().getId(),
        		                                            operation.getOperationDateTime(),
        		                                            operation.getStatus(),
        		                                            operation.getDescription(),
        		                                            operation.getOperationTheaterRoom(),
        		                                            operation.getNotes()
        		                                             );
    }



}
