package com.emma.app.bean;

import com.emma.app.model.entity.Attendance;
import com.emma.app.model.entity.Employee;
import com.emma.app.view.html.HtmlComponent;
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
    public String displayAttendanceSheet() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime displayTime = LocalTime.parse(currentTime.format(formatter), formatter);
        StringBuilder trBuilder = new StringBuilder();
        trBuilder.append("<h2 style=\"text-align: center; color: #533535; background-color: #fff; padding: 10px;\">TODAY'S ATTENDANCE! </h2>");
        trBuilder.append("<form action=\"./add-attendance\" method=\"post\">");

        trBuilder.append("<table>");
        trBuilder.append("<thead>");
        trBuilder.append("<tr>");
        trBuilder.append("<th>Employee ID</th>");
        trBuilder.append("<th>Employee Name</th>");
        trBuilder.append("<th>Attendance Time</th>");
        trBuilder.append("<th>Attendance Status</th>");
        trBuilder.append("<th>Submit Attendance</th>");
        trBuilder.append("</tr>");
        trBuilder.append("</thead>");

        for (Employee employee : Database.getDbInstance().getEmployees()) {
            trBuilder.append(" <form action=\"add-attendance\" method=\"post\">");
            trBuilder.append("<tr>");
            trBuilder.append("<td>").append(employee.getEmployeeId().strip()).append("</td>");
            trBuilder.append("<td>" + employee.getFirstName().strip()).append(" ").append(employee.getLastName()).append("</td>");
            trBuilder.append("<td>").append(displayTime).append("</td>");
            trBuilder.append("<td>");
            trBuilder.append("<input type=\"radio\" name=\"attendanceStatus_" + employee.getEmployeeId() + "\" value=\"Present\"> Present");
            trBuilder.append("<input type=\"radio\" name=\"attendanceStatus_" + employee.getEmployeeId() + "\" value=\"Absent\"> Absent");
            trBuilder.append("</td>");
            trBuilder.append("<td><input type=\"submit\" class = \"submit-button\" value=\"Submit\"></td>");
            trBuilder.append("</tr>");
            trBuilder.append("</form>");

        }

        trBuilder.append("</table>");

        return trBuilder.toString();

    }

    @Override
    public void deleteAttendance(Attendance attendance) {

    }


}
