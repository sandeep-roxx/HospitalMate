package com.verma.sandeep.hospital.mate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.verma.sandeep.hospital.mate.bind.AppointmentResponse;
import com.verma.sandeep.hospital.mate.entity.Appointment;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	
	//@Query("SELECT new com.verma.sandeep.hospital.mate.bind.AppointmentResponse(aptm.id,aptm.date, aptm.noOfSlots, aptm.amt,aptm.note) FROM Appointment aptm INNER JOIN aptm.doctor as doctor WHERE doctor.id=:docId")
	//public List<AppointmentResponse> getAppointmentByDoctor(Long docId);
	
	@Query("SELECT new com.verma.sandeep.hospital.mate.bind.AppointmentResponse(aptm.id, aptm.date, aptm.noOfSlots, aptm.amt, aptm.note) " +
		       "FROM Appointment aptm INNER JOIN aptm.doctor doctor " +
		       "WHERE doctor.id = :docId AND aptm.noOfSlots > 0  AND aptm.date > CURRENT_DATE")
	public List<AppointmentResponse> getAppointmentByDoctor(@Param("docId")Long docId);
	
	@Query("SELECT new com.verma.sandeep.hospital.mate.bind.AppointmentResponse(aptm.id,aptm.date, aptm.noOfSlots, aptm.amt,aptm.note)"
			+ " FROM Appointment aptm INNER JOIN aptm.doctor as doctor WHERE doctor.email=:email")
	public List<AppointmentResponse> getAppoinmentsByDoctorEmail(@Param("email")String email);
	
	
	@Modifying
	@Query("UPDATE Appointment a SET a.noOfSlots =a.noOfSlots + :count WHERE a.id = :apmtId")
	public void updateSlotCountForAppointment(@Param("apmtId") Long apmtId,@Param("count") int count);

}
