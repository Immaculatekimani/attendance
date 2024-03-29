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
                    add.setAttendanceDetails("Attendance for " + employeeName + " has been added at " +displayTime);
                    event.fire(add);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                // Update existing record
                handleAttendanceStatus(attendance, attendStatus);

                try {
                    update(employeeId, LocalDate.now(), attendance.getTimeOut(), attendance.getAttendanceStatus());
                    AttendanceLog editAttend = new AttendanceLog();
                    editAttend.setAttendanceDetails("Attendance for " + employeeName + " has been updated at " + displayTime);
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
            if (record.getAttendanceDate().isEqual(currentDate)) {
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
                    attendance.setTimeOut(LocalTime.MIN);
                } else {
                    attendance.setAttendanceStatus("Present");
                    attendance.setJoiningStatus("In-Time");
                    attendance.setTimeOut(LocalTime.parse("00.00", DateTimeFormatter.ofPattern("HH:mm")));
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
                attendance.setJoiningStatus("Not Attended");
                attendance.setTimeOut(LocalTime.MIN);
                attendance.setTimeIn(LocalTime.MIN);
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

            TypedQuery<Attendance> query = getDao().getEm().createNamedQuery("Attendance.findByRole", Attendance.class);
            query.setParameter("role", role);

            return query.getResultList();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Attendance> selectByDateAndRole(String date, String role) {
        try {
            EmployeeRole employeeRole = EmployeeRole.valueOf(role);
            LocalDate attendanceDate = LocalDate.parse(date);

            TypedQuery<Attendance> query = getDao().getEm().createNamedQuery("Attendance.findByDateAndRole", Attendance.class);
            query.setParameter("attendanceDate", attendanceDate);
            query.setParameter("employeeRole", employeeRole);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Attendance> selectByRoleAndDateRange(String startDate, String endDate, String role) {
        try {
            TypedQuery<Attendance> query = getDao().getEm().createNamedQuery("Attendance.findByDateRangeAndRole", Attendance.class);

            EmployeeRole employeeRole = EmployeeRole.valueOf(role);
            LocalDate attendanceStartDate = LocalDate.parse(startDate);
            LocalDate attendanceEndDate = LocalDate.parse(endDate);

            query.setParameter("startDate", attendanceStartDate);
            query.setParameter("endDate", attendanceEndDate);
            query.setParameter("role", employeeRole);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public void update(String displayId, LocalDate attendanceDate, LocalTime timeOut, String attendanceStatus) {
        try {
            String jpqlQuery = "UPDATE Attendance a SET a.timeOut = :timeOut, a.attendanceStatus = :attendanceStatus " +
                    "WHERE a.displayId = :displayId AND a.attendanceDate = :attendanceDate";

            Query query = getDao().getEm().createQuery(jpqlQuery);
            query.setParameter("timeOut", timeOut);
            query.setParameter("attendanceStatus", attendanceStatus);
            query.setParameter("displayId", displayId);
            query.setParameter("attendanceDate", attendanceDate);

            query.executeUpdate();
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
