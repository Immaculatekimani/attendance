package com.emma.action;

import com.emma.app.bean.EmployeeBean;
import com.emma.app.bean.EmployeeBeanI;
import com.emma.app.model.entity.EmployeeRole;
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
        StringBuilder rolesDropDown = new StringBuilder();
        rolesDropDown.append("<label for=\"role\">Employee Role:</label><br>");
        rolesDropDown.append("<select id=\"role\" name=\"role\">");

        for (EmployeeRole role : EmployeeRole.values()) {
            rolesDropDown.append("<option value=\"").append(role).append("\">").append(role).append("</option>");
        }

        rolesDropDown.append("</select><br><br>");
        new AppPage().renderHtml(req, resp, 2, "<button id=\"openPopup\" class=\"submit-button\">Add Employee</button>" +
                "<div id=\"popupForm\" class=\"modal\">" +
                "  <form action=\"./add-employee\" method=\"post\" class=\"modal-content\">" +
                "    <span class=\"close\" id=\"closePopup\">&times;</span>" +
                "    <h2>Employee activity</h2>" +
                "    <input type=\"text\" id=\"employee-id\" placeholder=\"Enter Employee ID\" name=\"employee-id\" class=\"form-control\">" +
                "    <input type=\"text\" id=\"firstname\" placeholder=\"Enter First Name \"name=\"firstname\" class=\"form-control\">" +
                "    <input type=\"text\" id=\"lastname\" placeholder=\"Enter Last Name \"name=\"lastname\" class=\"form-control\">" +
                rolesDropDown.toString() +
                "    <input type=\"submit\" value=\"Submit\" class=\"submit-button\">" +
                "  </form>" +
                "</div>" +
                employeeBean.employeeRecords() +
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
                "});</script>");


    }
}
