package com.verma.sandeep.hospital.mate.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MedicosDTO {
	
	private Long medId;
    private String medRecord;
    private Double medPrice;
    private int medQuantity;
    private Double medTotal; // Read-only in response
    private LocalDate medDate;
    private Long doctorId;
    private Long patientId;

}
