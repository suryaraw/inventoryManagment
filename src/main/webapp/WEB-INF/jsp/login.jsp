<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inventory Management System - Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@600;700&display=swap" rel="stylesheet">
    <style>
        body {
            background-image: url('${pageContext.request.contextPath}/images/final12.jpg');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            background-attachment: fixed;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0;
        }

        /* 🔹 Transparent Glass Panel */
        .panel {
            background: rgba(255, 255, 255, 0.1); /* transparent */
            backdrop-filter: blur(10px); /* glass blur effect */
            border-radius: 16px;
            padding: 30px;
            max-width: 380px;
            width: 100%;
            box-shadow: 0px 6px 25px rgba(0, 0, 0, 0.6);
            text-align: center;
        }

        .brand {
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 20px;
        }
        .brand img {
            width: 50px;
            height: 50px;
            margin-right: 12px;
        }
        .brand h1 {
            font-family: 'Poppins', sans-serif;
            font-weight: 700;
            font-size: 22px;
            color: white;
            margin: 0;
            white-space: nowrap;
        }

        .subtitle {font-size: 13px;color: #ddd;margin-bottom: 15px;}

        .input-group {
            display: flex;
            align-items: center;
            background: rgba(255,255,255,0.85);
            padding: 6px;
            border-radius: 6px;
            margin-bottom: 12px;
        }
        .input-group i {margin-right: 8px;color: #444;font-size: 14px;}
        .input-group input, .input-group select {
            border: none;outline: none;width: 100%;font-size: 14px;padding: 6px;background: transparent;
        }

        button.theme-btn {
            background: linear-gradient(to right, #2563eb, #22c55e);
            color: white;
            border: none;
            padding: 8px;
            width: 100%;
            border-radius: 6px;
            cursor: pointer;
            font-weight: 600;
            font-size: 14px;
            transition: 0.3s;
        }
        button.theme-btn:hover {transform: scale(1.05);}

        .extra-links a {
            text-decoration: none;
            font-size: 13px;
            font-weight: 600;
            color: #22c55e;
            margin: 0 5px;
        }
        .extra-links a:hover {color: #2563eb;}
    </style>
</head>
<body>
    <div class="panel">
        <div class="brand">
            <img src="${pageContext.request.contextPath}/images/logo1.jpg" alt="Logo">
            <h1>INVENTORY MANAGEMENT</h1>
        </div>
        <p class="subtitle">Secure Login</p>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="input-group">
                <i class="fa fa-user"></i>
                <input type="text" name="username" placeholder="Username" required>
            </div>
            <div class="input-group">
                <i class="fa fa-lock"></i>
                <input type="password" name="password" placeholder="Password" required>
            </div>
            <div class="input-group">
                <i class="fa fa-users"></i>
                <select name="role" required>
                    <option value="">-- Select Role --</option>
                    <option value="ADMIN">Admin</option>
                    <option value="RETAIL_SHOP">Retail Shop</option>
                </select>
            </div>
            <button type="submit" class="theme-btn">Login</button>
        </form>
        <div class="extra-links" style="margin-top:15px;">
            <a href="/forgotPassword">Forgot Password?</a> | 
            <a href="createAccount.jsp">Create Account</a>
        </div>
    </div>
</body>
</html>
