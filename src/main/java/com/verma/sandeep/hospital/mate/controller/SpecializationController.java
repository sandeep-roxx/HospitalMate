package com.verma.sandeep.hospital.mate.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RestController;

import com.verma.sandeep.hospital.mate.dto.SpecializationRequestDTO;
import com.verma.sandeep.hospital.mate.dto.SpecializationResponseDTO;
import com.verma.sandeep.hospital.mate.entity.Specialization;
import com.verma.sandeep.hospital.mate.exception.SpecializationNotFoundException;
import com.verma.sandeep.hospital.mate.service.impl.ISpecializationMgmtService;
import com.verma.sandeep.hospital.mate.service.impl.SpecializationExcelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/spec")
public class SpecializationController {
	
	@Autowired
	private ISpecializationMgmtService specService;
	
	@Autowired
    private SpecializationExcelService excelService;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerSpecialization(
			@RequestBody SpecializationRequestDTO  specializationRequestDTO
			    )
	{
		try {
			Long id=specService.saveSpecialization(specializationRequestDTO);
			return new ResponseEntity<String>("Specialization "+id+" saved", HttpStatus.CREATED);
		}catch (Exception e) {
			throw e;
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<SpecializationResponseDTO>> findAllSpecializations(){
		
		try {
			List<SpecializationResponseDTO> specList=specService.getAllSpecializations();
			return new ResponseEntity<List<SpecializationResponseDTO>>(specList, HttpStatus.OK);
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<SpecializationResponseDTO> findOneSpecialization(@PathVariable Long id){
		
		try {
			SpecializationResponseDTO specializationResponseDTO=specService.getOneSpecialization(id);
			return new ResponseEntity<SpecializationResponseDTO>(specializationResponseDTO, HttpStatus.OK);	
			
		}catch (SpecializationNotFoundException specex) {
			throw specex;
		}
		
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> RemoveOneSpecialization(@PathVariable Long id){
		
		ResponseEntity<String> response=null;	
		try {
			specService.removeSpecialization(id);
			response=new ResponseEntity<String>("Specialization removed!", HttpStatus.OK);	
			
		}catch (SpecializationNotFoundException specex) {
			throw specex;
		}
		return response;
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateSpecialization(
			@PathVariable Long id,
			@RequestBody SpecializationRequestDTO specializationRequestDTO
		)
	{
		ResponseEntity<String> response=null;
		
		try {
			
			specService.updateSpecialization(id,specializationRequestDTO);
			response=new ResponseEntity<String>("Specialization updated!", HttpStatus.OK);	
		}catch (SpecializationNotFoundException spece) {
			throw spece;
		}
		return response;
		
	}

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportSpecializationsToExcel() {
    	
    	try {
    		
    		List<SpecializationResponseDTO> specializationResponseDTOs=specService.getAllSpecializations();
    		byte[] excelData = excelService.generateExcel(specializationResponseDTOs);
    		return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=specializations.xlsx")
                    .header(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                    .body(excelData);
    		
		} catch (IOException ioe) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
    }
	

}
