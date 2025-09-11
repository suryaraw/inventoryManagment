<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inventory Management System - Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body class="dashboard-page">
	<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>

<div class="app" style="justify-content:center;align-items:center;">
    <div class="panel" style="max-width:400px; width:100%; text-align:center;">
        <!-- Branding -->
        <div class="brand" style="justify-content:center; margin-bottom:20px;">
            <div class="logo"><i class="fa-solid fa-boxes-stacked"></i></div>
            <h1>Inventory Management</h1>
        </div>
        <p class="subtitle">Secure Login</p>

        <!-- Form -->
        <form action="${pageContext.request.contextPath}/login" method="post" style="margin-top:20px;">
            <div class="input-group" style="margin-bottom:15px;">
                <i class="fa fa-user"></i>
                <input type="text" name="username" placeholder="Username" required>
            </div>

            <div class="input-group" style="margin-bottom:15px;">
                <i class="fa fa-lock"></i>
                <input type="password" name="password" placeholder="Password" required>
            </div>

            <div class="input-group" style="margin-bottom:20px;">
                <i class="fa fa-users"></i>
                <select name="role" required>
                    <option value="">-- Select Role --</option>
                    <option value="ADMIN">Admin</option>
                    <option value="RETAIL_SHOP">Retail Shop</option>
                </select>
            </div>

            <button type="submit" class="theme-toggle" style="width:100%;">Login</button>

            <div class="extra-links" style="margin-top:15px;">
                <a href="#">Forgot Password?</a>
                <span>|</span>
                <a href="${pageContext.request.contextPath}/signup">Create Account</a>
            </div>
