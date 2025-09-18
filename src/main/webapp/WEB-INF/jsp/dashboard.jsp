<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
	<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon.ico">

    <meta charset="utf-8">
    <title>Inventory Management - Admin Dashboard</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600;700&family=Roboto:wght@400;600&display=swap" rel="stylesheet">

    <!-- External CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
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
		    <a href="${pageContext.request.contextPath}/dashboard" class="active"><i class="fa fa-home"></i> Overview</a>

		    <!-- Items Dropdown -->
		    <div class="dropdown">
		        <a href="#"><i class="fa fa-box"></i> Items ▾</a>
		        <div class="dropdown-content">
		            <a href="${pageContext.request.contextPath}/sup/add">Add Item</a>
		            <a href="${pageContext.request.contextPath}/items">View Items</a>
		        </div>
		    </div>

		    <!-- Suppliers Dropdown -->
		    <div class="dropdown">
		        <a href="#"><i class="fa fa-truck"></i> Manage Suppliers ▾</a>
		        <div class="dropdown-content">
		            <a href="${pageContext.request.contextPath}/sup/addsup">Add Supplier</a>
		            <a href="${pageContext.request.contextPath}/sup/all">View Suppliers</a>
		        </div>
		    </div>

            <a href="${pageContext.request.contextPath}/orders"><i class="fa fa-shopping-cart"></i> Orders</a>
			<a href="${pageContext.request.contextPath}/checkout"><i class="fa fa-credit-card"></i> Payments</a>
            <a href="${pageContext.request.contextPath}/report"><i class="fa fa-chart-line"></i> Reports</a>
            <a href="${pageContext.request.contextPath}/settings"><i class="fa fa-cog"></i> Settings</a>
            <a href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out-alt"></i> Logout</a>
        </nav>
    </aside>

    <!-- Main -->
    <main class="main">
        <!-- Topbar -->
        <div class="topbar">
            <div class="search">
                <svg width="18" height="18" fill="none" viewBox="0 0 24 24">
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5"
                          d="M21 21l-4.35-4.35"></path>
                    <circle cx="11" cy="11" r="6" stroke="currentColor"
                            stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5"></circle>
                </svg>
                <input placeholder="Search users, orders, reports..."/>
            </div>
            <div class="topbar-right">
                <button id="themeToggle" class="theme-toggle">🌙</button>
                <div class="admin-info">
                    <div id="adminName" class="admin-name">${username}</div>
                    <div class="role">Admin</div>
                </div>
                <div id="adminAvatar" class="avatar"></div>
            </div>
        </div>

        <!-- Example dashboard cards -->
        <div class="cards">
            <div class="card">Users: 120</div>
            <div class="card">Orders: 87</div>
            <div class="card">Revenue: $24K</div>
        </div>

        <!-- Content -->
        <div class="content">
            <div class="panel chart-panel">
                <h3>Sales Overview</h3>
                <canvas id="salesChart"></canvas>
            </div>

            <div class="panel table-panel">
                <!-- Orders table -->
                <table>
                    <tr><th>Order</th><th>User</th><th>Status</th></tr>
                    <tr><td>#1001</td><td>Arya</td><td>Completed</td></tr>
                    <tr><td>#1002</td><td>Ravi</td><td>Pending</td></tr>
                </table>
            </div>
        </div>
    </main>
</div>

<!-- External JS -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>
</body>
</html>
