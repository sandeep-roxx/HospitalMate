package com.verma.sandeep.hospital.mate.service;

import java.util.List;

import com.verma.sandeep.hospital.mate.bind.AppointmentResponse;
import com.verma.sandeep.hospital.mate.entity.Appointment;

public interface IAppointmentService {
	
	public Long saveAppointment(Appointment pat);
	public List<Appointment> getAllAppointments();
	public void remove(Long id);
	public Appointment getOneAppointment(Long id);
	public void updateAppointment(Appointment pat);
	public List<AppointmentResponse> getAppointmentByDoctor(Long docId);
	public List<Object[]> getAppoinmentsByDoctorEmail(String userName);
	public void updateSlotCountForAppointment(Long id,int count);
	public Long getAppointmentCount();

}
