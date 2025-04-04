package com.verma.sandeep.hospital.mate.dto;

import java.time.LocalDateTime;

import com.verma.sandeep.hospital.mate.constant.OperationStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationDTO {
	
	private Long oid;
    private String oName;
    private Long patientId;
    private Long doctorId; 
    private LocalDateTime operationDateTime;
    private OperationStatus status; 
    private String description;
    private int operationTheaterRoom;
    private String notes;
	
    
    

}
