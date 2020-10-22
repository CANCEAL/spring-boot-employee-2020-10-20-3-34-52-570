package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    public void should_return_correct_employee_when_put_given_employee() throws Exception {
        //given
        Employee oldEmployee = new Employee(1,"Prince",22,"Male",100,1);
        String newEmployeeAsJson = "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Alfred\",\n" +
                "    \"age\": 25,\n" +
                "    \"gender\": \"male\",\n" +
                "    \"salary\": 500,\n" +
                "    \"company_code\": 1\n" +
                "}";
        employeeRepository.save(oldEmployee);

        //when
        //then
        mockMvc.perform(put("/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newEmployeeAsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Alfred"))
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(500));
    }

    @Test
    public void should_get_all_employees_when_get_all() throws Exception {
        //given
        Employee employee = new Employee(1,"Prince",22,"Male",100,1);
        employeeRepository.save(employee);
        //when
        //then
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("Prince"))
                .andExpect(jsonPath("$[0].age").value(22))
                .andExpect(jsonPath("$[0].gender").value("Male"))
                .andExpect(jsonPath("$[0].salary").value(100));
    }

    @Test
    public void should_create_employee_when_create_given_employee_request() throws Exception {
        String employeeAsJson = "{\n" +
                "    \"id\": 6,\n" +
                "    \"name\": \"Alfred\",\n" +
                "    \"age\": 25,\n" +
                "    \"gender\": \"male\",\n" +
                "    \"salary\": 500,\n" +
                "    \"company_code\": 1\n" +
                "}";
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeAsJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Alfred"))
                .andExpect(jsonPath("$.age").value("25"))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value("500"));

        List<Employee> employees = employeeRepository.findAll();
        Assertions.assertEquals(1, employees.size());
        Assertions.assertEquals("Alfred", employees.get(0).getName());
    }

    @Test
    public void should_return_employee_when_get_given_employee_id() throws Exception {
        //given
        Employee employee = new Employee(1,"Prince",22,"Male",100,1);
        employeeRepository.save(employee);
        //when
        //then
        mockMvc.perform(get("/employees/", employee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("Prince"))
                .andExpect(jsonPath("$[0].age").value(22))
                .andExpect(jsonPath("$[0].gender").value("Male"))
                .andExpect(jsonPath("$[0].salary").value(100));
    }


}