<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Supplier</title>
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

        .add-supplier-card {
            width: 100%;
            max-width: 600px;
            background: var(--card);
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 6px 20px rgba(0,0,0,0.2);
            transition: all 0.3s ease-in-out;
        }

        .add-supplier-card:hover {
            transform: translateY(-6px) scale(1.02);
            box-shadow: 0 12px 24px rgba(0,0,0,0.3);
        }

        .add-supplier-card h2 {
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

        .form-group input,
        .form-group textarea {
            padding: 10px 12px;
            border-radius: 8px;
            border: none;
            outline: none;
            background: var(--glass);
            color: #fff;
            transition: all 0.2s ease;
        }

        .form-group input:focus,
        .form-group textarea:focus {
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
</head>

<body>
<div class="app">
    <div class="add-supplier-card">
        <h2><i class="fa fa-truck"></i> Add New Supplier</h2>
        <form action="${pageContext.request.contextPath}/sup/newadd" method="post">
            <div class="form-group">
                <label for="name">Supplier Name</label>
                <input type="text" id="name" name="name" placeholder="Enter supplier name" required/>
            </div>

            <div class="form-group">
                <label for="contactPerson">Contact Person</label>
                <input type="text" id="contactPerson" name="contactPerson" placeholder="Enter contact person" required/>
            </div>

            <div class="form-group">
                <label for="phone">Phone</label>
                <input type="number" id="phone" name="phone" placeholder="Enter phone number" required/>
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Enter email" required/>
            </div>

            <div class="form-group">
                <label for="address">Address</label>
                <textarea id="address" name="address" placeholder="Enter address" rows="3" required></textarea>
            </div>

            <button type="submit" class="submit-btn">
                <i class="fa fa-save"></i> Save Supplier
            </button>
        </form>
    </div>
</div>
</body>
</html>
