package com.emma.app.action;

import com.emma.app.bean.EmployeeBean;
import com.emma.app.bean.EmployeeBeanI;
import com.emma.app.model.Employee;
import com.emma.app.view.helper.design.DesignI;
import com.emma.app.view.helper.design.EmployeeDesign;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("employee")
public class EmployeeAction extends BaseAction {
    @EJB
    EmployeeBeanI employeeBean;
    @Inject
    @Named("employee")
    DesignI design;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        renderPage(req, resp, 2, design.designer(), Employee.class, employeeBean.select(Employee.class,""));


    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String employeeId = req.getParameter("itemId");
        Employee employeeInput = serializeForm(Employee.class, req.getParameterMap());

        employeeBean.employeeAction(action, employeeId, employeeInput);
        resp.sendRedirect("./employee");


    }
}
