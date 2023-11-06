package com.emma.app.bean;

import com.emma.app.model.entity.Attendance;
import com.emma.app.model.entity.Employee;
import com.emma.database.Database;

import java.io.Serializable;

public class EmployeeBean implements EmployeeBeanI, Serializable {
    @Override
    public String employeeRecords() {
        StringBuilder trBuilder = new StringBuilder();
        trBuilder.append("<h2 style = \"text-align: center; color: #533535; background-color: #fff; padding: 10px;\">TATU EMPLOYEES </h2>" +
                "<table>\n" +
                "        <thead>\n" +
                "            <tr>\n" +
                "                <th>Employee ID</th>\n" +
                "                <th>Employee Firstname</th>\n" +
                "                <th>Employee Lastname</th>\n" +
                "                <th>Employee Role</th> \n" +
                "             </tr>\n" +
                "        </thead>");

        for (Employee employee : Database.getDbInstance().getEmployees()) {
            trBuilder.append(employee.tableRow());
        }
        trBuilder.append("</table>");
        return trBuilder.toString();
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
