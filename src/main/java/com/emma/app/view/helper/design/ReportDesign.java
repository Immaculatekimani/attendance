package com.emma.app.view.helper.design;

import com.emma.app.bean.EmployeeBeanI;
import com.emma.app.model.EmployeeRole;

import javax.inject.Named;

@Named("reports")
public class ReportDesign implements DesignI {

    @Override
    public String designer() {
        String reportHtml = "<h1 style=\"color: #3D0C11; text-align: center;\">VIEW ATTENDANCE RECORDS</h1>\n" +
                "<div class=\"row\">\n" +
                "    <div class=\"col-lg-12\">\n" +
                "        <!-- Role Form Card -->\n" +
                "        <div class=\"card mb-4\">\n" +
                "            <div class=\"card-header py-3 d-flex flex-row align-items-center justify-content-between\">\n" +
                "                <h6 class=\"m-0 font-weight-bold text-primary\">View Employee Attendance by Role</h6>\n" +
                "            </div>\n" +
                "            <div class=\"card-body\">\n" +
                "                <form method=\"post\">\n" +
                "                    <!-- Role Form Content -->\n" +
                "                    <div class=\"form-group row mb-3\">\n" +
                "                        <div class=\"col-xl-6\">\n" +
                "                            <label class=\"form-control-label\">Select Role<span class=\"text-danger ml-2\">*</span></label>\n" +
                "                            <select required name=\"role\" onchange=\"typeDropDown(this.value)\" class=\"form-control mb-3\">\n" +
                "                                <option>All</option>\n";

        // Loop through enum values to dynamically populate dropdown options
        for (EmployeeRole role : EmployeeRole.values()) {
            reportHtml += "<option value=\"" + role.name() + "\">" + role.name() + "</option>\n";
        }

        reportHtml +=
                "                            </select>\n" +
                        "                        </div>\n" +
                        "                        <div class=\"col-xl-6\">\n" +
                        "                            <label class=\"form-control-label\">Type<span class=\"text-danger ml-2\">*</span></label>\n" +
                        "                            <select required name=\"type\" onchange=\"typeDropDown(this.value)\" class=\"form-control mb-3\">\n" +
                        "                                <option value=\"1\">All</option>\n" +
                        "                                <option value=\"2\">By Single Date</option>\n" +
                        "                                <option value=\"3\">By Date Range</option>\n" +
                        "                            </select>\n" +
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "                    <!-- Role Form Content - Include date range picker -->\n" +
                        "                    <div id=\"singleDatePicker\" class=\"form-group row mb-3\" style=\"display: none;\">\n" +
                        "                        <div class=\"col-xl-6\">\n" +
                        "                            <label class=\"form-control-label\">Select Date<span class=\"text-danger ml-2\">*</span></label>\n" +
                        "                            <input type=\"date\" name=\"singleDate\" class=\"form-control mb-3\">\n" +
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "                    <div id=\"rangeDatePicker\" class=\"form-group row mb-3\" style=\"display: none;\">\n" +
                        "                        <div class=\"col-xl-12\"> \n" +
                        "                            <div class=\"row\">\n" +
                        "                                <div class=\"col-xl-6\">\n" +
                        "                                    <label class=\"form-control-label\">Start Date<span class=\"text-danger ml-2\">*</span></label>\n" +
                        "                                    <input type=\"date\" name=\"startDate\" class=\"form-control mb-3\">\n" +
                        "                                </div>\n" +
                        "                                <div class=\"col-xl-6\">\n" +
                        "                                    <label class=\"form-control-label\">End Date<span class=\"text-danger ml-2\">*</span></label>\n" +
                        "                                    <input type=\"date\" name=\"endDate\" class=\"form-control mb-3\">\n" +
                        "                                </div>\n" +
                        "                            </div>\n" +
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "                    <button type=\"submit\" name=\"view\" class=\"btn btn-primary\">View Attendance</button>\n" +
                        "                </form>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "</div>\n";

        return reportHtml;
    }

    @Override
    public String designer(EmployeeBeanI employeeBean) {
        return null;
    }
}
