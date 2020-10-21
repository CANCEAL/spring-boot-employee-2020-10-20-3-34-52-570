package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    // TODO remove later
    private List<Employee> employees = new ArrayList<>();

    private final EmployeeService employeeService;

    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // OK
    @GetMapping
    public List<Employee> getEmployees() {
       return employeeService.getAll();
    }

    // OK
    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    // OK
    @GetMapping(path = "/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employeeService.retrieve(id);
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
                .ifPresent(employee -> employees.remove(employee));
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
