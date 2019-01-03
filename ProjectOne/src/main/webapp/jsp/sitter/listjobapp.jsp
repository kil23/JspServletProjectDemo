<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> List Job Application </title>
</head>
<body>
	<div class="container mt-5 w-50">
        <div id="wrapper">
            <div id="header">
                <h2 class="text-center mb-5"> My Applications </h2>
            </div>
        </div>

        <c:if test="${not empty requestScope.errormsg}">
            <div class="alert alert-danger">
              ${requestScope.error}
            </div>
        </c:if>

        <c:if test="${not empty requestScope.resultmsg}">
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
                    <c:forEach var="jobApp" items="${jobApp}">

                        <c:url var="editLink" value="/ProjectOne/jsp/sitter/editjobapp">
                            <c:param name="JobAppId" value="${jobApp.id}"/>
                            <c:param name="ExpectedPay" value="${jobApp.expectedPay}"/>
                        </c:url>

                        <c:url var="deleteLink" value="/ProjectOne/jsp/sitter/deletejobapp">
                            <c:param name="JobAppId" value="${jobApp.id}"/>
                        </c:url>

                        <tr>
                            <td> ${jobApp.title} </td>
                            <td> ${jobApp.expectedPay} </td>
                            <td> ${jobApp.payPerHour} </td>
                            <td> ${jobApp.status} </td>
                            <td>
                            <c:choose>
                                <c:when test="${jobApp.status == 'Active'}">
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
        <br><br>
        <div style="text-align:center">
            <a href="/ProjectOne/" class="btn btn-primary" role="button" >Home</a>
        </div>
    </div>

</body>
</html>