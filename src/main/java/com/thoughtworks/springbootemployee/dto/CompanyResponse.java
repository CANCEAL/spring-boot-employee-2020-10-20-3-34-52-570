package com.thoughtworks.springbootemployee.dto;

import com.thoughtworks.springbootemployee.model.Employee;

import javax.persistence.*;
import java.util.List;

public class CompanyResponse {

    Integer code;
    String name;
    String location;
    List<Employee> employees;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public CompanyResponse(Integer code, String name, String location) {
        this.code = code;
        this.name = name;
        this.location = location;
    }

    public CompanyResponse() {}
}
