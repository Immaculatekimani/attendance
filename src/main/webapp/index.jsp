<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="./loginStyle.css">
    <title>Login or Register</title>

</head>

<body>
    <div class="login-container">
        <h2>Welcome To Tech-Star Attendance System!</h2>
        <div id="login-form" class="login-form">
            <form action="./login" method="post" onsubmit="return validateLoginForm()" id= "seleCheck">
                <input type="text" id="username" name="username" placeholder="Username" required><br>
                <input type="password" id="password" name="password" placeholder="Password" required><br>
                <button type="submit">Login</button>
            </form>
            <p>
                <button onclick="showRegisterForm()">Register</button>
            </p>
        </div>
        <div id="register-form" style="display: none;" class="login-form">
            <form action="./user" method="post" onsubmit="return validateRegistrationForm()">
                <div class="container login-form">
                    <input type="text" id="register-username" placeholder="Enter Username" name="username" required>

                    <input type="password" id="register-password" placeholder="Enter Password" name="password" required>

                    <input type="password" id="confirmPassword" placeholder="Confirm Enter Password" name="confirmPassword" required>

                    <button type="submit">Register</button>
                </div>
            </form>
            <p>
                <button onclick="showLoginForm()">Back to Login</button>
            </p>
        </div>
    </div>
            <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
        <jsp:include page="./app/script.jsp"/>
   </body>
</html>
