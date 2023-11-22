package com.emma.app.action;

import com.emma.app.bean.AttendanceBeanI;
import com.emma.app.model.Attendance;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/attendance-sheet")
public class AttendanceAction extends BaseAction {
    @EJB
    AttendanceBeanI attendanceBean;
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Attendance attendance = serializeForm(Attendance.class, req.getParameterMap());

        String selectedValue = req.getParameter("attendanceStatus");

        attendanceBean.logAttendance(attendance, selectedValue);

        resp.sendRedirect("./attendance-sheet");


    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        renderAttendanceSheetPage(req, resp, 1);

    }
}
