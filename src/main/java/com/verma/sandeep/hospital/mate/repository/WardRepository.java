package com.verma.sandeep.hospital.mate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verma.sandeep.hospital.mate.entity.Ward;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {

}
