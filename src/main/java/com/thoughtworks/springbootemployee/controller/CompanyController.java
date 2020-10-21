package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    List<Company> companies = new ArrayList<>();

    @GetMapping
    public List<Company> getCompanies() {
        return companies;
    }

    @PostMapping
    public Company createCompany(@RequestBody Company company) {
        companies.add(company);
        return company;
    }

    @GetMapping(path = "/{code}")
    public Company getCompanyByCode(@PathVariable Integer code) {
        return companies.stream()
                .filter(company -> company.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    @GetMapping(path = "/{id}/employees")
    public List<Employee> getAllEmployeesUnderCompany(@PathVariable Integer id) {
        return null;
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Company> getByPage(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        return null;
    }

    @PutMapping(path = "/{id}")
    public Company updateCompanyById(@PathVariable Integer id, @RequestBody Company updatedCompany) {
        return null;
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCompanyById(@PathVariable Integer id) {
        // TODO
    }
}
