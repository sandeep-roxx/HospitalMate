package com.verma.sandeep.hospital.mate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verma.sandeep.hospital.mate.entity.Ward;
import com.verma.sandeep.hospital.mate.exception.WardNotFoundException;
import com.verma.sandeep.hospital.mate.iservice.WardService;
import com.verma.sandeep.hospital.mate.repository.WardRepository;

@Service
public class WardServiceImpl implements WardService {
	
	@Autowired
	private WardRepository wardRepo;

	@Override
	public Long allocateWard(Ward ward) {
		return wardRepo.save(ward).getWid();
	}

	@Override
	public List<Ward> getAllWards() {
		return wardRepo.findAll();
	}

	@Override
	public Ward getWardById(Long id) {
		return wardRepo.findById(id)
				                          .orElseThrow(()->new WardNotFoundException("Ward not exist"));
	}

	@Override
	public void deleteWard(Long id) {
		wardRepo.delete(getWardById(id));

	}

}
