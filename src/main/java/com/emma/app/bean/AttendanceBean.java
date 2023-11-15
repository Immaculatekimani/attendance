package com.emma.app.bean;

import com.emma.app.model.Attendance;
import com.emma.app.model.Employee;
import com.emma.app.view.helper.HtmlComponent;
import com.emma.database.Database;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AttendanceBean implements AttendanceBeanI, Serializable {
    @Override
    public String attendanceRecord() {
        StringBuilder title = new StringBuilder();
        title.append("<h2 style = \"text-align: center; color: #533535; background-color: #fff; padding: 10px;\">LATEST ATTENDANCE RECORDS </h2>");


        return title.toString() + HtmlComponent.table(Database.getDbInstance().getAttendances());
    }

    @Override
    public Attendance addorUpdateAttendance(Attendance attendance, HttpServletRequest req) throws Exception {
        Database database = Database.getDbInstance();


        for (Employee employee : database.getEmployees()) {
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
                database.getAttendances().add(attendance);
            }


        }
        return attendance;

    }

    @Override
    public void deleteAttendance(Attendance attendance) {

    }


}
