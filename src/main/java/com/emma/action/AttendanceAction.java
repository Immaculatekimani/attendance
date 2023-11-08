package com.emma.action;

import com.emma.app.bean.AttendanceBean;
import com.emma.app.bean.AttendanceBeanI;
import com.emma.app.bean.EmployeeBean;
import com.emma.app.bean.EmployeeBeanI;
import com.emma.app.model.entity.Attendance;
import com.emma.app.model.entity.Employee;
import com.emma.app.model.entity.EmployeeRole;
import com.emma.database.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

@WebServlet("/add-attendance")
public class AttendanceAction extends BaseAction {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Attendance attendance = new Attendance();
        AttendanceBeanI attendanceBean = new AttendanceBean();

        HttpSession httpSession = req.getSession();

        serializeForm(attendance, req.getParameterMap());

        try {
            attendanceBean.addorUpdateAttendance(attendance, req);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        resp.sendRedirect("./attendance-sheet");


    }
}
