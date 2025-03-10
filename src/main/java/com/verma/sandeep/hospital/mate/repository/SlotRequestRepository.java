package com.verma.sandeep.hospital.mate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.verma.sandeep.hospital.mate.entity.SlotRequest;

@Repository
public interface SlotRequestRepository extends JpaRepository<SlotRequest, Long> {
	
	@Modifying
    @Query("UPDATE SlotRequest sr SET sr.status = :status WHERE sr.id = :id")
    public void updateSlotRequestStatus(@Param("id") Long id, @Param("status") String status);
	
	@Query("SELECT sr FROM SlotRequest sr INNER JOIN sr.patient p WHERE p.email = :email")
    public List<SlotRequest> findSlotRequestsByPatientEmail(@Param("email") String email);
	
	@Query("SELECT sr FROM SlotRequest sr INNER JOIN sr.appointment a INNER JOIN a.doctor d WHERE d.email = :email AND sr.status=:status")
	public List<SlotRequest> findAllBookedSlotsByDoctor(@Param("email") String email,@Param("status") String status);

}
