package com.emma.action;

import com.emma.app.model.entity.Employee;
import com.emma.app.view.html.AppPage;
import com.emma.database.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/attendance-sheet")
public class AttendanceSheet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

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
        trBuilder.append("</tr>");
        trBuilder.append("</thead>");

        for (Employee employee : Database.getDbInstance().getEmployees()) {
            trBuilder.append("<tr>");
            trBuilder.append("<td>").append(employee.getEmployeeId().strip()).append("</td>");
            trBuilder.append("<td>").append(employee.getFirstName().strip()).append(" ").append(employee.getLastName()).append("</td>");
            trBuilder.append("<td>").append(displayTime).append("</td>");
            trBuilder.append("<td>");
            trBuilder.append("<input type=\"radio\" name=\"attendanceStatus_" + employee.getEmployeeId() + "\" value=\"Present\"> Present");
            trBuilder.append("<input type=\"radio\" name=\"attendanceStatus_" + employee.getEmployeeId() + "\" value=\"Absent\"> Absent");
            trBuilder.append("</td>");
            trBuilder.append("</tr>");
        }

        trBuilder.append("</table>");
        trBuilder.append("<div style=\"text-align: center; margin-top: 2%\">");
        trBuilder.append("<input type=\"submit\" class=\"submit-button\" value=\"Add Attendance\">");
        trBuilder.append("</div>");
        trBuilder.append("</form>");


        new AppPage().renderHtml(req, resp, 1, "<h2>Adding and updating daily attendances</h2> " + trBuilder.toString());


    }
}
