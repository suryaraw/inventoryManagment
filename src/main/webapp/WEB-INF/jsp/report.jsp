<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inventory Management - Report</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon.ico">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <style>
        html, body {
            height: 100%;
            margin: 0;
            overflow-x: auto;
            overflow-y: auto;
        }

        .panel {
            width: 95%;
            max-width: 1200px;
            overflow-x: auto;
            overflow-y: auto;
        }

        #reportTable {
            width: 100%;
            border-collapse: collapse;
        }

        table tr:hover {
            background-color: #007bff;
            color: #fff;
            transition: background-color 0.2s ease-in-out;
        }

        .search-container {
            display: flex;
            justify-content: flex-end;
            margin-bottom: 15px;
        }

        .search-box {
            position: relative;
            width: 250px;
        }

        .search-box input {
            padding: 6px 28px 6px 10px;
            width: 100%;
            border: 1px solid #ccc;
            border-radius: 20px;
            font-size: 13px;
        }

        .search-box i {
            position: absolute;
            right: 10px;
            top: 50%;
            transform: translateY(-50%);
            color: #888;
        }
    </style>
</head>
<body class="dashboard-page">
    <script src="${pageContext.request.contextPath}/js/dashboard.js"></script>

<div class="app" style="flex-direction:column;align-items:center; padding:20px;">
    <div class="panel">
        <h2 style="margin-bottom:20px;">Inventory Management Report</h2>

        <!-- ✅ Search box right aligned -->
        <div class="search-container">
            <div class="search-box">
                <input type="text" id="searchInput" onkeyup="filterTable()" placeholder="Search by Item Name...">
                <i class="fas fa-search"></i>
            </div>
        </div>

        <table id="reportTable">
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
                <th>Dispatch Status</th>
                <th>Payment ID</th>
            </tr>
            <c:forEach var="report" items="${reports}" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${report.item_id}</td>
                    <td>${report.name}</td>
                    <td>${report.category}</td>
                    <td>${report.brand}</td>
                    <td>${report.model}</td>
                    <td>${report.price}</td>
                    <td>${report.gst}</td>
                    <td>${report.quantity}</td>
                    <td>${report.totalprice}</td>
                    <td>${report.amountPaid}</td>
                    <td>${report.overall}</td>
                    <td>${report.dispatchStatus}</td>
                    <td>${report.paymentId.id}</td>
                </tr>
            </c:forEach>
        </table>

        <!-- Generate PDF Button -->
        <form action="${pageContext.request.contextPath}/report/pdf" method="get" style="margin-top:20px; text-align:center;">
            <button type="submit" class="theme-toggle">GENERATE PDF</button>
        </form>
    </div>
</div>

<!-- ✅ JS filter function (fixed to use Name column index = 2) -->
<script>
function filterTable() {
    let input = document.getElementById("searchInput");
    let filter = input.value.toLowerCase();
    let table = document.getElementById("reportTable");
    let tr = table.getElementsByTagName("tr");

    for (let i = 1; i < tr.length; i++) { // skip header row
        let td = tr[i].getElementsByTagName("td")[2]; // ✅ 3rd column = Name
        if (td) {
            let textValue = td.textContent || td.innerText;
            tr[i].style.display = textValue.toLowerCase().includes(filter) ? "" : "none";
        }
    }
}
</script>

</body>
</html>
