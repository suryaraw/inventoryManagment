<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Account - Inventory Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <style>
        body {
            background: url('${pageContext.request.contextPath}/images/wmremove-transformed (1).png') no-repeat center center/cover;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .panel {
            background: rgba(15, 23, 42, 0.95);
            padding: 30px;
            border-radius: 12px;
            text-align: center;
            width: 380px;
            box-shadow: 0px 6px 20px rgba(0,0,0,0.5);
        }
        .panel h2 {
            color: white;
            margin-bottom: 15px;
        }
        input, select {
            width: 90%;
            padding: 10px;
            margin-top: 12px;
            border-radius: 6px;
            border: 1px solid #ccc;
        }
        button {
            width: 100%;
            margin-top: 15px;
            padding: 10px;
            background: linear-gradient(to right, #2563eb, #22c55e);
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
        }
        a {
            display: block;
            margin-top: 12px;
            color: #22c55e;
            text-decoration: none;
        }
        a:hover { color: #2563eb; }
    </style>
</head>
<body>
    <div class="panel">
        <h2>Create Account</h2>
        <form action="${pageContext.request.contextPath}/register" method="post">
            <input type="text" name="username" placeholder="Enter Username" required>
            <input type="email" name="email" placeholder="Enter Email" required>
            <input type="password" name="password" placeholder="Enter Password" required>
            <select name="role" required>
                <option value="">-- Select Role --</option>
                <option value="ADMIN">Admin</option>
                <option value="RETAIL_SHOP">Retail Shop</option>
            </select>
            <button type="submit">Create Account</button>
        </form>
        <a href="login.jsp">Back to Login</a>
    </div>
</body>
</html>
