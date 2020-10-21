package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    @Test
    void should_return_employees_when_get_all() {
        // given
        EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
        List<Employee> expected = Arrays.asList(new Employee(), new Employee());
        when(repository.getAll()).thenReturn(expected);

        EmployeeService employeeService = new EmployeeService(repository);

        // when
        // then
        Assertions.assertEquals(expected, employeeService.getAll());
    }

    @Test
    public void should_return_employee_when_created_given_employee_details() {
        //given
        Employee newEmployee = new Employee(1, "Alfred", 21, "Male", 1000);
        EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
        when(repository.save(newEmployee)).thenReturn(newEmployee);

        //when
        EmployeeService employeeService = new EmployeeService(repository);

        //then
        Assertions.assertEquals(employeeService.create(newEmployee), newEmployee);
    }
    
    @Test
    void should_return_employee_when_get_given_employee_id() {
        // given
        Employee employee = new Employee(1, "Alfred", 21, "Male", 1000);
        EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
        when(repository.retrieve(1)).thenReturn(employee);

        // when
        EmployeeService employeeService = new EmployeeService(repository);

        // then
        Assertions.assertEquals(employee, employeeService.retrieve(1));
    }

    @Test
    public void should_return_updated_employee_when_update_given_update_details() {
        //given
        Employee updatedEmployee = new Employee(1, "Baron", 21, "Male", 2000);
        EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
        when(repository.update(updatedEmployee)).thenReturn(updatedEmployee);

        EmployeeService employeeService = new EmployeeService(repository);

        //when
        //then
        Assertions.assertEquals(updatedEmployee, employeeService.update(1, updatedEmployee));
    }

    // If you don’t know how to mock delete method, try to search Mockito.verify()
    @Test
    void should_delete_employee_when_delete_given_employee_id() {
        // given
        Employee employee = new Employee(1, "Baron", 21, "Male", 2000);
        EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);

        EmployeeService employeeService = new EmployeeService(repository);
        employeeService.create(employee);

        // when
        employeeService.delete(1);
        
        // then
        verify(repository).delete(1);
    }

    @Test
    public void should_return_employees_when_searched_given_gender() {
        //given
        EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
        Employee employee1 = new Employee(1, "Baron", 21, "Male", 2000);
        Employee employee2 = new Employee(2, "Ana", 22, "Female", 1000);
        List<Employee> expected = Arrays.asList(employee1);
        when(repository.search("male")).thenReturn(expected);

        EmployeeService employeeService = new EmployeeService(repository);

        //when
        //then
        Assertions.assertEquals(expected, employeeService.search("male"));
    }
}
