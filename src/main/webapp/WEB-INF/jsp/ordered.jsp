<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ordered Items</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">
    <style>
        .app {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            padding: 20px;
            box-sizing: border-box;
        }
        .items-card {
            width: 100%;
            max-width: 1200px;
            background: var(--card);
            padding: 20px;
            border-radius: 12px;
            box-shadow: 0 6px 20px rgba(0,0,0,0.2);
            margin: auto;
            overflow-x: auto;
        }
        .items-card h2 {
            font-weight: 600;
            margin-bottom: 20px;
            color: #fff;
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 0 auto;
            background: rgba(255,255,255,0.05);
            border-radius: 8px;
            overflow: hidden;
        }
        table thead {
            background: linear-gradient(135deg, var(--accent-1), var(--accent-2));
            color: #fff;
        }
        table th, table td {
            padding: 12px 15px;
            text-align: left;
            color: #fff;
        }
        table tbody tr:nth-child(even) {
            background: rgba(255,255,255,0.05);
        }
        table tbody tr:hover {
            background: rgba(255,255,255,0.1);
        }
        .action-btn {
            display: inline-block;
            padding: 6px 12px;
            border-radius: 6px;
            font-size: 14px;
            font-weight: 600;
            text-decoration: none;
            transition: all 0.3s ease;
            margin-right: 8px;
            text-align: center;
            min-width: 80px;
        }
        .action-btn.approve {
            background: linear-gradient(135deg, #4cafef, #3a86ff);
            color: #fff;
        }
        .action-btn.approve:hover {
            background: linear-gradient(135deg, #3a86ff, #265d9c);
            box-shadow: 0 4px 10px rgba(0,0,0,0.3);
        }
    </style>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	
		<script>
		   const contextPath = "${pageContext.request.contextPath}";
		   function approve(itemId, quantity, paymentId, s_no) {
		       const btn = document.getElementById("approve-btn-" + itemId);

		       fetch(contextPath + "/approve/" + itemId + "/" + quantity + "/" + paymentId, {
		           method: "POST"
		       })
		       .then(response => response.json())
		       .then(data => {
		           if (data.success) {
		               Swal.fire("Approved!", "Order approved successfully!", "success")
		                   .then(() => location.reload());
		           } else {
		               Swal.fire("Rejected!", "Insufficient stock.", "error");
		               // turn button into "Mail"
		               btn.textContent = "Mail";
		               btn.classList.remove("approve");
		               btn.classList.add("mail-btn");
		               btn.onclick = function() {
		                   window.location.href = contextPath + "/sendFailureMail/" + s_no;
		               };
		           }
		       });
		   }
		</script>
</head>
<body>
<div class="app">
    <div class="items-card">
        <h2>🛒 Ordered Items</h2>
        <table>
            <thead>
                <tr>
                    <th>S.No</th>
                    <th>Item ID</th>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Brand</th>
                    <th>Model</th>
                    <th>Price</th>
                    <th>GST</th>
                    <th>Quantity</th>
                    <th>Total Price</th>
                    <th>Amount Paid</th>
                    <th>Overall</th>
                    <th>Payment ID</th>
                    <th>Dispatch Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.s_no}</td>
                        <td>${order.item_id}</td>
                        <td>${order.name}</td>
                        <td>${order.category}</td>
                        <td>${order.brand}</td>
                        <td>${order.model}</td>
                        <td>${order.price}</td>
                        <td>${order.gst}</td>
                        <td>${order.quantity}</td>
                        <td>${order.totalprice}</td>
                        <td>${order.amountPaid}</td>
                        <td>${order.overall}</td>
                        <td>${order.paymentId.id}</td>
                        <td>${order.dispatchStatus}</td>
						<td>
						    <
							<button type="button"
							        class="action-btn approve"
							        id="approve-btn-${order.item_id}"
							        onclick="approve('${order.item_id}', '${order.quantity}', '${order.paymentId.id}', '${order.s_no}')">
							    Approve
							</button>

						</td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
