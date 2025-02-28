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

import com.verma.sandeep.hospital.mate.entity.Specialization;
import com.verma.sandeep.hospital.mate.service.ISpecializationMgmtService;

@RestController
@RequestMapping("/spec")
public class SpecializationController {
	
	@Autowired
	private ISpecializationMgmtService specService;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveSpecialization(@RequestBody Specialization spec){
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
		Specialization spec=specService.getOneSpecialization(id);
		return new ResponseEntity<Specialization>(spec, HttpStatus.OK);	
		
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> RemoveOneSpecialization(@PathVariable Long id){
		specService.removeSpecialization(id);
		return new ResponseEntity<String>("Specialization removed!", HttpStatus.OK);		
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateSpecialization(@RequestBody Specialization spec){
		specService.updateSpecialization(spec);
		return new ResponseEntity<String>("Specialization updated!", HttpStatus.OK);	
		
	}
	

}
