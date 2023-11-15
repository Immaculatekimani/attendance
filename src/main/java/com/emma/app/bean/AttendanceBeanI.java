package com.emma.app.bean;

import com.emma.app.model.Attendance;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AttendanceBeanI {

    List<Attendance> list();
    Attendance addorUpdateAttendance(Attendance attendance, HttpServletRequest req) throws Exception;

    void deleteAttendance(Attendance attendance);
}
