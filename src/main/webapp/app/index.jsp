<%@ page isELIgnored="false" %>
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
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css">

</head>
<body class= "body-style">

     <c:set var="activeMenu" value='${requestScope.activeMenu}' />
        ${menuBean.setActiveMenu(activeMenu)}
        ${topBar.menu(menuBean.activeMenu)}
    </c:set>


    ${requestScope.header}
    ${requestScope.content}

   <jsp:include page="script.jsp"/>

  <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
  <script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
  <script>
 $(document).ready(function () {
     $('#dataTable').DataTable({
         "scrollY": "60vh", // Set the desired height for vertical scrolling
         "scrollCollapse": true, // Enable scrolling collapse
         "paging": true, // Enable pagination
         "pageLength": 5,
         "searching": true,
     });
 });
 </script>


</body>
</html>