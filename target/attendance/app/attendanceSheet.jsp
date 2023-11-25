<%@ page isELIgnored="false" %>
<%@ page import="java.util.List" %>
<%@ page import="com.emma.app.model.Employee" %>
<%@ page import="com.emma.app.model.Attendance" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.emma.app.model.Attendance" %>

<jsp:useBean id="topBar" class="com.emma.app.view.helper.TopBar" scope="request"/>
<jsp:useBean id="menuBean" class="com.emma.app.displaybean.MenuBean" scope="request"/>

<c:set var="username" value="${sessionScope.username}" />
${topBar.setSessionUsername(username)}

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

    <h2 style="text-align: center; color: #533535; background-color: #fff; padding: 10px;">TODAY'S ATTENDANCE! </h2>

    <form action="./attendance-sheet" onsubmit="showAttendanceAlert()" method="post">
        <table>
            <thead>
                <tr>
                    <th>Employee Image</th>
                    <th>Employee ID</th>
                    <th>Employee Name</th>
                    <th>Attendance Time</th>
                    <th>Attendance Status</th>
                    <th>Submit Attendance</th>
                </tr>
            </thead>

            <% List<Attendance> todaysAttendances = (List<Attendance>)request.getAttribute("todaysAttendances");
               LocalTime displayTime = (LocalTime)request.getAttribute("displayTime");

               for (Employee employee : (List<Employee>)request.getAttribute("allEmployees")) {
                   boolean isAttendanceEntered = todaysAttendances.stream()
                       .anyMatch(attendance -> attendance.getEmployeeID().equals(employee.getEmployeeId()));
            %>

            <% if (!isAttendanceEntered) { %>
                <tr id="row_<%= employee.getEmployeeId() %>">
                    <td><img src = "images/prof/<%= employee.getEmployeeImage().strip() %>" class="prof"></td>
                    <td><%= employee.getEmployeeId().strip() %></td>
                    <td><%= employee.getFirstName().strip() + " " + employee.getLastName() %></td>
                    <td><%= displayTime %></td>
                     <td>
                            <input type="radio" name="attendanceStatus" value="<%= employee.getEmployeeId() %>_Present"> Present
                            <input type="radio" name="attendanceStatus" value="<%= employee.getEmployeeId() %>_Absent"> Absent
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
