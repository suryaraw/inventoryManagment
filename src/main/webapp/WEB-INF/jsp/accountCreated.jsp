<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Account Created Successfully</title>
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
            background: rgba(255, 255, 255, 0.12);
            backdrop-filter: blur(12px);
            border-radius: 20px;
            padding: 35px 30px;
            max-width: 400px;
            width: 100%;
            box-shadow: 0px 8px 30px rgba(0, 0, 0, 0.6);
            text-align: center;
        }
        .brand {
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 25px;
        }
        .brand img {
            width: 55px;
            height: 55px;
            margin-right: 12px;
        }
        .brand h1 {
            font-family: 'Poppins', sans-serif;
            font-weight: 700;
            font-size: 24px;
            color: white;
            margin: 0;
            white-space: nowrap;
        }
        .msg {
            color: #22c55e;
            font-weight: 700;
            font-size: 18px;
            margin-bottom: 25px;
        }
        .msg-subtitle {
            font-size: 14px;
            color: #e0e0e0;
            margin-bottom: 30px;
        }
        .theme-btn {
            background: linear-gradient(to right, #2563eb, #22c55e);
            color: white;
            border: none;
            padding: 12px 28px;
            border-radius: 8px;
            cursor: pointer;
            font-weight: 600;
            font-size: 15px;
            text-decoration: none;
            transition: transform 0.3s, box-shadow 0.3s;
        }
        .theme-btn:hover {
            transform: scale(1.05);
            box-shadow: 0px 5px 15px rgba(0,0,0,0.3);
        }
        .button-wrapper {
            display: flex;
            justify-content: center;
        }
    </style>
</head>
<body>
    <div class="panel">
        <div class="brand">
            <img src="${pageContext.request.contextPath}/images/logo1.jpg" alt="Logo">
            <h1>INVENTORY MANAGEMENT</h1>
        </div>

        <div class="msg">Your account has been created successfully!</div>
        <div class="msg-subtitle">You can now login to your account using your credentials.</div>

        <div class="button-wrapper">
            <a href="${pageContext.request.contextPath}/login" class="theme-btn">Go to Login</a>
        </div>
    </div>
</body>
</html>
