package com.verma.sandeep.hospital.mate.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verma.sandeep.hospital.mate.dto.AppointmentRequestDTO;
import com.verma.sandeep.hospital.mate.dto.AppointmentResponseDTO;
import com.verma.sandeep.hospital.mate.entity.Appointment;
import com.verma.sandeep.hospital.mate.entity.Doctor;
import com.verma.sandeep.hospital.mate.exception.AppointmentNotFoundException;
import com.verma.sandeep.hospital.mate.exception.DoctorNotFoundException;
import com.verma.sandeep.hospital.mate.repository.AppointmentRepository;
import com.verma.sandeep.hospital.mate.repository.DoctorRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
	private AppointmentRepository appRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public Long saveAppointment(AppointmentRequestDTO appointmentRequestDTO) {
		Appointment appointment=modelMapper.map(appointmentRequestDTO, Appointment.class);
		Doctor doctor=doctorRepository.findById(appointmentRequestDTO.getDoctorId())
				.orElseThrow(()->new DoctorNotFoundException("Doctor not found "));
		appointment.setDoctor(doctor);
		return appRepo.save(appointment).getId();
	}

	@Override
	public List<AppointmentResponseDTO> getAllAppointments() {
		return appRepo.findAll()
				                        .stream()
				                        .map(appointment->modelMapper.map(appointment, AppointmentResponseDTO.class))
				                        .collect(Collectors.toList());
	}

	@Override
	public void remove(Long id) {
		AppointmentResponseDTO appResponseDTO=getOneAppointment(id);
		Appointment appointment=modelMapper.map(appResponseDTO, Appointment.class);
		appRepo.delete(appointment);

	}

	@Override
	public AppointmentResponseDTO getOneAppointment(Long id) {
		 Appointment appointment =appRepo.findById(id)
				                        .orElseThrow(()->new AppointmentNotFoundException("Appointment not found"));
		 return modelMapper.map(appointment, AppointmentResponseDTO.class);
	}

	@Override
	public void updateAppointment(Long id,AppointmentRequestDTO appRequestDTO) {
		if(appRepo.existsById(id)) {
			Appointment appointment=modelMapper.map(appRequestDTO, Appointment.class);
			appRepo.save(appointment);
		}
		throw new AppointmentNotFoundException("Appointment not found "+id);

	}

	@Override
	public List<AppointmentResponseDTO> getAppointmentByDoctor(Long docId){
		return appRepo.getAppointmentByDoctor(docId);
	}

	@Override
	public List<AppointmentResponseDTO> getAppoinmentsByDoctorEmail(String email) {
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
