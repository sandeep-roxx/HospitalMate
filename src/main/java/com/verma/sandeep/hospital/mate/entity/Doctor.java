package com.verma.sandeep.hospital.mate.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name="doctor_tab")
@Data
public class Doctor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doc_id_col")
	private Long id;
	
	@Column(name = "doc_fn_col")
	private String firstName;
	
	@Column(name = "doc_ln_col")
	private String lastName;
	
	@Column(name = "doc_email_col")
	private String email;
	
	@Column(name = "doc_addrs_col")
	private String address;
	
	@Column(name = "doc_mob_col")
	private String mobile;
	
	@Column(name = "doc_gen_col")
	private String gender;
	
	@Column(name = "doc_note_col")
	private String note;
	
	@Column(name="doc_img_col")
	private String fileUrl;
	
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
	
	//----------------Association mapping--------
	@ManyToOne
	@JoinColumn(name="doc_id_fk_col")
	private Specialization specialization;//has-a

}
