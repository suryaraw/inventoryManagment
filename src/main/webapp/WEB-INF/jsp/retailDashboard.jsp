<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inventory Management - Report</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <style>
        /* Table hover effect */
        table tbody tr:hover {
            background-color: #f2f6ff; /* light blue highlight */
            color: #000; /* keep text dark for readability */
            cursor: pointer;
            transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
            font-weight: 500; /* slightly bold for clarity */
        }

        table tbody tr:hover td {
            color: #000; /* ensures text inside cells stays dark */
        }
    </style>
</head>
<body class="dashboard-page">

<!-- Navbar -->
<div class="navbar">
    <span class="brand"><i class="fa fa-store"></i> Retail Shop</span>
    <div>
        <a href="${pageContext.request.contextPath}/retail/items"><i class="fa fa-box"></i> Items</a>
        <a href="${pageContext.request.contextPath}/retail/orders"><i class="fa fa-shopping-cart"></i> Orders</a>
        <a href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out-alt"></i> Logout</a>
    </div>
</div>

<!-- Dashboard -->
<div class="dashboard-box">
    <h2>Hello, ${sessionScope.user} 👋</h2>
    <p class="welcome-text">Welcome to your Retail Dashboard. Select items to order:</p>

    <!-- Form -->
    <form action="${pageContext.request.contextPath}/shop/selected" method="post">
        <table border="1" cellspacing="0" cellpadding="8" style="width:100%; border-collapse: collapse;">
            <thead>
                <tr>
                    <th>Select</th>
                    <th>ID</th>
                    <th>Item Name</th>
                    <th>Category</th>
                    <th>Brand</th>
                    <th>Model</th>
                    <th>Price</th>
                    <th>GST</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${items}">
                    <tr>
                        <td>
                            <input type="checkbox" name="picked" value="${item.id}">
                        </td>
                        <td>${item.id}</td>
                        <td>${item.name}</td>
                        <td>${item.category}</td>
                        <td>${item.brand}</td>
                        <td>${item.model}</td>
                        <td>₹${item.price}</td>
                        <td>${item.gst}</td>
                        <td>
                            <input type="number" name="quantity_${item.id}" min="1" value="1" style="width:60px;">
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <button type="submit" style="margin-top:15px;">Proceed to Checkout</button>
    </form>
</div>

</body>
</html>
