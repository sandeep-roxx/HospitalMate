package com.verma.sandeep.hospital.mate.service.impl;

import java.util.List;


import com.verma.sandeep.hospital.mate.dto.AppointmentDTO;
import com.verma.sandeep.hospital.mate.entity.Appointment;

public interface AppointmentService {
	
	public Long saveAppointment(Appointment pat);
	public List<Appointment> getAllAppointments();
	public void remove(Long id);
	public Appointment getOneAppointment(Long id);
	public void updateAppointment(Appointment pat);
	public List<AppointmentDTO> getAppointmentByDoctor(Long docId);
	public List<AppointmentDTO> getAppoinmentsByDoctorEmail(String email);
	public void updateSlotCountForAppointment(Long apmtId,int count);
	public Long getAppointmentCount();

}
