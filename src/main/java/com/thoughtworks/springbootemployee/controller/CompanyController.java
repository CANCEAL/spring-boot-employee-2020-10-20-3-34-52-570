package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    CompanyService companyService;
    EmployeeService employeeService;

    public CompanyController(CompanyService companyService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Company> getCompanies() {
        return companyService.getAll();
    }

    @PostMapping
    public Company createCompany(@RequestBody Company company) {
        return companyService.create(company);
    }

    @GetMapping(path = "/{code}")
    public Optional<Company> getCompanyByCode(@PathVariable Integer code) {
        return companyService.retrieve(code);
    }

    @GetMapping(path = "/{company_code}/employees")
    public List<Employee> getAllEmployeesUnderCompany(@PathVariable Integer company_code) {
        return employeeService.getEmployeeByCompanyId(company_code);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Company> getByPage(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        return companyService.getByPage(page, pageSize);
    }

    @PutMapping(path = "/{code}")
    public Company updateCompanyByCode(@PathVariable Integer code, @RequestBody Company updatedCompany) {
        companyService.update(code, updatedCompany);
        return updatedCompany;
    }

    @DeleteMapping(path = "/{code}")
    public void deleteCompanyById(@PathVariable Integer code) {
        companyService.delete(code);
    }
}
