package com.thoughtworks.springbootemployee.dto;

public class CompanyRequest {
    String company_name;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public CompanyRequest(String company_name) {
        this.company_name = company_name;
    }

    public CompanyRequest() {
    }
}
