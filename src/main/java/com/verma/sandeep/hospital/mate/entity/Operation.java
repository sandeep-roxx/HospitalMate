package com.verma.sandeep.hospital.mate.entity;

import java.time.LocalDateTime;

import com.verma.sandeep.hospital.mate.constant.OperationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="operation_tab")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "op_id")
    private Long oid;

    @Column(name = "op_name", nullable = false)
    private String oName;

    @OneToOne
    @JoinColumn(name = "pat_id", nullable = false)
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "doc_id", nullable = false)
    private Doctor doctor;

    @Column(name = "op_date_time", nullable = false)
    private LocalDateTime operationDateTime;


    @Enumerated(EnumType.STRING) 
    @Column(name = "op_status")
    private OperationStatus status; 

    @Column(name = "op_description", length = 500)
    private String description;

    @Column(name = "ot_room_number")
    private int operationTheaterRoom;


    @Column(name = "note", length = 1000)
    private String notes;

}
