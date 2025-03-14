package com.verma.sandeep.hospital.mate.iservice;

import java.util.List;

import com.verma.sandeep.hospital.mate.binding.DepartmentResponse;
import com.verma.sandeep.hospital.mate.entity.Department;

public interface DepartmentService {
	
	public Department createDepartment(Department department);
	public Department getDepartmentById(Long id);
	public void updateOneDepartment(Department department);
	public void deleteOneDepartment(Long id);
	public List<Department> getAllDepartments();
	public List<DepartmentResponse> getDepartmentsResponse();

}
