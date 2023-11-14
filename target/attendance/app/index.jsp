<%@ page isELIgnored="false" %>
<jsp:useBean id="topBar" class="com.emma.app.view.toolbar.TopBar" scope="request"/>
<jsp:useBean id="menuBean" class="com.emma.app.displaybean.MenuBean" scope="request"/>


<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="./app/indexStyle.css">
</head>
<body>

     <c:set var="activeMenu" value='${requestScope.activeMenu}' />
        ${menuBean.setActiveMenu(activeMenu)}
        ${topBar.menu(menuBean.activeMenu)}
    </c:set>

    <h2>Welcome ${sessionScope.username} </h2>

    ${requestScope.content}

</body>
</html>