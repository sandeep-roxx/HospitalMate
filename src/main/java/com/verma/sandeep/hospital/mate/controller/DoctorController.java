package com.verma.sandeep.hospital.mate.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.verma.sandeep.hospital.mate.dto.DoctorRequestDTO;
import com.verma.sandeep.hospital.mate.dto.DoctorResponseDTO;
import com.verma.sandeep.hospital.mate.exception.DoctorNotFoundException;
import com.verma.sandeep.hospital.mate.service.impl.DoctorService;


@RestController
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService docService;
	
	@PostMapping("/register")
    public ResponseEntity<String> registerDoctor(
    		@RequestPart("doctorRequestDTO") DoctorRequestDTO doctorRequestDTO,
            @RequestPart("file") MultipartFile file
    		 ) throws IOException
	{
		
		ResponseEntity<String> response=null;
		try {
		     Long id = docService.saveDoctor(doctorRequestDTO,file);
		     response=new ResponseEntity<String>("Doctor " + id + " registered successfully!", HttpStatus.CREATED);
		  }catch (IOException ioe) {
			  throw ioe;
		  }catch (Exception e) {
			  throw e;
		  }
		return  response;
    }
	
	 @GetMapping("/all")
	    public ResponseEntity<List<DoctorResponseDTO>> getAllDoctors() {
		 
		 try {
			 List<DoctorResponseDTO> responseDTOs=docService.getAllDoctor();
		      return ResponseEntity.ok(responseDTOs);
		} catch (Exception e) {
			throw e;
		}
		 
	    }
	 
	 @GetMapping("find/{id}")
	    public ResponseEntity<DoctorResponseDTO> getDoctorById(@PathVariable Long id) {
		 
		 ResponseEntity<DoctorResponseDTO> response=null;
		 try {
			 DoctorResponseDTO responseDTO=docService.getOneDoctor(id);
			 response=ResponseEntity.ok(responseDTO);
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
	 
	 @PutMapping("/update/{id}")
	    public ResponseEntity<String> updateDoctor(
	    		@PathVariable Long id,
	    		@RequestBody DoctorRequestDTO doctorRequestDTO
	    		) 
	 {
		   ResponseEntity<String> response=null;
	        try {
	        	docService.updateDoctor(id,doctorRequestDTO);
	        	response=ResponseEntity.ok("Doctor updated successfully with ID: "+ id);
	        }catch (DoctorNotFoundException doce) {
				throw doce;
			}
	        return response;
	    }
	


}
