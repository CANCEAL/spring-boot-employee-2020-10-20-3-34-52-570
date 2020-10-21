package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyService {
    private CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public List<Company> getAll() {
        return repository.getAll();
    }

    public Company create(Company company) {
        return repository.save(company);
    }

    public Company retrieve(Integer companyCode) {
        List<Company> companies = repository.getAll();
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
        List<Company> companies = repository.getAll();
        companies.stream()
                .filter(company -> company.getCode().equals(companyCode))
                .findFirst()
                .ifPresent(company -> repository.delete(company));
    }

    public List<Company> getByPage(int page, int pageSize) {
        return repository.getAll().stream()
                .skip(page * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}
