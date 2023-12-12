package com.emma.app.view.helper.design;

import com.emma.app.bean.EmployeeBeanI;
import com.emma.app.bean.EmployeeObserver;
import com.emma.app.model.Employee;
import com.emma.app.model.EmployeeLog;
import com.emma.app.view.helper.HtmlComponent;

import javax.ejb.EJB;
import javax.inject.Named;
import java.util.List;

@Named("editEmployeeDesign")
public abstract class EditEmployeeDesign implements DesignI {
    @EJB
    EmployeeObserver employeeLogsObserver;

    @EJB
    EmployeeBeanI employeeBean;
    public String designer(String employeeId) {
        List<EmployeeLog> employeeLogs = employeeLogsObserver.employeeLogs();

        String empHtml = "<div id=\"popupForm\" class=\"modal\">" +
                "    <span class=\"close\" id=\"closePopup\">&times;</span>" +
                HtmlComponent.editForm(Employee.class, employeeId) +
                "</div>" +
                "<div class=\"fixed-bottom-emp\" style=\"background-color: #3D0C11;  style=\"top: 80%;\">\n" +
                "       <div class=\"row\">\n" +
                "           <div class=\"col-xl-6 col-xl-12\">\n" +
                "               <div class=\"card\">\n" +
                "                   <div class=\"card-body\" style=\"background-color: #068DA9; color:white;\">\n" +
                "                       <h3>Recent Employee Activities</h3>\n" +
                "                       <div id=\"logs\">\n";

        for (EmployeeLog log : employeeLogs) {
            empHtml += "<p>" + log.getEmployeeLogDetails() + "</p>\n";
        }

        empHtml += "</div>\n" +
                "                   </div>\n" +
                "               </div>\n" +
                "           </div>\n" +
                "       </div>\n" +
                "   </div>" +
                "   </div>" +

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
