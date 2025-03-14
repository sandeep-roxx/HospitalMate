package com.verma.sandeep.hospital.mate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="employee_tab")
@Data
public class Employee {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Emp_Id")
    private long empId;

    @Column(name = "Emp_Name", nullable = false)
    private String empName;

    @Column(name = "Emp_Mobile", nullable = false, unique = true)
    private long empMobileNo;

    @Column(name = "Emp_Address")
    private String empAdd;

    @OneToOne
    @JoinColumn(name = "DEP_ID", referencedColumnName = "DEP_ID")
    private Department department;


}
