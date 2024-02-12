package com.emma.app.action;

import com.emma.app.bean.AttendanceBeanI;
import com.emma.app.model.Attendance;
import com.emma.app.utility.exception.MyExceptionUtils;

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

        try {
            Attendance attendance = serializeForm(Attendance.class, req.getParameterMap());
            System.out.println("attendance = " + attendance);
            String selectedValue = req.getParameter("attendanceStatus");
            System.out.println("selectedValue = " + selectedValue);
            attendanceBean.logAttendance(attendance, selectedValue);

            resp.sendRedirect("./attendance-sheet");

        } catch (Exception e) {
            MyExceptionUtils.redirectToErrorPage(req, resp, e);

        }

    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            renderAttendanceSheetPage(req, resp, 1);

        } catch (Exception e) {
            MyExceptionUtils.redirectToErrorPage(req, resp, e);

        }


    }
}
