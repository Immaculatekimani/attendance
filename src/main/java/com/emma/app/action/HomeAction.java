package com.emma.app.action;

import com.emma.app.bean.AttendanceBean;
import com.emma.app.bean.AttendanceBeanI;
import com.emma.app.model.Attendance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/home")
public class HomeAction extends BaseAction {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AttendanceBeanI attendanceBean = new AttendanceBean();
        renderPage(req, resp, 0 , Attendance.class, attendanceBean.list()
//                " <div class=\"image-container\">" +
//                " <div> " + "<img src=\"images/Slide1crop.jpg\" alt=\"Image 1\">" +
//                "</div>" +
//                "</div>" + attendanceBean.attendanceRecord()
               );

        System.out.println("#############attend list #########");
        System.out.println(attendanceBean.list());
    }
}
