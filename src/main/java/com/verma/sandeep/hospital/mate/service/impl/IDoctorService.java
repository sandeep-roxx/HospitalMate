package com.verma.sandeep.hospital.mate.service.impl;

import java.util.List;
import java.util.Map;

import com.verma.sandeep.hospital.mate.entity.Doctor;


public interface IDoctorService {
	
	public Long saveDoctor(Doctor doc);
	public List<Doctor> getAllDoctor();
	public void removeDoctor(Long id);
	public Doctor getOneDoctor(Long id);
	public void updateDoctor(Doctor doc);
	public Map<Long,String> getDoctorIdAndNames();
	public List<Doctor> findDoctorBySpecId(Long specId);
	public List<Doctor> findDoctorById(Long doctorId);
	public List<Doctor> findDoctorBySpecIdAndDoctorId(Long specId, Long doctorId);
	public Long getDoctorCount();

}
