<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Logout</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
</head>
<body class="dashboard-page">
	<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>

<div class="app" style="justify-content:center;align-items:center;">
    <div class="panel" style="max-width:400px; width:100%; text-align:center;">
        <h2>You have successfully logged out.</h2>
        <a href="login">
            <button class="theme-toggle" style="margin-top:20px;">Login Again</button>
        </a>
    </div>
</div>

</body>
</html>
