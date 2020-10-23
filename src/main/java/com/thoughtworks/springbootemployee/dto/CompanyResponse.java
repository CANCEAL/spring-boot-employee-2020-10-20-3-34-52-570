package com.thoughtworks.springbootemployee.dto;

import com.thoughtworks.springbootemployee.model.Employee;

import java.util.List;

public class CompanyResponse {

    Integer company_id;
    String company_name;
    Integer employee_count;
    List<Employee> employees;

    public CompanyResponse(Integer company_id, String company_name, Integer employee_count) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.employee_count = employee_count;
    }

    public CompanyResponse() {}

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Integer getEmployee_count() {
        return employee_count;
    }

    public void setEmployee_count(Integer employee_count) {
        this.employee_count = employee_count;
    }
}
