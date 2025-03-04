package com.verma.sandeep.hospital.mate.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verma.sandeep.hospital.mate.entity.Doctor;
import com.verma.sandeep.hospital.mate.exception.DoctorNotFoundException;
import com.verma.sandeep.hospital.mate.repository.DoctorRepository;
import com.verma.sandeep.hospital.mate.util.MyCollectionUtil;

@Service
public class DoctorServiceImpl implements IDoctorService {
	
	@Autowired
	private DoctorRepository docRepo;

	@Override
	public Long saveDoctor(Doctor doc) {
		return docRepo.save(doc).getId();
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
