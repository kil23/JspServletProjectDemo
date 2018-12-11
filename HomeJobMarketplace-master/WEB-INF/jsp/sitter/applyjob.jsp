<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.care.model.Member" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Apply for Job</title>
    <link type="text/css" rel="stylesheet" href="/HomeJobMarketplace/css/bootstrap.css">
    <script src="/HomeJobMarketplace/js/bootstrap.js"></script>
</head>
<body>
    <div class="container mt-5 w-25 mb-5 center-block">
        <div id="wrapper">
            <div id="header">
                <h3 class="text-center mb-3">${requestScope.Title}</h3>
            </div>
        </div>
        <form action="/HomeJobMarketplace/sitter/apply-job" method="post">
            <input type="hidden" name="jobid" value="${JobId}" />

            <div class="form-group">
                <label for="expPay">Expected Pay:</label>
                <input type="number" class="form-control" name="expectedpay" id="expPay" step="any" value="${jobApplication.expectedPay}">
                <c:if test="${not empty jobApplication.errors['expectedPay']}">
                    <div class="alert alert-danger">
                        ${jobApplication.errors['expectedPay']}
                    </div>
                </c:if>
            </div>

            <div style="text-align:center">
                <input type="submit" class="btn btn-default" value="Apply"/>
            </div>
        </form>
        </br></br>
        <div style="text-align:center">
            <a href="/HomeJobMarketplace/" class="btn btn-primary" role="button" >Home</a>
        </div>
    </div>
</body>
</html>