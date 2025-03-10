package com.verma.sandeep.hospital.mate.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.verma.sandeep.hospital.mate.entity.Doctor;
import com.verma.sandeep.hospital.mate.exception.DoctorNotFoundException;
import com.verma.sandeep.hospital.mate.service.IDoctorService;
import com.verma.sandeep.hospital.mate.service.ISpecializationMgmtService;
import com.verma.sandeep.hospital.mate.service.S3FileService;


@RestController
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private IDoctorService docService;
	@Autowired
	private ISpecializationMgmtService specService;
	@Autowired
	private S3FileService s3FileService;
	
	@PostMapping("/register")
    public ResponseEntity<String> registerDoctor(
    		@RequestPart("doctor") Doctor doctor,
            @RequestPart("file") MultipartFile file,
    		 Authentication authentication) 
	{
		//Get logged-in username
		String username=authentication.getName();
		doctor.setCreatedBy(username);
		doctor.setUpdatedBy(username);
		try {
			
			// Upload file to S3 and get the URL
			 String fileUrl = s3FileService.uploadFile(file, "doctor-photos");
			 doctor.setFileUrl(fileUrl);
			 //save doctor details
		     Long id = docService.saveDoctor(doctor);
		     return new ResponseEntity<>("Doctor " + id + " registered successfully!", HttpStatus.CREATED);
		     
		  }catch (IOException ioe) {
			 return ResponseEntity.status(500).body("File upload failed: " + ioe.getMessage()); 
		  }
    }
	
	 @GetMapping("/all")
	    public ResponseEntity<List<Doctor>> getAllDoctors() {
	       return ResponseEntity.ok(docService.getAllDoctor());
	    }
	 
	 @GetMapping("find/{id}")
	    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
		 
		 ResponseEntity<Doctor> response=null;
		 try {
			 response=ResponseEntity.ok(docService.getOneDoctor(id));
		 }catch (DoctorNotFoundException doce) {
			throw doce;
		}
	        return  response;
	    }
	 
	 @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
		 
		   ResponseEntity<String> response=null;
		   try {
			   docService.removeDoctor(id);
			   response=ResponseEntity.ok("Doctor with ID: " + id + " deleted successfully.");
		   }catch (DoctorNotFoundException doce) {
			throw doce;
		}
	        return response;
	    }
	 
	 @PutMapping("/update")
	    public ResponseEntity<String> updateDoctor(@RequestBody Doctor doctor,
	    		                                                                                  Authentication authentication
	    		                                                                               ) 
	 {
		  //Get current username
		   String username=authentication.getName();
		   ResponseEntity<String> response=null;
		   
	        try {
	        	
	        	//Fetch the existing record
	        	Doctor existingDoctor=docService.getOneDoctor(doctor.getId());
	        	// Preserve the 'createdBy' field from the existing record
	        	doctor.setCreatedBy(existingDoctor.getCreatedBy());
	        	// Set the 'updatedBy' field to the current user's username
	        	doctor.setUpdatedBy(username);
	        	docService.updateDoctor(doctor);
	        	response=ResponseEntity.ok("Doctor updated successfully with ID: " + doctor.getId());
	        }catch (DoctorNotFoundException doce) {
				throw doce;
			}
	        return response;
	    }
	
	@GetMapping("/dropdown")
    public ResponseEntity<Map<Long, String>> getSpecializationDropdown() {
		Map<Long,String> specMap=specService.getSpecIdAndName();
		return new ResponseEntity<Map<Long,String>>(specMap,HttpStatus.OK);
		
	}

}
