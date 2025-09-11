<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inventory Management - Report</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body class="dashboard-page">
	<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>

<div class="app" style="flex-direction:column;align-items:center; padding:20px;">
    <div class="panel" style="width:80%; max-width:900px;">
        <h2 style="margin-bottom:20px;">Inventory Management Report</h2>
        <table>
            <tr>
                <th>Item Name</th>
                <th>Total Stock</th>
                <th>Sold</th>
            </tr>
            <c:forEach var="report" items="${reports}">
                <tr>
                    <td>${report.itemName}</td>
                    <td>${report.totalStock}</td>
                    <td>${report.sold}</td>
                </tr>
            </c:forEach>
        </table>

        <!-- Generate PDF Button -->
        <form action="${pageContext.request.contextPath}/report/pdf" method="get" style="margin-top:20px; text-align:center;">
            <button type="submit" class="theme-toggle">GENERATE PDF</button>
        </form>
    </div>
</div>

</body>
</html>
