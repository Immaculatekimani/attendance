package com.emma.app.model;

import com.emma.app.view.helper.MyHtmlFormField;
import com.emma.app.view.helper.MyTableColHeader;
import com.emma.database.helper.DbTable;
import com.emma.database.helper.DbTableColumn;
import com.emma.database.helper.DbTableId;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@DbTable(name = "attendances")
public class Attendance extends BaseEntity {
    @MyTableColHeader(header = "Employee Image")
    @DbTableColumn(name = "employeeImage")
    private String employeeImage;
    @MyTableColHeader(header = "Employee ID")
    @DbTableColumn(name = "employee_id")
    private String employeeID;
    @MyTableColHeader(header = "Employee Name")
    @DbTableColumn(name = "employee_name")
    private String employeeName;
    @MyTableColHeader(header = "Attendance Date")
    @DbTableColumn(name = "attendance_date", definition = "DATE")
    private LocalDate attendanceDate;
    @DbTableColumn(name = "attendance_time", definition = "TIME")
    private LocalTime attendanceTime;

    @MyTableColHeader(header = "Time In")
    @DbTableColumn(name = "time_in", definition = "TIME")
    private LocalTime timeIn;

    @MyTableColHeader(header = "Time Out")
    @DbTableColumn(name = "time_out", definition = "TIME")
    private LocalTime timeOut;

    @MyTableColHeader(header = "Day Status")
    @DbTableColumn(name = "attendance_status")
    private String attendanceStatus;
    @MyTableColHeader(header = "Joining Status")
    @DbTableColumn(name = "joining_status")
    private String joiningStatus;


    public Attendance() {
    }

    public Attendance(String employeeImage, String employeeID, String employeeName, LocalDate attendanceDate, LocalTime attendanceTime, String attendanceStatus) {
        this.employeeImage = employeeImage;
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

    public void setEmployeeImage(String employeeImage) {
        this.employeeImage = employeeImage;
    }

    public String getEmployeeImage() {
        return employeeImage;
    }

    public LocalTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalTime timeIn) {
        this.timeIn = timeIn;
    }

    public LocalTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalTime timeOut) {
        this.timeOut = timeOut;
    }

    public String getJoiningStatus() {
        return joiningStatus;
    }

    public void setJoiningStatus(String joiningStatus) {
        this.joiningStatus = joiningStatus;
    }

}
