<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Suppliers List</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">

    <style>
        body {
            font-family: 'Inter', sans-serif;
            background: var(--background);
            color: #fff;
        }

        .app {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            padding: 20px;
        }

        .suppliers-card {
            width: 100%;
            max-width: 1100px;
            background: var(--card);
            padding: 20px;
            border-radius: 12px;
            box-shadow: 0 6px 20px rgba(0,0,0,0.2);
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            font-weight: 600;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: rgba(255,255,255,0.05);
            border-radius: 8px;
            overflow: hidden;
        }

        thead {
            background: linear-gradient(135deg, var(--accent-1), var(--accent-2));
            color: #fff;
        }

        th, td {
            padding: 12px 15px;
            text-align: left;
        }

        tbody tr:nth-child(even) {
            background: rgba(255,255,255,0.05);
        }

        tbody tr:hover {
            background: rgba(255,255,255,0.1);
        }

        .action-btn {
            padding: 6px 12px;
            border-radius: 6px;
            font-size: 14px;
            font-weight: 600;
            text-decoration: none;
            margin-right: 8px;
        }

        .edit {
            background: linear-gradient(135deg, #4cafef, #3a86ff);
            color: #fff;
        }
        .edit:hover {
            background: linear-gradient(135deg, #3a86ff, #265d9c);
        }

        .delete {
            background: linear-gradient(135deg, #ff6b6b, #e63946);
            color: #fff;
        }
        .delete:hover {
            background: linear-gradient(135deg, #e63946, #c1121f);
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
    </style>

    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
    function confirmDelete(event, url, supplierName) {
        event.preventDefault();
        Swal.fire({
            title: 'Delete Supplier?',
            text: "Are you sure you want to delete '" + supplierName + "'?",
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
    <div class="suppliers-card">
        <h2>👤 Suppliers List</h2>
        <table>
            <thead>
                <tr>
                    <th>Supplier Name</th>
                    <th>Contact Person</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Address</th>
                    <th>Modify</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="supplier" items="${suppliers}">
                    <tr>
                        <td>${supplier.name}</td>
                        <td>${supplier.contactPerson}</td>
                        <td>${supplier.phone}</td>
                        <td>${supplier.email}</td>
                        <td>${supplier.address}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/sup/update/${supplier.id}" class="action-btn edit">✏ Edit</a>
                            <a href="${pageContext.request.contextPath}/sup/delete/${supplier.id}"
                               onclick="confirmDelete(event, this.href, '${supplier.name}')"
                               class="action-btn delete">🗑 Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a href="${pageContext.request.contextPath}/sup/addsup" class="add-btn">➕ Add New Supplier</a>
    </div>
</div>
</body>
</html>
