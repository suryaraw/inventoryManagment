<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Logout</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <style>
        body {
            margin: 0;
            padding: 0;
            height: 100vh;
            background: url('${pageContext.request.contextPath}/images/final12.jpg') no-repeat center center fixed;
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: Arial, sans-serif;
        }

        /* Transparent Center Box */
        .panel {
            background: rgba(255, 255, 255, 0.2);
            backdrop-filter: blur(8px);
            border-radius: 15px;
            padding: 30px;
            max-width: 400px;
            width: 100%;
            text-align: center;
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.4);
        }

        .panel h2 {
            color: white;
            margin-bottom: 20px;
        }

        /* Button Styling */
        .login-again-btn {
            background: linear-gradient(135deg, #4e9af1, #2563eb);
            color: white;
            font-size: 18px;
            font-weight: 600;
            padding: 12px 30px;
            border: none;
            border-radius: 12px;
            cursor: pointer;
            transition: transform 0.2s ease, box-shadow 0.3s ease, background 0.5s ease;
            animation: pulse 1.5s infinite;
        }

        .login-again-btn:hover {
            background: linear-gradient(135deg, #2563eb, #1d4ed8);
            transform: scale(1.08);
            box-shadow: 0 8px 20px rgba(0,0,0,0.25);
        }

        @keyframes pulse {
            0% { transform: scale(1); }
            50% { transform: scale(1.05); }
            100% { transform: scale(1); }
        }
    </style>
</head>
<body>

    <div class="panel">
        <h2>You have successfully logged out.</h2>
        <a href="login">
            <button class="login-again-btn">Login Again</button>
        </a>

	<div class="logout-container">
	    <div class="logout-box">
<body class="dashboard-page">
	<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>

    <div class="app" style="justify-content:center;align-items:center;">
        <div class="panel" style="max-width:400px; width:100%; text-align:center;">
            <h2>You have successfully logged out.</h2>
            <a href="login">
                <button class="login-again-btn" style="margin-top:20px;">Login Again</button>
            </a>
        </div>

    </div>
</body>
</html>
