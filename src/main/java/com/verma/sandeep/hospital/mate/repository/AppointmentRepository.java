package com.verma.sandeep.hospital.mate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.verma.sandeep.hospital.mate.bind.AppointmentResponse;
import com.verma.sandeep.hospital.mate.entity.Appointment;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	
	@Query("SELECT new com.verma.sandeep.hospital.mate.bind.AppointmentResponse(aptm.id,aptm.date, aptm.noOfSlots, aptm.amt,aptm.note) FROM Appointment aptm INNER JOIN aptm.doctor as doctor WHERE doctor.id=:docId")
	public List<AppointmentResponse> getAppointmentByDoctor(Long docId);
	
	@Query("SELECT new com.verma.sandeep.hospital.mate.bind.AppointmentResponse(aptm.id,aptm.date, aptm.noOfSlots, aptm.amt,aptm.note) FROM Appointment aptm INNER JOIN aptm.doctor as doctor WHERE doctor.email=:email")
	public List<AppointmentResponse> getAppoinmentsByDoctorEmail(String email);

}
