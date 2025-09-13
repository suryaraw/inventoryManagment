<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inventory Management System - Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@600;700&display=swap" rel="stylesheet">

    <style>
        /* Background Image */
        body.dashboard-page {
            background-image: url('${pageContext.request.contextPath}/images/background1.jpg');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            background-attachment: fixed;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        /* Login Panel */
        .panel {
            background: rgba(15, 23, 42, 0.95);
            border-radius: 12px;
            padding: 30px;
            max-width: 380px;
            width: 100%;
            box-shadow: 0px 6px 20px rgba(0, 0, 0, 0.5);
            text-align: center;
        }

        /* Branding (Logo + Text in one line) */
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

        .subtitle {
            font-size: 13px;
            color: #bbb;
            margin-bottom: 15px;
        }

        /* Input fields */
        .input-group {
            display: flex;
            align-items: center;
            background: #fff;
            padding: 6px;
            border-radius: 6px;
            margin-bottom: 12px;
            transition: all 0.3s ease;
        }

        .input-group i {
            margin-right: 8px;
            color: #555;
            font-size: 14px;
        }

        .input-group input,
        .input-group select {
            border: none;
            outline: none;
            width: 100%;
            font-size: 14px;
            padding: 6px;
        }

        .input-group:focus-within {
            box-shadow: 0 0 0 2px #3b82f6;
        }

        /* Login button */
        button.theme-toggle {
            background: linear-gradient(to right, #2563eb, #22c55e);
            color: white;
            border: none;
            padding: 8px;
            border-radius: 6px;
            cursor: pointer;
            font-weight: 600;
            font-size: 14px;
            margin-top: 5px;
            transition: all 0.3s ease-in-out;
        }

        button.theme-toggle:hover {
            transform: scale(1.03);
            box-shadow: 0 6px 15px rgba(37,99,235,0.4);
            opacity: 0.95;
        }

        /* Extra links */
        .extra-links a {
            text-decoration: none;
            font-size: 13px;
            font-weight: 600;
            color: #22c55e;
            transition: color 0.3s ease;
            cursor: pointer;
        }

        .extra-links a:hover {
            color: #2563eb;
        }

        .extra-links span {
            margin: 0 5px;
            color: #999;
        }

        /* Popup Modal */
        .modal {
            display: none;
            position: fixed;
            z-index: 9999;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background: rgba(0,0,0,0.6);
            backdrop-filter: blur(3px);
            justify-content: center;
            align-items: center;
            animation: fadeIn 0.4s ease;
        }

        .modal-content {
            background: #fff;
            padding: 20px;
            border-radius: 12px;
            max-width: 350px;
            width: 90%;
            text-align: center;
            animation: slideIn 0.4s ease;
        }

        .modal-content h2 {
            margin-bottom: 15px;
            font-family: 'Poppins', sans-serif;
        }

        .close-btn {
            background: #ef4444;
            color: #fff;
            border: none;
            padding: 8px 16px;
            border-radius: 8px;
            cursor: pointer;
            margin-top: 15px;
        }

        .close-btn:hover {
            background: #dc2626;
        }

        @keyframes fadeIn {
            from {opacity: 0;}
            to {opacity: 1;}
        }

        @keyframes slideIn {
            from {transform: translateY(-50px); opacity: 0;}
            to {transform: translateY(0); opacity: 1;}
        }
    </style>
</head>
<body class="dashboard-page">
    <script src="${pageContext.request.contextPath}/js/dashboard.js"></script>

    <div class="app" style="justify-content:center;align-items:center;">
        <div class="panel">
            <!-- Branding -->
            <div class="brand">
                <img src="${pageContext.request.contextPath}/images/logo1.jpg" alt="Inventory Logo">
                <h1>INVENTORY MANAGEMENT</h1>
            </div>
            <p class="subtitle">Secure Login</p>

            <!-- Login Form -->
            <form action="${pageContext.request.contextPath}/login" method="post" style="margin-top:20px;">
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

                <button type="submit" class="theme-toggle" style="width:100%;">Login</button>

                <div class="extra-links" style="margin-top:15px;">
                    <a onclick="openModal('forgotModal')">Forgot Password?</a>
                    <span>|</span>
                    <a onclick="openModal('signupModal')">Create Account</a>
                </div>
            </form>
        </div>
    </div>

    <!-- Forgot Password Modal -->
    <div id="forgotModal" class="modal">
        <div class="modal-content">
            <h2>Forgot Password</h2>
            <p>Enter your email to reset your password.</p>
            <input type="email" placeholder="Enter Email" style="padding:8px;width:90%;margin-top:10px;border:1px solid #ccc;border-radius:6px;">
            <br>
            <button class="close-btn" onclick="closeModal('forgotModal')">Close</button>
        </div>
    </div>

    <!-- Create Account Modal -->
    <div id="signupModal" class="modal">
        <div class="modal-content">
            <h2>Create Account</h2>
            <p>Enter your details to sign up.</p>
            <input type="text" placeholder="Username" style="padding:8px;width:90%;margin-top:10px;border:1px solid #ccc;border-radius:6px;">
            <input type="email" placeholder="Email" style="padding:8px;width:90%;margin-top:10px;border:1px solid #ccc;border-radius:6px;">
            <input type="password" placeholder="Password" style="padding:8px;width:90%;margin-top:10px;border:1px solid #ccc;border-radius:6px;">
            <br>
            <button class="close-btn" onclick="closeModal('signupModal')">Close</button>
        </div>
    </div>

    <script>
        function openModal(id) {
            document.getElementById(id).style.display = "flex";
        }
        function closeModal(id) {
            document.getElementById(id).style.display = "none";
        }
    </script>
</body>
</html>
