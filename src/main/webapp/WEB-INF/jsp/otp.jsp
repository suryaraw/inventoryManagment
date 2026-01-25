<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Enter OTP</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@600;700&display=swap" rel="stylesheet">
    <style>
        body {
            background-image: url('${pageContext.request.contextPath}/images/final12.jpg');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
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

        .panel h2 {
            color: white;
            margin-bottom: 20px;
        }

        .input-group {
            display: flex;
            align-items: center;
            background: rgba(255,255,255,0.85);
            padding: 6px;
            border-radius: 6px;
            margin-bottom: 12px;
        }

        .input-group i {
            margin-right: 8px;
            color: #444;
            font-size: 14px;
        }

        .input-group input {
            border: none;
            outline: none;
            width: 100%;
            font-size: 14px;
            padding: 6px;
            background: transparent;
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

        button.theme-btn:hover {
            transform: scale(1.05);
        }

        .error {
            color: red;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .message {
            color: lightgreen;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="panel">
    <h2>Enter OTP</h2>

    <!-- Display message from controller -->
    <c:if test="${not empty message}">
        <p class="message">${message}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/validateOtpAndReset" method="post">
        <div class="input-group">
            <i class="fa fa-key"></i>
            <input type="text" name="otp" placeholder="Enter OTP" required maxlength="6">
        </div>
        <input type="hidden" name="username" value="${username}"/>
		<input type="hidden" name="newpass" value="${password}"/>
        <button type="submit" class="theme-btn">Validate OTP</button>
    </form>
</div>
</body>
</html>
