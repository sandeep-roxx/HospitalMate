package com.verma.sandeep.hospital.mate.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name="spec_tab")
@Data
public class Specialization {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="spec_id_col")
	private Long id;
	
	@NotBlank(message = "Specialization name is required")
    @Size(min = 3, max = 50, message = "Specialization name must be between 3 and 50 characters")
	@Column(name="spec_name_col")
	private String name;
	
	@NotBlank(message = "Specialization code is required")
    @Size(min = 2, max = 10, message = "Specialization code must be between 2 and 10 characters")
	@Column(name="spec_code_col")
	private String specCode;
	
	@Size(max = 255, message = "Description should not exceed 255 characters")
	@Column(name="spec_des_col")
	private String description;
	
	@Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
