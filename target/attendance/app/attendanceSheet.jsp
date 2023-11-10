<%@ page import="java.time.LocalTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.emma.app.model.entity.Employee" %>
<%@ page import="com.emma.database.Database" %>
<jsp:useBean id="topBar" class="com.emma.app.view.toolbar.TopBar" scope="request"/>
<jsp:useBean id="menuBean" class="com.emma.app.displaybean.MenuBean" scope="request"/>


<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="./app/indexStyle.css">
</head>
<body>
    <% menuBean.setActiveMenu((int)request.getAttribute("activeMenu")); %>
    <%= topBar.menu(menuBean.getActiveMenu()) %>

     <h2>Welcome <%= session.getAttribute("username") %></h2>
    <% LocalTime currentTime = LocalTime.now();
                       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                       LocalTime displayTime = LocalTime.parse(currentTime.format(formatter), formatter);
                       %>
    <h2 style="text-align: center; color: #533535; background-color: #fff; padding: 10px;">TODAY'S ATTENDANCE! </h2>

    <form action="./add-attendance" method="post">
        <table>
            <thead>
                <tr>
                    <th>Employee ID</th>
                    <th>Employee Name</th>
                    <th>Attendance Time</th>
                    <th>Attendance Status</th>
                    <th>Submit Attendance</th>
                </tr>
            </thead>

            <% for (Employee employee : Database.getDbInstance().getEmployees()) { %>
                <tr>
                    <td><%= employee.getEmployeeId().strip() %></td>
                    <td><%= employee.getFirstName().strip() + " " + employee.getLastName() %></td>
                    <td><%= displayTime %></td>
                    <td>
                        <input type="radio" name="attendanceStatus_<%= employee.getEmployeeId() %>" value="Present"> Present
                        <input type="radio" name="attendanceStatus_<%= employee.getEmployeeId() %>" value="Absent"> Absent
                    </td>
                    <td><input type="submit" class="submit-button" value="Submit"></td>
                </tr>
            <% } %>
        </table>
    </form>

</body>
</html>
