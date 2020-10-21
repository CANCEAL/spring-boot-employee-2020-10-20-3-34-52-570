package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class CompanyServiceTest {

    @Test
    void should_return_companies_when_get_all() {
        // given
        CompanyRepository repository = new CompanyRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        CompanyService companyService = new CompanyService(repository, employeeRepository);

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
        EmployeeRepository employeeRepository = new EmployeeRepository();

        //when
        CompanyService companyService = new CompanyService(repository, employeeRepository);

        //then
        Assertions.assertEquals(companyService.create(newCompany), newCompany);
    }

    @Test
    void should_return_company_when_get_given_company_code() {
        // given
        Company newCompany = new Company(123, "Microsoft", "Ayala");
        CompanyRepository repository = new CompanyRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        CompanyService companyService = new CompanyService(repository, employeeRepository);
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
        EmployeeRepository employeeRepository = new EmployeeRepository();
        CompanyService companyService = new CompanyService(repository, employeeRepository);
        companyService.create(oldCompany);

        //when
        //then
        Assertions.assertEquals(updatedCompany, companyService.update(123, updatedCompany));
    }
    
    @Test
    void should_delete_employees_under_company_when_delete_given_company_id() {
        //given
        Company company = new Company(123, "MACROHARD", "Ayala");
        CompanyRepository companyRepository = new CompanyRepository();

        Employee belongEmployee = new Employee(1, "Baron", 21, "Male", 2000, 123);
        Employee otherEmployee = new Employee(2, "Maria", 16, "Female", 2000, 45);
        EmployeeRepository employeeRepository = new EmployeeRepository();

        EmployeeService employeeService = new EmployeeService(employeeRepository);
        employeeService.create(belongEmployee);
        employeeService.create(otherEmployee);

        CompanyService companyService = new CompanyService(companyRepository, employeeRepository);
        companyService.create(company);

        //when
        companyService.delete(123);

        //then
        Assertions.assertEquals(1, companyService.getAll().size());
        Assertions.assertEquals(1, employeeService.getAll().size());
        Assertions.assertNull(employeeService.retrieve(1));
    }

    @Test
    void should_list_all_employees_under_given_company_when_get_company_employees_given_company_id_and_header_employees() {
        //given
        Company company = new Company(123, "MACROHARD", "Ayala");
        CompanyRepository companyRepository = new CompanyRepository();

        Employee belongEmployee = new Employee(1, "Baron", 21, "Male", 2000, 123);
        Employee otherEmployee = new Employee(2, "Maria", 16, "Female", 2000, 45);
        EmployeeRepository employeeRepository = new EmployeeRepository();

        EmployeeService employeeService = new EmployeeService(employeeRepository);
        employeeService.create(belongEmployee);
        employeeService.create(otherEmployee);

        CompanyService companyService = new CompanyService(companyRepository, employeeRepository);
        companyService.create(company);

        //when
        //then
        Assertions.assertEquals(Arrays.asList(belongEmployee), companyService.getEmployeesUnderCompany(123));
    }
    
    @Test
    void should_display_2_companies_when_page_query_given_page_size_2_and_companies_are_4() {
        // given
        
        // when
        
        // then
    }
}
