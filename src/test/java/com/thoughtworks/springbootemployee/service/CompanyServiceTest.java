package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class CompanyServiceTest {

    @Test
    void should_return_companies_when_get_all() {
        // given
        CompanyRepository repository = new CompanyRepository();
        CompanyService companyService = new CompanyService(repository);

        Company firstCompany = new Company(123, "Microsoft", "Ayala");
        Company secondCompany = new Company(234, "MACROHARD", "Ayala");

        // when
        companyService.create(firstCompany);
        companyService.create(secondCompany);

        // then
        Assertions.assertEquals(2, companyService.getAll().size());
    }

    @Test
    public void should_return_company_when_created_given_company_details() {
        //given
        Company newCompany = new Company(123, "Microsoft", "Ayala");
        CompanyRepository repository = new CompanyRepository();

        //when
        CompanyService companyService = new CompanyService(repository);

        //then
        Assertions.assertEquals(companyService.create(newCompany), newCompany);
    }

    @Test
    void should_return_company_when_get_given_company_code() {
        // given
        Company newCompany = new Company(123, "Microsoft", "Ayala");
        CompanyRepository repository = new CompanyRepository();
        CompanyService companyService = new CompanyService(repository);
        companyService.create(newCompany);

        // when
        // then
        Assertions.assertEquals(newCompany, companyService.retrieve(123));
    }

    @Test
    public void should_return_updated_company_when_update_given_update_details() {
        //given
        Company updatedCompany = new Company(123, "MACROHARD", "Ayala");
        Company oldCompany = new Company(123, "Microsoft", "Ayala");
        CompanyRepository repository = new CompanyRepository();
        CompanyService companyService = new CompanyService(repository);
        companyService.create(oldCompany);

        //when
        //then
        Assertions.assertEquals(updatedCompany, companyService.update(123, updatedCompany));
    }
    
    @Test
    void should_delete_employees_under_company_when_delete_given_company_id() {
        // given
        
        // when
        
        // then
    }

    @Test
    void should_list_all_employees_under_given_company_when_get_company_employees_given_company_id_and_header_employee() {
        // given
        
        // when
        
        // then
    }
    
    @Test
    void should_display_2_companies_when_page_query_given_page_size_2_and_companies_are_4() {
        // given
        
        // when
        
        // then
    }
}
