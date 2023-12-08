package com.emma.app.bean;

import com.emma.app.model.Attendance;
import com.emma.app.model.Employee;
import com.emma.app.model.EmployeeLog;
import com.emma.app.model.EmployeeRole;
import com.emma.app.utility.TimeFormatter;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless(name = "attendance/EmployeeBean")
@Remote
public class EmployeeBean extends GenericBean<Employee> implements EmployeeBeanI {
    @Inject
    private Event<EmployeeLog> employeeLogEvent;
    @Inject
    private TimeFormatter timeFormatter;
    @EJB
    private AttendanceBeanI attendanceBean;

    @Override
    public void employeeAction(String action, String employeeId, Employee employeeInput) {
        try {

            if ("update".equals(action)) {
                System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&" + employeeInput.toString());
                // Perform update operation
                update(employeeInput, "employee_id", employeeId);
            } else if ("delete".equals(action)) {
                // Perform delete operation
                deleteEmployee(employeeId);
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

    public void deleteEmployee(String employeeId) {
        attendanceBean.deleteRecord(Attendance.class, "displayId", employeeId);
        deleteRecord(Employee.class, "employeeId", employeeId);
    }

}