package com.emma.app.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Attendance implements Serializable {
    private String userId;
    private String userName;
    private LocalDate attendanceDate;
    private LocalTime attendanceTime;
    private String attendanceStatus;

    public Attendance() {
    }

    public Attendance(String userId, String userName, LocalDate attendanceDate, LocalTime attendanceTime, String attendanceStatus) {
        this.userId = userId;
        this.userName = userName;
        this.attendanceDate = attendanceDate;
        this.attendanceTime = attendanceTime;
        this.attendanceStatus = attendanceStatus;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
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
        trBuilder.append("<td>").append(getUserId().strip()).append("</td>");
        trBuilder.append("<td>").append(getUserName().strip()).append("</td>");
        trBuilder.append("<td>").append(getAttendanceDate()).append("</td>");
        trBuilder.append("<td>").append(getAttendanceTime()).append("</td>");
        trBuilder.append("<td>").append(getAttendanceStatus().strip()).append("</td>");
        trBuilder.append("<tr>");

        return trBuilder.toString();

    }

}
