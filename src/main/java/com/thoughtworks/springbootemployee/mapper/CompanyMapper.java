package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public CompanyResponse toResponse(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();

        companyResponse.setCompany_id(company.getCompany_id());
        companyResponse.setCompany_name(company.getCompany_name());
        companyResponse.setEmployee_count(company.getEmployeeCount());
        companyResponse.setEmployees(company.getEmployeeList());

        return companyResponse;
    }

    public Company toEntity(CompanyRequest companyRequest) {
        Company company = new Company();
        company.setCompany_name(companyRequest.getCompany_name());
        return company;
    }
}
