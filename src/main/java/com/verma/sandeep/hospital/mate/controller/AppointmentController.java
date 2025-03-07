package com.verma.sandeep.hospital.mate.controller;

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

import com.verma.sandeep.hospital.mate.bind.AppointmentResponse;
import com.verma.sandeep.hospital.mate.entity.Appointment;
import com.verma.sandeep.hospital.mate.entity.Doctor;
import com.verma.sandeep.hospital.mate.service.IAppointmentService;
import com.verma.sandeep.hospital.mate.service.IDoctorService;
import com.verma.sandeep.hospital.mate.service.ISpecializationMgmtService;


@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private IAppointmentService appointmentService;
	
	@Autowired
	private IDoctorService docService;
	
	@Autowired
	private ISpecializationMgmtService specService;
	
	
	@GetMapping("/docotor/dropdown")
    public ResponseEntity<Map<Long, String>> getDoctorDropdown() {
		Map<Long,String> DoctorMap=docService.getDoctorIdAndNames();
		return new ResponseEntity<Map<Long,String>>(DoctorMap,HttpStatus.OK);
		
	}
	
	@GetMapping("/spec/dropdown")
    public ResponseEntity<Map<Long, String>> getSpecializationDropdown() {
		Map<Long,String> SpecMap=specService.getSpecIdAndName();
		return new ResponseEntity<Map<Long,String>>(SpecMap,HttpStatus.OK);
		
	}

	// Create an appointment
	@PostMapping("/register")
	public ResponseEntity<String> saveAppointment(@RequestBody Appointment appointment) {
		Long id = appointmentService.saveAppointment(appointment);
		return new ResponseEntity<>("Appointment with ID " + id + " created successfully!", HttpStatus.CREATED);
	}

	// Get all appointments
	@GetMapping("/all")
	public ResponseEntity<List<Appointment>> getAllAppointments() {
		List<Appointment> list = appointmentService.getAllAppointments();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// Get appointment by ID
	@GetMapping("find/{id}")
	public ResponseEntity<Appointment> getAppointment(@PathVariable Long id) {
		Appointment appointment = appointmentService.getOneAppointment(id);
		return new ResponseEntity<>(appointment, HttpStatus.OK);
	}

	// Update appointment
	@PutMapping("/update")
	public ResponseEntity<String> updateAppointment(@RequestBody Appointment appointment) {
		appointmentService.updateAppointment(appointment);
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
	    public ResponseEntity<List<Doctor>> searchDoctorsForVeiwAppointments(
	    		@RequestParam(required = false) Long specId,
	    		@RequestParam(required = false) Long doctorId
	    		)
	    {
		 List<Doctor> doctors;
	        if (specId != null && doctorId != null) {
	            doctors = docService.findDoctorBySpecIdAndDoctorId(specId, doctorId);
	        }
	        else if(specId != null) {
	        	doctors=docService.findDoctorBySpecId(specId);
	        }
	        else if (doctorId != null) {
            doctors = docService.findDoctorById(doctorId);
             } else {
            doctors = docService.getAllDoctor();
            }
	        return ResponseEntity.ok(doctors);
	    }
	
	 //View appointment slots of specific doctor
	 @GetMapping("/view/slots/{doctorId}")
	 public ResponseEntity<Map<String,Object>> viewSlots(@PathVariable Long doctorId){
		 
		 Map<String,Object> response=new HashMap<>();
		 List<AppointmentResponse> appRespList = appointmentService.getAppointmentByDoctor(doctorId);
		 String message="Showing result for "+docService.getOneDoctor(doctorId);
		 response.put("appointment", appRespList);
		 response.put("message", message);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		 
	 }


	// Get appointments by Doctor Email
	@GetMapping("/doctor/email/{email}")
	public ResponseEntity<List<Object[]>> getAppointmentsByDoctorEmail(@PathVariable String email) {
		List<Object[]> list = appointmentService.getAppoinmentsByDoctorEmail(email);
		return new ResponseEntity<>(list, HttpStatus.OK);
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
