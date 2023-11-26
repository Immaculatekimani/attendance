package com.emma.app.bean;

import com.emma.app.model.Attendance;

import java.util.List;


public interface AttendanceBeanI extends GenericBeanI<Attendance> {

    public Attendance logAttendance(Attendance attendance, String selectedValue);

    public List<Attendance> getEmployeeAttendance(String employeeId);
}
