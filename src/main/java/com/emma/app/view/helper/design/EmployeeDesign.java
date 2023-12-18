package com.emma.app.view.helper.design;

import com.emma.app.bean.EmployeeBeanI;
import com.emma.app.bean.EmployeeObserver;
import com.emma.app.model.AttendanceLog;
import com.emma.app.model.Employee;
import com.emma.app.model.EmployeeLog;
import com.emma.app.view.helper.HtmlComponent;

import javax.ejb.EJB;
import javax.inject.Named;
import java.util.List;

@Named("employeeDesign")
public class EmployeeDesign implements DesignI {
    @EJB
    EmployeeObserver employeeLogsObserver;

    public String designer() {
        List<EmployeeLog> employeeLogs = employeeLogsObserver.employeeLogs();

        String empHtml = "<button id=\"openPopup\" class=\"submit-button\">Add Employee</button>" +
                "<div id=\"popupForm\" class=\"modal\">" +
                "    <span class=\"close\" id=\"closePopup\">&times;</span>" +
                HtmlComponent.form(Employee.class) +
                "</div>" +
                "<div class= \"searchDiv\">" +
                "<h1 style=\" color: #3D0C11;\n" +
                "    text-align: center; \"> EMPLOYEES LISTING </h1>" +
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
                "   </div>";

        return empHtml;

    }

    @Override
    public String designer(EmployeeBeanI employeeBean) {
        return null;
    }
}
