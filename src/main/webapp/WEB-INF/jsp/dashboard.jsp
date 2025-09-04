<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    

    <!-- Navbar -->
    <div class="navbar">
        <a href="/dashboard">Home</a>
        <a href="#">Profile</a>
        <a href="#">Reports</a>
        <a href="#">Settings</a>
        <a href="/logout">Logout</a>
    </div>

    <!-- Welcome text -->
    <div class="welcome-text">
        <h2>Welcome, admin 🎉</h2>
    </div>

    <!-- Dashboard box -->
    <div class="dashboard-box">
        <h2>Dashboard</h2>
        <p>Here are your quick actions:</p>
        <ul>
            <li>📦 <a href="#">Manage Orders</a></li>
            <li>📊 <a href="#">View Reports</a></li>
            <li>⚙️ <a href="#">Update Settings</a></li>
        </ul>
    </div>
</body>
</html>
