package com.emma.app.bean;

import com.emma.app.model.entity.Attendance;
import com.emma.database.Database;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AttendanceBean implements AttendanceBeanI {
    @Override
    public String attendanceRecord() {

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

        for (Attendance attendance : Database.getDbInstance().getAttendances()) {
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
