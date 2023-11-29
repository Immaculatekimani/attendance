package com.emma.app.bean;

import com.emma.app.model.Employee;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Stateless(name = "attendance/EmployeeBean")
@Remote
public class EmployeeBean extends GenericBean<Employee> implements EmployeeBeanI {


    @Override
    public void employeeAction(String action, String employeeId, Employee employeeInput) {
        try {

            if ("update".equals(action)) {
                System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&"+employeeInput.toString());
                // Perform update operation
                update(employeeInput, "employee_id", employeeId);
            } else if ("delete".equals(action)) {
                // Perform delete operation
                deleteRecord(Employee.class, "employee_id", employeeId );
            } else {
                // Default to add operation if action is not specified
                addRecord(employeeInput);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}