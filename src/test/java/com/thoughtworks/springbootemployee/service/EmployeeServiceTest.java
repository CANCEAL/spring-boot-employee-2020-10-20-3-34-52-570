package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    @Test
    void should_return_employees_when_get_all() {
        // given
        EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
        List<Employee> expected = Arrays.asList(new Employee(), new Employee());
        when(repository.findAll()).thenReturn(expected);

        EmployeeService employeeService = new EmployeeService(repository);

        // when
        // then
        Assertions.assertEquals(expected, employeeService.getAll());
    }
}
