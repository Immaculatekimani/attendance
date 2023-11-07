package com.emma.action;

import com.emma.app.bean.EmployeeBean;
import com.emma.app.bean.EmployeeBeanI;
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

@WebServlet("/add-employee")
public class AddEmployeeAction extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Database database = Database.getDbInstance();

        String employeeId = req.getParameter("employee-id");
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        EmployeeRole employeeRole = EmployeeRole.valueOf(req.getParameter("role"));
        HttpSession session = req.getSession();

        EmployeeBeanI employeeBean = new EmployeeBean();
        database.getEmployees().add(new Employee(employeeId, firstName, lastName, employeeRole));
        resp.sendRedirect("./employee");


    }
}