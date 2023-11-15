package com.emma.app.action;

import com.emma.app.bean.AttendanceBean;
import com.emma.app.bean.AttendanceBeanI;
import com.emma.app.model.Attendance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/attendance-sheet")
public class AttendanceAction extends BaseAction {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Attendance attendance = serializeForm(Attendance.class, req.getParameterMap());
        AttendanceBeanI attendanceBean = new AttendanceBean();

        try {
            attendanceBean.addorUpdateAttendance(attendance, req);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        resp.sendRedirect("./attendance-sheet");


    }
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AttendanceBeanI attendanceBean = new AttendanceBean();
        renderAttendanceSheetPage(req, resp, 1);

    }
}
