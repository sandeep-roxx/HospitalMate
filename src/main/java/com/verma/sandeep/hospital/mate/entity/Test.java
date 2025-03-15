package com.verma.sandeep.hospital.mate.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name="test_tab")
@Data
public class Test {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id_col")
    private Long tid;

    @Column(name = "test_name_col", nullable = false)
    private String tName;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "test_date_col", nullable = false)
    private Date tDate;

    @OneToOne
    @JoinColumn(name = "doc_id_fk_col", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id_fk_col", nullable = false)
    private Patient patient;

}
