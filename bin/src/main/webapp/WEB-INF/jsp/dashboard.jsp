<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inventory Management - Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body class="dashboard-page">

<!-- Navbar -->
<div class="navbar">
    <a href="${pageContext.request.contextPath}/dashboard"><i class="fa fa-home"></i> Dashboard</a>
    <a href="${pageContext.request.contextPath}/items"><i class="fa fa-box"></i> Items</a>
    <a href="${pageContext.request.contextPath}/orders"><i class="fa fa-shopping-cart"></i> Orders</a>
    <a href="${pageContext.request.contextPath}/report"><i class="fa fa-chart-line"></i> Reports</a>
    <a href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out-alt"></i> Logout</a>
</div>

<!-- Banner -->
<!--<div class="banner">
    Welcome to Inventory Management System
</div>-->

<!-- Dashboard Box -->
<div class="dashboard-box">
    <h2>Hello, ${username} 👋</h2>
    <p class="welcome-text">Here’s a quick overview of your system:</p>

    <ul>
        <li><i class="fa fa-box"></i> Total Items: <b>120</b></li>
        <li><i class="fa fa-shopping-cart"></i> Active Orders: <b>45</b></li>
        <li><i class="fa fa-users"></i> Registered Users: <b>30</b></li>
        <li><i class="fa fa-chart-line"></i> Monthly Sales: <b>₹1,20,000</b></li>
    </ul>
</div>

</body>
</html>
