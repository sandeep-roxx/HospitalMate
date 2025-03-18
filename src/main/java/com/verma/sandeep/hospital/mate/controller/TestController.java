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

import com.verma.sandeep.hospital.mate.dto.DoctorDTO;
import com.verma.sandeep.hospital.mate.dto.PatientDTO;
import com.verma.sandeep.hospital.mate.entity.Test;
import com.verma.sandeep.hospital.mate.exception.TestNotFoundException;
import com.verma.sandeep.hospital.mate.iservice.TestService;
import com.verma.sandeep.hospital.mate.service.impl.DoctorService;
import com.verma.sandeep.hospital.mate.service.impl.SlotRequestService;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private TestService testService;
	@Autowired
	 private SlotRequestService slotRequestService;
	@Autowired
	private DoctorService doctorService;
	
	 @PostMapping("/add")
	    public ResponseEntity<?> addTest(@RequestBody Test test) {
	        try {
	            Long testId = testService.addTest(test);
	            return ResponseEntity.status(HttpStatus.CREATED).body(testId);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("Error adding test: " + e.getMessage());
	        }
	    }
	   
		@GetMapping("/all")
		public ResponseEntity<?> getAllTests() {
			try {
				List<Test> tests = testService.getAllTests();
				return ResponseEntity.ok(tests);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Error fetching tests: " + e.getMessage());
			}
		}
		
		@GetMapping("/find/{id}")
		public ResponseEntity<?> getTestById(@PathVariable Long id) {
			try {
				Test test = testService.getTestById(id);
				return ResponseEntity.ok(test);
			} catch (TestNotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Error fetching test: " + e.getMessage());
			}
		}
		
		@GetMapping("/patient/{patientId}")
		public ResponseEntity<?> getTestsByPatient(@PathVariable Long patientId) {
			try {
				List<Test> tests = testService.fetchByPatientId(patientId);
				return ResponseEntity.ok(tests);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Error fetching tests for patient: " + e.getMessage());
			}
		}
		
		@PutMapping("/update")
		public ResponseEntity<?> updateTest(@RequestBody Test test) {
			try {
				testService.updateOneTest(test);
				return ResponseEntity.ok("Test updated successfully");
			} catch (TestNotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Error updating test: " + e.getMessage());
			}
		}
		
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<?> deleteTest(@PathVariable Long id) {
			try {
				testService.deleteTest(id);
				return ResponseEntity.ok("Test deleted successfully");
			} catch (TestNotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Error deleting test: " + e.getMessage());
			}
		}
	   
	   //Fetch all paid patients 
	   @GetMapping("/paid")
	   public ResponseEntity<List<PatientDTO>> getPaidPatients(){
		   return ResponseEntity.ok(slotRequestService.fetchPaidPatients());
	   }
	   
	      // doctor drop-down
			@GetMapping("/doctor/dropdown")
		    public ResponseEntity<List<DoctorDTO>> searchDoctor() {
				List<DoctorDTO> docList = doctorService.getAllDoctorsDTO();
		        return ResponseEntity.ok(docList);
		    }
		   

}
