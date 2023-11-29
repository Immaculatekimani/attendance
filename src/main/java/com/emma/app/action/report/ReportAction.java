package com.emma.app.action.report;

import com.emma.app.action.BaseAction;
import com.emma.app.bean.AttendanceBeanI;
import com.emma.app.model.Attendance;
import com.emma.app.view.helper.design.DesignI;
import com.emma.app.view.helper.design.EmployeeDesign;
import com.emma.app.view.helper.design.ReportDesign;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("reports")
public class ReportAction extends BaseAction {
    @EJB
    AttendanceBeanI attendanceBean;
    @Inject
    @Named("reports")
    DesignI design;


    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            renderPage(req, resp, 3, design.designer(),  Attendance.class, attendanceBean.list(Attendance.class, ""));


    }
}
