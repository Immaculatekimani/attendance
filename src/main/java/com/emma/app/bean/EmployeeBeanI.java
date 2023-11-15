package com.emma.app.bean;

import com.emma.app.model.Employee;

public interface EmployeeBeanI {
    String employeeRecords();

    Employee addorUpdateEmployee(Employee employee) throws Exception;

    void deleteEmployee(Employee employee);
}
