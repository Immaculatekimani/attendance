package com.emma.app.bean;

import com.emma.app.model.Attendance;
import com.emma.app.model.AttendanceLog;
import com.emma.app.model.Employee;
import com.emma.app.model.EmployeeRole;
import com.emma.app.utility.TimeFormatter;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Stateless(name = "attendance/AttendanceBean")
@Remote
public class AttendanceBean extends GenericBean<Attendance> implements AttendanceBeanI {

    @EJB
    private EmployeeBeanI employeeBean;
    @Inject
    private Event<AttendanceLog> event;
    @Inject
    private TimeFormatter timeFormatter;

    public Attendance logAttendance(Attendance attendance, String selectedValue) {
        String[] parts = selectedValue.split("_");
        String employeeId = parts[0];
        String attendStatus = parts[1];

        Employee employee = employeeBean.find(Employee.class, "employeeId", employeeId);
        String employeeName = employee.getFirstName() + " " + employee.getLastName();

        if (employee != null) {
            LocalTime displayTime = timeFormatter.timeDisplay();
            attendance.setEmployeeImage(employee.getEmployeeImage());
            attendance.setEmployeeName(employeeName);
            attendance.setDisplayId(employeeId);
            attendance.setEmployee(employee);
            attendance.setAttendanceDate(LocalDate.now());
            attendance.setAttendanceTime(displayTime);

            // No existing record, create a new one
            Attendance existingRecord = findExistingRecord(employee.getAttendances(), LocalDate.now());
            if (existingRecord == null) {
                handleAttendanceStatus(attendance, attendStatus);

                try {
                    addRecord(attendance);
                    AttendanceLog add = new AttendanceLog();
                    add.setAttendanceDetails("Attendance for " + employeeName + " has been added at " + timeFormatter.timeDisplay());
                    event.fire(add);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                // Update existing record
                handleAttendanceStatus(attendance, attendStatus);
                existingRecord.setTimeOut(attendance.getAttendanceTime());
                existingRecord.setAttendanceStatus(attendance.getAttendanceStatus());

                try {
                    update(existingRecord, "display_id", employeeId);
                    AttendanceLog editAttend = new AttendanceLog();
                    editAttend.setAttendanceDetails("Attendance for " + employeeName + " has been updated at " + timeFormatter.timeDisplay());
                    event.fire(editAttend);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return attendance;
    }


    public Attendance findExistingRecord(List<Attendance> records, LocalDate currentDate) {
        for (Attendance record : records) {
            if (record.getAttendanceDate().equals(currentDate)) {
                return record;
            }
        }
        return null;
    }

    private void handleAttendanceStatus(Attendance attendance, String attendStatus) {
        switch (attendStatus) {
            case "Entry":
                if (attendance.getAttendanceTime().isAfter(LocalTime.parse("08:00", DateTimeFormatter.ofPattern("HH:mm")))) {
                    attendance.setAttendanceStatus("Late");
                    attendance.setJoiningStatus("Late");
                } else {
                    attendance.setAttendanceStatus("Present");
                    attendance.setJoiningStatus("In-Time");
                }
                attendance.setTimeIn(attendance.getAttendanceTime());
                break;
            case "Exit":
                attendance.setTimeOut(attendance.getAttendanceTime());

                // Check if the exit time is before 5:00 PM
                if (attendance.getTimeOut().isBefore(LocalTime.parse("17:00", DateTimeFormatter.ofPattern("HH:mm")))) {
                    attendance.setAttendanceStatus("HalfDay");
                } else {
                    attendance.setAttendanceStatus("FullDay");
                }
                break;
            case "Absent":
                attendance.setAttendanceStatus("Absent");
                break;
        }
    }

    @Override
    public List<Attendance> getEmployeeAttendance(String employeeId) {
        try {
            String whereClause = "employee_id = ?1";
            return select(Attendance.class, whereClause, employeeId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Attendance> getAttendanceByRole(String employeeRole) {
        try {
            EmployeeRole role = EmployeeRole.valueOf(employeeRole);
            System.out.println("Role: {} ------------------------------------------" + role);

            // Use the named query to fetch attendances based on employee role
            TypedQuery<Attendance> query = getDao().getEm().createNamedQuery("Attendance.findByRole", Attendance.class);
            query.setParameter("role", role);

            return query.getResultList();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Attendance> getTodaysAttendance() {
        String namedQuery = "Attendance.findTodaysAttendance";
        Query query = getDao().getEm().createNamedQuery(namedQuery, Attendance.class);

        return query.getResultList();
    }
}
