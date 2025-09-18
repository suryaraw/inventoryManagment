<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon.ico">

    <title>Create Account - Inventory</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@600;700&display=swap" rel="stylesheet">
    <style>
        body {background-image: url('${pageContext.request.contextPath}/images/background1.jpg');background-size: cover;background-position: center;background-repeat: no-repeat;background-attachment: fixed;height: 100vh;display: flex;justify-content: center;align-items: center;}
        .panel {background: rgba(15, 23, 42, 0.95);border-radius: 12px;padding: 30px;max-width: 380px;width: 100%;box-shadow: 0px 6px 20px rgba(0, 0, 0, 0.5);text-align: center;}
        h2 {color: white;font-family: 'Poppins', sans-serif;margin-bottom: 15px;}
        input {padding: 8px;width: 90%;margin-top: 10px;border: 1px solid #ccc;border-radius: 6px;}
        button.theme-btn {background: linear-gradient(to right, #2563eb, #22c55e);color: white;border: none;padding: 8px;width: 100%;border-radius: 6px;cursor: pointer;font-weight: 600;font-size: 14px;transition: 0.3s;margin-top: 15px;}
        button.theme-btn:hover {transform: scale(1.03);}
        a {color: #22c55e;font-weight: 600;text-decoration: none;}
    </style>
</head>
<body>
    <div class="panel">
        <h2>Create Account</h2>
        <form action="${pageContext.request.contextPath}/signup" method="post">
            <input type="text" name="username" placeholder="Username" required>
            <input type="email" name="email" placeholder="Email" required>
            <input type="password" name="password" placeholder="Password" required>
            <button type="submit" class="theme-btn">Sign Up</button>
        </form>
        <p style="margin-top:15px;"><a href="login.jsp">Back to Login</a></p>
    </div>
</body>
</html>
