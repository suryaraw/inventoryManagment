<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Retail Shop Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
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
