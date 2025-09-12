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
                <th>Price</th>
				<th>GST(%)</th>
				<th>Quantity</th>
				<th>Amount</th>
				
				id;
				    private String name;
				    private String category;
				    private String brand;
				    private String model;
				    private Double retailPrice;
				    private Double gstRate;
				    private Integer quantity;
				    private Double Amount;
            </tr>
        </thead>
        <tbody>
            <c:forEach var="entry" items="${selectedItem}">
                <tr>
                    <td>${entry.id}</td>
                    <td>${entry.name}</td>
                    <td>${entry.category}</td>
                    <td>${entry.brand}</td>
                    <td>${entry.model}</td>
                    <td>${entry.getRetailPrice()}</td>
					<td>${entry.getGstRate()}</td>
					<td>${entry.getQuantity()}</td>
					<td>${entry.getAmount()}</td>
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
