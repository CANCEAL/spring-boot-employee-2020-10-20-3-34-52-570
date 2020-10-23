package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;
    EmployeeMapper employeeMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper, EmployeeMapper employeeMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public List<CompanyResponse> getCompanies() {
        List<Company> companies = companyService.getAll();
        return companies.stream().map(companyMapper::toResponse).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CompanyResponse createCompany(@RequestBody CompanyRequest companyRequest) {
        Company company = companyService.create(companyMapper.toEntity(companyRequest));
        return companyMapper.toResponse(company);
    }

    @GetMapping(path = "/{company_id}")
    public CompanyResponse getCompanyByCode(@PathVariable Integer company_id) {
        Company company = companyService.retrieve(company_id);
        return companyMapper.toResponse(company);
    }

    @GetMapping({"/{companyID}/employees"})
    public List<EmployeeResponse> getEmployees(@PathVariable Integer companyID) {
        List<Employee> employees = companyService.getCompanyEmployee(companyID);
        return employees.stream().map(employee -> employeeMapper.toResponse(employee)).collect(Collectors.toList());
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<CompanyResponse> getByPage(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        List<Company> companies = companyService.getByPage(page, pageSize);
        return companies.stream().map(companyMapper::toResponse).collect(Collectors.toList());
    }

    @PutMapping(path = "/{company_id}")
    public CompanyResponse updateCompanyById(@PathVariable Integer company_id, @RequestBody CompanyRequest companyRequest) {
        Company company = companyService.update(company_id, companyMapper.toEntity(companyRequest));
        return companyMapper.toResponse(company);
    }

    @DeleteMapping(path = "/{company_id}")
    public void deleteCompanyById(@PathVariable Integer company_id) {
        companyService.delete(company_id);
    }
}
