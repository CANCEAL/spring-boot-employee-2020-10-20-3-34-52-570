package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Employee> retrieve(Integer employeeId) {
        return repository.findById(employeeId);
    }

    public List<Employee> getEmployeeByCompanyId(Integer companyCode) {
        return repository.findByCompanyCode(companyCode);
    }

    public Employee update(Integer employeeId, Employee updatedEmployee) {
        Employee employee = repository.findById(employeeId).orElse(null);

        if (employee != null) {
            if (updatedEmployee.getName() != null) {
                employee.setName(updatedEmployee.getName());
            }
            if (updatedEmployee.getAge() != null) {
                employee.setAge(updatedEmployee.getAge());
            }
            if (updatedEmployee.getCompany_code() != null) {
                employee.setCompany_code(updatedEmployee.getCompany_code());
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
