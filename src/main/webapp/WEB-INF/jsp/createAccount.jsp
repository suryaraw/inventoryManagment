<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Account - Inventory</title>
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
            background: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(10px);
            border-radius: 16px;
            padding: 30px;
            max-width: 380px;
            width: 100%;
            box-shadow: 0px 6px 25px rgba(0, 0, 0, 0.6);
            text-align: center;

            /* ✅ Pop-in animation only once */
            transform: scale(0.8);
            opacity: 0;
            animation: popIn 0.8s ease-out forwards;
        }

        /* Pop animation */
        @keyframes popIn {
            0% { transform: scale(0.8); opacity: 0; }
            60% { transform: scale(1.05); opacity: 1; }
            100% { transform: scale(1); opacity: 1; }
        }

        h2 {
            color: white;
            font-family: 'Poppins', sans-serif;
            margin-bottom: 15px;
            font-size: 20px;
            font-weight: 700;
        }

        input, select {
            padding: 8px;
            width: 90%;
            margin-top: 10px;
            border: none;
            border-radius: 6px;
            background: rgba(255, 255, 255, 0.85);
            font-size: 14px;
            outline: none;
        }

        button.theme-btn {
            background: linear-gradient(to right, #2563eb, #22c55e);
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 6px;
            cursor: pointer;
            font-weight: 600;
            font-size: 14px;
            transition: 0.3s;
            width: 100%;
            margin-top: 15px;
        }
        button.theme-btn:hover { transform: scale(1.05); }

        a {
            color: #22c55e;
            font-weight: 600;
            text-decoration: none;
            font-size: 13px;
        }
        a:hover { color: #2563eb; }
    </style>
</head>
<body>
    <div class="panel">
        <h2>Create Account</h2>
        <form action="${pageContext.request.contextPath}/signup" method="post">
            <input type="text" name="username" placeholder="Username" required>
            <input type="email" name="email" placeholder="Email" required>

            <!-- Role Dropdown -->
            <select name="role" required>
                <option value="" disabled selected>Select Role</option>
                <option value="ADMIN">Admin</option>
                <option value="RETAIL">Retail Shop</option>
            </select>

            <input type="password" name="password" placeholder="Password" required>
            <input type="password" name="confirmPassword" placeholder="Confirm Password" required>

            <button type="submit" class="theme-btn">Sign Up</button>
        </form>
        <p style="margin-top:15px;"><a href="login.jsp">Back to Login</a></p>
    </div>
</body>
</html>
