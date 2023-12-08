package com.emma.app.bean;

import com.emma.app.model.Attendance;

import java.time.LocalDate;
import java.util.List;


public interface AttendanceBeanI extends GenericBeanI<Attendance> {

    public Attendance logAttendance(Attendance attendance, String selectedValue);

    public List<Attendance> getEmployeeAttendance(String employeeId);

    public Attendance findExistingRecord(List<Attendance> records, LocalDate currentDate);

    public List<Attendance> getAttendanceByRole(String employeeRole);

    public List<Attendance> getTodaysAttendance();
}
