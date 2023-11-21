package com.emma.app.model;

import com.emma.app.view.helper.MyTableColHeader;
import com.emma.database.helper.DbTable;
import com.emma.database.helper.DbTableColumn;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@DbTable(name = "attendances")
public class Attendance extends BaseEntity {
    @MyTableColHeader(header = "Employee ID")
    @DbTableColumn(name = "employee_id")
    private String employeeID;
    @MyTableColHeader(header = "Employee Name")
    @DbTableColumn(name = "employee_name")
    private String employeeName;
    @MyTableColHeader(header = "Attendance Date")
    @DbTableColumn(name = "attendance_date", definition = "DATE")
    private LocalDate attendanceDate;
    @MyTableColHeader(header = "Attendance Time")
    @DbTableColumn(name = "attendance_time", definition = "TIME")
    private LocalTime attendanceTime;
    @MyTableColHeader(header = "Attendance Status")
    @DbTableColumn(name = "attendance_status")
    private String attendanceStatus;

    public Attendance() {
    }

    public Attendance(String employeeID, String employeeName, LocalDate attendanceDate, LocalTime attendanceTime, String attendanceStatus) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.attendanceDate = attendanceDate;
        this.attendanceTime = attendanceTime;
        this.attendanceStatus = attendanceStatus;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "employeeID='" + employeeID + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", attendanceDate=" + attendanceDate +
                ", attendanceTime=" + attendanceTime +
                ", attendanceStatus='" + attendanceStatus + '\'' +
                '}';
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public void setAttendanceTime(LocalTime attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public LocalTime getAttendanceTime() {
        return attendanceTime;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }


}
