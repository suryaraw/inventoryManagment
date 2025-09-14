<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String status = (String) request.getAttribute("status");
    String recipient = (String) request.getAttribute("recipient");
    String subject = (String) request.getAttribute("subject");
    String message = (String) request.getAttribute("message");
    String errorDetails = (String) request.getAttribute("errorDetails");
    boolean ok = "success".equalsIgnoreCase(status);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mail Confirmation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #0f172a, #1e293b);
            color: #f1f5f9;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
        }
        .card {
            background: rgba(255, 255, 255, 0.05);
            border-radius: 14px;
            padding: 30px;
            width: 420px;
            text-align: center;
            box-shadow: 0 8px 20px rgba(0,0,0,0.5);
            animation: fadeIn 0.6s ease-in-out;
        }
        h1 {
            margin: 0 0 10px;
            font-size: 22px;
        }
        .status {
            font-size: 16px;
            font-weight: bold;
            padding: 10px;
            border-radius: 8px;
            margin: 15px 0;
        }
        .success {
            background: rgba(16,185,129,0.15);
            color: #10b981;
        }
        .failure {
            background: rgba(239,68,68,0.15);
            color: #ef4444;
        }
        .details {
            text-align: left;
            font-size: 14px;
            margin-top: 15px;
        }
        .details strong {
            display: inline-block;
            width: 70px;
        }
        .error-box {
            background: rgba(239,68,68,0.1);
            color: #fecaca;
            border-radius: 6px;
            padding: 10px;
            margin-top: 12px;
            font-size: 13px;
            white-space: pre-wrap;
        }
        .btn {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 18px;
            border-radius: 6px;
            font-size: 14px;
            font-weight: bold;
            text-decoration: none;
            background: linear-gradient(90deg,#06b6d4,#7c3aed);
            color: white;
            transition: 0.3s;
        }
        .btn:hover {
            opacity: 0.9;
        }
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(20px);}
            to { opacity: 1; transform: translateY(0);}
        }
    </style>
</head>
<body>
<div class="card">
    <h1><%= message %></h1>

    <div class="status <%= ok ? "success" : "failure" %>">
        <%= ok ? "Mail Sent Successfully ✅" : "Mail Sending Failed ❌" %>
    </div>

    <div class="details">
        <div><strong>To:</strong> <%= recipient %></div>
        <div><strong>Subject:</strong> <%= subject %></div>
        <div><strong>Time:</strong> <%= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()) %></div>
    </div>

    <% if(!ok && errorDetails != null) { %>
        <div class="error-box">
            <strong>Error:</strong><br><%= errorDetails %>
        </div>
    <% } %>

    <a href="<%= request.getContextPath() %>/dashboard" class="btn">Back to Dashboard</a>
</div>
</body>
</html>
