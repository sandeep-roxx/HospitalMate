package com.verma.sandeep.hospital.mate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verma.sandeep.hospital.mate.dto.DepartmentDTO;
import com.verma.sandeep.hospital.mate.entity.Department;
import com.verma.sandeep.hospital.mate.exception.DepartmentNotFoundException;
import com.verma.sandeep.hospital.mate.iservice.DepartmentService;
import com.verma.sandeep.hospital.mate.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentRepository deptRepo;
	

	@Override
	public Department createDepartment(Department department) {
		return deptRepo.save(department);
	}

	@Override
	public Department getDepartmentById(Long id) {
		return deptRepo.findById(id)
				                         .orElseThrow(()->new DepartmentNotFoundException("Department not exist"));
	}

	@Override
	public List<Department> getAllDepartments() {
		return deptRepo.findAll();
	}

	@Override
	public void updateOneDepartment(Department department) {
		if(deptRepo.existsById(department.getDeptId()))
			deptRepo.save(department);
		else
			throw new DepartmentNotFoundException("Department not exits");
		
	}

	@Override
	public void deleteOneDepartment(Long id) {
		deptRepo.delete(getDepartmentById(id));
		
	}
	
   //To implement Drop down
	@Override
	public List<DepartmentDTO> getDepartmentsResponse() {
		return deptRepo.getDepartmentsResponse();
	}

}
