<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.care.model.Member" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>New Job Post</title>
    <link type="text/css" rel="stylesheet" href="/HomeJobMarketplace/css/bootstrap.css">
    <script src="/HomeJobMarketplace/js/bootstrap.js"></script>
</head>
<body>
    <div class="container mt-5 w-25 mb-5 center-block">
        <div id="wrapper">
            <div id="header">
                <h3 class="text-center mb-3">Post a new Job</h3>
            </div>
        </div>
        <form action="/HomeJobMarketplace/seeker/new-job" method="post">
            <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" class="form-control" name="title" id="title" value="${jobData.title}">
            <c:if test="${not empty jobData.errors['title']}">
                <div class="alert alert-danger">
                    ${jobData.errors['title']}
                </div>
            </c:if>
            </div>
            <div class="form-group">
            <label for="sdate">Start Date:</label>
            <input type="date" class="form-control" id="sdate" name="startdate" value="${jobData.startDate}">
            <c:if test="${not empty jobData.errors['startDate']}">
                <div class="alert alert-danger">
                    ${jobData.errors['startDate']}
                </div>
            </c:if>
            </div>
            <div class="form-group">
            <label for="edate">End Date:</label>
            <input type="date" class="form-control" id="edate" name="enddate" value="${jobData.endDate}">
            <c:if test="${not empty jobData.errors['endDate']}">
                <div class="alert alert-danger">
                    ${jobData.errors['endDate']}
                </div>
            </c:if>
            </div>
            <div class="form-group">
            <label for="payperhr">Pay Per Hour:</label>
            <input type="number" step="any" class="form-control" id="payperhr" name="payperhour" value="${jobData.payPerHour}">
            <c:if test="${not empty jobData.errors['payPerHour']}">
                <div class="alert alert-danger">
                    ${jobData.errors['payPerHour']}
                </div>
            </c:if>
            </div>
            <div style="text-align:center">
                <input type="submit" class="btn btn-default" value="PostJob"/>
            </div>
        </form>
        </br></br>
        <div style="text-align:center">
            <a href="/HomeJobMarketplace/" class="btn btn-primary" role="button" >Home</a>
        </div>
    </div>
</body>
</html>