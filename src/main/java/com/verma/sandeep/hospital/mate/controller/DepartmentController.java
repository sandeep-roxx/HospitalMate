package com.verma.sandeep.hospital.mate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verma.sandeep.hospital.mate.dto.DepartmentDTO;
import com.verma.sandeep.hospital.mate.entity.Department;
import com.verma.sandeep.hospital.mate.iservice.DepartmentService;

@RestController
@RequestMapping("/dept")
public class DepartmentController {
	
	@Autowired
    private DepartmentService departmentService;

    @PostMapping("/save")
    public ResponseEntity<String> createDepartment(@RequestBody Department department) {
        Department savedDept = departmentService.createDepartment(department);
        Long id=savedDept.getDeptId();
        return new ResponseEntity<>("Saved successfully "+id, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateDepartment(@RequestBody Department department) {
        departmentService.updateOneDepartment(department);
        return ResponseEntity.ok("Department updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteOneDepartment(id);
        return ResponseEntity.ok("Department deleted successfully");
    }

    @GetMapping("/dropdown")
    public ResponseEntity<List<DepartmentDTO>> getDepartmentsForDropdown() {
        return ResponseEntity.ok(departmentService.getDepartmentsResponse());
    }


}
