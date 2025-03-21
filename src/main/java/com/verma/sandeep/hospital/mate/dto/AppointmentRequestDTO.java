package com.verma.sandeep.hospital.mate.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequestDTO {
	
	private Long doctorId;
	private String date;
	private Integer noOfSlots;
	private String note;
	private Double amt;

}
