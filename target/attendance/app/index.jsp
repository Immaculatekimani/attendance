<jsp:useBean id="topBar" class="com.emma.app.view.toolbar.TopBar" scope="request"/>
<jsp:useBean id="contentBean" class="com.emma.app.displaybean.ContentBean" scope="request"/>
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

    <% contentBean.setContent((String)request.getAttribute("content")); %>
    <%= contentBean.getContent() %>

</body>
</html>