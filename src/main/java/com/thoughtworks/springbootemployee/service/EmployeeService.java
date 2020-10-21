package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;

import java.util.List;

public class EmployeeService {
    EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAll() {
        return repository.getAll();
    }

    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    public Employee retrieve(int employeeId) {
        return repository.retrieve(employeeId);
    }
}
