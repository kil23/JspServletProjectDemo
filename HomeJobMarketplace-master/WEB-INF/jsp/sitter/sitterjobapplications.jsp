<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Job Application List</title>
    <link type="text/css" rel="stylesheet" href="/HomeJobMarketplace/css/bootstrap.css">
    <script src="/HomeJobMarketplace/js/bootstrap.js"></script>
</head>
<body>
    <div class="container mt-5 w-50">
        <div id="wrapper">
            <div id="header">
                <h2 class="text-center mb-5">Your Applcations</h2>
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

        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Job Title</th>
                        <th>Expected Pay</th>
                        <th>Pay per Hour</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="tempJobApplication" items="${jobApplications}">

                        <c:url var="editLink" value="/sitter/edit-job-application">
                            <c:param name="JobApplicationId" value="${tempJobApplication.id}"/>
                            <c:param name="ExpectedPay" value="${tempJobApplication.expectedPay}"/>
                        </c:url>

                        <c:url var="deleteLink" value="/sitter/delete-job-application">
                            <c:param name="JobApplicationId" value="${tempJobApplication.id}"/>
                        </c:url>

                        <tr>
                            <td> ${tempJobApplication.title} </td>
                            <td> ${tempJobApplication.expectedPay} </td>
                            <td> ${tempJobApplication.payPerHour} </td>
                            <td> ${tempJobApplication.status} </td>
                            <td>
                            <c:choose>
                                <c:when test="${tempJobApplication.status == 'ACTIVE'}">
                                    <a href="${editLink}">Edit</a>
                                            |
                                    <a href="${deleteLink}" onclick="if(!(confirm('Sure you want to delete this application?'))) return false">Delete</a>
                                </c:when>
                                <c:otherwise>

                                </c:otherwise>
                            </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        </br></br>
        <div style="text-align:center">
            <a href="/HomeJobMarketplace/" class="btn btn-primary" role="button" >Home</a>
        </div>
    </div>
</body>
</html>