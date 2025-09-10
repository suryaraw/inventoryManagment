<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inventory Management - Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
	<div class="add-item-container">
	    <h2><i class="fa fa-plus-circle"></i> Add New Item</h2>
	    <form action="${pageContext.request.contextPath}/items/save" method="post">
	        <div class="form-group">
	            <label for="name">Item Name</label>
	            <input type="text" id="name" name="name" required/>
	        </div>

	        <div class="form-group">
	            <label for="category">Category</label>
	            <input type="text" id="category" name="category" required/>
	        </div>

	        <div class="form-group">
	            <label for="brand">Brand</label>
	            <input type="text" id="brand" name="brand"/>
	        </div>

	        <div class="form-group">
	            <label for="model">Model</label>
	            <input type="text" id="model" name="model"/>
	        </div>

	        <div class="form-group">
	            <label for="wholesalePrice">Wholesale Price</label>
	            <input type="number" id="wholesalePrice" name="wholesalePrice" step="0.01" required/>
	        </div>

	        <div class="form-group">
	            <label for="retailPrice">Retail Price</label>
	            <input type="number" id="retailPrice" name="retailPrice" step="0.01" required/>
	        </div>

	        <div class="form-group">
	            <label for="gstRate">GST Rate (%)</label>
	            <input type="number" id="gstRate" name="gstRate" step="0.01" required/>
	        </div>

	        <button type="submit"><i class="fa fa-save"></i> Save Item</button>
	    </form>
	</div>

</body>
</html>
