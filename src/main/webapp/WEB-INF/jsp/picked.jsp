<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Selected Items</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body class="dashboard-page">

<div class="navbar">
    <span class="brand"><i class="fa fa-store"></i> Retail Shop</span>
    <div>
        <a href="${pageContext.request.contextPath}/retail/items"><i class="fa fa-box"></i> Items</a>
        <a href="${pageContext.request.contextPath}/retail/orders"><i class="fa fa-shopping-cart"></i> Orders</a>
        <a href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out-alt"></i> Logout</a>
    </div>
</div>

<div class="dashboard-box">
    <h2>🛒 Order Summary</h2>

    <table border="1" cellspacing="0" cellpadding="8" style="width:100%; border-collapse: collapse;">
        <thead>
            <tr>
                <th>ID</th>
                <th>Item</th>
                <th>Category</th>
                <th>Brand</th>
                <th>Model</th>
                <th>Calculation</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="entry" items="${selectedItem}">
                <tr>
                    <td>${entry.key.id}</td>
                    <td>${entry.key.name}</td>
                    <td>${entry.key.category}</td>
                    <td>${entry.key.brand}</td>
                    <td>${entry.key.model}</td>
                    <td>${entry.value}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <h3 style="margin-top:15px;">Total Amount: ₹${orderSum}</h3>

    <div style="display:flex; justify-content:space-between; margin-top:20px;">
        <!-- Back button -->
        <form action="${pageContext.request.contextPath}/shop/items" method="get">
            <button type="submit" class="btn"><i class="fa fa-arrow-left"></i> Back</button>
        </form>

        <!-- Purchase Order button -->
        <form action="${pageContext.request.contextPath}/purchaseOrder" method="post">
            <button type="submit" class="btn btn-primary">Generate Purchase Order <i class="fa fa-arrow-right"></i></button>
        </form>
    </div>
</div>

</body>
</html>
