package com.verma.sandeep.hospital.mate.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponseDTO {
	
	private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String mobile;
    private String gender;
    private String note;
    private String fileUrl; 
    private Long specializationId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
