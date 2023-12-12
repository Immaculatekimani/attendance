package com.emma.app.bean;

import com.emma.app.dao.GenericDaoI;
import com.emma.app.model.Attendance;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;


@Stateless(name = "attendance/ReportBean")
@Remote
public class ReportBean implements ReportBeanI {
    @EJB
    AttendanceBeanI attendanceBean;
    @EJB
    EmployeeBeanI employeeBean;
    @Inject
    GenericDaoI genericDao;

    public List<Attendance> getAttendanceData(String type, String singleDate, String startDate, String endDate, String role) {
        if ("2".equals(type)) {
            // By Single Date
            return attendanceBean.select(Attendance.class, "attendance_date = ?1", singleDate);
        } else if ("3".equals(type)) {
            // By Date Range
            return attendanceBean.select(Attendance.class, "attendance_date BETWEEN ?1 AND ?2", startDate, endDate);

        } else if (role != null && !role.isEmpty()) {
            // Handle the case when a role is selected
            return getAttendanceByRole(role);
        } else {
            return attendanceBean.list(Attendance.class);
        }
    }

    public List<Attendance> getEmployeeAttendanceData(String employeeId, String type, String singleDate, String
            startDate, String endDate) {
        if ("2".equals(type)) {
            // By Single Date for a specific employee
            return attendanceBean.select(Attendance.class, "employee_id = ?1 AND attendance_date = ?2", employeeId, singleDate);
        } else if ("3".equals(type)) {
            // By Date Range for a specific employee
            return attendanceBean.select(Attendance.class, "employee_id = ?1 AND attendance_date BETWEEN ?2 AND ?3", employeeId, startDate, endDate);
        } else {
            // Default case or handle other cases as needed
            return attendanceBean.select(Attendance.class, "employee_id = ?1", employeeId);
        }

    }

    public List<Attendance> getAttendanceByRole(String employeeRole) {

        try {

            return attendanceBean.getAttendanceByRole(employeeRole);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
