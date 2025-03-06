package com.verma.sandeep.hospital.mate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		// TODO Auto-generated method stub

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
	public List<Object[]> getAppointmentByDoctor(Long docId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> getAppoinmentsByDoctorEmail(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateSlotCountForAppointment(Long id, int count) {
		// TODO Auto-generated method stub

	}

	@Override
	public Long getAppointmentCount() {
		// TODO Auto-generated method stub
		return null;
	}

}
