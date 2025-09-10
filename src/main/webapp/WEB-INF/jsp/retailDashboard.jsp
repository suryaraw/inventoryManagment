<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Retail Shop Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body class="dashboard-page">

<!-- Navbar -->
<div class="navbar">
    <span class="brand"><i class="fa fa-store"></i> Retail Shop</span>
    <div>
        <a href="${pageContext.request.contextPath}/retail/items"><i class="fa fa-box"></i> Items</a>
        <a href="${pageContext.request.contextPath}/retail/orders"><i class="fa fa-shopping-cart"></i> Orders</a>
        <a href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out-alt"></i> Logout</a>
    </div>
</div>

<!-- Dashboard Box -->
<div class="dashboard-box">
    <h2>Hello, ${sessionScope.user} 👋</h2>
    <p class="welcome-text">Welcome to your Retail Dashboard. Here’s an overview:</p>

    <ul>
        <li><i class="fa fa-box"></i> Available Items: <b>50</b></li>
        <li><i class="fa fa-shopping-cart"></i> Orders Placed: <b>12</b></li>
        <li><i class="fa fa-wallet"></i> Pending Payments: <b>₹15,000</b></li>
    </ul>
</div>

</body>
</html>
