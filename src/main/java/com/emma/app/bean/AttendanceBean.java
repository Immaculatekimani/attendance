package com.emma.app.bean;

import com.emma.app.model.Attendance;
import com.emma.app.model.Employee;
import com.emma.app.view.helper.HtmlComponent;
import com.emma.database.Database;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AttendanceBean extends GenericBean<Attendance> implements AttendanceBeanI {

    public Attendance logAttendance(Attendance attendance, HttpServletRequest req) {
        EmployeeBeanI employeeBean = new EmployeeBean();
        for (Employee employee : employeeBean.list(Employee.class)) {
            String employeeId = employee.getEmployeeId();
            String employeeName = employee.getFirstName() + " " + employee.getLastName();
            String attendStatus = req.getParameter("attendanceStatus_" + employeeId);
            if (attendStatus != null) {
                LocalTime currentTime = LocalTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime displayTime = LocalTime.parse(currentTime.format(formatter), formatter);

                attendance.setEmployeeID(employeeId);
                attendance.setEmployeeName(employeeName);
                attendance.setAttendanceDate(LocalDate.now());
                attendance.setAttendanceTime(displayTime);
                attendance.setAttendanceStatus(attendStatus);


                // Add the new attendance record to the database
                try {
                    addOrUpdateRecord(attendance);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }


        }
        return attendance;
    }
}
