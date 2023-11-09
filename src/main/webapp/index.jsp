<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login or Register</title>
    <style>
        body {
    font-family: Arial, sans-serif;
    background-color: #3D0C11;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}
 h1 {
            text-align: center;
            color: #186F65;
   }
.login-container {
    background-color: #F9DEC9;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
    border-radius: 5px;
    padding: 20px;
    width: 400px;
}

.login-container h2 {
    text-align: center;
    color: #068DA9;
}

.login-form {
    text-align: center;
}

.login-form input[type="text"],
.login-form input[type="password"] {
    width: 80%;
    padding: 10px;
    margin: 10px 0;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.login-form button {
    background-color: #068DA9;
    color: #fff;
    border: none;
    border-radius: 5px;
    padding: 10px 20px;
    cursor: pointer;
}

.login-form button:hover {
    background-color: #1f77c6;
}

    </style>
</head>

<body>
<div class="login-container">
    <h2>Welcome To Tech-Star Attendance System!</h2>
    <div id="login-form" class="login-form">
        <form action="./login" method="post">
            <input type="text" id="username" name="username" placeholder="Username" required><br>
            <input type="password" id="password" name="password" placeholder="Password" required><br>
            <button type="submit">Login</button>
        </form>
        <p>
            <button onclick="showRegisterForm()">Register</button>
        </p>
    </div>
    <div id="register-form" style="display: none;" class="login-form">
        <form action="./user" method="post">
            <div class="container login-form">
                <input type="text" placeholder="Enter Username" name="username" required>

                <input type="password" placeholder="Enter Password" name="password" required>

                <input type="password" placeholder="Confirm Enter Password" name="confirmPassword" required>

                <button type="submit">Register</button>
            </div>
        </form>
        <p>
            <button onclick="showLoginForm()">Back to Login</button>
        </p>
    </div>
</div>

<script>
    function showRegisterForm() {
        document.getElementById('login-form').style.display = 'none';
        document.getElementById('register-form').style.display = 'block';
    }

    function showLoginForm() {
        document.getElementById('register-form').style.display = 'none';
        document.getElementById('login-form').style.display = 'block';
    }
</script>
</body>
</html>
