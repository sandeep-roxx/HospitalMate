package com.verma.sandeep.hospital.mate.controller;

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

import com.verma.sandeep.hospital.mate.dto.PatientRequestDTO;
import com.verma.sandeep.hospital.mate.dto.PatientResponseDTO;
import com.verma.sandeep.hospital.mate.exception.PatientsNotFoundException;
import com.verma.sandeep.hospital.mate.service.impl.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@PostMapping("/register")
    public ResponseEntity<String> registerPatient(@RequestBody PatientRequestDTO patientRequestDTO) {
		ResponseEntity<String> response=null;
		try {
			 Long id = patientService.savePatient(patientRequestDTO);
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
    public ResponseEntity<Page<PatientResponseDTO> > getAllPatients(
    		 @RequestParam(defaultValue = "0") int page,  //page number.
             @RequestParam(defaultValue = "5") int size  //page size
    		) 
	{
		Pageable pageable = PageRequest.of(page, size);
        Page<PatientResponseDTO> patientDTOs = patientService.getAllPatients(pageable);
        return new ResponseEntity<>(patientDTOs, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable Long id) {
    	
    	ResponseEntity<PatientResponseDTO> response=null;
        try {
            PatientResponseDTO patientDTOs = patientService.getOnePatient(id);
            response= new ResponseEntity<PatientResponseDTO>(patientDTOs, HttpStatus.OK);
        } catch (PatientsNotFoundException e) {
            throw e;
        }
        return response;
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<PatientResponseDTO> getPatientByEmail(@RequestParam String email) {
    	
    	ResponseEntity<PatientResponseDTO> response=null;
        try {
            PatientResponseDTO patientDTOs = patientService.getOnePatient(email);
            response=new ResponseEntity<PatientResponseDTO>(patientDTOs, HttpStatus.OK);
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

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable Long id,
    		                                                                                 @RequestBody PatientRequestDTO patientRequestDTO) 
    {
    	
    	ResponseEntity<String> response=null;
        try {
            patientService.updatePatient(id,patientRequestDTO);
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
