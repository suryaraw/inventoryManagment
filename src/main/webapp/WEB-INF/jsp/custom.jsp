<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
    <h2>Login Page</h2>

    <!-- Show error if login failed -->
    <c:if test="${param.error != null}">
        <p style="color:red;">Invalid username or password.</p>
    </c:if>

    <!-- Show message if logged out -->
    <c:if test="${param.logout != null}">
        <p style="color:green;">You have been logged out successfully.</p>
    </c:if>

    <form action="<c:url value='/doLogin'/>" method="post">
        <div>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required/>
        </div>

        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required/>
        </div>

        <!-- Spring Security CSRF token (if CSRF is enabled) -->
        <c:if test="${_csrf != null}">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </c:if>

        <div>
            <button type="submit">Login</button>
        </div>
    </form>
</body>
</html>
