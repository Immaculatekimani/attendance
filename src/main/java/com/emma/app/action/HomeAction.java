package com.emma.app.action;

import com.emma.app.bean.AttendanceBean;
import com.emma.app.bean.AttendanceBeanI;
import com.emma.app.bean.EmployeeBeanI;
import com.emma.app.model.Attendance;
import com.emma.app.view.helper.design.HomeDesign;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeAction extends BaseAction {
    @EJB
    EmployeeBeanI employeeBean;
    @EJB
    AttendanceBeanI attendanceBean;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        renderPage(req, resp, 0, HomeDesign.design(employeeBean),
                Attendance.class, attendanceBean.list(Attendance.class, "")

        );

    }
}
