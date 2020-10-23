package com.thoughtworks.springbootemployee.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer company_id;
    String company_name;

    @OneToMany(
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(name = "company_id")
    List<Employee> employees;

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


    public Company(Integer company_id, String company_name) {
        this.company_id = company_id;
        this.company_name = company_name;
    }

    public Company() {}
}
