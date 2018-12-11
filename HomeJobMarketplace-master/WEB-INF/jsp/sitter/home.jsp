<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Home</title>
    <link type="text/css" rel="stylesheet" href="/HomeJobMarketplace/css/bootstrap.css">
    <script src="/HomeJobMarketplace/js/bootstrap.js"></script>
</head>
<body>
    <div class="container mt-5 w-25">
        <div id="wrapper">
            <div id="header">
                <h3 class="text-center mb-5">Welcome to homepage sitter</h3>
            </div>
        </div>

        <c:if test="${not empty requestScope.error}">
            <div class="alert alert-danger">
              ${requestScope.error}
            </div>
        </c:if>

        <c:if test="${not empty requestScope.msg}">
            <div class="alert alert-success">
              ${requestScope.msg}
            </div>
        </c:if>

        <a href="/HomeJobMarketplace/member/profile">View profile</a> </br>
        <a href="/HomeJobMarketplace/sitter/list-job">Apply for a Job</a> </br>
        <a href="/HomeJobMarketplace/sitter/list-job-application">View your applications</a> </br>
        <a href="/HomeJobMarketplace/member/logout">Logout</a> </br>
    </div>
</body>
</html>