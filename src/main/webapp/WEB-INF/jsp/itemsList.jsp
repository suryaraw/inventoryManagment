<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Items List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">
    <style>
        .app {
            display: flex;
            justify-content: center;
            align-items: flex-start;
            min-height: 100vh;
            padding: 30px;
        }

        .items-card {
            width: 100%;
            max-width: 1000px;
            background: var(--card);
            padding: 25px;
            border-radius: 12px;
            box-shadow: 0 6px 20px rgba(0,0,0,0.2);
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
            color: var(--accent-1);
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

    </style>
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
						<th>PurchaseDate</th>
                        <th>GST (%)</th>
                        <th>Supplier</th>
						<th>Modify</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${items}">
                        <tr>
                            <td>${item.id}</td>
                            <td>${item.name}</td>
                            <td>${item.category}</td>
                            <td>${item.brand}</td>
                            <td>${item.model}</td>
                            <td>${item.wholesalePrice}</td>
                            <td>${item.retailPrice}</td>
							<td>${item.dateOfPurchase}</td>
                            <td>${item.gstRate}</td>
							<td>${item.supplier.name}</td>
                            <td class="actions">
                                <a href="${pageContext.request.contextPath}/items/edit/${item.id}">✏ Edit</a>
                                <a href="${pageContext.request.contextPath}/items/delete/${item.id}">🗑 Delete</a>
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
