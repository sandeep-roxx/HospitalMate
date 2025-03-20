package com.verma.sandeep.hospital.mate.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verma.sandeep.hospital.mate.dto.SpecializationRequestDTO;
import com.verma.sandeep.hospital.mate.dto.SpecializationResponseDTO;
import com.verma.sandeep.hospital.mate.entity.Specialization;
import com.verma.sandeep.hospital.mate.exception.SpecializationNotFoundException;
import com.verma.sandeep.hospital.mate.repository.SpecializationRepository;

@Service
public class SpecializationMgmtService implements ISpecializationMgmtService {
	
	@Autowired
	private SpecializationRepository specRepo;

	@Override
	public Long saveSpecialization(SpecializationRequestDTO specializationRequestDTO) {
		Specialization specialization = new Specialization();
		specialization.setSpecName(specializationRequestDTO.getSpecName());
        specialization.setSpecCode(specializationRequestDTO.getSpecCode());
        specialization.setDescription(specializationRequestDTO.getDescription());
		return specRepo.save(specialization).getId();
	}

	@Override
	public List<SpecializationResponseDTO> getAllSpecializations() {
		return specRepo.findAll()
				                         .stream()
				                         .map(specialization->mapToSpecializationResponseDTO(specialization))
				                         .collect(Collectors.toList());
	}

	@Override
	public SpecializationResponseDTO getOneSpecialization(Long id) {
		Specialization specialization= specRepo.findById(id)
				                        .orElseThrow(()->new SpecializationNotFoundException("Specialization not exit"));
		return mapToSpecializationResponseDTO(specialization);
	}

	@Override
	public void removeSpecialization(Long id) {
		SpecializationResponseDTO specializationResponseDTO=getOneSpecialization(id);
		specRepo.delete(mapToSpecialization(specializationResponseDTO));
		
	}

	@Override
	public void updateSpecialization(Long id,SpecializationRequestDTO specializationRequestDTO) {
		SpecializationResponseDTO specializationResponseDTO=getOneSpecialization(id);
		specRepo.save(mapToSpecialization(specializationResponseDTO));
		
	}

	
	private SpecializationResponseDTO mapToSpecializationResponseDTO(Specialization specialization)
	{
        return new SpecializationResponseDTO(
                specialization.getId(),
                specialization.getSpecName(),
                specialization.getSpecCode(),
                specialization.getDescription(),
                specialization.getCreatedAt(),
                specialization.getUpdatedAt()
        );
    }
	
	private Specialization mapToSpecialization(SpecializationResponseDTO specializationResponseDTO)
	
	{
		Specialization specialization = new Specialization();
		specialization.setId(specializationResponseDTO.getId());
		specialization.setSpecName(specializationResponseDTO.getSpecName());
        specialization.setSpecCode(specializationResponseDTO.getSpecCode());
        specialization.setDescription(specializationResponseDTO.getDescription());
		
		return specialization;
	}

}
