package com.emma.app.view.helper.design;

import com.emma.app.bean.EmployeeBeanI;
import com.emma.app.model.Employee;
import com.emma.app.view.helper.HtmlComponent;

import javax.inject.Named;

@Named("employee")
public class EmployeeDesign implements DesignI {
    public String designer() {

        String empHtml = "<button id=\"openPopup\" class=\"submit-button\">Add Employee</button>" +
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
                "});</script>";
        return empHtml;

    }

    @Override
    public String designer(EmployeeBeanI employeeBean) {
        return null;
    }
}
