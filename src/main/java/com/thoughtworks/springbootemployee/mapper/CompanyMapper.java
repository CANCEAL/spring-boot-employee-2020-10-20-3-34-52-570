package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.model.Company;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Aspect
@Component
public class CompanyMapper {

    public CompanyResponse toResponse(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();

        companyResponse.setCompany_id(company.getCompany_id());
        companyResponse.setCompany_name(company.getCompany_name());

        boolean companyHasEmployees = nonNull(company.getEmployeeList());
        companyResponse.setEmployee_count(companyHasEmployees ? company.getEmployeeCount() : 0);
        companyResponse.setEmployees(companyHasEmployees ? company.getEmployeeList() : null);

        return companyResponse;
    }

    public Company toEntity(CompanyRequest companyRequest) {
        Company company = new Company();
        company.setCompany_name(companyRequest.getCompany_name());
        return company;
    }
}
