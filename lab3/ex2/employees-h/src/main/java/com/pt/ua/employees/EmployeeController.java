package com.pt.ua.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import java.util.Optional;
import java.util.List;

@Controller
@RequestMapping("api/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository EmployeeRepository) {
        this.employeeRepository = EmployeeRepository;
    }
    
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/employees/{id}
    @GetMapping("{id}")
    public ResponseEntity<Employee> getUserById(@PathVariable("id") Long userId){
        Optional<Employee> optionalEmp = employeeRepository.findById(userId);
        Employee emp = optionalEmp.get();
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllUsers(){
        List<Employee> users = employeeRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // http://localhost:8080/api/employees/{id}
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long empId,
                                           @RequestBody Employee emp){
        emp.setId(empId);
        Employee existingUser = employeeRepository.findById(emp.getId()).get();
        existingUser.setName(emp.getName());
        existingUser.setEmail(emp.getEmail());
        Employee updatedEmployee = employeeRepository.save(existingUser);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        employeeRepository.deleteById(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }
}
