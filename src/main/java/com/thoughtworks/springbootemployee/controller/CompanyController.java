package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @GetMapping
    public List<CompanyResponse> getCompanies() {
        return companyService.getAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CompanyResponse createCompany(@RequestBody CompanyRequest companyRequest) {
        Company company = companyService.create(companyMapper.toEntity(companyRequest));
        System.out.println("beee"+companyRequest.getCompany_name());
        return companyMapper.toResponse(company);
    }

    @GetMapping(path = "/{company_id}")
    public Company getCompanyByCode(@PathVariable Integer code) {
        return companyService.retrieve(code);
    }

//    @GetMapping(path = "/{company_code}/employees")
//    public List<Employee> getAllEmployeesUnderCompany(@PathVariable Integer company_code) {
//        return employeeService.getEmployeeByCompanyId(company_code);
//    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Company> getByPage(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        return companyService.getByPage(page, pageSize);
    }

    @PutMapping(path = "/{company_id}")
    public Company updateCompanyByCode(@PathVariable Integer code, @RequestBody Company updatedCompany) {
        return companyService.update(code, updatedCompany);
    }

    @DeleteMapping(path = "/{company_id}")
    public void deleteCompanyById(@PathVariable Integer code) {
        companyService.delete(code);
    }
}
