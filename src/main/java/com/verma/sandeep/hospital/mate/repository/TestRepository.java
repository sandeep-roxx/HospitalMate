package com.verma.sandeep.hospital.mate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verma.sandeep.hospital.mate.entity.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
	
	 // Fetch tests by patient ID
    List<Test> findByPatientId(Long patientId);
    

}
