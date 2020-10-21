package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        List<Employee> employees = repository.getAll();
        return employees.stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .orElse(null);
    }

    public Employee update(int employeeId, Employee employee) {
        return repository.update(employee);
    }

    public void delete(int employeeId) {
        repository.delete(employeeId);
    }

    public List<Employee> search(String gender) {
        return repository.search(gender);
    }
}
