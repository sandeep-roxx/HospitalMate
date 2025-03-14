package com.verma.sandeep.hospital.mate.iservice;

import java.util.List;

import com.verma.sandeep.hospital.mate.entity.Employee;

public interface EmployeeService {
	
	public Employee createEmployee(Employee employee);
	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(Long id);
	public void updateEmployee(Employee updatedEmployee);
	public void deleteEmployee(Long id);

}
