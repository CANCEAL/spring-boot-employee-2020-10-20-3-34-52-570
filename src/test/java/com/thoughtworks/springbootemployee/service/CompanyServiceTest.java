package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CompanyServiceTest {
    private CompanyRepository repository = Mockito.mock(CompanyRepository.class);
    private EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);


    @Test
    void should_get_all_when_get_company() {
        //given
        List<Company> expectedCompany = asList(new Company(), new Company());
        when(repository.findAll()).thenReturn(expectedCompany);
        CompanyService service = new CompanyService(repository);

        //when
        List<Company> actual = service.getAll();

        //given
        assertEquals(2, actual.size());
    }

    @Test
    void should_create_company_when_create_given_company() {
        //given

        Company company = new Company(1, "OOCL", "Pasay");
        CompanyService service = new CompanyService(repository);
        when(repository.save(company)).thenReturn(company);
        //when
        Company actual = service.create(company);
        //then
        assertEquals("OOCL", actual.getName());
    }

    @Test
    void should_get_company_when_get_by_name_given_company_id() {
        //given
        Company company = new Company(1, "OOCL","Pasay");
        when(repository.findById(1)).thenReturn(java.util.Optional.of(company));
        CompanyService service = new CompanyService(repository);
        //when
        Optional<Company> actual = service.retrieve(1);
        //then
        assertEquals(company.getCode(), actual.get().getCode());
    }

    @Test
    void should_get_list_of_employee_when_search_given_certain_company() {
        //given
        Company company = new Company(1, "OOCL","Pasay");
        Employee employee = new Employee(1,"Prince",1,"Male",1,1);

        when(employeeRepository.findByCompanyCode(1)).thenReturn(Collections.singletonList(employee));
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        //when
        List<Employee> actual = employeeService.getEmployeeByCompanyId(1);
        //then
        assertTrue(actual.contains(employee));
    }

    @Test
    void should_update_company_when_update_by_company_id_given_company_id() {
        //given
        Company company = new Company(1, "OOCL","Pasay");
        Company updateCompany = new Company(1, "COSCO", "Pasay");
        when(repository.findById(1)).thenReturn(Optional.of(company));
        when(repository.save(company)).thenReturn(updateCompany);
        CompanyService service = new CompanyService(repository);
        //when
        Company actual = service.update(1, updateCompany);
        //then
        assertEquals(updateCompany, actual);
    }

    @Test
    void should_delete_company_when_delete_by_company_id_given_company() {
        //given
        Company company = new Company(1, "OOCL","Pasay");
        CompanyService service = new CompanyService(repository);
        //when
        service.delete(1);
        //then
        verify(repository, times(1)).deleteById(1);
    }

    @Test
    void should_get_page_when_get_page_given_company() {
        //given
        List<Company> expectedCompany = asList(new Company(),
                new Company());
        Pageable pageable = PageRequest.of(0,2);
        Page<Company> page = new PageImpl<>(expectedCompany);
        when(repository.findAll(pageable)).thenReturn(page);
        CompanyService service = new CompanyService(repository);
        //when
        //then
        assertEquals(2, service.getByPage(0,2).size());
    }
}
