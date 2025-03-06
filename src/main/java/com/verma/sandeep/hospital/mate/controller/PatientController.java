package com.verma.sandeep.hospital.mate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.verma.sandeep.hospital.mate.service.IPatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	private IPatientService patientService;
	
	@PostMapping("/register")
    public ResponseEntity<String> registerPatient(@Valid @RequestBody Patient patient) {
        Long id = patientService.savePatient(patient);
        return new ResponseEntity<>("Patient " + id + " saved", HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        try {
            Patient patient = patientService.getOnePatient(id);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (PatientsNotFoundException e) {
            throw e;
        }
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<Patient> getPatientByEmail(@RequestParam String email) {
        try {
            Patient patient = patientService.getOnePatient(email);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (PatientsNotFoundException e) {
            throw e;
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        try {
            patientService.remove(id);
            return new ResponseEntity<>("Patient removed successfully!", HttpStatus.OK);
        } catch (PatientsNotFoundException e) {
            throw e;
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePatient(@Valid @RequestBody Patient patient) {
        try {
            patientService.updatePatient(patient);
            return new ResponseEntity<>("Patient updated successfully!", HttpStatus.OK);
        } catch (PatientsNotFoundException e) {
            throw e;
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getPatientCount() {
        Long count = patientService.getPatientCount();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

}
