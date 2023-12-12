package com.emma.app.action;

import com.emma.app.bean.EmployeeBeanI;
import com.emma.app.model.Employee;
import com.emma.app.view.helper.HtmlComponent;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("updateEmployee")
public class UpdateEmployeeAction extends BaseAction {
    @EJB
    EmployeeBeanI employeeBean;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeId = req.getParameter("employeeId");

        // Fetch employee data based on the employeeId (you need to implement this)
        Employee employeeToEdit = employeeBean.getEmployeeById(employeeId);

        // Create HTML for the edit form using the existing editForm method
        String editFormHtml = HtmlComponent.editForm(Employee.class, employeeToEdit);

        // Send the HTML response
        resp.setContentType("text/ahtml");
        resp.getWriter().write(editFormHtml);

    }

}
