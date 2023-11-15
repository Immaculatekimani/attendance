package com.emma.app.action;

import com.emma.app.bean.EmployeeBean;
import com.emma.app.bean.EmployeeBeanI;
import com.emma.app.model.Employee;
import com.emma.app.view.helper.HtmlComponent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("employee")
public class EmployeeAction extends BaseAction {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EmployeeBeanI employeeBean = new EmployeeBean();

        renderPage(req, resp, 2, "<button id=\"openPopup\" class=\"submit-button\">Add Employee</button>" +
                "<div id=\"popupForm\" class=\"modal\">" +
                "    <span class=\"close\" id=\"closePopup\">&times;</span>" +
                HtmlComponent.form(Employee.class) +
                "</div>" +

                "<script> document.getElementById(\"openPopup\").addEventListener(\"click\", function() {" +
                "  document.getElementById(\"popupForm\").style.display = \"block\";" +
                "});" +
                "document.getElementById(\"closePopup\").addEventListener(\"click\", function() {" +
                "  document.getElementById(\"popupForm\").style.display = \"none\";" +
                "});" +
                "window.addEventListener(\"click\", function(event) {" +
                "  if (event.target == document.getElementById(\"popupForm\")) {" +
                "    document.getElementById(\"popupForm\").style.display = \"none\";" +
                "  }" +
                "});</script>", Employee.class, employeeBean.list());


    }
}
