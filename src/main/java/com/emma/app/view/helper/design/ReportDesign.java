package com.emma.app.view.helper.design;

import com.emma.app.bean.EmployeeBeanI;

import javax.inject.Named;

@Named("reports")
public class ReportDesign implements DesignI {

    @Override
    public String designer() {
        return null;
    }

    @Override
    public String designer(EmployeeBeanI employeeBean) {
        return null;
    }
}
