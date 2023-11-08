package com.emma.app.model.entity;

import com.emma.app.view.html.MyTableColHeader;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Attendance implements Serializable {
    @MyTableColHeader(header = "Employee ID")
    private String employeeID;
    @MyTableColHeader(header = "Employee Name")
    private String employeeName;
    @MyTableColHeader(header = "Attendance Date")
    private LocalDate attendanceDate;
    @MyTableColHeader(header = "Attendance Time")
    private LocalTime attendanceTime;
    @MyTableColHeader(header = "Attendance Status")
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
