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
</head>
<body class= "body-style" style = "background-color: #3D0C11;">

     <c:set var="activeMenu" value='${requestScope.activeMenu}' />
        ${menuBean.setActiveMenu(activeMenu)}
        ${topBar.menu(menuBean.activeMenu)}
    </c:set>



       <div  class="modal-content" style = "background-color: papayawhip;">
        <h2>THAT WON'T WORK! :(</h2>

                   <p>${errorMessage}</p>
       </div>


 <jsp:include page="script.jsp"/>

</body>
</html>