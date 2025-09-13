<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add Item</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">
    <style>
        /* Center the card */
        .app {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            padding: 20px;
        }

        .add-item-card {
            width: 100%;
            max-width: 600px;
            background: var(--card);
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 6px 20px rgba(0,0,0,0.2);
            transition: all 0.3s ease-in-out;
        }

        .add-item-card:hover {
            transform: translateY(-6px) scale(1.02);
            box-shadow: 0 12px 24px rgba(0,0,0,0.3);
        }

        .add-item-card h2 {
            font-weight: 600;
            margin-bottom: 20px;
            color: #fff;
            text-align: center;
        }

        .form-group {
            margin-bottom: 15px;
            display: flex;
            flex-direction: column;
        }

        .form-group label {
            margin-bottom: 6px;
            color: var(--muted);
            font-weight: 500;
        }

        .form-group input {
            padding: 10px 12px;
            border-radius: 8px;
            border: none;
            outline: none;
            background: var(--glass);
            color: #fff;
            transition: all 0.2s ease;
        }

        .form-group input:focus {
            background: rgba(255,255,255,0.1);
        }

        .submit-btn {
            width: 100%;
            padding: 12px;
            border-radius: 8px;
            border: none;
            background: linear-gradient(135deg, var(--accent-1), var(--accent-2));
            color: #fff;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease-in-out;
            margin-top: 10px;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 8px;
        }

        .submit-btn:hover {
            transform: translateY(-2px) scale(1.02);
            box-shadow: 0 10px 20px rgba(0,0,0,0.25);
        }
    </style>
	<style>
	    /* Same look as input fields */
	    .form-group select {
	        padding: 10px 12px;
	        border-radius: 8px;
	        border: none;
	        outline: none;
	        background: var(--glass);
	        color: #fff;
	        transition: all 0.2s ease;
	        cursor: pointer;
	    }

	    /* On hover */
	    .form-group select:hover {
	        background: rgba(255, 255, 255, 0.1);
	        border: 1px solid var(--accent-1); /* Outer color on hover */
	    }

	    /* On focus (when clicked) */
	    .form-group select:focus {
	        background: rgba(255, 255, 255, 0.15);
	        border: 1px solid var(--accent-2);
	    }

	    /* Dropdown options */
	    .form-group select option {
	        background: #333;   /* Dark dropdown */
	        color: #fff;        /* White text */
	        padding: 10px;
	    }

	    .form-group select option:hover {
	        background: var(--accent-1);
	        color: #000;
	    }
	</style>

</head>

<body>
    <div class="app">
        <div class="add-item-card">
            <h2><i class="fa fa-plus-circle"></i> Add New Item</h2>
           <!-- <form action="${pageContext.request.contextPath}/addItem" method="post">-->
			<form action="${pageContext.request.contextPath}/items/save" method="post">
                <div class="form-group">
                    <label for="name">Item Name</label>
                    <input type="text" id="name" name="name" placeholder="Enter item name" required/>
                </div>
                <div class="form-group">
                    <label for="category">Category</label>
                    <input type="text" id="category" name="category" placeholder="Enter category" required/>
                </div>
                <div class="form-group">
                    <label for="brand">Brand</label>
                    <input type="text" id="brand" name="brand" placeholder="Enter brand"/>
                </div>
                <div class="form-group">
                    <label for="model">Model</label>
                    <input type="text" id="modelName" name="modelName" placeholder="Enter model"/>
                </div>
				<div class="form-group">
				    <label for="supplierId">Supplier</label>
				    <select id="supplierId" name="supplierId" required>
				        <c:forEach var="supplier" items="${suppliers}">
				            <option value="${supplier.id}">${supplier.name}</option>
				        </c:forEach>
				    </select>
				</div>
                <div class="form-group">
                    <label for="wholesalePrice">Wholesale Price</label>
                    <input type="number" id="wholesalePrice" name="wholesalePrice" step="0.01" placeholder="0.00" required/>
                </div>
				<div class="form-group">
				                    <label for="quantity">Quantity(pcs)</label>
				                    <input type="number" id="quantity" name="quantity" step="0.01" placeholder="1" required/>
				                </div>
                <div class="form-group">
                    <label for="retailPrice">Retail Price</label>
                    <input type="number" id="retailPrice" name="retailPrice" step="0.01" placeholder="0.00" required/>
                </div>
                <div class="form-group">
                    <label for="gstRate">GST Rate (%)</label>
                    <input type="number" id="gstRate" name="gstRate" step="0.01" placeholder="0.00" required/>
                </div>
                <button type="submit" class="submit-btn">
                    <i class="fa fa-save"></i> Save Item
                </button>
            </form>
        </div>
    </div>

</body>
</html>
