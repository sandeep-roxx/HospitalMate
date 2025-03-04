package com.verma.sandeep.hospital.mate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verma.sandeep.hospital.mate.entity.Patient;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
