package com.emma.app.action;

import com.emma.app.bean.AuthBean;
import com.emma.app.bean.AuthBeanI;
import com.emma.app.model.Attendance;
import com.emma.app.model.Employee;
import com.emma.app.model.EmployeeRole;
import com.emma.app.model.User;
import com.emma.database.Database;
import org.apache.commons.lang3.StringUtils;

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
            if (userDetails != null && StringUtils.isNotBlank(userDetails.getUsername())) {
                HttpSession httpSession = req.getSession(true);

                httpSession.setAttribute("loggedInId", new Date().getTime() + "");
                httpSession.setAttribute("username", userDetails.getUsername());

                resp.sendRedirect("./home");
            }


            PrintWriter print = resp.getWriter();
            print.write("<html><body>Invalid login details <a href=\".\"> Login again </a></body></html>");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
