package com.verma.sandeep.hospital.mate.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verma.sandeep.hospital.mate.constant.UserRole;
import com.verma.sandeep.hospital.mate.dto.DoctorDTO;
import com.verma.sandeep.hospital.mate.entity.Doctor;
import com.verma.sandeep.hospital.mate.entity.User;
import com.verma.sandeep.hospital.mate.exception.DoctorNotFoundException;
import com.verma.sandeep.hospital.mate.repository.DoctorRepository;
import com.verma.sandeep.hospital.mate.util.MyCollectionUtil;
import com.verma.sandeep.hospital.mate.util.PasswordGeneratorUtil;

@Service
public class DoctorServiceImpl implements DoctorService {
	
	@Autowired
	private DoctorRepository docRepo;
	
	@Autowired
	private UserMgmtService userService;
	
	@Autowired
	private PasswordGeneratorUtil passwordGenerator;

	@Override
	public Long saveDoctor(Doctor doc) {
		Long id=docRepo.save(doc).getId();
		if(id!=null) {
			User user=new User();
			user.setName(doc.getFirstName()+" "+doc.getLastName());
			user.setEmail(doc.getEmail());
			user.setPassword(passwordGenerator.generatePassword());
			user.setRole(UserRole.DOCTOR.name());
			userService.saveUser(user);
			//TODO : Email is pending
		}
		return id;
	}

	@Override
	public List<Doctor> getAllDoctor() {
		return docRepo.findAll();
	}

	@Override
	public void removeDoctor(Long id) {
		docRepo.delete(getOneDoctor(id));

	}

	@Override
	public Doctor getOneDoctor(Long id) {
		return docRepo.findById(id)
				                        .orElseThrow(()->new DoctorNotFoundException("Docter not found"));
	}

	@Override
	public void updateDoctor(Doctor doc) {
		docRepo.save(doc);

	}

	@Override
	public Map<Long, String> getDoctorIdAndNames() {
		List<Object[]> list=docRepo.getDoctorIdAndNames();
		return MyCollectionUtil.convertToMapEntery(list);
	}

	@Override
	public List<Doctor> findDoctorBySpecId(Long specId) {
		return docRepo.findDoctorBySpecId(specId);
	}

	@Override
	public Long getDoctorCount() {
		return docRepo.count();
	}

	@Override
	public List<Doctor> findDoctorBySpecIdAndDoctorId(Long specId, Long doctorId) {
		return docRepo.findDoctorBySpecIdAndDoctorId(specId, doctorId);
	}

	@Override
	public List<Doctor> findDoctorById(Long doctorId) {
		return docRepo.findDoctorById(doctorId);
	}

	//Get doctor by id, name and specialization
	@Override
	public List<DoctorDTO> getAllDoctorsDTO() {
		return docRepo.getAllDoctorsDTO();
	}

}
