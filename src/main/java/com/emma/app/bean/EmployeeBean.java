package com.emma.app.bean;

import com.emma.app.model.Employee;
import com.emma.app.view.helper.HtmlComponent;
import com.emma.database.Database;

import java.io.Serializable;
import java.util.List;

public class EmployeeBean implements EmployeeBeanI, Serializable {


    @Override
    public List<Employee> list() {
        return Database.getDbInstance().getEmployees();
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