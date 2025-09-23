<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inventory Management System - Create Account</title>
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
        .subtitle {font-size: 13px;color: #ddd;margin-bottom: 15px;}
        .input-group {display: flex;align-items: center;background: rgba(255,255,255,0.85);padding: 6px;border-radius: 6px;margin-bottom: 12px;}
        .input-group i {margin-right: 8px;color: #444;font-size: 14px;}
        .input-group input, .input-group select {
            border: none;outline: none;width: 100%;font-size: 14px;padding: 6px;background: transparent;
        }
        button.theme-btn {
            background: linear-gradient(to right, #2563eb, #22c55e);
            color: white;border: none;padding: 10px;width: 180px;
            border-radius: 6px;cursor: pointer;font-weight: 600;
            font-size: 15px;transition: 0.3s;
        }
        button.theme-btn:hover {transform: scale(1.05);}
    </style>
    <script>
        function validateForm() {
            var phone = document.getElementsByName("phone")[0].value;
            var phonePattern = /^[0-9]{10}$/;
            if (!phonePattern.test(phone)) {
                alert("Phone number must be exactly 10 digits.");
                return false; // Prevent form submission
            }
            return true; // Allow form submission
        }
    </script>
</head>
<body>
    <div class="panel">
        <div class="brand">
            <img src="${pageContext.request.contextPath}/images/logo1.jpg" alt="Logo">
            <h1>INVENTORY MANAGEMENT</h1>
        </div>
        <p class="subtitle">Create Account</p>
        <form action="${pageContext.request.contextPath}/createAccountService" method="post" onsubmit="return validateForm();">
            <div class="input-group">
                <i class="fa fa-user"></i>
                <input type="text" name="username" placeholder="Username" required>
            </div>
            <div class="input-group">
                <i class="fa fa-envelope"></i>
                <input type="email" name="email" placeholder="Email" required>
            </div>
            <div class="input-group">
                <i class="fa fa-phone"></i>
                <input type="text" name="phone" placeholder="Phone Number" required>
            </div>
            <div class="input-group">
                <i class="fa fa-users"></i>
                <select name="role" required>
                    <option value="">-- Select Role --</option>
                    <option value="ADMIN">Admin</option>
                    <option value="RETAIL_SHOP">Retail Shop</option>
                </select>
            </div>
            <div class="input-group">
                <i class="fa fa-lock"></i>
                <input type="password" name="password" placeholder="Password" required>
            </div>
            <div class="input-group">
                <i class="fa fa-lock"></i>
                <input type="password" name="confirmPassword" placeholder="Confirm Password" required>
            </div>
            <button type="submit" class="theme-btn">Create</button>
        </form>
    </div>
</body>
</html>
