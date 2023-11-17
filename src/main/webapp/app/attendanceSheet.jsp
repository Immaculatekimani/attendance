<%@ page import="java.time.LocalTime" %>
<%@ page isELIgnored="false" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.emma.app.model.Employee" %>
<%@ page import="com.emma.database.Database" %>
<jsp:useBean id="topBar" class="com.emma.app.view.helper.TopBar" scope="request"/>
<jsp:useBean id="menuBean" class="com.emma.app.displaybean.MenuBean" scope="request"/>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="java.util.List" %>
<%@ page import="com.emma.app.model.Attendance" %>


<!DOCTYPE html>
<html>
<head>
    <link href="./app/style/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="./app/style/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="./app/style/indexStyle.css">

</head>
<body class= "body-style">
    <c:set var="activeMenu" value='${requestScope.activeMenu}' />
            ${menuBean.setActiveMenu(activeMenu)}
            ${topBar.menu(menuBean.activeMenu)}
    </c:set>

    <% LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime displayTime = LocalTime.parse(currentTime.format(formatter), formatter);

       List<Attendance> allAttendances = Database.getDbInstance().getAttendances();
       LocalDate currentDate = LocalDate.now();

       // Filter the attendances for the current date
       List<Attendance> todaysAttendances = allAttendances.stream()
               .filter(attendance -> attendance.getAttendanceDate().equals(currentDate))
               .collect(Collectors.toList());
    %>
    <h2 style="text-align: center; color: #533535; background-color: #fff; padding: 10px;">TODAY'S ATTENDANCE! </h2>

    <form action="./attendance-sheet" onsubmit= "showAttendanceAlert()" method="post">
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
                <%-- Check if attendance for today has been entered for this employee --%>
                <% boolean isAttendanceEntered = todaysAttendances.stream()
                    .anyMatch(attendance -> attendance.getEmployeeID().equals(employee.getEmployeeId()));
                %>

                <% if (!isAttendanceEntered) { %>
                    <tr id="row_<%= employee.getEmployeeId() %>">
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
            <% } %>
        </table>
    </form>
<script>
    function showAttendanceAlert() {
        alert("Attendance submitted successfully!");
    }
</script>
</body>
</html>
