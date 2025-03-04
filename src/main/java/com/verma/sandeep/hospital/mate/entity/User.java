package com.verma.sandeep.hospital.mate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="user_tab")
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usr_id_col")
	private Long id;
	@Column(name = "usr_name_col")
	private String name;
	@Column(name = "usr_email_col")
	private String email;
	@Column(name = "usr_pswrd_col")
	private String password;
	@Column(name = "usr_role_col")
	private String role;

}
