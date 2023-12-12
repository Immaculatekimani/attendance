package com.emma.app.action;

import com.emma.app.bean.EmployeeBeanI;
import com.emma.app.model.Employee;
import com.emma.app.view.helper.HtmlComponent;
import com.emma.app.view.helper.design.DesignI;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("updateEmployee")
public class UpdateEmployee extends BaseAction {
    @EJB
    EmployeeBeanI employeeBean;
    @Inject
    @Named("employeeDesign")
    DesignI design;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeId = req.getParameter("employeeId");
        // Assuming you have an instance of Employee to edit, retrieve it from your data source
        Employee employeeToEdit = employeeBean.getEmployeeById(employeeId);
        String editFormHtml = HtmlComponent.editForm(Employee.class, employeeToEdit);


        // Set the combined HTML content as an attribute in the request
        req.setAttribute("editForm", editFormHtml);

        // Forward to a JSP or write the HTML directly in the servlet
        RequestDispatcher dispatcher = req.getRequestDispatcher("./app/editEmployee.jsp");
        dispatcher.forward(req, resp);


    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeId = req.getParameter("employeeId");

        // Use serializeEmployeeForm to create an Employee instance from the form data
        Employee updatedEmployee = serializeForm(Employee.class, req.getParameterMap());

        // Set the employeeId explicitly (assuming it's not editable in the form)
        updatedEmployee.setEmployeeId(employeeId);

        // Call the update method in the bean
        employeeBean.update(updatedEmployee);

        resp.sendRedirect("./employee");


    }
}
