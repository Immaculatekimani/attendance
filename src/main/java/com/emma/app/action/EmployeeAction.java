package com.emma.app.action;

import com.emma.app.bean.EmployeeBeanI;
import com.emma.app.model.Employee;
import com.emma.app.utility.exception.MyExceptionUtils;
import com.emma.app.view.helper.design.DesignI;

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
    @Named("employeeDesign")
    DesignI design;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            renderPage(req, resp, 2, design.designer(), Employee.class, employeeBean.select(Employee.class, ""));

        } catch (Exception e) {
            MyExceptionUtils.redirectToErrorPage(req, resp, e);

        }


    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String action = req.getParameter("action");
            String employeeId = req.getParameter("employeeId");
            Employee employeeInput = serializeForm(Employee.class, req.getParameterMap());
            String itemID = req.getParameter("itemId");
            employeeBean.employeeAction(action, employeeId, employeeInput, itemID);
            resp.sendRedirect("./employee");

        } catch (Exception e) {
            MyExceptionUtils.redirectToErrorPage(req, resp, e);

        }


    }
}
