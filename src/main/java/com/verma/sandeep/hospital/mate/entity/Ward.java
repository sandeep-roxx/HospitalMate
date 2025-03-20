package com.verma.sandeep.hospital.mate.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
    
    @Column(name = "ward_type_col")
    private String type;
    
    @Column(name = "ward_capacity_col")
    private int capacity;
    
    @Column(nullable = false)
    private Integer availableBeds;
    
    @Column(name = "ward_status_col")
    private String status; 
    
    @Column(nullable = false)
    private Integer floorNumber;
    
    @Column(name = "ward_description_col")
    private String description;

    // Relationship with Patient (One Ward can have multiple Patients)
    @OneToMany(mappedBy = "ward", cascade = CascadeType.REMOVE,
    		                   orphanRemoval = true, fetch = FetchType.LAZY
    		                   )
    private List<Patient> patients;

    @OneToOne
    @JoinColumn(name = "doc_id_col", nullable = false)  
    private Doctor doctor;  // One doctor per ward
    
    //@Column(nullable = false)
    //private Employee supervisorId; // Reference to hospital staff managing the ward
    
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
