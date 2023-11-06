package com.emma.action;

import com.emma.app.bean.EmployeeBean;
import com.emma.app.bean.EmployeeBeanI;
import com.emma.app.view.html.AppPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("employee")
public class EmployeeAction extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        EmployeeBeanI employeeBean = new EmployeeBean();
        if (session.getAttribute("loggedInId") != null) {
            new AppPage().renderHtml(req, resp,  2,"<h2>Employee activity</h2> "+employeeBean.employeeRecords()) ;

        } else
            resp.sendRedirect("./");


    }
}
