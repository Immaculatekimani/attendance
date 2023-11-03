package com.emma.app.bean;

import com.emma.app.model.entity.Attendance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AttendanceBean implements AttendanceBeanI {
    @Override
    public String attendanceRecord() {
        List<Attendance> attendances = new ArrayList<>();

        attendances.add(new Attendance("E12345", "John Doe", LocalDate.of(2023, 11, 5), LocalTime.of(8, 30), "Present"));
        attendances.add(new Attendance("E38292", "Taylor Swift", LocalDate.of(2023, 11, 5), LocalTime.of(8, 50), "Present"));
        attendances.add(new Attendance("E39293", "Eminem Mark", LocalDate.of(2023, 11, 5), LocalTime.of(8, 55), "Absent"));

        StringBuilder trBuilder = new StringBuilder();
        trBuilder.append("<table>\n" +
                        "        <thead>\n" +
                        "            <tr>\n" +
                        "                <th>Employee ID</th>\n" +
                        "                <th>Employee Name</th>\n" +
                        "                <th>Date</th>\n" +
                        "                <th>Time</th> <!-- Add the Time column -->\n" +
                        "                <th>Attendance Status</th>\n" +
                        "             </tr>\n" +
                        "        </thead>");

        for (Attendance attendance : attendances) {
            trBuilder.append(attendance.tableRow());
        }
        trBuilder.append("</table>");
        return trBuilder.toString();
    }

    @Override
    public Attendance addorUpdateAttendance(Attendance attendance) throws Exception {
        return null;
    }

    @Override
    public void deleteAttendance(Attendance attendance) {

    }
}
