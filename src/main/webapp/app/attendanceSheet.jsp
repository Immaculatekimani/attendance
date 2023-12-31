<%@ page isELIgnored="false" %>
<%@ page import="java.util.List" %>
<%@ page import="com.emma.app.model.Employee" %>
<%@ page import="com.emma.app.model.Attendance" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.emma.app.model.Attendance" %>
<%@ page import="com.emma.app.model.AttendanceLog" %>


<jsp:useBean id="topBar" class="com.emma.app.view.helper.TopBar" scope="request"/>
<jsp:useBean id="menuBean" class="com.emma.app.displaybean.MenuBean" scope="request"/>

<c:set var="username" value="${sessionScope.username}" />
${topBar.setSessionUsername(username)}

<!DOCTYPE html>
<html>
<head>
     <script src="https://kit.fontawesome.com/8e79aa9e2c.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="./app/style/indexStyle.css">
</head>
<body class="body-style">
    <c:set var="activeMenu" value='${requestScope.activeMenu}' />
    ${menuBean.setActiveMenu(activeMenu)}
    ${topBar.menu(menuBean.activeMenu)}

    <h2 style="text-align: center; color: #533535; background-color: #fff; padding: 10px;">TODAY'S ATTENDANCE! </h2>
    <div class="row mb-3" style="margin-left:10%">
        <!-- Time Card -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card h-100">
                <div class="card-body time-card">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <h2>Current Time:<p id="currentTime"></p></h2>
                        </div>
                        <div class="col-auto">
                            <i class="far fa-clock fa-2x text-info"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Search Input -->
        <div class="col-xl-3 col-md-6 mb-4" style="margin-left:20%">
            <div class="card h-100">
                <div class="card-body time-card" style="width: 900px;">
                    <div class="searchDiv" style="width: 80%;">
                        <input type="text" id="searchInput" placeholder="Search" class="form-control" style="text-align:center; width:100%;">
                    </div>
                </div>
            </div>
        </div>
    </div>

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
                  .anyMatch(attendance -> attendance.getEmployee().equals(employee));
              boolean isAbsent = todaysAttendances.stream().anyMatch(attendance ->
                              attendance.getEmployee().equals(employee) && attendance.getAttendanceStatus().equals("Absent"));
              boolean isExit = todaysAttendances.stream().anyMatch(attendance ->
                              attendance.getEmployee().equals(employee) &&
                              (attendance.getAttendanceStatus().equals("HalfDay") || attendance.getAttendanceStatus().equals("FullDay")));
             %>
                <% if (!isAttendanceEntered) { %>
               <tr id="row_<%= employee.getEmployeeId() %>">
                   <td><img src="images/prof/<%= employee.getEmployeeImage().strip() %>" class="prof"></td>
                   <td><%= employee.getEmployeeId().strip() %></td>
                   <td><%= employee.getFirstName().strip() + " " + employee.getLastName() %></td>
                   <td><%= displayTime %></td>
                   <td>
                       <input type="radio" name="attendanceStatus" value="<%= employee.getEmployeeId() %>_Entry"> Entry
                       <input type="radio" name="attendanceStatus" value="<%= employee.getEmployeeId() %>_Absent"> Absent
                   </td>
                   <td><input type="submit" class="submit-button" value="Submit"></td>
               </tr>
           <% } %>
            <% if (isAttendanceEntered && !isAbsent && !isExit) { %>
            <tr id="row_<%= employee.getEmployeeId() %>">
                <td><img src="images/prof/<%= employee.getEmployeeImage().strip() %>" class="prof"></td>
                <td><%= employee.getEmployeeId().strip() %></td>
                <td><%= employee.getFirstName().strip() + " " + employee.getLastName() %></td>
                <td><%= displayTime %></td>
                <td>
                        <input type="radio" name="attendanceStatus" value="<%= employee.getEmployeeId() %>_Exit"> Exit
                </td>
                <td><input type="submit" class="submit-button" value="Submit"></td>
            </tr>
            <% } %>
            <% } %>
        </table>
    </form>
   <!-- Logs Section (Fixed to Bottom) -->
   <div class="fixed-bottom" style="background-color: #068DA9; ">
       <div class="row">
           <div class="col-xl-6 col-xl-12">
               <div class="card">
                   <div class="card-body" style="background-color: #3D0C11;; color:white;">
                       <h3>Recent Activities</h3>
                       <div id="logs">
                           <%-- Insert logs dynamically here --%>
                                <% for (AttendanceLog event : (List<AttendanceLog>)request.getAttribute("attendanceLogs")) { %>
                                       <p><%= event.getAttendanceDetails().strip() %></p>
                               <% } %>
                           </div>
                   </div>
               </div>
           </div>
       </div>
   </div>


    <script>
     function searchTable() {
            var input, filter, table, tr, td, i, txtValue;
            input = document.getElementById('searchInput');
            filter = input.value.toUpperCase();
            table = document.querySelector('table');
            tr = table.querySelectorAll('tr');
            for (i = 1; i < tr.length; i++) {  // Start from 1 to skip the header row
                tds = tr[i].querySelectorAll('td');
                for (j = 0; j < tds.length; j++) {
                    td = tds[j];
                    if (td) {
                        txtValue = td.textContent || td.innerText;
                        if (txtValue.toUpperCase().indexOf(filter) > -1) {
                            tr[i].style.display = '';
                            break;
                        } else {
                            tr[i].style.display = 'none';
                        }
                    }
                }
            }
        }

        document.getElementById('searchInput').addEventListener('input', searchTable);


    </script>
        <jsp:include page="script.jsp"/>

</body>
<footer>

</footer>
</html>