package com.verma.sandeep.hospital.mate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="spec_tab")
@Data
public class Specialization {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="spec_id_col")
	private Long id;
	@Column(name="spec_name_col")
	private String name;
	@Column(name="spec_code_col")
	private String specCode;
	@Column(name="spec_des_col")
	private String description;

}
