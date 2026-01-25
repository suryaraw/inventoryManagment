<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Items List</title>
	<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon.ico">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">
    <style>
        .app {
            display: flex;
            justify-content: center;
            align-items: center;   /* vertical centering */
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
            overflow-x: auto;   /* scroll on small screens */
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

        .actions a {
            margin-right: 10px;
            text-decoration: none;
            font-weight: bold;
        }

        .add-btn {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 15px;
            border-radius: 8px;
            background: linear-gradient(135deg, var(--accent-1), var(--accent-2));
            color: #fff;
            font-weight: 600;
            text-decoration: none;
        }

        .add-btn:hover {
            box-shadow: 0 8px 16px rgba(0,0,0,0.3);
        }

        .pagination {
            margin-top: 25px;
            text-align: center;
        }

        .page-btn {
            display: inline-block;
            margin: 0 5px;
            padding: 10px 15px;
            border-radius: 50%;
            background: rgba(255, 255, 255, 0.1);
            color: #fff;
            font-weight: bold;
            text-decoration: none;
            transition: all 0.3s ease;
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
        }

        .page-btn:hover {
            background: rgba(255, 255, 255, 0.3);
            color: #000;
            transform: scale(1.15);
            box-shadow: 0 8px 16px rgba(255,255,255,0.5);
        }

        .page-btn.active {
            background: linear-gradient(135deg, var(--accent-1), var(--accent-2));
            color: #fff;
            transform: scale(1.2);
            box-shadow: 0 8px 18px rgba(0,0,0,0.4);
        }

        /* Action buttons */
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
            min-width: 60px;
        }

        .action-btn.edit {
            background: linear-gradient(135deg, #4cafef, #3a86ff);
            color: #fff;
        }
        .action-btn.edit:hover {
            background: linear-gradient(135deg, #3a86ff, #265d9c);
            box-shadow: 0 4px 10px rgba(0,0,0,0.3);
        }

        .action-btn.delete {
            background: linear-gradient(135deg, #ff6b6b, #e63946);
            color: #fff;
        }
        .action-btn.delete:hover {
            background: linear-gradient(135deg, #e63946, #c1121f);
            box-shadow: 0 4px 10px rgba(0,0,0,0.3);
        }

        /* Responsive table */
        @media (max-width: 768px) {
            table, thead, tbody, th, td, tr {
                display: block;
            }
            thead {
                display: none;
            }
            tr {
                margin-bottom: 15px;
                background: rgba(255,255,255,0.05);
                padding: 10px;
                border-radius: 8px;
            }
            td {
                padding: 10px;
                text-align: right;
                position: relative;
            }
            td::before {
                content: attr(data-label);
                position: absolute;
                left: 10px;
                font-weight: bold;
                text-align: left;
            }
        }
    </style>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
    function confirmDelete(event, url, itemName) {
        event.preventDefault();
        Swal.fire({
            title: 'Delete Item?',
            text: "Are you sure you want to delete '" + itemName + "'?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            cancelButtonColor: '#3085d6',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = url;
            }
        });
    }
    </script>
</head>
<body>
    <div class="app">
        <div class="items-card">
            <h2>📦 Items List</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Item Name</th>
                        <th>Category</th>
                        <th>Brand</th>
                        <th>Model</th>
                        <th>Wholesale Price</th>
                        <th>Retail Price</th>
                        <th>Purchase Date</th>
                        <th>GST (%)</th>
                        <th>Stock</th>
                        <th>Supplier</th>
                        <th>Modify</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${items}">
                        <tr>
                            <td data-label="ID">${item.id}</td>
                            <td data-label="Item Name">${item.name}</td>
                            <td data-label="Category">${item.category}</td>
                            <td data-label="Brand">${item.brand}</td>
                            <td data-label="Model">${item.model}</td>
                            <td data-label="Wholesale Price">${item.wholesalePrice}</td>
                            <td data-label="Retail Price">${item.retailPrice}</td>
                            <td data-label="Purchase Date">${item.dateOfPurchase}</td>
                            <td data-label="GST (%)">${item.gstRate}</td>
                            <td data-label="Stock">${item.getQuantity()}</td>
                            <td data-label="Supplier">${item.supplier.name}</td>
                            <td data-label="Modify" class="actions">
                                <a href="${pageContext.request.contextPath}/items/edit/${item.id}" class="action-btn edit">✏ Edit</a>
                                <a href="${pageContext.request.contextPath}/item/delete/${item.id}"
                                   onclick="confirmDelete(event, this.href, '${item.name}')"
                                   class="action-btn delete">🗑 Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div class="pagination">
                <c:if test="${totalPages > 1}">
                    <c:if test="${currentPage > 0}">
                        <a class="page-btn" href="${pageContext.request.contextPath}/items?page=${currentPage - 1}&size=10">⬅</a>
                    </c:if>

                    <c:forEach var="i" begin="0" end="${totalPages - 1}">
                        <c:choose>
                            <c:when test="${i == currentPage}">
                                <span class="page-btn active">${i + 1}</span>
                            </c:when>
                            <c:otherwise>
                                <a class="page-btn" href="${pageContext.request.contextPath}/items?page=${i}&size=10">${i + 1}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="${currentPage < totalPages - 1}">
                        <a class="page-btn" href="${pageContext.request.contextPath}/items?page=${currentPage + 1}&size=10">➡</a>
                    </c:if>
                </c:if>
            </div>

            <a href="${pageContext.request.contextPath}/sup/add" class="add-btn">➕ Add New Item</a>
        </div>
    </div>
</body>
</html>
