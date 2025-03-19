package com.verma.sandeep.hospital.mate.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecializationResponseDTO {
	
	private Long id;
    private String specName;
    private String specCode;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
