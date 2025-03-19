package com.verma.sandeep.hospital.mate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequestDTO {
	
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String mobile;
    private String gender;
    private String note;
    private Long specializationId;

}
