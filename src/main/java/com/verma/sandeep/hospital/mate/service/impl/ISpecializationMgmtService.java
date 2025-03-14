package com.verma.sandeep.hospital.mate.service.impl;

import java.util.List;
import java.util.Map;

import com.verma.sandeep.hospital.mate.entity.Specialization;

public interface ISpecializationMgmtService {
	
	public Long saveSpecialization(Specialization spec);
	public List<Specialization> getAllSpecializations();
	public Specialization getOneSpecialization(Long id);
	public void removeSpecialization(Long id);
	public void updateSpecialization(Specialization spec);
	public Map<Long,String> getSpecIdAndName();
	

}
