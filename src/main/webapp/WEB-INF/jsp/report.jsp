<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Inventory Management - Dashboard</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;600&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
	<style>
	    body {
	        font-family: 'Roboto', sans-serif;
	        background-color: #f0f4ff;
	        margin: 0;
	        display: flex;
	        flex-direction: column;
	        align-items: center;
	        justify-content: flex-start;
	        padding: 40px 0;
	        min-height: 100vh;
	    }
	    h2 {
	        font-size: 36px;
	        font-weight: 800;
	        color: #007bff;
	        margin-bottom: 40px; /* space between title and table */
	        text-transform: uppercase;
	        letter-spacing: 1px;
	        text-align: center;
	        text-shadow: 1px 1px 4px rgba(0, 0, 0, 0.15);
	    }
	    table {
	        width: 70%;
	        border-collapse: collapse;
	        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
	        border-radius: 8px;
	        overflow: hidden;
	        background: #fff;
	    }
	    th, td {
	        padding: 12px 15px;
	        text-align: center;
	        border-bottom: 1px solid #ddd;
	    }
	    th {
	        background-color: #007bff;
	        color: #fff;
	        font-weight: 600;
	        text-transform: uppercase;
	    }
	    tr:hover {
	        background-color: #f1f1f1;
	        transform: scale(1.02);
	        transition: all 0.2s ease-in-out;
	    }
	    td {
	        color: #555;
	    }
	</style>
</head>
<body>
    <h2>Inventory Management Report</h2>
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
</body>
</html>
