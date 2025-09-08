<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inventory Management System - Login</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>

<div class="login-container">
    <div class="login-card">
        <!-- Branding -->
        <div class="login-header">
            <i class="fa-solid fa-boxes-stacked logo-icon"></i>
            <h2>Inventory Management</h2>
            <p>Secure Login</p>
        </div>

        <!-- Form -->
		<form action="${pageContext.request.contextPath}/login" method="post">
            <div class="input-group">
                <i class="fa fa-user"></i>
                <input type="text" name="username" placeholder="Username" required>
            </div>

            <div class="input-group">
                <i class="fa fa-lock"></i>
                <input type="password" name="password" placeholder="Password" required>
            </div>

            <button type="submit">Login</button>

            <div class="extra-links">
                <a href="#">Forgot Password?</a>
                <span>|</span>
                <a href="${pageContext.request.contextPath}/signup">Create Account</a>
            </div>
        </form>
    </div>
</div>

</body>
</html>
