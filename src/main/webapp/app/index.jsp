<%@ page import="com.emma.app.view.toolbar.TopBar" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="./app/indexStyle.css">
</head>
<body>
    <%= new TopBar().menu((int)request.getAttribute("activeMenu")) %>
    <h2>Welcome <%= session.getAttribute("username") %></h2>
       <%= request.getAttribute("content") %>
</body>
</html>