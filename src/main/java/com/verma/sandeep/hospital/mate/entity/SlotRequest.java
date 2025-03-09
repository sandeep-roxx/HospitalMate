package com.verma.sandeep.hospital.mate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="slot_request_tab",
uniqueConstraints = @UniqueConstraint(columnNames = {"appointment_id_fk_col", "patient_id_fk_col"})
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlotRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
    @JoinColumn(name = "appointment_id_fk_col", nullable = false)
    private Appointment appointment;

    @OneToOne
    @JoinColumn(name = "patient_id_fk_col", nullable = false)
    private Patient patient;

    @Column(name = "status", nullable = false)
    private String status;

}
