package com.verma.sandeep.hospital.mate.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verma.sandeep.hospital.mate.constant.UserRole;
import com.verma.sandeep.hospital.mate.entity.Doctor;
import com.verma.sandeep.hospital.mate.entity.User;
import com.verma.sandeep.hospital.mate.exception.DoctorNotFoundException;
import com.verma.sandeep.hospital.mate.repository.DoctorRepository;
import com.verma.sandeep.hospital.mate.util.MyCollectionUtil;
import com.verma.sandeep.hospital.mate.util.PasswordGeneratorUtil;

@Service
public class DoctorServiceImpl implements IDoctorService {
	
	@Autowired
	private DoctorRepository docRepo;
	
	@Autowired
	private IUserMgmtService userService;
	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getDoctorCount() {
		// TODO Auto-generated method stub
		return null;
	}

}
