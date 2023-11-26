package com.emma.app.action;

import com.emma.app.bean.AttendanceBeanI;
import com.emma.app.model.Attendance;
import com.emma.app.model.Employee;
import com.emma.app.view.helper.design.EmployeeDesign;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/employeeAttendance")
public class EmployeeAttendance extends BaseAction {
    @EJB
    AttendanceBeanI attendanceBean;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeId = req.getParameter("employeeId");

        if (employeeId != null && !employeeId.isEmpty()) {
            // If employeeId is provided, fetch attendance records for the specific employee

            List<Attendance> attendanceList = attendanceBean.getEmployeeAttendance(employeeId);

            // Render the page with attendance records for the specific employee
            renderPage(req, resp, 2, EmployeeDesign.design(), Attendance.class, attendanceList);
        } else {
            // Handle the case when employeeId is not provided
            resp.getWriter().println("Invalid request. Please provide a valid employeeId.");
        }


    }
}
