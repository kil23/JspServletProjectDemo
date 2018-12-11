<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Home Job Marketplace</title>
    <script src="/HomeJobMarketplace/js/bootstrap.js"></script>
    <link type="text/css" rel="stylesheet" href="/HomeJobMarketplace/css/bootstrap.css">
</head>
<body>
    <div class="container mt-5 w-25">
        <div id="wrapper">
            <div id="header">
                <h1 class="text-center mb-5">404 Page not found</h1>
            </div>
        </div>

        <c:if test="${not empty requestScope.error}">
            <div class="alert alert-danger">
              ${requestScope.error}
            </div>
        </c:if>

        Lost? Go to <a href="/HomeJobMarketplace">homepage</a>.
    </div>
</body>
</html>