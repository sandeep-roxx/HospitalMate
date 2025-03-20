package com.verma.sandeep.hospital.mate.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequestDTO {
	
	private String firstName;
    private String lastName;
    private String gender;
    private String mobile;
    private String email;
    private String dateOfBirth;
    private String maritalStatus;
    private String presentAddrs;
    private String commAddrs;
    private Set<String> mediHistory;
    private String ifOther;
    private String otherDetails;

}
