package com.verma.sandeep.hospital.mate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecializationRequestDTO {
	
	private String specName;
    private String specCode;
    private String description;

}
