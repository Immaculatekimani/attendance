package com.emma.app.model.entity;

import com.emma.app.view.html.MyHtmlForm;
import com.emma.app.view.html.MyHtmlFormField;
import com.emma.app.view.html.MyTableColHeader;

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

    public String tableRow() {
        StringBuilder trBuilder = new StringBuilder();

        trBuilder.append("<tr>");
        trBuilder.append("<td>").append(getEmployeeId().strip()).append("</td>");
        trBuilder.append("<td>").append(getFirstName().strip()).append("</td>");
        trBuilder.append("<td>").append(getLastName().strip()).append("</td>");
        trBuilder.append("<td>").append(getRole()).append("</td>");
        trBuilder.append("<tr>");

        return trBuilder.toString();

    }
}
