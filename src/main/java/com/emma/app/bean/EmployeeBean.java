package com.emma.app.bean;

import com.emma.app.model.Attendance;
import com.emma.app.model.Employee;
import com.emma.app.model.EmployeeLog;
import com.emma.app.utility.TimeFormatter;
import com.emma.app.utility.exception.MyExceptionUtils;

import javax.ejb.*;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.NoResultException;
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
    public void employeeAction(String action, String employeeId, Employee employeeInput, String itemId) {
            if ("update".equals(action)) {
                employeeInput.setEmployeeId(employeeId);
                update(employeeInput);
                EmployeeLog log = new EmployeeLog();
                log.setEmployeeLogDetails("Details for " + employeeInput.getFirstName() + " have been updated at " + timeFormatter.timeDisplay());
                employeeLogEvent.fire(log);

            } else if ("delete".equals(action)) {
                // Perform delete operation
                deleteEmployee(itemId);
                EmployeeLog log = new EmployeeLog();
                log.setEmployeeLogDetails("Successfully deleted an employee at " + timeFormatter.timeDisplay());
                employeeLogEvent.fire(log);

            } else {
                addRecord(employeeInput);
                EmployeeLog log = new EmployeeLog();
                log.setEmployeeLogDetails("Successfully added an employee at " + timeFormatter.timeDisplay());
                employeeLogEvent.fire(log);
            }

    }

    public void deleteEmployee(String employeeId) {
        attendanceBean.deleteRecord(Attendance.class, "displayId", employeeId);
        deleteRecord(Employee.class, "employeeId", employeeId);
    }

    public Employee getEmployeeById(String employeeId) {
        TypedQuery<Employee> query = getDao().getEm().createNamedQuery("Employee.findById", Employee.class);
        query.setParameter("employeeId", employeeId);

        try {
            return query.getSingleResult();

        } catch (NoResultException e) {
            return null;
        }

    }

    public void update(Employee updatedEmployee) {
        // Retrieve the existing Employee by ID
        Employee existingEmployee = getEmployeeById(updatedEmployee.getEmployeeId());
        if (existingEmployee != null) {
            // Update the Employee properties
            existingEmployee.setFirstName(updatedEmployee.getFirstName());
            existingEmployee.setLastName(updatedEmployee.getLastName());
            existingEmployee.setRole(updatedEmployee.getRole());
            existingEmployee.setEmployeeImage(updatedEmployee.getEmployeeImage());

            addRecord(existingEmployee);
            // Flush changes to the database
            getDao().getEm().flush();

            List<Attendance> attendances = existingEmployee.getAttendances();
            if (attendances != null) {
                for (Attendance attendance : attendances) {
                    attendance.setEmployeeName(existingEmployee.getFirstName() + " " + existingEmployee.getLastName());
                    attendance.setEmployeeImage(existingEmployee.getEmployeeImage());
                    attendance.setDisplayId(existingEmployee.getEmployeeId());
                    attendance.setEmployee(existingEmployee);

                    attendanceBean.addRecord(attendance);
                }
                getDao().getEm().flush();
            }
        } else {
            MyExceptionUtils.throwNotFoundException("Employee", updatedEmployee.getEmployeeId());
        }

    }


}