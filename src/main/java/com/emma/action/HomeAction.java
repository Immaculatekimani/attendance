package com.emma.action;

import com.emma.app.bean.AttendanceBean;
import com.emma.app.bean.AttendanceBeanI;
import com.emma.app.view.html.AppPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/home")
public class HomeAction extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        AttendanceBeanI attendanceBean = new AttendanceBean();
        new AppPage().renderHtml(req, resp, 0, " <div class=\"image-container\">" + "        <div> " + "            <img src=\"images/Slide1crop.jpg\" alt=\"Image 1\">" + "        </div>" + "    </div>" + attendanceBean.attendanceRecord());
    }
}
