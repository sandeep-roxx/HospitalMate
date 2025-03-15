package com.verma.sandeep.hospital.mate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verma.sandeep.hospital.mate.entity.Employee;
import com.verma.sandeep.hospital.mate.exception.EmployeeNotFoundException;
import com.verma.sandeep.hospital.mate.iservice.EmployeeService;
import com.verma.sandeep.hospital.mate.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public Employee createEmployee(Employee employee) {
		return empRepo.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return empRepo.findAll();
	}

	@Override
	public Employee getEmployeeById(Long id) {
		return empRepo.findById(id)
				                        .orElseThrow(()->new EmployeeNotFoundException("Employee not exist"));
	}

	@Override
	public void updateEmployee(Employee employee) {
		if(empRepo.existsById(employee.getEmpId())) 
			empRepo.save(employee);
		else
			throw new EmployeeNotFoundException("Employee not exist");

	}

	@Override
	public void deleteEmployee(Long id) {
		empRepo.delete(getEmployeeById(id));

	}

}
