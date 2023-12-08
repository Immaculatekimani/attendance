package com.emma.app.bean;

import com.emma.app.model.Attendance;
import com.emma.app.model.Employee;

import java.util.List;

public interface EmployeeBeanI extends GenericBeanI<Employee> {
    public void employeeAction(String action, String employeeId, Employee employeeInput);
}
