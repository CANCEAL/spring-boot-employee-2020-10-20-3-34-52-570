package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
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
    public Company getCompanyByCode(@PathVariable Integer code) {
        return companyService.retrieve(code);
    }

    @GetMapping(path = "/{id}")
    public List<Employee> getAllEmployeesUnderCompany(@PathVariable Integer id) {
        return null;
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
