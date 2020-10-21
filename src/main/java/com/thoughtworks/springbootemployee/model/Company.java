package com.thoughtworks.springbootemployee.model;

public class Company {
    Integer code;

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

    String name;
    String location;

    public Company(Integer code, String name, String location) {
        this.code = code;
        this.name = name;
        this.location = location;
    }

    public Company() {}
}
