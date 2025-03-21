package com.verma.sandeep.hospital.mate.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.verma.sandeep.hospital.mate.dto.AppointmentRequestDTO;
import com.verma.sandeep.hospital.mate.dto.AppointmentResponseDTO;
import com.verma.sandeep.hospital.mate.dto.DoctorResponseDTO;
import com.verma.sandeep.hospital.mate.exception.AppointmentNotFoundException;
import com.verma.sandeep.hospital.mate.exception.DoctorNotFoundException;
import com.verma.sandeep.hospital.mate.service.impl.AppointmentService;
import com.verma.sandeep.hospital.mate.service.impl.DoctorService;


@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private DoctorService docService;


	// Create an appointment
	@PostMapping("/register")
	public ResponseEntity<String> saveAppointment(
			@RequestBody AppointmentRequestDTO appRequestDTO
			) 
	{
		Long id = appointmentService.saveAppointment(appRequestDTO);
		return new ResponseEntity<>("Appointment with ID " + id + " created successfully!", HttpStatus.CREATED);
	}

	// Get all appointments
	@GetMapping("/all")
	public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointments() {
		List<AppointmentResponseDTO> responseDTOs = appointmentService.getAllAppointments();
		return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
	}

	// Get appointment by ID
	@GetMapping("find/{id}")
	public ResponseEntity<AppointmentResponseDTO> getAppointment(@PathVariable Long id) {
		ResponseEntity<AppointmentResponseDTO> response=null;
		try {
			AppointmentResponseDTO appResponseDTO = appointmentService.getOneAppointment(id);
			response=new ResponseEntity<AppointmentResponseDTO>(appResponseDTO, HttpStatus.OK);
		}catch (AppointmentNotFoundException e) {
			throw e;
		}
		return response;
	}

	// Update appointment
	@PutMapping("/update")
	public ResponseEntity<String> updateAppointment(
			@PathVariable Long id,
			@RequestBody AppointmentRequestDTO appRequestDTO
			) 
	{
		appointmentService.updateAppointment(id,appRequestDTO);
		return new ResponseEntity<>("Appointment updated successfully!", HttpStatus.OK);
	}

	// Delete appointment by ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
		appointmentService.remove(id);
		return new ResponseEntity<>("Appointment with ID " + id + " deleted successfully!", HttpStatus.OK);
	}
	
	// Fetch doctors based on specialization or doctor (or all doctors if no specialization is given) 
	//To view the appointments 
	 @GetMapping("/search")
	    public ResponseEntity<?> searchDoctorsForVeiwAppointments(
	    		@RequestParam(required = false) Long specId,
	    		@RequestParam(required = false) Long doctorId
	    		)
				{
					try {

						if (specId != null && doctorId != null) {
							DoctorResponseDTO doctorResponseDTO = docService.findDoctorBySpecIdAndDoctorId(specId,
									doctorId);
							return ResponseEntity.ok(doctorResponseDTO);
						} else if (specId != null && doctorId == null) {
							List<DoctorResponseDTO> doctorResponseDTOs = docService.findDoctorBySpecId(specId);
							return ResponseEntity.ok(doctorResponseDTOs);
						} else if (doctorId != null && specId == null) {
							DoctorResponseDTO doctorResponseDTO = docService.getOneDoctor(doctorId);
							return ResponseEntity.ok(doctorResponseDTO);

						} else {
							List<DoctorResponseDTO> doctorResponseDTOs = docService.getAllDoctor();
							return ResponseEntity.ok(doctorResponseDTOs);
						}

					} catch (DoctorNotFoundException e) {
						throw e;
					}
				}
	
	 //View appointment slots of specific doctor
	 @GetMapping("/view/slots/{doctorId}")
	 public ResponseEntity<Map<String,Object>> viewSlots(@PathVariable Long doctorId){
		 
		 Map<String,Object> response=new HashMap<>();
		 List<AppointmentResponseDTO> appRespList = appointmentService.getAppointmentByDoctor(doctorId);
		 String message="Showing result for "+docService.getOneDoctor(doctorId);
		 response.put("appointment", appRespList);
		 response.put("message", message);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		 
	 }

	// Fetch appointments for the logged-in doctor
	@GetMapping("/doctor/email")
	public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByDoctorEmail(
			                                                                  Principal principal
			                                                                 )
	{
		//Get current login username(email)
		String email=principal.getName();
		List<AppointmentResponseDTO> appResponseDTOs = appointmentService.getAppoinmentsByDoctorEmail(email);
		return new ResponseEntity<>(appResponseDTOs, HttpStatus.OK);
	}

	// Update slot count for an appointment
	@PatchMapping("/update-slots/{id}/{count}")
	public ResponseEntity<String> updateSlotCount(@PathVariable Long id, @PathVariable int count) {
		appointmentService.updateSlotCountForAppointment(id, count);
		return new ResponseEntity<>("Slot count updated successfully for Appointment ID " + id, HttpStatus.OK);
	}

	// Get total appointment count
	@GetMapping("/count")
	public ResponseEntity<Long> getAppointmentCount() {
		Long count = appointmentService.getAppointmentCount();
		return new ResponseEntity<>(count, HttpStatus.OK);

	}

}
