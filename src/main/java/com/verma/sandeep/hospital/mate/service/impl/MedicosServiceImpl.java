package com.verma.sandeep.hospital.mate.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verma.sandeep.hospital.mate.dto.MedicosDTO;
import com.verma.sandeep.hospital.mate.entity.Doctor;
import com.verma.sandeep.hospital.mate.entity.Medicos;
import com.verma.sandeep.hospital.mate.entity.Patient;
import com.verma.sandeep.hospital.mate.exception.MedicosNotFoundException;
import com.verma.sandeep.hospital.mate.iservice.MedicosService;
import com.verma.sandeep.hospital.mate.repository.MedicosRepository;

@Service
public class MedicosServiceImpl implements MedicosService {
	
	@Autowired
	private MedicosRepository medicosRepo;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private PatientService patientService;

	@Override
	public Long saveMedicos(MedicosDTO medicosDTO) {
		return medicosRepo.save(convertToEntity(medicosDTO)).getMedId();
	}

	@Override
	public List<MedicosDTO> getAllMedicos() {
		return medicosRepo.findAll()
				                                .stream()
				                                .map(medicos->convertToDTO(medicos))
				                                .collect(Collectors.toList());
	}

	@Override
	public MedicosDTO getMedicosById(Long id) {
		Medicos medicos=medicosRepo.findById(id)
				                                                    .orElseThrow(()->new MedicosNotFoundException("Medicos not found "+id));
		return convertToDTO(medicos);
	}

	@Override
	public void updateOneMedicos(MedicosDTO medicosDTO) {
		if(medicosRepo.existsById(medicosDTO.getMedId())) {
			medicosRepo.save(convertToEntity(medicosDTO));
		}else {
			throw new MedicosNotFoundException("Medicos not found "+medicosDTO.getMedId());
		}
	}

	@Override
	public void deleteMedicos(Long id) {
		medicosRepo.delete(convertToEntity(getMedicosById(id)));

	}
	
	 // Convert DTO to Entity
    private Medicos convertToEntity(MedicosDTO dto) {
        Medicos medicos = new Medicos();
        medicos.setMedId(dto.getMedId());
        medicos.setMedRecord(dto.getMedRecord());
        medicos.setMedPrice(dto.getMedPrice());
        medicos.setMedQuantity(dto.getMedQuantity());
        //Calculate med total
        medicos.setMedTotal(dto.getMedPrice()*dto.getMedQuantity());
        medicos.setMedDate(dto.getMedDate());
        
        Doctor doctor = doctorService.getOneDoctor(dto.getDoctorId());
        medicos.setDoctor(doctor);

        Patient patient = patientService.getOnePatient(dto.getPatientId());
        medicos.setPatient(patient);

        return medicos;
    }
    
   // Convert Entity to DTO
    private MedicosDTO convertToDTO(Medicos medicos) {
        return new MedicosDTO(
            medicos.getMedId(),
            medicos.getMedRecord(),
            medicos.getMedPrice(),
            medicos.getMedQuantity(),
            medicos.getMedTotal(),
            medicos.getMedDate(),
            medicos.getDoctor() != null ? medicos.getDoctor().getId() : null,
            medicos.getPatient() != null ? medicos.getPatient().getId() : null
        );
    }
    
    //Fetching the prescriptions of a patient.
	@Override
	public List<MedicosDTO> getByPatientId(Long patientId) {
		return medicosRepo.findByPatientId(patientId)
				                                .stream()
				                                .map(medicos->convertToDTO(medicos))
				                                .collect(Collectors.toList());
	}

	@Override
	public Long getTotalCost(Long patientId) {
		long total=0;
		for(MedicosDTO medicos :getAllMedicos()) {
			total+=medicos.getMedTotal();
		}
		return total;
	}

}
