<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Inventory Management - Payment</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">

    <!-- Fonts & CSS -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600;700&family=Roboto:wght@400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body class="dashboard-page">
	<div class="app">
	    <!-- Sidebar -->
	    <aside class="sidebar">
	        <div class="brand">
	            <div class="logo">SB</div>
	            <div>
	                <h1>SpringBoard</h1>
	                <div class="subtitle">Admin Dashboard</div>
	            </div>
	        </div>
	        <nav class="nav">
	            <a href="${pageContext.request.contextPath}/dashboard"><i class="fa-solid fa-home"></i> Overview</a>
	            <div class="dropdown">
	                <a href="#"><i class="fa-solid fa-box"></i> Items ▾</a>
	                <div class="dropdown-content">
	                    <a href="${pageContext.request.contextPath}/items/add">Add Item</a>
	                    <a href="${pageContext.request.contextPath}/items">View Items</a>
	                </div>
	            </div>
	            <a href="${pageContext.request.contextPath}/orders"><i class="fa-solid fa-shopping-cart"></i> Orders</a>
	            <a href="${pageContext.request.contextPath}/report"><i class="fa-solid fa-chart-line"></i> Reports</a>
	            <a href="${pageContext.request.contextPath}/checkout" class="active"><i class="fa-solid fa-credit-card"></i> Payments</a>
	            <a href="${pageContext.request.contextPath}/settings"><i class="fa-solid fa-cog"></i> Settings</a>
	            <a href="${pageContext.request.contextPath}/logout"><i class="fa-solid fa-sign-out-alt"></i> Logout</a>
	        </nav>
	    </aside>

	    <!-- Main -->
	    <main class="main">
	        <div class="topbar">
	            <div class="search">
	                <input placeholder="Search..." />
	            </div>
	            <div class="topbar-right">
	                <div class="admin-info">
	                    <div class="admin-name">${username}</div>
	                    <div class="role">Admin</div>
	                </div>
	                <div id="adminAvatar" class="avatar"></div>
	            </div>
	        </div>

	        <div class="content">
	            <div class="panel">
	                <h2>Make a Payment</h2>
	                <p>Enter the amount and click pay.</p>

	                <form id="paymentForm">
	                    <label for="amount">Amount (INR):</label>
	                    <input type="number" id="amount" name="amount" min="1" required />
	                    <button type="button" id="payBtn" class="btn">Pay with Razorpay</button>
	                </form>
	            </div>
	        </div>
	    </main>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
    <script>
    $(document).ready(function() {
        $("#payBtn").click(function () {
            var amount = $("#amount").val();

            if (amount <= 0 || amount > 100000) {
                alert("Amount must be between ₹1 and ₹1,00,000");
                return;
            }

            // Call backend to create order
            $.post("${pageContext.request.contextPath}/createOrder", {amount: amount}, function (order) {
                var orderObj = typeof order === 'string' ? JSON.parse(order) : order;

                var options = {
                    "key": "${razorpayKeyId}", 
                    "amount": orderObj.amount, 
                    "currency": "INR",
                    "name": "My Shop",
                    "order_id": orderObj.id, // generated from backend
                    "handler": function (response) {
                        // send details back to backend
						alert("OrderId: " + response.razorpay_order_id +
										          "\nPaymentId: " + response.razorpay_payment_id +
										          "\nSignature: " + response.razorpay_signature);
                        $.post("${pageContext.request.contextPath}/paymentSuccess", {
                            razorpayOrderId: response.razorpay_order_id,
                            razorpayPaymentId: response.razorpay_payment_id,
                            razorpaySignature: response.razorpay_signature,
                            amount: amount
                        }, function (msg) {
                            alert(msg);
							window.location.href = "${pageContext.request.contextPath}/paymentSuccess";
                        });
                    }
                };

                var rzp = new Razorpay(options);
                rzp.open();
            });
        });
    });
    </script>
</body>
</html>
