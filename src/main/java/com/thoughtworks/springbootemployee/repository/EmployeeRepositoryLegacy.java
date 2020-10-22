package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepositoryLegacy {
    private List<Employee> employeeList = new ArrayList<>();

    public List<Employee> getAll() {
        return employeeList;
    }

    public Employee save(Employee employee) {
         employeeList.add(employee);
         return employee;
    }

    public void delete(Employee employee) {
        employeeList.remove(employee);
    }
}
