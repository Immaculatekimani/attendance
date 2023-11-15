package com.emma.app.model;

import com.emma.app.view.helper.MyHtmlForm;
import com.emma.app.view.helper.MyHtmlFormField;
import com.emma.app.view.helper.MyTableColHeader;

@MyHtmlForm(label = "Add Employee", url = "./add-employee")
public class Employee {
    @MyHtmlFormField(placeholder = "Enter Employee ID")
    @MyTableColHeader(header = "Employee ID")
    private String employeeId;
    @MyHtmlFormField(placeholder = "Enter First Name")
    @MyTableColHeader(header = "Employee First Name")
    private String firstName;
    @MyHtmlFormField(placeholder = "Enter Last Name")
    @MyTableColHeader(header = "Employee Last Name")
    private String lastName;
    @MyHtmlFormField(label = "Choose Employee Role")
    @MyTableColHeader(header = "Employee Role")
    private EmployeeRole role;

    public Employee(String employeeId, String firstName, String lastName, EmployeeRole role) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public Employee() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }


}
