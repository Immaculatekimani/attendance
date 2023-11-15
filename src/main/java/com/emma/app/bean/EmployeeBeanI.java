package com.emma.app.bean;

import com.emma.app.model.Employee;

import java.util.List;

public interface EmployeeBeanI {

    List<Employee> list();
    Employee addorUpdateEmployee(Employee employee) throws Exception;

    void deleteEmployee(Employee employee);
}
