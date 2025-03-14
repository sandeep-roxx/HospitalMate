package com.verma.sandeep.hospital.mate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.verma.sandeep.hospital.mate.entity.Patient;
import com.verma.sandeep.hospital.mate.exception.PatientsNotFoundException;
import com.verma.sandeep.hospital.mate.service.impl.IPatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	private IPatientService patientService;
	
	@PostMapping("/register")
    public ResponseEntity<String> registerPatient(@Valid @RequestBody Patient patient) {
		ResponseEntity<String> response=null;
		try {
			 Long id = patientService.savePatient(patient);
		     response=new ResponseEntity<String>("Patient " + id + " saved", HttpStatus.CREATED);
		}catch (Exception e) {
			throw e;
		}
		return response;
    }
     /*
    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
    */
	
	@GetMapping("/all")
    public ResponseEntity<Page<Patient> > getAllPatients(
    		 @RequestParam(defaultValue = "0") int page,  //page number.
             @RequestParam(defaultValue = "5") int size  //page size
    		) 
	{
		Pageable pageable = PageRequest.of(page, size);
        Page<Patient> patients = patientService.getAllPatients(pageable);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
    	
    	ResponseEntity<Patient> response=null;
        try {
            Patient patient = patientService.getOnePatient(id);
            response= new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (PatientsNotFoundException e) {
            throw e;
        }
        return response;
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<Patient> getPatientByEmail(@RequestParam String email) {
    	
    	ResponseEntity<Patient> response=null;
        try {
            Patient patient = patientService.getOnePatient(email);
            response=new ResponseEntity<Patient>(patient, HttpStatus.OK);
        } catch (PatientsNotFoundException e) {
            throw e;
        }
        return response;
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
    	
    	ResponseEntity<String> response=null;
        try {
            patientService.remove(id);
            response=new ResponseEntity<String>("Patient removed successfully!", HttpStatus.OK);
        } catch (PatientsNotFoundException e) {
            throw e;
        }
        return response;
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePatient(@Valid @RequestBody Patient patient) {
    	
    	ResponseEntity<String> response=null;
        try {
            patientService.updatePatient(patient);
            response=new ResponseEntity<String>("Patient updated successfully!", HttpStatus.OK);
        } catch (PatientsNotFoundException e) {
            throw e;
        }
        return response;
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getPatientCount() {
        Long count = patientService.getPatientCount();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

}
