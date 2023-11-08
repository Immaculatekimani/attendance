package com.emma.action;

import com.emma.app.bean.EmployeeBean;
import com.emma.app.bean.EmployeeBeanI;
import com.emma.app.model.entity.Attendance;
import com.emma.app.model.entity.Employee;
import com.emma.app.model.entity.EmployeeRole;
import com.emma.database.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

@WebServlet("/add-attendance")
public class AttendanceAction extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Database database = Database.getDbInstance();

        for(Employee employee : database.getEmployees()){
            String employeeId = employee.getEmployeeId();
            String employeeName = employee.getFirstName()+" "+ employee.getLastName();
            String attendStatus =  req.getParameter("attendanceStatus_" + employeeId);


            if (attendStatus != null){
                LocalTime currentTime =  LocalTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime displayTime = LocalTime.parse(currentTime.format(formatter), formatter);
                database.getAttendances().add(new Attendance(employeeId, employeeName, LocalDate.now(),displayTime,attendStatus));

            }

        }
        resp.sendRedirect("./attendance-sheet");


    }
}
