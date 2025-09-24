<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Payment Success</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
	<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon.ico">

    <!-- Fonts & CSS -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600;700&family=Roboto:wght@400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body class="dashboard-page">
<div class="app">
    <!-- Sidebar -->
    <aside class="sidebar">
        <div class="brand">
            <div class="logo">SB</div>
            <div>
                <h1>SpringBoard</h1>
                <div class="subtitle">Admin Dashboard</div>
            </div>
        </div>
        <nav class="nav">
            <a href="${pageContext.request.contextPath}/dashboard"><i class="fa-solid fa-home"></i> Overview</a>
            <div class="dropdown">
                <a href="#"><i class="fa-solid fa-box"></i> Items ▾</a>
                <div class="dropdown-content">
                    <a href="${pageContext.request.contextPath}/items/add">Add Item</a>
                    <a href="${pageContext.request.contextPath}/items">View Items</a>
                </div>
            </div>
            <a href="${pageContext.request.contextPath}/orders"><i class="fa-solid fa-shopping-cart"></i> Orders</a>
            <a href="${pageContext.request.contextPath}/report"><i class="fa-solid fa-chart-line"></i> Reports</a>
            <a href="${pageContext.request.contextPath}/payment" class="active"><i class="fa-solid fa-credit-card"></i> Payments</a>
            <a href="${pageContext.request.contextPath}/settings"><i class="fa-solid fa-cog"></i> Settings</a>
            <a href="${pageContext.request.contextPath}/logout"><i class="fa-solid fa-sign-out-alt"></i> Logout</a>
        </nav>
    </aside>

    <!-- Main -->
    <main class="main">
        <div class="topbar">
            <div class="search">
                <input placeholder="Search..." />
            </div>
            <div class="topbar-right">
                <div class="admin-info">
                    <div class="admin-name">${username}</div>
                    <div class="role">Admin</div>
                </div>
                <div id="adminAvatar" class="avatar"></div>
            </div>
        </div>
        <div class="content">
            <div class="panel success-panel">
                <h2><i class="fa-solid fa-check-circle" style="color:green"></i> Payment Successful</h2>
                <p>Your payment was processed successfully!</p> 
            </div>
        </div>
    </main>
</div>
</body>
</html>
