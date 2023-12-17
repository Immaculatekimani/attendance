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
       <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/2.4.2/css/buttons.dataTables.min.css">
</head>
<body class= "body-style">

    <c:set var="activeMenu" value='${requestScope.activeMenu}' />
    ${menuBean.setActiveMenu(activeMenu)}
    ${topBar.menu(menuBean.activeMenu)}
    </c:set>

    ${requestScope.header}
    ${requestScope.content}

    <jsp:include page="script.jsp"/>

    <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
   <script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
   <script src="https://cdn.datatables.net/buttons/2.4.2/js/dataTables.buttons.min.js"></script>
   <script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.html5.min.js"></script>
   <script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.print.min.js"></script>
   <script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.colVis.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.min.js"></script>
   <script>
       $(document).ready(function () {
           var dataTable = $('#dataTable').DataTable({
               "scrollY": "60vh",
               "scrollCollapse": true,
               "paging": true,
               "pageLength": 5,
               "searching": true,
               dom: 'Bfrtip',
               buttons: [
                   {
                       extend: 'print',
                       exportOptions: { stripHtml: false, columns: ':visible' },
                       customize: function (win) {
                           $(win.document.body)
                               .css('font-size', '10pt');

                           $(win.document.body).find('table')
                               .addClass('compact')
                               .css('font-size', 'inherit');
                       }
                   },
                    {
                       extend: 'excelHtml5',
                       exportOptions: {
                           columns: ':visible'
                       }
                   },

                   'colvis'
               ]
           });
       });
   </script>
</body>
</html>
