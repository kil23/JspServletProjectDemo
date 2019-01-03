<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="/ProjectOne/css/bootstrap.css">
<script src="/ProjectOne/js/bootstrap.js"></script>
<title> Edit Job </title>
</head>
<body>
	<div class="container mt-10 w-20 mb-10 center-block">
		<div id="wrapper">
			<div id="head">
				<h2 class="text-center mb-5"> Edit Job </h2>
			</div>
		</div>
		<form action="/ProjectOne/jsp/seeker/editjob" method="post">
            <input type="hidden" name="jobid" value="${requestScope.Job.id}" />
            <div class="form-group">
                <label for="title">Title:</label>
                <input type="text" class="form-control" name="title" id="title" value="${requestScope.Job.title}" />
                <c:if test="${not empty jobError['title']}">
                    <div class="alert alert-danger">
                        ${jobError['title']}
                    </div>
                </c:if>
            </div>

            <div class="form-group">
                <label for="start_date">Start Date:</label>
                <input type="date" class="form-control" id="start_date" name="startdate" value="${requestScope.Job.startDate}" />
                <c:if test="${not empty jobError['startDate']}">
                    <div class="alert alert-danger">
                        ${jobError['startDate']}
                    </div>
                </c:if>
            </div>

            <div class="form-group">
                <label for="end_date">End Date:</label>
                <input type="date" class="form-control" id="end_date" name="enddate" value="${requestScope.Job.endDate}" />
                <c:if test="${not empty jobError['endDate']}">
                    <div class="alert alert-danger">
                        ${jobError['endDate']}
                    </div>
                </c:if>
            </div>

            <div class="form-group">
                <label for="payperhr">Pay Per Hour:</label>
                <input type="number" step="any" class="form-control" id="payperhr" name="payPerHr" value="${requestScope.Job.payPerHr}" />
                <c:if test="${not empty jobError['payPerHr']}">
                    <div class="alert alert-danger">
                        ${jobError['payPerHr']}
                    </div>
                </c:if>
            </div>
            <div style="text-align:center">
                <input type="submit" class="btn btn-default" value="Save"/>
                <a href="/ProjectOne/" class="btn btn-primary" role="button">Home</a>
            </div>
        </form>
	</div>
</body>
</html>