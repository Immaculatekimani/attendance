package com.emma.app.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Attendance implements Serializable {
    private String employeeID;
    private String employeeName;
    private LocalDate attendanceDate;
    private LocalTime attendanceTime;
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

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public LocalTime getAttendanceTime() {
        return attendanceTime;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public String tableRow() {
        StringBuilder trBuilder = new StringBuilder();

        trBuilder.append("<tr>");
        trBuilder.append("<td>").append(getEmployeeID().strip()).append("</td>");
        trBuilder.append("<td>").append(getEmployeeName().strip()).append("</td>");
        trBuilder.append("<td>").append(getAttendanceDate()).append("</td>");
        trBuilder.append("<td>").append(getAttendanceTime()).append("</td>");
        trBuilder.append("<td>").append(getAttendanceStatus().strip()).append("</td>");
        trBuilder.append("<tr>");

        return trBuilder.toString();

    }

}
