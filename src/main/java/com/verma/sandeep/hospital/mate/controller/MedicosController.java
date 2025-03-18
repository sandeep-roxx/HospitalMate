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

import com.verma.sandeep.hospital.mate.dto.MedicosDTO;
import com.verma.sandeep.hospital.mate.exception.MedicosNotFoundException;
import com.verma.sandeep.hospital.mate.iservice.MedicosService;

@RestController
@RequestMapping("/medicos")
public class MedicosController {

    @Autowired
    private MedicosService medicosService;

    @PostMapping("/save")
    public ResponseEntity<String> saveMedicos(@RequestBody MedicosDTO medicosDTO) {
        try {
            Long id = medicosService.saveMedicos(medicosDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Medicos saved with ID: " + id);
        } catch (Exception ex) {
          throw ex;
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllMedicos() {
        try {
            List<MedicosDTO> list = medicosService.getAllMedicos();
            return ResponseEntity.ok(list);
        } catch (Exception ex) {
        	 throw ex;
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getMedicosById(@PathVariable Long id) {
        try {
            MedicosDTO dto = medicosService.getMedicosById(id);
            return ResponseEntity.ok(dto);
        } catch (MedicosNotFoundException ex) {
        	 throw ex;
        } catch (Exception ex) {
        	 throw ex;
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateMedicos(@RequestBody MedicosDTO medicosDTO) {
        try {
            medicosService.updateOneMedicos(medicosDTO);
            return ResponseEntity.ok("Medicos updated successfully");
        } catch (MedicosNotFoundException ex) {
        	 throw ex;
        } catch (Exception ex) {
        	 throw ex;
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMedicos(@PathVariable Long id) {
        try {
            medicosService.deleteMedicos(id);
            return ResponseEntity.ok("Medicos deleted successfully");
        } catch (MedicosNotFoundException ex) {
        	 throw ex;
        } catch (Exception ex) {
        	 throw ex;
        }
    }
    
    //Get the prescriptions of a patient
    @GetMapping("/patient/meds/{id}")
    public ResponseEntity<List<MedicosDTO>> getPrescriptionsByPatientId(@PathVariable Long id){
    	
    	try {
    		List<MedicosDTO> medicosDTOs=medicosService.getByPatientId(id);
       	    return ResponseEntity.ok(medicosDTOs);
			
		} catch (Exception e) {
			throw e;
		}
    }
    
    //Get cost of prescribed medicines
    @GetMapping("/patient/cost/{id}")
    public ResponseEntity<Long> getTotalCostByPatientId(@PathVariable Long id){
    	
    	try {
    		
    		Long cost=medicosService.getTotalCost(id);
        	return ResponseEntity.ok(cost);
			
		} catch (Exception e) {
			throw e;
		}
    }
}

