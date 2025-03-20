package com.verma.sandeep.hospital.mate.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.verma.sandeep.hospital.mate.constant.UserRole;
import com.verma.sandeep.hospital.mate.dto.PatientRequestDTO;
import com.verma.sandeep.hospital.mate.dto.PatientResponseDTO;
import com.verma.sandeep.hospital.mate.entity.Patient;
import com.verma.sandeep.hospital.mate.entity.User;
import com.verma.sandeep.hospital.mate.exception.PatientsNotFoundException;
import com.verma.sandeep.hospital.mate.repository.MedicosRepository;
import com.verma.sandeep.hospital.mate.repository.PatientRepository;
import com.verma.sandeep.hospital.mate.repository.TestRepository;
import com.verma.sandeep.hospital.mate.util.PasswordGeneratorUtil;

@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired
	private PatientRepository patRepo;
	@Autowired
	private UserMgmtService userService;
	@Autowired
	private PasswordGeneratorUtil passwordGenerator;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private MedicosRepository medicosRepo;
	@Autowired
	private TestRepository testRepository;

	@Override
	public Long savePatient(PatientRequestDTO patRequestDTO) {
		Patient patient=modelMapper.map(patRequestDTO, Patient.class);
		Long id=patRepo.save(patient).getId();
		if(id!=null) {
			User user=new User();
			user.setName(patient.getFirstName()+" "+patient.getLastName());
			user.setEmail(patient.getEmail());
			user.setPassword(passwordGenerator.generatePassword());
			user.setRole(UserRole.PATIENT.name());
			userService.saveUser(user);
			//TODO : Email pending
		}
		return id;
	}
   /*
	@Override
	public List<Patient> getAllPatients() {
		return patRepo.findAll();
	}
	*/
	
	public Page<PatientResponseDTO> getAllPatients(Pageable pageable){
		return patRepo.findAll(pageable)
				                       .map(patient->modelMapper.map(patient,PatientResponseDTO.class));
		
	}

	@Override
	public PatientResponseDTO getOnePatient(Long id) {
		Patient patient=patRepo.findById(id)
				                       .orElseThrow(()->new PatientsNotFoundException("Patient not exist"));
		return modelMapper.map(patient, PatientResponseDTO.class);
	}
	
	@Override
	public void remove(Long id) {
		patRepo.delete(modelMapper.map(getOnePatient(id), Patient.class));

	}

	@Override
	public void updatePatient(Long id,PatientRequestDTO patRequestDTO) {
		if(patRepo.existsById(id)) {
			Patient patient=modelMapper.map(patRequestDTO, Patient.class);
			patient.setId(id);
			patient.setPrescriptions(medicosRepo.findByPatientId(id));
			patient.setTests(testRepository.findByPatientId(id));
			patRepo.save(patient);
		}else {
			throw new PatientsNotFoundException("Patient not exist");
		}

	}

	@Override
	public PatientResponseDTO getOnePatient(String email) {
		Patient patient= patRepo.findByEmail(email)
				                       .orElseThrow(()-> new PatientsNotFoundException("Patient not found"));
		return  modelMapper.map(patient, PatientResponseDTO.class);
	}

	@Override
	public Long getPatientCount() {
		return patRepo.count();
	}
	
	//Search patient by email or number
	@Override
    public PatientResponseDTO searchPatientByEmailOrMobile(String query) {
		Patient patient= patRepo.findByEmailOrMobile(query, query)
                .orElseThrow(() -> new PatientsNotFoundException("Patient not found with given email or mobile"));
		return modelMapper.map(patient, PatientResponseDTO.class);
    }


}
