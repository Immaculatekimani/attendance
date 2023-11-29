package com.emma.app.bean;

import com.emma.app.model.Attendance;
import com.emma.app.model.Employee;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Stateless(name = "attendance/ReportBean")
@Remote
public class ReportBean implements ReportBeanI {
    @EJB
    AttendanceBeanI attendanceBean;
    @EJB
    EmployeeBeanI employeeBean;

    public List<Attendance> getAttendanceData(String type, String singleDate, String startDate, String endDate, String role) {
        if ("2".equals(type)) {
            // By Single Date
            return attendanceBean.list(Attendance.class, "attendance_date = ?", singleDate);
        } else if ("3".equals(type)) {
            // By Date Range
            return attendanceBean.list(Attendance.class, "attendance_date BETWEEN ? AND ?", startDate, endDate);

        } else if (role != null && !role.isEmpty()) {
            // Handle the case when a role is selected
            return getAttendanceByRole(role);
        } else {
            return attendanceBean.list(Attendance.class, "");
        }
    }

    public List<Attendance> getEmployeeAttendanceData(String employeeId, String type, String singleDate, String
            startDate, String endDate) {
        if ("2".equals(type)) {
            // By Single Date for a specific employee
            return attendanceBean.list(Attendance.class, "employee_id = ? AND attendance_date = ?", employeeId, singleDate);
        } else if ("3".equals(type)) {
            // By Date Range for a specific employee
            return attendanceBean.list(Attendance.class, "employee_id = ? AND attendance_date BETWEEN ? AND ?", employeeId, startDate, endDate);
        } else {
            // Default case or handle other cases as needed
            return attendanceBean.list(Attendance.class, "employee_id = ?", employeeId);
        }

    }

    public List<Attendance> getAttendanceByRole(String role) {

        try {
            // Step 1: Get employee IDs for the specified role
            List<String> employeeIds = employeeBean.getEmployeeByRole(role).stream()
                    .map(Employee::getEmployeeId)
                    .collect(Collectors.toList());

            // Step 2: Get attendance records for the retrieved employee IDs
            if (!employeeIds.isEmpty()) {
                // You can adjust the WHERE clause as needed based on your database schema
                String whereClause = new StringBuilder().append("employee_id IN (").append(String.join(",", Collections.nCopies(employeeIds.size(), "?"))).append(")").toString();
                Object[] parameters = employeeIds.toArray();

                return attendanceBean.list(Attendance.class, whereClause, parameters);
            } else {
                // Return an empty list if no employees found for the specified role
                return Collections.emptyList();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}