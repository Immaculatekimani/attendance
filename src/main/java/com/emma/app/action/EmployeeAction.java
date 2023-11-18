package com.emma.app.action;

import com.emma.app.bean.EmployeeBean;
import com.emma.app.bean.EmployeeBeanI;
import com.emma.app.model.Employee;
import com.emma.app.view.helper.design.EmployeeDesign;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("employee")
public class EmployeeAction extends BaseAction {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EmployeeBeanI employeeBean = new EmployeeBean();

        renderPage(req, resp, 2, EmployeeDesign.design(), Employee.class, employeeBean.list(Employee.class));


    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeBeanI employeeBean = new EmployeeBean();

        try {
            employeeBean.addOrUpdateRecord(serializeForm(Employee.class, req.getParameterMap()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("./employee");


    }
}
