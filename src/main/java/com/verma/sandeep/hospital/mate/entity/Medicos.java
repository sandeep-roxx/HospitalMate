package com.verma.sandeep.hospital.mate.entity;

import java.time.LocalDate;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medicos_tab")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "med_id")
    private Long medId;

    @Column(name = "med_record")
    private String medRecord;

    @Column(name = "med_price")
    private Double medPrice;

    @Column(name = "med_quantity")
    private int medQuantity;

    @Column(name = "med_total")
    private Double medTotal;

    @Column(name = "med_date")
    private LocalDate medDate;

    @ManyToOne
    @JoinColumn(name = "doc_id_fk")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "pat_id_fk")
    private Patient patient;

}

