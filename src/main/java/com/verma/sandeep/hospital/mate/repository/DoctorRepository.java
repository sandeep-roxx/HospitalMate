package com.verma.sandeep.hospital.mate.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.verma.sandeep.hospital.mate.dto.DoctorDTO;
import com.verma.sandeep.hospital.mate.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	
    Optional<Doctor> findByEmail(String email); // To find doctor by email
    
    @Query("SELECT id,firstName,lastName FROM Doctor")
	public List<Object[]> getDoctorIdAndNames();
	
	@Query("SELECT doc  FROM Doctor  doc INNER JOIN doc.specialization as spec WHERE spec.id=:specId")
	public List<Doctor> findDoctorBySpecId(Long specId);
	
	@Query("SELECT d FROM Doctor d JOIN d.specialization s WHERE s.id = :specId AND d.id = :doctorId")
    List<Doctor> findDoctorBySpecIdAndDoctorId(@Param("specId") Long specId, @Param("doctorId") Long doctorId);
	
	@Query("SELECT d FROM Doctor d WHERE d.id = :doctorId")
    List<Doctor> findDoctorById(@Param("doctorId") Long doctorId);
	
	 @Query("SELECT new com.verma.sandeep.hospital.mate.dto.DoctorDTO(d.id, d.firstName, d.lastName, d.specialization.name) FROM Doctor d")
	    List<DoctorDTO> getAllDoctorsDTO();
}

