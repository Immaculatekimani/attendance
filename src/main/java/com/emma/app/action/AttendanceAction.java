package com.emma.app.action;

import com.emma.app.bean.AttendanceBean;
import com.emma.app.bean.AttendanceBeanI;
import com.emma.app.model.Attendance;
import com.emma.app.model.Employee;
import com.emma.database.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/attendance-sheet")
public class AttendanceAction extends BaseAction {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Attendance attendance = serializeForm(Attendance.class, req.getParameterMap());
        AttendanceBeanI attendanceBean = new AttendanceBean();

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
                try {
                    attendanceBean.addOrUpdateRecord(attendance);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }


        }


        resp.sendRedirect("./attendance-sheet");


    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AttendanceBeanI attendanceBean = new AttendanceBean();
        renderAttendanceSheetPage(req, resp, 1);

    }
}
