<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Insufficient Stock Orders</title>
    <style>
        body { background:#121212; font-family:Arial,sans-serif; color:white; }
        .brand-card {
            background: #1e1e2f;
            padding: 20px;
            margin-bottom: 25px;
            border-radius: 10px;
        }
        h2 { color:#fbbf24; }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 15px 0;
            background: rgba(255,255,255,0.05);
        }
        table th, table td {
            padding: 10px 15px;
            color: #fff;
            border-bottom: 1px solid rgba(255,255,255,0.1);
        }
        table thead { background:#3a86ff; }
        .mail-btn {
            background: #ef476f;
            color: white;
            border: none;
            padding: 10px 20px;
            font-weight: bold;
            border-radius: 6px;
            cursor: pointer;
        }
        .mail-btn:hover { background:#d90429; }
    </style>
</head>
<body>
<h1 style="text-align:center;">📦 Insufficient Stock Orders</h1>
<c:forEach var="entry" items="${groupedOrders}">
    <div class="brand-card">
        <h2>Brand: ${entry.key}</h2>
        <form action="${pageContext.request.contextPath}/notifyBrand/${entry.key}" method="post">
            <table>
                <thead>
                    <tr>
                        <th>S.No</th>
                        <th>Item ID</th>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Quantity Ordered</th>
                        <th>Current Stock</th>   <!-- ✅ New -->
                        <th>Needed Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="row" items="${entry.value}">
                        <c:set var="order" value="${row.order}" />
                        <c:set var="currentStock" value="${row.currentStock}" />
                        <tr>
                            <td>${order.s_no}</td>
                            <td>${order.item_id}</td>
                            <td>${order.name}</td>
                            <td>${order.category}</td>
                            <td>${order.quantity}</td>
                            <td>${currentStock}</td> <!-- ✅ shows stock -->
                            <td>
                                <input type="number"
                                       name="neededQuantities"
                                       placeholder="Enter qty"
                                       min="1"
                                       style="width:80px;"
                                       required />
                                <input type="hidden" name="itemIds" value="${order.item_id}" />
								<input type="hidden" name="sNos" value="${order.s_no}" /> 
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <button type="submit" class="mail-btn">📧 Send Mail</button>
        </form>
    </div>
</c:forEach>


</body>
</html>
