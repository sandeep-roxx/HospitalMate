package com.verma.sandeep.hospital.mate.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verma.sandeep.hospital.mate.entity.Specialization;
import com.verma.sandeep.hospital.mate.exception.SpecializationNotFoundException;
import com.verma.sandeep.hospital.mate.repository.SpecializationRepository;
import com.verma.sandeep.hospital.mate.util.MyCollectionUtil;

@Service
public class SpecializationMgmtService implements ISpecializationMgmtService {
	
	@Autowired
	private SpecializationRepository specRepo;

	@Override
	public Long saveSpecialization(Specialization spec) {
		return specRepo.save(spec).getId();
	}

	@Override
	public List<Specialization> getAllSpecializations() {
		return specRepo.findAll();
	}

	@Override
	public Specialization getOneSpecialization(Long id) {
		return specRepo.findById(id)
				                        .orElseThrow(()->new SpecializationNotFoundException("Specialization not exit"));
	}

	@Override
	public void removeSpecialization(Long id) {
		specRepo.delete(getOneSpecialization(id));
		
	}

	@Override
	public void updateSpecialization(Specialization spec) {
		specRepo.save(spec);
		
	}

	@Override
	public Map<Long, String> getSpecIdAndName() {
		List<Object[]> list=specRepo.getSpecIdAndName();
		return MyCollectionUtil.convertToMap(list);
	}

}
