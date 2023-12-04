package com.emma.app.model;

import com.emma.app.view.helper.MyTableColHeader;
import com.emma.database.helper.DbTable;
import com.emma.database.helper.DbTableColumn;

@DbTable(name = "employee_logs")
public class EmployeeLog {
    @DbTableColumn(name = "employee_details", definition = "longtext")
    @MyTableColHeader(header = "Employee logs")
    private String employeeLogDetails;

    public String getEmployeeLogDetails() {
        return employeeLogDetails;
    }

    public void setEmployeeLogDetails(String employeeLogDetails) {
        this.employeeLogDetails = employeeLogDetails;
    }
}
