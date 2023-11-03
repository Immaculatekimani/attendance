package com.emma.app.bean;

import com.emma.app.model.entity.Attendance;

public interface AttendanceBeanI {
    String attendanceRecord();

    Attendance addorUpdateAttendance(Attendance attendance) throws Exception;

    void deleteAttendance(Attendance attendance);
}
