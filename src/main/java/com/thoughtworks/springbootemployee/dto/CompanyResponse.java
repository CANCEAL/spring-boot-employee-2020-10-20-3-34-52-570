package com.thoughtworks.springbootemployee.dto;

import com.thoughtworks.springbootemployee.model.Employee;

import javax.persistence.*;
import java.util.List;

public class CompanyResponse {

    Integer company_id;
    String company_name;

    public Integer getCode() {
        return company_id;
    }

    public void setCode(Integer code) {
        this.company_id = code;
    }

    public String getName() {
        return company_name;
    }

    public void setName(String name) {
        this.company_name = name;
    }

    public CompanyResponse(Integer code, String name) {
        this.company_id = code;
        this.company_name = name;

    }

    public CompanyResponse() {}
}
