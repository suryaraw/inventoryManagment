<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Forgot Password - Inventory Management</title>
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

        .subtitle {
            font-size: 13px;
            color: #ddd;
            margin-bottom: 15px;
        }

        .message { font-size: 13px; color: #22c55e; margin: 5px 0; }
        .message.error { color: #ff4d4d; }

        .input-group {
            display: flex;
            align-items: center;
            background: rgba(255,255,255,0.85);
            padding: 6px;
            border-radius: 6px;
            margin-bottom: 12px;
        }
        .input-group i { margin-right: 8px; color: #444; font-size: 14px; }
        .input-group input {
            border: none; outline: none; width: 100%;
            font-size: 14px; padding: 6px; background: transparent;
        }

        button.theme-btn {
            background: linear-gradient(to right, #2563eb, #22c55e);
            color: white;
            border: none;
            padding: 10px 20px;   /* 🔹 resized to medium */
            border-radius: 6px;
            cursor: pointer;
            font-weight: 600;
            font-size: 14px;
            transition: 0.3s;
        }
        button.theme-btn:hover { transform: scale(1.05); }

        .extra-links a {
            text-decoration: none;
            font-size: 13px;
            font-weight: 600;
            color: #22c55e;
            margin: 0 5px;
        }
        .extra-links a:hover { color: #2563eb; }
    </style>
</head>
<body>
    <div class="panel">
        <p class="subtitle">Reset Your Password</p>

        <c:if test="${not empty error}">
            <p class="message error">${error}</p>
        </c:if>
        <c:if test="${not empty message}">
            <p class="message">${message}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/resetPassword" method="post">
            <div class="input-group">
                <i class="fa fa-envelope"></i>
                <input type="email" name="email" placeholder="Enter Email" required>
            </div>
            <div class="input-group">
                <i class="fa fa-lock"></i>
                <input type="password" name="newPassword" placeholder="Enter New Password" required>
            </div>
            <button type="submit" class="theme-btn">Reset Password</button>

            <div class="extra-links" style="margin-top:15px;">
                <a href="${pageContext.request.contextPath}/login">Back to Login</a>
            </div>
        </form>
    </div>
</body>
</html>
