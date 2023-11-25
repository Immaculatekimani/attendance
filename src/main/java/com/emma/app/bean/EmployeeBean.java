package com.emma.app.bean;

import com.emma.app.model.Employee;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless(name = "attendance/EmployeeBean")
@Remote
public class EmployeeBean extends GenericBean<Employee> implements EmployeeBeanI {


}