package com.emma.app.bean;

import com.emma.app.model.Attendance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public interface AttendanceBeanI extends GenericBeanI<Attendance> {

    public Attendance logAttendance(Attendance attendance, String selectedValue);

    public List<Attendance> getEmployeeAttendance(String employeeId);

    public Attendance findExistingRecord(List<Attendance> records, LocalDate currentDate);

    public List<Attendance> getAttendanceByRole(String employeeRole);

    public List<Attendance> getTodaysAttendance();

    public void update(String displayId, LocalDate attendanceDate, LocalTime timeOut, String attendanceStatus);

    public List<Attendance> selectByDateAndRole(String date, String role);

    public List<Attendance> selectByRoleAndDateRange(String startDate, String endDate, String role);
}
