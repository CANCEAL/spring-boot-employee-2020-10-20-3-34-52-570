package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeesController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public List<EmployeeResponse> getEmployees() {
        List<Employee> employees = employeeService.getAll();
        return employees.stream().map(employeeMapper::toResponse).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse create(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeService.create(employeeMapper.toEntity(employeeRequest));
        return employeeMapper.toResponse(employee);
    }

    @GetMapping(path = "/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable Integer id) {
        Employee employee = employeeService.retrieve(id);
        return employeeMapper.toResponse(employee);
    }

    @PutMapping(path = "/{id}")
    public EmployeeResponse updateEmployeeById(@PathVariable Integer id, @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeService.update(id, employeeMapper.toEntity(employeeRequest));
        return employeeMapper.toResponse(employee);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteEmployeeById(@PathVariable Integer id) {
        employeeService.delete(id);
    }

    @GetMapping(params = "gender")
    public List<EmployeeResponse> getEmployeeByGender(@RequestParam("gender") String gender) {
        List<Employee> employees = employeeService.search(gender);
        return employees.stream().map(employeeMapper::toResponse).collect(Collectors.toList());
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<EmployeeResponse> getByPage(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        List<Employee> employees = employeeService.getByPage(page, pageSize);
        return employees.stream().map(employeeMapper::toResponse).collect(Collectors.toList());
    }

}
