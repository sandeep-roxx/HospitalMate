package com.verma.sandeep.hospital.mate.service;

import java.util.List;

import com.verma.sandeep.hospital.mate.entity.Specialization;

public interface ISpecializationMgmtService {
	
	public Long saveSpecialization(Specialization spec);
	public List<Specialization> getAllSpecializations();
	public Specialization getOneSpecialization(Long id);
	public void removeSpecialization(Long id);
	public void updateSpecialization(Specialization spec);
	

}
