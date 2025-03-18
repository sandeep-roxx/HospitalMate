package com.verma.sandeep.hospital.mate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.verma.sandeep.hospital.mate.entity.Medicos;

@Repository
public interface MedicosRepository extends JpaRepository<Medicos, Long> {
	
	//Fetching the prescriptions of a patient.
	@Query("SELECT m FROM Medicos m WHERE m.patient.id=:patientId")
	List<Medicos> findByPatientId(@Param("patientId") Long patientId);

}
