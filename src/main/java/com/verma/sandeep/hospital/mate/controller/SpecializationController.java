package com.verma.sandeep.hospital.mate.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

import com.verma.sandeep.hospital.mate.entity.Specialization;
import com.verma.sandeep.hospital.mate.exception.SpecializationNotFoundException;
import com.verma.sandeep.hospital.mate.service.ISpecializationMgmtService;
import com.verma.sandeep.hospital.mate.service.SpecializationExcelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/spec")
public class SpecializationController {
	
	@Autowired
	private ISpecializationMgmtService specService;
	
	@Autowired
    private SpecializationExcelService excelService;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerSpecialization(@Valid  @RequestBody Specialization spec){
		Long id=specService.saveSpecialization(spec);
		return new ResponseEntity<String>("Specialization "+id+" saved", HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Specialization>> findAllSpecializations(){
		List<Specialization> specList=specService.getAllSpecializations();
		return new ResponseEntity<List<Specialization>>(specList, HttpStatus.OK);		
		
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<Specialization> findOneSpecialization(@PathVariable Long id){
		
		ResponseEntity<Specialization> response=null;
		try {
			Specialization spec=specService.getOneSpecialization(id);
			response= new ResponseEntity<Specialization>(spec, HttpStatus.OK);	
			
		}catch (SpecializationNotFoundException specex) {
			throw specex;
		}
		return response;
		
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
	
	@PutMapping("/update")
	public ResponseEntity<String> updateSpecialization(@Valid @RequestBody Specialization spec){
		specService.updateSpecialization(spec);
		return new ResponseEntity<String>("Specialization updated!", HttpStatus.OK);	
		
	}

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportSpecializationsToExcel() {
    	
    	try {
    		
    		List<Specialization> specializations=specService.getAllSpecializations();
    		byte[] excelData = excelService.generateExcel(specializations);
    		return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=specializations.xlsx")
                    .header(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                    .body(excelData);
    		
		} catch (IOException ioe) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
    }
	

}
