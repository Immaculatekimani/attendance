package com.emma.app.model;

import com.emma.app.view.helper.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "employees")
@MyHtmlForm(label = "Add Employee", url = "./employee", editLabel = "Edit Employee", editURL = "./updateEmployee", editSubmit = "Change details")
@TableForm(label = "Edit Employee", url = "./employee")
@MyTableSetting(includeActions = true)
@NamedQueries({
        @NamedQuery(name = "Employee.findById", query = "SELECT e FROM Employee e WHERE e.employeeId = :employeeId")
})
public class Employee extends BaseEntity {
    @MyTableColHeader(header = "Employee Image")
    @Column(name = "employeeImage")
    @MyHtmlFormField(label = "Add Employee image", editLabel = "Edit Profile")
    private String employeeImage;
    @TableColumnIdentifier(columnIdentifier = "Employee FK")
    @MyHtmlFormField(placeholder = "Enter Employee ID")
    @MyTableColHeader(header = "Employee ID")
    @Column(name = "employee_id", unique = true, nullable = false)
    private String employeeId;
    @MyHtmlFormField(placeholder = "Enter First Name")
    @MyTableColHeader(header = "Employee First Name")
    @Column(name = "firstname")
    private String firstName;
    @MyHtmlFormField(placeholder = "Enter Last Name")
    @MyTableColHeader(header = "Employee Last Name")
    @Column(name = "lastname")
    private String lastName;
    @MyHtmlFormField(label = "Choose Employee Role", editLabel = "Change Role")
    @MyTableColHeader(header = "Employee Role")
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private EmployeeRole role;
    @JsonIgnore
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Attendance> attendances;

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

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return Objects.equals(employeeId, employee.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }
}
