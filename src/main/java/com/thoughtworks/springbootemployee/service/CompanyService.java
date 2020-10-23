package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.exceptions.CompanyNotFoundException;
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
    private CompanyMapper companyMapper;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
        this.companyMapper = new CompanyMapper();
    }

    public List<CompanyResponse> getAll() {
        List<Company> companies = companyRepository.findAll();
        System.out.println("here is " + companies.size());
        return companies.stream().map(company -> companyMapper.toResponse(company)).collect(Collectors.toList());
    }

    public Company create(Company company) {
        return companyRepository.save(company);
    }

    public Company retrieve(Integer companyCode) {
        return companyRepository.findById(companyCode).orElseThrow(() -> new CompanyNotFoundException("Company not found"));
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

    public void delete(Integer companyCode) {
        companyRepository.deleteById(companyCode);
    }

    public List<Company> getByPage(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return companyRepository.findAll(pageable).toList();
    }
}
