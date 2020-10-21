package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    // TODO remove later
    List<Employee> employees = new ArrayList<>();

    private final EmployeeService employeeService;

    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees() {
       return employeeService.getAll();
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        employees.add(employee);
        return employee;
    }

    @GetMapping(path = "/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PutMapping(path = "/{id}")
    public Employee updateEmployeeById(@PathVariable Integer id, @RequestBody Employee updatedEmployee) {
        employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .ifPresent(employee -> {
                    employees.remove(employee);
                    employees.add(updatedEmployee);
                });
        return updatedEmployee;
    }

    @DeleteMapping(path = "/{id}")
    public void deleteEmployeeById(@PathVariable Integer id) {
        employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .ifPresent(employee -> {
                    employees.remove(employee);
                });
    }

    @GetMapping(params = "gender")
    public List<Employee> getEmployeeByGender(@RequestParam("gender") String gender) {
        return employees.stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Employee> getByPage(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        return employees.stream()
                .skip(page * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}
