=<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
	<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon.ico">

    <meta charset="utf-8">
    <title>Inventory Management - Admin Dashboard</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600;700&family=Roboto:wght@400;600&display=swap" rel="stylesheet">

    <!-- External CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
	
	<style>
	/* Chat Button */
	
	#chatMessages div {
	    color: black !important;
	    background: #f1f1f1;
	    padding: 6px;
	    border-radius: 6px;
	}

	#chatBtn {
	    position: fixed;
	    bottom: 20px;
	    right: 20px;
	    background: #007bff;
	    color: white;
	    border-radius: 50%;
	    width: 60px;
	    height: 60px;
	    display: flex;
	    justify-content: center;
	    align-items: center;
	    font-size: 26px;
	    cursor: pointer;
	    box-shadow: 0px 0px 10px #888;
	    z-index: 9999;
	}

	/* Chat Window */
	#chatBox {
	    position: fixed;
	    bottom: 90px;
	    right: 20px;
	    width: 320px;
	    height: 400px;
	    background: white;
	    border-radius: 10px;
	    box-shadow: 0px 0px 10px #888;
	    display: flex;
	    flex-direction: column;
	    font-family: Arial, sans-serif;
	    z-index: 9999;
	}

	#chatHeader {
	    background: #007bff;
	    color: white;
	    padding: 10px;
	    display: flex;
	    justify-content: space-between;
	    border-top-left-radius: 10px;
	    border-top-right-radius: 10px;
	}

	#chatMessages {
	    flex: 1;
	    padding: 10px;
	    overflow-y: auto;
	    font-size: 14px;
	}
	
	

	#chatInput {
	    display: flex;
	    padding: 10px;
	    border-top: 1px solid #ddd;
	}

	#chatInput input {
	    flex: 1;
	    padding: 6px;
	    border: 1px solid #aaa;
	    border-radius: 4px;
	}

	#chatInput button {
	    margin-left: 5px;
	    padding: 6px 10px;
	    background: #007bff;
	    border: none;
	    color: white;
	    border-radius: 4px;
	    cursor: pointer;
	}

	.close-btn {
	    cursor: pointer;
	    font-size: 16px;
	}
	</style>

</head>
<body class="dashboard-page">
<div class="app">
    <!-- Sidebar -->
    <aside class="sidebar">
        <div class="brand">
            <div class="logo">SB</div>
            <div>
                <h1>SpringBoard</h1>
                <div class="subtitle">Admin Dashboard</div>
            </div>
        </div>
		<nav class="nav">
		    <a href="${pageContext.request.contextPath}/dashboard" class="active"><i class="fa fa-home"></i> Overview</a>

		    <!-- Items Dropdown -->
		    <div class="dropdown">
		        <a href="#"><i class="fa fa-box"></i> Items ▾</a>
		        <div class="dropdown-content">
		            <a href="${pageContext.request.contextPath}/sup/add">Add Item</a>
		            <a href="${pageContext.request.contextPath}/items">View Items</a>
		        </div>
		    </div>

		    <!-- Suppliers Dropdown -->
		    <div class="dropdown">
		        <a href="#"><i class="fa fa-truck"></i> Manage Suppliers ▾</a>
		        <div class="dropdown-content">
		            <a href="${pageContext.request.contextPath}/sup/addsup">Add Supplier</a>
		            <a href="${pageContext.request.contextPath}/sup/all">View Suppliers</a>
		        </div>
		    </div>
			<!-- Orders Dropdown -->
			<div class="dropdown">
			    <a href="#"><i class="fa fa-shopping-cart"></i> Orders ▾</a>
			    <div class="dropdown-content">
			        <a href="${pageContext.request.contextPath}/orders/order">All Orders</a>
			        <a href="${pageContext.request.contextPath}/orders/dispatched">Dispatched</a>
			    </div>
			</div>

			<a href="${pageContext.request.contextPath}/orders/insufficient"><!--<i class="fa fa-credit-card">--></i>📦📊 Stock Manage</a>
            <a href="${pageContext.request.contextPath}/report"><i class="fa fa-chart-line"></i> Reports</a>
            <a href="${pageContext.request.contextPath}/settings"><i class="fa fa-cog"></i> Settings</a>
            <a href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out-alt"></i> Logout</a>
        </nav>
    </aside>

    <!-- Main -->
    <main class="main">
        <!-- Topbar -->
        <div class="topbar">
            <div class="search">
                <svg width="18" height="18" fill="none" viewBox="0 0 24 24">
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5"
                          d="M21 21l-4.35-4.35"></path>
                    <circle cx="11" cy="11" r="6" stroke="currentColor"
                            stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5"></circle>
                </svg>
                <input placeholder="Search users, orders, reports..."/>
            </div>
            <div class="topbar-right">
                <button id="themeToggle" class="theme-toggle">🌙</button>
                <div class="admin-info">
                    <div id="adminName" class="admin-name">${username}</div>
                    <div class="role">Admin</div>
                </div>
                <div id="adminAvatar" class="avatar"></div>
            </div>
        </div>

        <!-- Example dashboard cards -->
        <div class="cards">
            <div class="card">Users: 120</div>
            <div class="card">Orders: 87</div>
            <div class="card">Revenue: $24K</div>
        </div>

        <!-- Content -->
        <div class="content">
            <div class="panel chart-panel">
                <h3>Sales Overview</h3>
                <canvas id="salesChart"></canvas>
            </div>

            <div class="panel table-panel">
                <!-- Orders table -->
                <table>
                    <tr><th>Order</th><th>User</th><th>Status</th></tr>
                    <tr><td>#1001</td><td>Arya</td><td>Completed</td></tr>
                    <tr><td>#1002</td><td>Ravi</td><td>Pending</td></tr>
                </table>
            </div>
        </div>
    </main>
</div>

<!-- External JS -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>
<script>
function openChat() {
    document.getElementById("chatBox").style.display = "flex";
}

function closeChat() {
    document.getElementById("chatBox").style.display = "none";
}

function sendMessage() {
    var query = document.getElementById("query").value;
    if (!query.trim()) return;

    addMessage("user", query);
    document.getElementById("query").value = "";

    fetch("${pageContext.request.contextPath}/api/ai/ask", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ query: query })
    })
    .then(res => res.json())
    .then(data => {
        addMessage("bot", data.answer);
    })
    .catch(() => {
        addMessage("bot", "Error contacting the AI server.");
    });
}

function addMessage(sender, msg) {
    var box = document.getElementById("chatMessages");

    var div = document.createElement("div");
    div.style.marginBottom = "10px";

    if (sender === "user") {
        div.innerHTML = "<b>You:</b> " + msg;
    } else {
        div.innerHTML = "<b>AI:</b> " + msg;
    }

    box.appendChild(div);
    box.scrollTop = box.scrollHeight;
}
</script>

<!-- AI Chatbot: Floating Button + Chat Window -->
<div id="chatBtn" onclick="openChat()">
    💬 
</div>

<div id="chatBox" style="display:none;">
    <div id="chatHeader">
        <b>AI Inventory Assistant</b>
        <span onclick="closeChat()" class="close-btn">✖</span>
    </div>

    <div id="chatMessages"></div>

    <div id="chatInput">
        <input type="text" id="query" placeholder="Ask something..."/>
        <button onclick="sendMessage()">Send</button>
    </div>
</div>

</body>
</html>
