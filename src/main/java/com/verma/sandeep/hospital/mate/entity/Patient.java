package com.verma.sandeep.hospital.mate.entity;

import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;


@Entity
@Table(name = "patient_tab")
@Data
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_col")
	private Long id;
	
	@Column(name = "pat_first_name_col")
	private String firstName;
	
	@Column(name = "pat_last_name_col")
	private String lastName;
	
	@Column(name = "pat_gender_col")
	private String gender;
	
	@Column(name = "pat_phone_col")
	private String mobile;
	
	@Column(name = "pat_mail_col")
	private String mail;
	
	@Column(name = "pat_dob_col")
	@DateTimeFormat(iso = ISO.DATE)
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@Column(name = "pat_ms_col")
	private String meritalStatus;
	
	@Column(name = "pat_present_addrs_col")
	private String presentAddrs;
	
	@Column(name = "pat_comm_addrs_col")
	private String commAddrs;
	
	@ElementCollection
	@CollectionTable(
			                           name = "pat_medi_hist_tab",
			                           joinColumns = @JoinColumn(name="pat_medi_hist_id_fk_col"))
	@Column(name="pat_medi_hist_col")
	private Set<String> mediHistory;
	
	@Column(name = "pat_other_col")
	private String ifOther;
	
	@Column(name = "pat_other_details_col")
	private String otherDetails;


}
