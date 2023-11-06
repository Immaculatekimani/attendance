package com.emma.app.bean;

import com.emma.app.model.entity.Attendance;
import com.emma.database.Database;

public class AttendanceBean implements AttendanceBeanI {
    @Override
    public String attendanceRecord() {

        StringBuilder trBuilder = new StringBuilder();
        trBuilder.append("<h2 style = \"text-align: center; color: #533535; background-color: #fff; padding: 10px;\">LATEST ATTENDANCE RECORDS </h2>" + "<table>" + "        <thead>" + "            <tr>" + "                <th>Employee ID</th>" + "                <th>Employee Name</th>" + "                <th>Date</th>" + "                <th>Time</th> " + "                <th>Attendance Status</th>" + "             </tr>" + "        </thead>");

        for (Attendance attendance : Database.getDbInstance().getAttendances()) {
            trBuilder.append(attendance.tableRow());
        }
        trBuilder.append("</table>");
        return trBuilder.toString();
    }

    @Override
    public Attendance addorUpdateAttendance(Attendance attendance) throws Exception {
        Database database = Database.getDbInstance();
        database.getAttendances().add(attendance);
        return attendance;
    }

    @Override
    public void deleteAttendance(Attendance attendance) {

    }
}
