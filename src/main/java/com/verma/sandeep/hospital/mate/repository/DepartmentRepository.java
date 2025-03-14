package com.verma.sandeep.hospital.mate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.verma.sandeep.hospital.mate.binding.DepartmentResponse;
import com.verma.sandeep.hospital.mate.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
	@Query("SELECT new your.package.DepartmentResponse(d.deptId, d.deptName) FROM Department d")
    List<DepartmentResponse> getDepartmentsResponse();

}
