package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

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
}
