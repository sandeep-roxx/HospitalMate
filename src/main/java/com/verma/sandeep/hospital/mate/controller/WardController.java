package com.verma.sandeep.hospital.mate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.verma.sandeep.hospital.mate.dto.DoctorDTO;
import com.verma.sandeep.hospital.mate.entity.Patient;
import com.verma.sandeep.hospital.mate.entity.Ward;
import com.verma.sandeep.hospital.mate.iservice.WardService;
import com.verma.sandeep.hospital.mate.service.impl.IDoctorService;
import com.verma.sandeep.hospital.mate.service.impl.IPatientService;

@RestController
@RequestMapping("/ward")
public class WardController {
	
	@Autowired
    private IPatientService patientService;
	
	@Autowired
    private WardService wardService;
	
	@Autowired
	private IDoctorService doctorService;

    @PostMapping("/allocate")
    public ResponseEntity<String> allocateWard(@RequestBody Ward ward) {
        Long wardId = wardService.allocateWard(ward);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ward created "+wardId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ward>> getAllWards() {
        List<Ward> wards = wardService.getAllWards();
        return ResponseEntity.ok(wards);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Ward> getWardById(@PathVariable Long id) {
        Ward ward = wardService.getWardById(id);
        return ResponseEntity.ok(ward);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWard(@PathVariable Long id) {
        wardService.deleteWard(id);
        return ResponseEntity.ok("Ward deleted successfully");
    }
    
	//Search patient by email or mobile
	@GetMapping("/patient/search")
    public ResponseEntity<Patient> searchPatient(@RequestParam String query) {
        Patient patient = patientService.searchPatientByEmailOrMobile(query);
        return ResponseEntity.ok(patient);
    }
	
	// doctor drop-down
		@GetMapping("/doctor/dropdown")
	    public ResponseEntity<List<DoctorDTO>> searchDoctor() {
			List<DoctorDTO> docList = doctorService.getAllDoctorsDTO();
	        return ResponseEntity.ok(docList);
	    }

}
