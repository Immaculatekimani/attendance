<%@ page isELIgnored="false" %>
<jsp:useBean id="topBar" class="com.emma.app.view.helper.TopBar" scope="request"/>
<jsp:useBean id="menuBean" class="com.emma.app.displaybean.MenuBean" scope="request"/>


<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="./app/style/indexStyle.css">
    <link href="./app/style/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="./app/style/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>
<body>

     <c:set var="activeMenu" value='${requestScope.activeMenu}' />
        ${menuBean.setActiveMenu(activeMenu)}
        ${topBar.menu(menuBean.activeMenu)}
    </c:set>

    <h2>Welcome ${sessionScope.username} </h2>

    ${requestScope.header}
    ${requestScope.content}
 <script src="./app/style/jquery/jquery.min.js"></script>
  <script src="./app/style/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="./app/style/jquery-easing/jquery.easing.min.js"></script>
</body>
</html>