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
@Table(name="ward_tab")
@Data
public class Ward {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ward_id_col")
    private long wid;

    @Column(name = "ward_name_col", nullable = false)
    private String wardName;

    @OneToOne
    @JoinColumn(name = "patient_id_col", unique = true, nullable = false)  
    private Patient patient;  // Only one patient per ward

    @OneToOne
    @JoinColumn(name = "doc_id_col", nullable = false)  
    private Doctor doctor;  // One doctor per ward


}
