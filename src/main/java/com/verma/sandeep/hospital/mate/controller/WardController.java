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
import org.springframework.web.bind.annotation.RestController;

import com.verma.sandeep.hospital.mate.constant.WardStatus;
import com.verma.sandeep.hospital.mate.dto.DoctorDTO;
import com.verma.sandeep.hospital.mate.dto.PatientDTO;
import com.verma.sandeep.hospital.mate.dto.WardDTO;
import com.verma.sandeep.hospital.mate.exception.WardNotFoundException;
import com.verma.sandeep.hospital.mate.iservice.WardService;
import com.verma.sandeep.hospital.mate.service.impl.DoctorService;
import com.verma.sandeep.hospital.mate.service.impl.SlotRequestService;

@RestController
@RequestMapping("/ward")
public class WardController {
	
	@Autowired
    private SlotRequestService slotRequestService;
	
	@Autowired
    private WardService wardService;
	
	@Autowired
	private DoctorService doctorService;

    @PostMapping("/allocate")
    public ResponseEntity<String> allocateWard(@RequestBody WardDTO wardDTO) {
    	//Set available beds and status
    	wardDTO.setAvailableBeds(wardDTO.getCapacity()-1);
    	wardDTO.setStatus(WardStatus.AVAILABLE.name());
    	
      try {
    	  Long wardId = wardService.allocateWard(wardDTO);
          return ResponseEntity.status(HttpStatus.CREATED).body("Ward created "+wardId);
	} catch (Exception e) {
		throw e;
	}
    }
    
    //Add more patient in same ward
    @PutMapping("/addPatients/{wardId}")
    public ResponseEntity<String> addPatientsToWard(
    		@PathVariable Long wardId,
    		@RequestBody List<Long> patientIds) 
    {
        try {
        	wardService.addPatientsToWard(wardId, patientIds);
            return ResponseEntity.ok("Patients added successfully to ward " + wardId);
        }catch (WardNotFoundException e) {
			throw e;
		}
    }

    @GetMapping("/all")
    public ResponseEntity<List<WardDTO>> getAllWards() {
        List<WardDTO> wardDTOs = wardService.getAllWards();
        return ResponseEntity.ok(wardDTOs);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity< WardDTO> getWardById(@PathVariable Long id) {
       try {
    	   WardDTO wardDTO = wardService.getWardById(id);
           return ResponseEntity.ok(wardDTO);
		
	} catch (WardNotFoundException e) {
		throw e;
	}
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> updateWard(@RequestBody WardDTO wardDTO) {
       try {
    	   wardService.updateOneWard(wardDTO);
           return ResponseEntity.ok("Ward updated successfully");
	} catch (WardNotFoundException e) {
		throw e;
	}
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWard(@PathVariable Long id) {
        try {
        	wardService.deleteWard(id);
            return ResponseEntity.ok("Ward deleted successfully");
		} catch (WardNotFoundException e) {
			throw e;
		}
		}
    
	//Checkbox List (Each patient has a checkbox)
	@GetMapping("/patient/all")
    public ResponseEntity<List<PatientDTO>> searchPatient() {
        List<PatientDTO> patients = slotRequestService.fetchPaidPatients();
        return ResponseEntity.ok(patients);
    }
	
	// doctor drop-down
		@GetMapping("/doctor/dropdown")
	    public ResponseEntity<List<DoctorDTO>> searchDoctor() {
			List<DoctorDTO> docList = doctorService.getAllDoctorsDTO();
	        return ResponseEntity.ok(docList);
	    }

}
