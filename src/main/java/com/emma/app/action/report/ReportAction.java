package com.emma.app.action.report;

import com.emma.app.action.BaseAction;
import com.emma.app.bean.AttendanceBeanI;
import com.emma.app.bean.ReportBeanI;
import com.emma.app.model.Attendance;
import com.emma.app.view.helper.design.DesignI;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("reports")
public class ReportAction extends BaseAction {
    @EJB
    ReportBeanI reportBean;
    @EJB
    AttendanceBeanI attendanceBean;
    @Inject
    @Named("reports")
    DesignI design;


    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        renderPage(req, resp, 3, design.designer(), Attendance.class, attendanceBean.select(Attendance.class,""));


    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        String singleDate = req.getParameter("singleDate");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String role = req.getParameter("role");

        renderPage(req, resp, 3, design.designer(), Attendance.class, reportBean.getAttendanceData(type, singleDate, startDate, endDate, role));


    }

}
