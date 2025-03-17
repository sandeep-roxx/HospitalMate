package com.verma.sandeep.hospital.mate.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WardDTO {
	
	private Long wid;
    private String wardName;
    private String type;
    private int capacity;
    private Integer floorNumber;
    private String description;
    
    private Long doctorId;
    private List<Long> patientIds;

}
