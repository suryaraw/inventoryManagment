<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon.ico">

    <title>Forgot Password - Inventory Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@600;700&display=swap" rel="stylesheet">
</head>
<body class="dashboard-page">
    <div class="app" style="justify-content:center;align-items:center;">
        <div class="panel">
            <div class="brand">
                <img src="${pageContext.request.contextPath}/images/logo1.jpg" alt="Inventory Logo">
                <h1>INVENTORY MANAGEMENT</h1>
            </div>
            <p class="subtitle">Reset Your Password</p>

            <c:if test="${not empty error}">
                <p class="message error">${error}</p>
            </c:if>
            <c:if test="${not empty message}">
                <p class="message">${message}</p>
            </c:if>

            <form action="${pageContext.request.contextPath}/resetPassword" method="post">
                <div class="input-group">
                    <i class="fa fa-envelope"></i>
                    <input type="email" name="email" placeholder="Enter Email" required>
                </div>
                <div class="input-group">
                    <i class="fa fa-lock"></i>
                    <input type="password" name="newPassword" placeholder="Enter New Password" required>
                </div>
                <button type="submit" class="theme-toggle" style="width:100%;">Reset Password</button>

                <div class="extra-links" style="margin-top:15px;">
                    <a href="${pageContext.request.contextPath}/login">Back to Login</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
