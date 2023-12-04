package com.emma.app.bean;

import com.emma.app.model.Employee;
import com.emma.app.model.EmployeeLog;
import com.emma.app.utility.TimeFormatter;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.List;

@Stateless(name = "attendance/EmployeeBean")
@Remote
public class EmployeeBean extends GenericBean<Employee> implements EmployeeBeanI {
    @Inject
    private Event<EmployeeLog> employeeLogEvent;
    @Inject
    private TimeFormatter timeFormatter;

    @Override
    public void employeeAction(String action, String employeeId, Employee employeeInput) {
        try {

            if ("update".equals(action)) {
                System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&" + employeeInput.toString());
                // Perform update operation
                update(employeeInput,"");
            } else if ("delete".equals(action)) {
                // Perform delete operation
                deleteRecord(new Employee());
                EmployeeLog log = new EmployeeLog();
                log.setEmployeeLogDetails("Successfully deleted " + employeeId + " at " + timeFormatter.timeDisplay());
                employeeLogEvent.fire(log);

            } else {
                // Default to add operation if action is not specified
                addRecord(employeeInput);
                EmployeeLog log = new EmployeeLog();
                log.setEmployeeLogDetails("Successfully added an employee at " + timeFormatter.timeDisplay());
                employeeLogEvent.fire(log);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Employee> getEmployeeByRole(String employeeRole) {
        try {
            return select(Employee.class, "role = ?1", employeeRole);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}