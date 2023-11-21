package com.emma.app.bean;

import com.emma.app.model.Attendance;
import com.emma.database.Database;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AttendanceBeanI extends GenericBeanI<Attendance> {

    public Attendance logAttendance(Attendance attendance, HttpServletRequest req);
}
