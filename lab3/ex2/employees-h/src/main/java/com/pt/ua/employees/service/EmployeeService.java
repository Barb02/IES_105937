package com.pt.ua.employees.service;

import com.pt.ua.employees.domain.Employee;
import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee emp);

    Employee getEmployeeById(Long empId);

    Employee getEmployeeByEmail(String email);

    List<Employee> getAllEmployees();

    Employee updateEmployee(Employee emp);

    void deleteEmployee(Long empId);
}