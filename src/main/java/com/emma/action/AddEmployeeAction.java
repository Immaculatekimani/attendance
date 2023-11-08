package com.emma.action;

import com.emma.app.bean.EmployeeBean;
import com.emma.app.bean.EmployeeBeanI;
import com.emma.app.model.entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/add-employee")
public class AddEmployeeAction extends BaseAction {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = new Employee();
        EmployeeBeanI employeeBean = new EmployeeBean();

        HttpSession httpSession = req.getSession();

        serializeForm(employee, req.getParameterMap());

        try {
            employeeBean.addorUpdateEmployee(employee);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("./employee");


    }
}