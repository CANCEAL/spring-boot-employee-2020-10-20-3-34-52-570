package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
        Employee oldEmployee = employeeRepository.save(new Employee(1, "Leo", 22, "Male", 100));
        String newEmployeeAsJson = "{\n" +
                "    \"employee_name\": \"Alfred\",\n" +
                "    \"age\": 25,\n" +
                "    \"gender\": \"male\",\n" +
                "    \"salary\": 500\n" +
                "}";

        //when
        //then
        mockMvc.perform(put("/employees/{id}", oldEmployee.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(newEmployeeAsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.employee_name").value("Alfred"))
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(500));
    }

    @Test
    public void should_get_all_employees_when_get_all() throws Exception {
        //given
        employeeRepository.save(new Employee(2, "Alfred", 22, "Male", 100));

        //when
        //then
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].employee_name").value("Alfred"))
                .andExpect(jsonPath("$[0].age").value(22))
                .andExpect(jsonPath("$[0].gender").value("Male"))
                .andExpect(jsonPath("$[0].salary").value(100));
    }

    @Test
    public void should_create_employee_when_create_given_employee_request() throws Exception {
        //given
        String employeeAsJson = "{\n" +
                "    \"employee_name\": \"Alfred\",\n" +
                "    \"age\": 25,\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"salary\": 500\n" +
                "}";

        //when
        //then
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeAsJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.employee_name").value("Alfred"))
                .andExpect(jsonPath("$.age").value("25"))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.salary").value("500"));
    }

    @Test
    public void should_return_employee_when_get_given_employee_id() throws Exception {
        //given
        Employee employee = employeeRepository.save(new Employee(1, "Alfred", 22, "Male", 100));

        //when
        //then
        mockMvc.perform(get("/employees/", employee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].employee_name").value("Alfred"))
                .andExpect(jsonPath("$[0].age").value(22))
                .andExpect(jsonPath("$[0].gender").value("Male"))
                .andExpect(jsonPath("$[0].salary").value(100));
    }

    @Test
    public void should_delete_employee_when_delete_given_employee_id() throws Exception {
        //given
        Employee employee = employeeRepository.save(new Employee(2, "Leo", 22, "Male", 100));

        //when
        //then
        mockMvc.perform(delete("/employees/{id}", employee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").doesNotExist());
    }

    @Test
    public void should_return_male_employees_when_get_given_employee_gender() throws Exception {
        //given
        Employee employee1 = employeeRepository.save(new Employee(1, "Leo", 22, "Male", 100));
        employeeRepository.save(new Employee(2, "Ana", 22, "Female", 100));
        employeeRepository.save(new Employee(3, "Alfred", 22, "Male", 100));

        //when
        //then
        mockMvc.perform(get("/employees?gender={gender}", employee1.getGender()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].employee_name").value("Leo"))
                .andExpect(jsonPath("$[0].age").value(22))
                .andExpect(jsonPath("$[0].gender").value("Male"))
                .andExpect(jsonPath("$[0].salary").value(100));

        mockMvc.perform(get("/employees?gender={gender}", employee1.getGender()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].id").isNumber())
                .andExpect(jsonPath("$[1].employee_name").value("Alfred"))
                .andExpect(jsonPath("$[1].age").value(22))
                .andExpect(jsonPath("$[1].gender").value("Male"))
                .andExpect(jsonPath("$[1].salary").value(100));
    }

    @Test
    public void should_return_2_employees_when_get_given_page_0_and_size_2() throws Exception {
        //given
        employeeRepository.save(new Employee(1, "Leo", 22, "Male", 100));
        employeeRepository.save(new Employee(2, "Ana", 22, "Female", 100));
        employeeRepository.save(new Employee(3, "Alfred", 22, "Male", 100));

        //when
        //then
        mockMvc.perform(get("/employees?page=0&pageSize=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].employee_name").value("Leo"))
                .andExpect(jsonPath("$[0].age").value(22))
                .andExpect(jsonPath("$[0].gender").value("Male"))
                .andExpect(jsonPath("$[0].salary").value(100));

        mockMvc.perform(get("/employees?page=0&pageSize=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[2].id").doesNotExist())
                .andExpect(jsonPath("$[2].employee_name").doesNotExist())
                .andExpect(jsonPath("$[2].age").doesNotExist())
                .andExpect(jsonPath("$[2].gender").doesNotExist())
                .andExpect(jsonPath("$[2].salary").doesNotExist());
    }
}
