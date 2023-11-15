package com.emma.app.bean;

import com.emma.app.model.Employee;
import com.emma.app.view.helper.HtmlComponent;
import com.emma.database.Database;

import java.io.Serializable;

public class EmployeeBean implements EmployeeBeanI, Serializable {
    @Override
    public String employeeRecords() {
        StringBuilder title = new StringBuilder();
        title.append("<h2 style = \"text-align: center; color: #533535; background-color: #fff; padding: 10px;\">TECH-STAR EMPLOYEES </h2>");


        return title.toString() + HtmlComponent.table(Database.getDbInstance().getEmployees());
    }

    @Override
    public Employee addorUpdateEmployee(Employee employee) throws Exception {
        Database database = Database.getDbInstance();

        database.getEmployees().add(employee);

        return employee;
    }

    @Override
    public void deleteEmployee(Employee employee) {

    }
}