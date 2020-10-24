package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CompanyIntegrationTest {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @Test
    public void should_return_correct_company_when_get_given_company_id() throws Exception {
        //given
        Company company =  companyRepository.save(new Company(2,"OOCL"));
        //when
        //then
        mockMvc.perform(get("/companies/{company_id}",company.getCompany_id()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.company_id").isNumber())
                .andExpect(jsonPath("$.company_name").value("OOCL"));
    }
    @Test
    public void should_get_all_company_when_get_all() throws Exception {
        //given
        Company company = companyRepository.save(new Company(1, "OOCL"));
        //when
        //then
        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].company_id").isNumber())
                .andExpect(jsonPath("$[0].company_name").value("OOCL"));
    }
    @Test
    public void should_create_company_when_create_given_company_request() throws Exception {
        //Company company =companyRepository.save( new Company(3, "OOCL"));
        String companyAsJson = "{\n" +
                "    \"company_name\": \"OOCL\"\n" +
                "}";
        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(companyAsJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.company_id").isNumber())
                .andExpect(jsonPath("$.company_name").value("OOCL"));
    }
    @Test
    public void should_delete_company_when_delete_given_company_id() throws Exception {
        //given
        Company company = companyRepository.save(new Company(1, "OOCL"));
        //when
        //then
        mockMvc.perform(delete("/companies/{company_id}", company.getCompany_id()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.company_id").doesNotExist());
    }
    @Test
    public void should_return_correct_employee_when_put_given_employee() throws Exception {
        //given
        Company oldCompany = companyRepository.save(new Company(1, "Leo"));
        String newCompanyAsJson = "{\n" +
                "    \"company_name\": \"Alfred\"\n" +
                "}";
        //when
        //then
        mockMvc.perform(put("/companies/{company_id}", oldCompany.getCompany_id())
                .contentType(MediaType.APPLICATION_JSON)
                .content(newCompanyAsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.company_id").isNumber())
                .andExpect(jsonPath("$.company_name").value("Alfred"));
    }
    @Test
    public void should_return_2_company_when_get_given_page_0_and_size_2() throws Exception {
        //given
        companyRepository.save(new Company(1, "OOCL"));
        companyRepository.save(new Company(2, "COSCO"));
        companyRepository.save(new Company(3, "OOL"));
        //when
        //then
        mockMvc.perform(get("/companies?page=0&pageSize=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].company_id").isNumber())
                .andExpect(jsonPath("$[0].company_name").value("OOCL"));
        mockMvc.perform(get("/companies?page=0&pageSize=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[2].company_id").doesNotExist())
                .andExpect(jsonPath("$[2].company_name").doesNotExist());
    }


}
