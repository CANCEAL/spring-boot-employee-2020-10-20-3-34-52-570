package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    public CompanyService(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployeesUnderCompany (Integer companyCode) {
        return employeeRepository.getAll().stream()
                .filter(employee -> employee.getCompanyCode().equals(companyCode))
                .collect(Collectors.toList());
    }

    public List<Company> getAll() {
        return companyRepository.getAll();
    }

    public Company create(Company company) {
        return companyRepository.save(company);
    }

    public Company retrieve(Integer companyCode) {
        List<Company> companies = companyRepository.getAll();
        return companies.stream()
                .filter(company -> company.getCode().equals(companyCode))
                .findFirst()
                .orElse(null);
    }

    public Company update(Integer companyCode, Company updatedCompany) {
        delete(companyCode);
        return create(updatedCompany);
    }

    public void delete(Integer companyCode) {
        List<Employee> employees = employeeRepository.getAll().stream()
                .filter(employee -> employee.getCompanyCode().equals(companyCode))
                .collect(Collectors.toList());

        employees.stream().forEach(employee -> employeeRepository.delete(employee));
    }

    public List<Company> getByPage(int page, int pageSize) {
        return companyRepository.getAll().stream()
                .skip(page * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}
