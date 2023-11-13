package com.emma.action;

import com.emma.app.bean.AttendanceBean;
import com.emma.app.bean.AttendanceBeanI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/attendance-sheet")
public class AttendanceSheetAction extends BaseAction {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AttendanceBeanI attendanceBean = new AttendanceBean();
        renderAttendanceSheetPage(req, resp, 1);

    }
}