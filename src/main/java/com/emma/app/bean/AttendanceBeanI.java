package com.emma.app.bean;

import com.emma.app.model.entity.Attendance;

import javax.servlet.http.HttpServletRequest;

public interface AttendanceBeanI {
    String attendanceRecord();

    Attendance addorUpdateAttendance(Attendance attendance, HttpServletRequest req) throws Exception;

    void deleteAttendance(Attendance attendance);
    String displayAttendanceSheet();
}
