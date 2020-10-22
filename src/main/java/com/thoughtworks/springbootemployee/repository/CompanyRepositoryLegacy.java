package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyRepositoryLegacy {
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
