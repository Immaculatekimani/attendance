package com.emma.app.action.report;

import com.emma.app.action.BaseAction;
import com.emma.app.bean.AttendanceBeanI;
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
    AttendanceBeanI attendanceBean;
    @Inject
    @Named("reports")
    DesignI design;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeId = req.getParameter("employeeId");

        if (employeeId != null && !employeeId.isEmpty()) {
            // If employeeId is provided, fetch attendance records for the specific employee

            List<Attendance> attendanceList = attendanceBean.getEmployeeAttendance(employeeId);

            // Render the page with attendance records for the specific employee
            renderPage(req, resp, 3, design.designer(), Attendance.class, attendanceList);
        } else {
            // Handle the case when employeeId is not provided
            resp.getWriter().println("Invalid request. Please provide a valid employeeId.");
        }


    }
}
