<%@ page isELIgnored="false" %>
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
<body class= "body-style" style = "background-color: #3D0C11;">

     <c:set var="activeMenu" value='${requestScope.activeMenu}' />
        ${menuBean.setActiveMenu(activeMenu)}
        ${topBar.menu(menuBean.activeMenu)}
    </c:set>



       <div  class="modal-content" style = "background-color: papayawhip;">
        <h1>Error Page</h1>

                   <p>${errorMessage}</p>
       </div>


 <jsp:include page="script.jsp"/>
  <script src="./app/style/jquery/jquery.min.js"></script>
  <script src="./app/style/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="./app/style/jquery-easing/jquery.easing.min.js"></script>
</body>
</html>