package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    void teardown(){
        companyRepository.deleteAll();
    }

//    @Test
//    void should_get_all_companies_when_get_all() throws Exception {
//        Company company = new Company("OOCL", Arrays.asList());
//        companyRepository.save(company);
//
//        mockMvc.perform(get("/companies"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").isNumber())
//                .andExpect(jsonPath("$[0].companyName").value("OOCL"))
//                .andExpect(jsonPath("$[0].employees").isEmpty());
//    }
}
