package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exceptions.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.exceptions.InvalidEmployeeException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    public Employee retrieve(Integer employeeId) {
        return repository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found!"));
    }

//    public List<Employee> getEmployeeByCompanyId(Integer company_id) {
//        return repository.findByCompanyCode(company_id);
//    }

    public Employee update(Integer employeeId, Employee updatedEmployee) {
        Employee employee = repository.findById(employeeId)
                .orElseThrow(() -> new InvalidEmployeeException("Employee " + employeeId + " is invalid!"));

        if (employee != null) {
            if (updatedEmployee.getEmployee_name() != null) {
                employee.setEmployee_name(updatedEmployee.getEmployee_name());
            }
            if (updatedEmployee.getAge() != null) {
                employee.setAge(updatedEmployee.getAge());
            }
            if (updatedEmployee.getCompany_id() != null) {
                employee.setCompany_id(updatedEmployee.getCompany_id());
            }
            if (updatedEmployee.getGender() != null) {
                employee.setGender(updatedEmployee.getGender());
            }
            if (updatedEmployee.getSalary() != null) {
                employee.setSalary(updatedEmployee.getSalary());
            }
        }
        return repository.save(employee);
    }

    public void delete(int employeeId) {
        repository.deleteById(employeeId);
    }

    public List<Employee> search(String gender) {
        return repository.findByGender(gender);
    }

    public List<Employee> getByPage(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return repository.findAll(pageable).toList();
    }
}
