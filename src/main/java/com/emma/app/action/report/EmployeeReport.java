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
import java.util.List;

@WebServlet("/employeeReport")
public class EmployeeReport extends BaseAction {
    @EJB
    ReportBeanI reportBean;
    @EJB
    AttendanceBeanI attendanceBean;
    @Inject
    @Named("singleEmployee")
    DesignI design;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeId = req.getParameter("employeeId");

        if (employeeId != null && !employeeId.isEmpty()) {
            List<Attendance> attendanceList = attendanceBean.getEmployeeAttendance(employeeId);

            renderPage(req, resp, 3, design.designer(), Attendance.class, attendanceList);
        } else {
            resp.getWriter().println("Invalid request. Please provide a valid employeeId.");
        }


    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeId = req.getParameter("employeeId");
        String type = req.getParameter("type");
        String singleDate = req.getParameter("singleDate");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");

        renderPage(req, resp, 3, design.designer(), Attendance.class, reportBean.getEmployeeAttendanceData(employeeId, type, singleDate, startDate, endDate));


    }
}
