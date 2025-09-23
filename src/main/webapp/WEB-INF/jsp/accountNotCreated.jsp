<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Account Creation Failed</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon.ico">
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
            font-family: 'Poppins', sans-serif;
        }
        .panel {
            background: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(10px);
            border-radius: 16px;
            padding: 30px;
            max-width: 380px;
            width: 100%;
            box-shadow: 0px 6px 25px rgba(0, 0, 0, 0.6);
            text-align: center;
        }
        .brand {display: flex;align-items: center;justify-content: center;margin-bottom: 20px;}
        .brand img {width: 50px;height: 50px;margin-right: 12px;}
        .brand h1 {font-family: 'Poppins', sans-serif;font-weight: 700;font-size: 22px;color: white;margin: 0;white-space: nowrap;}
        .msg {
            color: #cc1f1a;
            font-weight: 900;
            font-size: 16px;
            display: inline-block;
            position: relative;
            animation: liquidify 2s ease-in-out infinite;
            text-shadow: 1px 1px 4px rgba(0,0,0,0.7);
            letter-spacing: 0.5px;
            margin-bottom: 20px;
        }
        @keyframes liquidify {
            0% { transform: translateY(0) rotate(0deg); }
            25% { transform: translateY(-2px) rotate(-1deg); }
            50% { transform: translateY(2px) rotate(1deg); }
            75% { transform: translateY(-1px) rotate(-0.5deg); }
            100% { transform: translateY(0) rotate(0deg); }
        }
        .theme-btn {
            background: linear-gradient(to right, #2563eb, #22c55e);
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 6px;
            cursor: pointer;
            font-weight: 600;
            font-size: 15px;
            transition: 0.3s;
            text-decoration: none;
        }
        .theme-btn:hover {transform: scale(1.05);}
        .button-wrapper {
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="panel">
        <div class="brand">
            <img src="${pageContext.request.contextPath}/images/logo1.jpg" alt="Logo">
            <h1>INVENTORY MANAGEMENT</h1>
        </div>

        <!-- Display error message from the model -->
        <div class="msg">
            <c:out value="${error}" />
        </div>

        <!-- Back to Create Account page -->
        <div class="button-wrapper">
            <a href="${pageContext.request.contextPath}/createAccount" class="theme-btn">Back to Create Account</a>
        </div>
    </div>
</body>
</html>
