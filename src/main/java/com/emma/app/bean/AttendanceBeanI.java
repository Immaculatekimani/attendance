package com.emma.app.bean;

import com.emma.app.model.Attendance;


public interface AttendanceBeanI extends GenericBeanI<Attendance> {

    public Attendance logAttendance(Attendance attendance, String selectedValue);
}
