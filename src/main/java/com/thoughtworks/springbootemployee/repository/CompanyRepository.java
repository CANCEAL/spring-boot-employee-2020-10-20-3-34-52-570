package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;

import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {
    private List<Company> companyList = new ArrayList<>();

    public List<Company> getAll() {
        return companyList;
    }

    public Company save(Company company) {
        companyList.add(company);
        return company;
    }

    public void delete(Company company) {
        companyList.remove(company);
    }
}
