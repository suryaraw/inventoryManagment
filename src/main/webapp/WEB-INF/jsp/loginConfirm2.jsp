<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Password Reset - Success</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon.ico">
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
            background: rgba(0, 0, 0, 0.55);
            backdrop-filter: blur(20px) saturate(180%);
            border-radius: 18px;
            padding: 45px;
            max-width: 420px;
            width: 100%;
            box-shadow: 0px 8px 35px rgba(0, 0, 0, 0.8);
            text-align: center;
            color: white;
        }
        .icon {
            font-size: 50px; /* smaller size */
            color: #b0b0b0; /* grey color */
            margin-bottom: 20px;
            text-shadow: 0 0 8px rgba(176, 176, 176, 0.7);
        }
        .msg {
            font-size: 22px;
            font-weight: 700;
            color: #22c55e;
            margin-bottom: 12px;
            text-shadow: 0 0 12px rgba(34, 197, 94, 0.7);
        }
        .note {
            font-size: 15px;
            color: #ddd;
            text-shadow: 0 0 8px rgba(255, 255, 255, 0.4);
        }
        a.back-link {
            display: inline-block;
            margin-top: 25px;
            text-decoration: none;
            font-weight: 600;
            font-size: 15px;
            color: #22c55e;
            padding: 10px 18px;
            border-radius: 8px;
            background: rgba(34, 197, 94, 0.1);
            border: 1px solid #22c55e;
            transition: 0.3s ease;
        }
        a.back-link:hover {
            background: #22c55e;
            color: white;
            transform: scale(1.05);
            box-shadow: 0 0 12px rgba(34, 197, 94, 0.6);
        }
    </style>
</head>
<body>
    <div class="panel">
        <div class="icon">✔</div>
        <div class="msg">User verified! You can now reset your password.</div>
        <div class="note">Proceed to create your new secure password.</div>
        <a class="back-link" href="${pageContext.request.contextPath}actualRestPage.jsp">Go to Rest</a>
    </div>
</body>
</html>
