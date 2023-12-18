package com.emma.app.model;

import com.emma.app.view.helper.MyTableColHeader;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "attendance_logs")
public class AttendanceLog extends BaseEntity{
    @Column(name = "attendance_details", columnDefinition = "longtext")
    @MyTableColHeader(header = "Attendance log")
    private String attendanceDetails;

    public String getAttendanceDetails() {
        return attendanceDetails;
    }

    public void setAttendanceDetails(String attendanceDetails) {
        this.attendanceDetails = attendanceDetails;
    }
}
