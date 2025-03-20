package com.verma.sandeep.hospital.mate.dto;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.verma.sandeep.hospital.mate.entity.Medicos;
import com.verma.sandeep.hospital.mate.entity.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String mobile;
    private String email;
    private Date dateOfBirth;
    private String meritalStatus;
    private String presentAddrs;
    private String commAddrs;
    private Set<String> mediHistory;
    private String ifOther;
    private String otherDetails;

    private List<Medicos> prescriptions; // Prescriptions for the patient
    private List<Test> tests;  // Tests related to the patient
}
