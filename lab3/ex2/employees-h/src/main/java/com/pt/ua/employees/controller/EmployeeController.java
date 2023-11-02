package com.pt.ua.employees.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pt.ua.employees.domain.Employee;
import com.pt.ua.employees.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("api/employees")
public class EmployeeController {

    private EmployeeService empService;

    @Autowired
    public EmployeeController(EmployeeService empService) {
        this.empService = empService;
    }
    
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = empService.createEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/employees/{id}
    @GetMapping("{id}")
    public ResponseEntity<Employee> getUserById(@PathVariable("id") Long empId){
        Employee emp = empService.getEmployeeById(empId);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllUsers(@PathVariable(required = false) String email){
        List<Employee> emps;
        if (email == null){
            emps = empService.getAllEmployees();
        }
        else{
            emps = new ArrayList<Employee>();
            emps.add(empService.getEmployeeByEmail(email));
        } 
        return new ResponseEntity<>(emps, HttpStatus.OK);
    }

    // http://localhost:8080/api/employees/{id}
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long empId,
                                           @RequestBody Employee emp){
        emp.setId(empId);
        Employee updatedUser = empService.updateEmployee(emp);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long empId){
        empService.deleteEmployee(empId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }
}
