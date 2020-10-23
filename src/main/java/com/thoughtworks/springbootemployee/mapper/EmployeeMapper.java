package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public EmployeeResponse toResponse(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();

        BeanUtils.copyProperties(employee, employeeResponse);

        return employeeResponse;
    }

    public Employee toEntity(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();

        employee.setEmployee_name(employeeRequest.getEmployee_name());
        employee.setAge(employeeRequest.getAge());
        employee.setGender(employeeRequest.getGender());
        employee.setSalary(employeeRequest.getSalary());
        employee.setCompany_id(employeeRequest.getCompany_id());

        return employee;
    }
}
