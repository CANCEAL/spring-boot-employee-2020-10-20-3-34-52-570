package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;
import static java.util.Optional.of;

public class EmployeeServiceTest {

    @Test
    void should_return_employees_when_get_all() {
        // given
        EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
        List<Employee> expected = asList(new Employee(), new Employee());
        when(repository.findAll()).thenReturn(expected);

        EmployeeService employeeService = new EmployeeService(repository);

        // when
        // then
        Assertions.assertEquals(expected, employeeService.getAll());
    }

    @Test
    public void should_return_employee_when_created_given_employee_details() {
        //given
        Employee newEmployee = new Employee(1, "Alfred", 21, "Male", 1000, 1);
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
        Employee employee = new Employee(1, "Alfred", 21, "Male", 1000, 1);
        EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
        when(repository.findById(employee.getId())).thenReturn(Optional.of(employee));

        EmployeeService employeeService = new EmployeeService(repository);

        // when
        // then
        Assertions.assertEquals(1, employee.getId());
    }

    @Test
    public void should_return_updated_employee_when_update_given_update_details() {
        //given
        Employee employee = new Employee(1, "Alfred", 21, "Male", 1000, 1);
        Employee expectedEmployee = new Employee(1, "Leo", 21, "Male", 1000, 1);
        EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);

        Optional<Employee> optionalEmployee = of(expectedEmployee);
        when(repository.findById(employee.getId())).thenReturn(optionalEmployee);
        when(repository.save(optionalEmployee.get())).thenReturn(expectedEmployee);
        EmployeeService employeeService = new EmployeeService(repository);

        //when
        Employee updatedEmployee = employeeService.update(employee.getId(), employee);

        //then
        assertSame(expectedEmployee, updatedEmployee);
    }

    @Test
    void should_delete_employee_when_delete_given_employee_id() {
        //given
        Employee employee = new Employee(1, "Leo", 18, "male", 1000, 1);
        EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
        EmployeeService service = new EmployeeService(repository);
        //when
        service.delete(employee.getId());
        //then
        verify(repository, times(1)).deleteById(employee.getId());
    }

    @Test
    public void should_return_employees_when_searched_given_gender() {
        //given
        Employee employee1 = new Employee(1, "Baron", 21, "Male", 2000, 1);
        List<Employee> expected = asList(employee1);

        EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
        when(repository.findByGender("Male")).thenReturn(expected);

        EmployeeService employeeService = new EmployeeService(repository);

        //when
        //then
        Assertions.assertEquals(expected, employeeService.search("Male"));
    }

    @Test
    void should_get_page_when_get_page_given_employee() {
        //given
        List<Employee> expectedEmployee = asList(new Employee(),
                new Employee());
        EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
        Pageable pageable = PageRequest.of(0, 2);
        Page<Employee> page = new PageImpl<>(expectedEmployee);
        when(repository.findAll(pageable)).thenReturn(page);
        EmployeeService service = new EmployeeService(repository);
        //when
        //then
        Assertions.assertEquals(2, service.getByPage(0, 2).size());
    }
}
