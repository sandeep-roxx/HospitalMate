package com.verma.sandeep.hospital.mate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verma.sandeep.hospital.mate.constant.UserRole;
import com.verma.sandeep.hospital.mate.entity.Patient;
import com.verma.sandeep.hospital.mate.entity.User;
import com.verma.sandeep.hospital.mate.exception.PatientsNotFoundException;
import com.verma.sandeep.hospital.mate.repository.PatientRepository;
import com.verma.sandeep.hospital.mate.util.PasswordGeneratorUtil;

@Service
public class PatientServiceImpl implements IPatientService {
	
	@Autowired
	private PatientRepository patRepo;
	@Autowired
	private IUserMgmtService userService;
	@Autowired
	private PasswordGeneratorUtil passwordGenerator;

	@Override
	public Long savePatient(Patient pat) {
		Long id=patRepo.save(pat).getId();
		if(id!=null) {
			User user=new User();
			user.setName(pat.getFirstName()+" "+pat.getLastName());
			user.setEmail(pat.getEmail());
			user.setPassword(passwordGenerator.generatePassword());
			user.setRole(UserRole.PATIENT.name());
			userService.saveUser(user);
			//TODO : Email pending
		}
		return id;
	}

	@Override
	public List<Patient> getAllPatients() {
		return patRepo.findAll();
	}

	@Override
	public void remove(Long id) {
		patRepo.delete(getOnePatient(id));

	}

	@Override
	public Patient getOnePatient(Long id) {
		return patRepo.findById(id)
				                       .orElseThrow(()->new PatientsNotFoundException("Patient not exist"));
	}

	@Override
	public void updatePatient(Patient pat) {
		if(patRepo.existsById(pat.getId())) {
			patRepo.save(pat);
		}else {
			throw new PatientsNotFoundException("Patient not exist");
		}

	}

	@Override
	public Patient getOnePatient(String email) {
		return patRepo.findByEmail(email)
				                       .orElseThrow(()-> new PatientsNotFoundException("Patient not found"));
	}

	@Override
	public Long getPatientCount() {
		return patRepo.count();
	}

}
