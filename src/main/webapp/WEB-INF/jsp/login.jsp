<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

</head>
<body>
	<div class="page-heading">
	        <h1>Inventory Management System</h1>
	</div>
		
    <div class="content-box">
        <h2>Login</h2>
        <form action="/login" method="post">
            <input type="text" name="username" placeholder="Enter Username" required />
            <input type="password" name="password" placeholder="Enter Password" required />
            <button type="submit">Login</button>
        </form>
        <p style="color:red;text-align:center">${error}</p>
    </div>
</body>
</html>
