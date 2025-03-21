package com.verma.sandeep.hospital.mate.service.impl;

import java.util.List;

import com.verma.sandeep.hospital.mate.dto.AppointmentRequestDTO;
import com.verma.sandeep.hospital.mate.dto.AppointmentResponseDTO;

public interface AppointmentService {
	
	public Long saveAppointment(AppointmentRequestDTO pat);
	public List<AppointmentResponseDTO> getAllAppointments();
	public void remove(Long id);
	public AppointmentResponseDTO getOneAppointment(Long id);
	public void updateAppointment(Long id,AppointmentRequestDTO pat);
	public List<AppointmentResponseDTO> getAppointmentByDoctor(Long docId);
	public List<AppointmentResponseDTO> getAppoinmentsByDoctorEmail(String email);
	public void updateSlotCountForAppointment(Long apmtId,int count);
	public Long getAppointmentCount();

}
