package com.emma.app.bean;

import com.emma.app.model.Attendance;

import java.io.Serializable;
import java.util.List;


public interface ReportBeanI extends Serializable {
    public List<Attendance> getAttendanceData(String type, String singleDate, String startDate, String endDate, String role);

    public List<Attendance> getEmployeeAttendanceData(String employeeId, String type, String singleDate, String startDate, String endDate);

    public List<Attendance> getAttendanceByRole(String role);
}
