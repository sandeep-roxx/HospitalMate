package com.verma.sandeep.hospital.mate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.verma.sandeep.hospital.mate.constant.OperationStatus;
import com.verma.sandeep.hospital.mate.dto.OperationDTO;
import com.verma.sandeep.hospital.mate.iservice.OperationService;

@RestController
@RequestMapping("/operation")
public class OperationController {
	
	@Autowired
	private OperationService operationService; 
	
	// Create a new operation
    @PostMapping
    public ResponseEntity<Long> createOperation(@RequestBody OperationDTO operationDTO) {
    	//Set the operation status
    	operationDTO.setStatus(OperationStatus.Scheduled.name());
        Long operationId = operationService.createOperation(operationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(operationId);
    }
    
 // Get all operations
    @GetMapping
    public ResponseEntity<List<OperationDTO>> getAllOperations() {
        List<OperationDTO> operations = operationService.getAllOperations();
        return ResponseEntity.ok(operations);
    }

    // Get operation by ID
    @GetMapping("/{id}")
    public ResponseEntity<OperationDTO> getOperationById(@PathVariable Long id) {
        OperationDTO operationDTO = operationService.getOperationById(id);
        return ResponseEntity.ok(operationDTO);
    }

    // Update operation details
    @PutMapping("/{id}")
    public ResponseEntity<String> updateOperation(@RequestBody OperationDTO operationDTO) {
        operationService.updateOperation(operationDTO);
        return ResponseEntity.ok("Operation updated successfully.");
    }

    // Delete operation
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOperation(@PathVariable Long id) {
        operationService.deleteOperation(id);
        return ResponseEntity.ok("Operation deleted successfully.");
    }

	
	// Update operation status
    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateOperationStatus(@PathVariable Long id, @RequestParam String status) {
        operationService.updateOperationStatus(id, status);
        return ResponseEntity.ok("Operation status updated successfully.");
    }

}
