package com.emma.app.action;

import com.emma.app.bean.EmployeeBeanI;
import com.emma.app.model.Employee;
import com.emma.database.SqlDatabase;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/updateEmployee")
public class UpdateEmployeeAction extends BaseAction{
    @EJB
    EmployeeBeanI employeeBean;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve data from the HTML form
        String employeeId = request.getParameter("employeeId"); // Assuming "employeeId" is the name attribute of the input field

        // Retrieve the existing employee from the database
        List<Employee> employees = employeeBean.list(Employee.class, "employee_id = ?", employeeId);
        if (((List<?>) employees).isEmpty()) {
            // Handle error: Employee not found
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Employee not found");
            return;
        }

        Employee existingEmployee = employees.get(0);

        // Update the fields of the existing employee with the new values from the form
        existingEmployee.setFirstName(request.getParameter("firstName"));
        existingEmployee.setLastName(request.getParameter("lastName"));
        existingEmployee.setLastName(request.getParameter("role"));
        existingEmployee.setLastName(request.getParameter("employeeImage"));
        // Update other fields as needed

        // Update the employee in the database
        //employeeBean.update(existingEmployee, "employee_id");

        // Redirect to a success page or display a success message
        response.sendRedirect("./employee");
    }
}
