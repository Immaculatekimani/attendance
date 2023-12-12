package com.emma.app.bean;

import com.emma.app.model.Attendance;
import com.emma.app.model.Employee;
import com.emma.app.model.EmployeeLog;
import com.emma.app.utility.TimeFormatter;

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
        try {

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

    public Employee getEmployeeById(String employeeId) {
        TypedQuery<Employee> query = getDao().getEm().createNamedQuery("Employee.findById", Employee.class);
        query.setParameter("employeeId", employeeId);

        try {
            return query.getSingleResult();

        } catch (NoResultException e) {
            return null; // Handle the case where no employee is found with the given ID
        }

    }

    public void update(Employee updatedEmployee) {
        // Retrieve the existing Employee by ID
        Employee existingEmployee = getEmployeeById(updatedEmployee.getEmployeeId());

        // Update the Employee properties
        existingEmployee.setFirstName(updatedEmployee.getFirstName());
        existingEmployee.setLastName(updatedEmployee.getLastName());
        existingEmployee.setRole(updatedEmployee.getRole());
        existingEmployee.setEmployeeImage(updatedEmployee.getEmployeeImage());

        // Use the same EntityManager to persist the changes to the Employee
        addRecord(existingEmployee);
        // Flush changes to the database
        getDao().getEm().flush();

        // Now that the Employee is persisted, update relevant information in each attendance record
        List<Attendance> attendances = existingEmployee.getAttendances();
        if (attendances != null) {
            for (Attendance attendance : attendances) {
                attendance.setEmployeeName(existingEmployee.getFirstName() + " " + existingEmployee.getLastName());
                attendance.setEmployeeImage(existingEmployee.getEmployeeImage());
                attendance.setDisplayId(existingEmployee.getEmployeeId());
                attendance.setEmployee(existingEmployee);

                attendanceBean.addRecord(attendance);
            }
            // Flush changes to the database after updating all Attendance records
            getDao().getEm().flush();
        }
    }


}