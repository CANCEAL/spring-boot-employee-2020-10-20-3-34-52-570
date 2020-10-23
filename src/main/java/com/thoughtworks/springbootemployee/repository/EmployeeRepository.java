package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByGender(String gender);

    //@Query("select new Employee(id, name, age, gender, salary, company_id) from Employee where company_id = ?1")
    //List<Employee> findByCompanyCode(Integer company_id);
}
