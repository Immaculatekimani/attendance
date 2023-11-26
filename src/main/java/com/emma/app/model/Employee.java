package com.emma.app.model;

import com.emma.app.view.helper.MyHtmlForm;
import com.emma.app.view.helper.MyHtmlFormField;
import com.emma.app.view.helper.MyTableColHeader;
import com.emma.app.view.helper.MyTableSetting;
import com.emma.database.helper.DbTable;
import com.emma.database.helper.DbTableColumn;

@DbTable(name = "employees")
@MyHtmlForm(label = "Add Employee", url = "./employee")
@MyTableSetting(includeActions = true)
public class Employee extends BaseEntity {
    @MyTableColHeader(header = "Employee Image")
    @DbTableColumn(name = "employeeImage")
    @MyHtmlFormField(label = "Add Employee image")
    private String employeeImage;
    @MyHtmlFormField(placeholder = "Enter Employee ID")
    @MyTableColHeader(header = "Employee ID")
    @DbTableColumn(name = "employee_id")
    private String employeeId;
    @MyHtmlFormField(placeholder = "Enter First Name")
    @MyTableColHeader(header = "Employee First Name")
    @DbTableColumn(name = "firstname")
    private String firstName;
    @MyHtmlFormField(placeholder = "Enter Last Name")
    @MyTableColHeader(header = "Employee Last Name")
    @DbTableColumn(name = "lastname")
    private String lastName;
    @MyHtmlFormField(label = "Choose Employee Role")
    @MyTableColHeader(header = "Employee Role")
    @DbTableColumn(name = "role")
    private EmployeeRole role;

    public Employee(String employeeId, String firstName, String lastName, EmployeeRole role, String employeeImage) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.employeeImage = employeeImage;
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

    public String getEmployeeImage() {
        return employeeImage;
    }

    public void setEmployeeImage(String employeeImage) {
        this.employeeImage = employeeImage;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", employeeImage='" + employeeImage + '\'' +
                '}';
    }
}
