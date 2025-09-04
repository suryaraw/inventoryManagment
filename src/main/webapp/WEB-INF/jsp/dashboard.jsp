<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Dashboard</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

</head>
<body>
    <div class="navbar">
        <a href="/dashboard">Home</a>
        <a href="#">Profile</a>
        <a href="#">Reports</a>
        <a href="#">Settings</a>
        <a href="/logout">Logout</a>
    </div>

    <div class="banner">
        Welcome, ${username} 🎉
    </div>

    <div class="content-box" style="margin-top:50px;">
        <h2>Dashboard</h2>
        <p>Here are your quick actions:</p>
        <ul>
            <li><a href="#">📦 Manage Orders</a></li>
            <li><a href="#">📊 View Reports</a></li>
            <li><a href="#">⚙️ Update Settings</a></li>
        </ul>
    </div>
</body>
</html>
