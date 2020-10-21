package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private List<Employee> employeeList = new ArrayList<>();

    public List<Employee> getAll() {
        return employeeList;
    }

    public Employee create() {
        return null;
    }

    public Employee save(Employee employee) {
         employeeList.add(employee);
         return employee;
    }

    public Employee retrieve(int employeeId) {
        return null;
    }

    public Employee update(Employee employee) {
        return null;
    }

    public void delete(int employeeId) {

    }

    public List<Employee> search(String gender) {
        return null;
    }
}
