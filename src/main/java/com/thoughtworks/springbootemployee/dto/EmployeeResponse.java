package com.thoughtworks.springbootemployee.dto;

public class EmployeeResponse {
    Integer id;
    String employee_name;
    Integer age;
    String gender;
    Integer salary;
    Integer company_id;

    public EmployeeResponse(Integer id, String employee_name, Integer age, String gender, Integer salary, Integer company_id) {
        this.id = id;
        this.employee_name = employee_name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.company_id = company_id;
    }

    public EmployeeResponse() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }
}
