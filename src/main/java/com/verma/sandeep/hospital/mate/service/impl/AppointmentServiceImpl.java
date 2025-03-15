package com.verma.sandeep.hospital.mate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verma.sandeep.hospital.mate.dto.AppointmentDTO;
import com.verma.sandeep.hospital.mate.entity.Appointment;
import com.verma.sandeep.hospital.mate.exception.AppointmentNotFoundException;
import com.verma.sandeep.hospital.mate.repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements IAppointmentService {
	
	@Autowired
	private AppointmentRepository appRepo;

	@Override
	public Long saveAppointment(Appointment appointment) {
		return appRepo.save(appointment).getId();
	}

	@Override
	public List<Appointment> getAllAppointments() {
		return appRepo.findAll();
	}

	@Override
	public void remove(Long id) {
		appRepo.delete(getOneAppointment(id));

	}

	@Override
	public Appointment getOneAppointment(Long id) {
		return appRepo.findById(id)
				                        .orElseThrow(()->new AppointmentNotFoundException("Appointment not found"));
	}

	@Override
	public void updateAppointment(Appointment pat) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AppointmentDTO> getAppointmentByDoctor(Long docId){
		return appRepo.getAppointmentByDoctor(docId);
	}

	@Override
	public List<AppointmentDTO> getAppoinmentsByDoctorEmail(String email) {
		return appRepo.getAppoinmentsByDoctorEmail(email);
	}

	@Override
	public void updateSlotCountForAppointment(Long id, int count) {
		appRepo.updateSlotCountForAppointment(id, count);

	}

	@Override
	public Long getAppointmentCount() {
		return appRepo.count();
	}

}
