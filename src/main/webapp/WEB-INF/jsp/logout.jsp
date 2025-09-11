<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Logout</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <style>
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
        }

        /* Hover Effect */
        .login-again-btn:hover {
            background: linear-gradient(135deg, #2563eb, #1d4ed8);
            transform: scale(1.08);
            box-shadow: 0 8px 20px rgba(0,0,0,0.25);
        }

        /* Animation: Gentle pulse */
        .login-again-btn {
            animation: pulse 1.5s infinite;
        }

        @keyframes pulse {
            0% { transform: scale(1); }
            50% { transform: scale(1.05); }
            100% { transform: scale(1); }
        }
    </style>
</head>
<<<<<<< HEAD
<body>
	<div class="logout-container">
	    <div class="logout-box">
        <h2>You have successfully logged out.</h2>
        <a href="${pageContext.request.contextPath}/"><button>Login Again</button></a>
=======
<body class="dashboard-page">
	<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>

    <div class="app" style="justify-content:center;align-items:center;">
        <div class="panel" style="max-width:400px; width:100%; text-align:center;">
            <h2>You have successfully logged out.</h2>
            <a href="login">
                <button class="login-again-btn" style="margin-top:20px;">Login Again</button>
            </a>
        </div>
>>>>>>> origin/feature_surya
    </div>
</body>
</html>
