package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.model.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public CompanyResponse toResponse(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setCode(company.getCompany_id());
        companyResponse.setName(company.getCompany_name());
        return companyResponse;
    }

    public Company toEntity(CompanyRequest companyRequest) {
        Company company = new Company();
        company.setCompany_name(companyRequest.getCompany_name());
        return company;
    }
}
