package com.emma.app.view.helper.design;

import com.emma.app.bean.EmployeeBeanI;

import javax.inject.Named;

@Named("singleEmployee")
public class SingleEmployee implements DesignI {

    @Override
    public String designer() {
        String reportHtml = "<div class=\"row\">\n" +
                "    <div class=\"col-lg-12\">\n" +
                "        <!-- Form Basic -->\n" +
                "        <div class=\"card mb-4\">\n" +
                "            <div class=\"card-header py-3 d-flex flex-row align-items-center justify-content-between\">\n" +
                "                <h6 class=\"m-0 font-weight-bold text-primary\">View Employee Attendance</h6>\n" +
                "            </div>\n" +
                "            <div class=\"card-body\">\n" +
                "                <form method=\"post\">\n" +
                "                    <div class=\"form-group row mb-3\">\n" +
         "                        <div class=\"col-xl-6\">\n" +
                "                            <label class=\"form-control-label\">Type<span class=\"text-danger ml-2\">*</span></label>\n" +
                "                            <select required name=\"type\" onchange=\"typeDropDown(this.value)\" class=\"form-control mb-3\">\n" +
                "                                <option value=\"\">--Select--</option>\n" +
                "                                <option value=\"1\">All</option>\n" +
                "                                <option value=\"2\">By Single Date</option>\n" +
                "                                <option value=\"3\">By Date Range</option>\n" +
                "                            </select>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
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
                "</div>\n" ;

        return reportHtml;
    }

    @Override
    public String designer(EmployeeBeanI employeeBean) {
        return null;
    }
}

