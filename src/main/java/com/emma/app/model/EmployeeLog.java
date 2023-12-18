package com.emma.app.model;

import com.emma.app.view.helper.MyTableColHeader;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "employee_logs")
public class EmployeeLog extends BaseEntity{
    @MyTableColHeader(header = "Employee logs")
    @Column(name = "employee_details", columnDefinition = "longtext")
    private String employeeLogDetails;

    public String getEmployeeLogDetails() {
        return employeeLogDetails;
    }

    public void setEmployeeLogDetails(String employeeLogDetails) {
        this.employeeLogDetails = employeeLogDetails;
    }
}
