package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

//    public List<Employee> getEmployeesUnderCompany (Integer companyCode) {
//        return employeeRepository.getAll().stream()
//                .filter(employee -> employee.getCompanyCode().equals(companyCode))
//                .collect(Collectors.toList());
//    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company create(Company company) {
        return companyRepository.save(company);
    }

    public Optional<Company> retrieve(Integer companyCode) {
        return companyRepository.findById(companyCode);
    }

    public Company update(Integer companyCode, Company updatedCompany) {
        Company company = companyRepository.findById(companyCode).orElse(null);

        if (company != null) {
            if (updatedCompany.getName() != null) {
                company.setName(updatedCompany.getName());
            }
            if (updatedCompany.getLocation() != null) {
                company.setLocation(updatedCompany.getLocation());
            }
        }
        return companyRepository.save(company);
    }
//
//    public void delete(Integer companyCode) {
//        List<Employee> employees = employeeRepository.getAll().stream()
//                .filter(employee -> employee.getCompanyCode().equals(companyCode))
//                .collect(Collectors.toList());
//
//        employees.stream().forEach(employee -> employeeRepository.delete(employee));
//    }
//
//    public List<Company> getByPage(int page, int pageSize) {
//        return companyRepository.getAll().stream()
//                .skip(page * pageSize)
//                .limit(pageSize)
//                .collect(Collectors.toList());
//    }
}
