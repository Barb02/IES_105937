package com.pt.ua.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pt.ua.employees.domain.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {}