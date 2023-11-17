package com.emma.app.action;

import com.emma.app.bean.AuthBean;
import com.emma.app.bean.AuthBeanI;
import com.emma.app.model.Attendance;
import com.emma.app.model.Employee;
import com.emma.app.model.EmployeeRole;
import com.emma.app.model.User;
import com.emma.database.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;


@WebServlet(urlPatterns = "/login")

public class LoginAction extends BaseAction {

    AuthBeanI authBean = new AuthBean();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        if (httpSession.getAttribute("loggedInId") != null) {
            resp.sendRedirect("./home");
        } else
            resp.sendRedirect("./");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = serializeForm(User.class, req.getParameterMap());

        try {
            User userDetails = authBean.authenticate(loginUser);
            if (userDetails != null) {
                HttpSession httpSession = req.getSession(true);

                httpSession.setAttribute("loggedInId", new Date().getTime() + "");
                httpSession.setAttribute("username", loginUser.getUsername());

                resp.sendRedirect("./home");
            }


            PrintWriter print = resp.getWriter();
            print.write("<helper><body>Invalid login details <a href=\".\"> Login again </a></body></helper>");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Database db = Database.getDbInstance();
        db.getAttendances().add(new Attendance("E12345", "John Doe", LocalDate.of(2023, 11, 5), LocalTime.of(8, 30), "Present"));
        db.getAttendances().add(new Attendance("E38292", "Taylor Swift", LocalDate.of(2023, 11, 5), LocalTime.of(8, 50), "Present"));
        db.getAttendances().add(new Attendance("E39293", "Eminem Mark", LocalDate.of(2023, 11, 5), LocalTime.of(8, 55), "Absent"));
        db.getAttendances().add(new Attendance("E37829", "Hope Kimani", LocalDate.of(2023, 11, 5), LocalTime.of(8, 40), "Present"));
        db.getAttendances().add(new Attendance("E27738", "Smino Otieno", LocalDate.of(2023, 11, 5), LocalTime.of(8, 25), "Absent"));
        db.getAttendances().add(new Attendance("E40283", "Jack Ireri", LocalDate.of(2023, 11, 5), LocalTime.of(8, 34), "Present"));
        db.getAttendances().add(new Attendance("E10392", "Cynthia Wangari", LocalDate.of(2023, 11, 5), LocalTime.of(8, 52), "Absent"));
        db.getAttendances().add(new Attendance("E29390", "Grace Emily", LocalDate.of(2023, 11, 5), LocalTime.of(8, 41), "Present"));

        db.getEmployees().add(new Employee("E12345","John", "Doe",EmployeeRole.DEVELOPER));
        db.getEmployees().add(new Employee("E38292","Taylor", "Swift", EmployeeRole.DEVELOPER));
        db.getEmployees().add(new Employee("E39293","Eminem", "Mark",EmployeeRole.QA));
        db.getEmployees().add(new Employee("E37829","Hope", "Kimani",EmployeeRole.DEVOPS));
        db.getEmployees().add(new Employee("E27738","Smino", "Otieno",EmployeeRole.DEVOPS));
        db.getEmployees().add(new Employee("E40283","Jack", "Ireri",EmployeeRole.QA));
        db.getEmployees().add(new Employee("E10392","Cynthia", "Wangari",EmployeeRole.QA));
        db.getEmployees().add(new Employee("E29390","Grace", "Emily",EmployeeRole.DEVELOPER));


    }

}
