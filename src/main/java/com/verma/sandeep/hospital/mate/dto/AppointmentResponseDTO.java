package com.verma.sandeep.hospital.mate.dto;

import java.util.Date;

import com.verma.sandeep.hospital.mate.entity.Doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponseDTO {
	
	private Long id;
	private Doctor doctor;
	private Date date;
	private Integer noOfSlots;
	private String note;
	private Double amt;

}
