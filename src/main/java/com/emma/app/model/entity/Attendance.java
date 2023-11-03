package com.emma.app.model.entity;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Attendance implements Serializable {
    private String employeeId;
    private String employeeName;
    private LocalDate attendanceDate;
    private LocalTime attendanceTime;
    private String attendanceStatus;

    public Attendance() {
    }

    public Attendance(String employeeId, String employeeName, LocalDate attendanceDate, LocalTime attendanceTime, String attendanceStatus) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.attendanceDate = attendanceDate;
        this.attendanceTime = attendanceTime;
        this.attendanceStatus = attendanceStatus;
    }

    public String getEmployeeId() {
        return employeeId;
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
        trBuilder.append("<td>").append(getEmployeeId().strip()).append("</td>");
        trBuilder.append("<td>").append(getEmployeeName().strip()).append("</td>");
        trBuilder.append("<td>").append(getAttendanceDate()).append("</td>");
        trBuilder.append("<td>").append(getAttendanceTime()).append("</td>");
        trBuilder.append("<td>").append(getAttendanceStatus().strip()).append("</td>");
        trBuilder.append("<tr>");

        return trBuilder.toString();

    }

}
