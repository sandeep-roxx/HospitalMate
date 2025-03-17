package com.verma.sandeep.hospital.mate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.verma.sandeep.hospital.mate.entity.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
	
	@Modifying
	@Query("UPDATE Operation o SET o.status = :status WHERE o.oid = :oid")
	int updateOperationStatus(@Param("oid") long oid, @Param("status") String status);
	
	@Query("SELECT o.status FROM Operation o WHERE o.oid = :oid")
    String findOperationStatusById(@Param("oid") Long oid);


}
