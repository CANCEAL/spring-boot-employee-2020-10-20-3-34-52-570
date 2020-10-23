package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.exceptions.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.exceptions.InvalidCompanyException;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company create(Company company) {
        return companyRepository.save(company);
    }

    public Company retrieve(Integer companyCode) {
        return companyRepository.findById(companyCode).orElseThrow(() -> new CompanyNotFoundException("Company not found"));
    }

    public Company update(Integer companyCode, Company updatedCompany) {
        Company company = companyRepository.findById(companyCode)
                .orElseThrow(() -> new InvalidCompanyException("Company " + companyCode + " is invalid!"));;
        if (company != null) {
            if (updatedCompany.getCompany_name() != null) {
                company.setCompany_name(updatedCompany.getCompany_name());
            }
        }
        return companyRepository.save(company);
    }

    public void delete(Integer companyCode) {
        companyRepository.deleteById(companyCode);
    }

    public List<Company> getByPage(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return companyRepository.findAll(pageable).toList();
    }
}
