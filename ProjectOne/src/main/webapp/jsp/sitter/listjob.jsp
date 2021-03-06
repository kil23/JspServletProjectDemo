<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Job Listing </title>
</head>
<body>
	<div class="container mt-5" style="max-width: 500px">
        <div id="wrapper">
            <div id="header">
                <h2 class="text-center mb-5">Apply for Job</h2>
            </div>
        </div>

        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Pay per Hour</th>
                        <th>Start Date</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="joblisting" items="${joblist}">

                        <c:url var="applylink" value="/ProjectOne/jsp/sitter/applyjob">
                            <c:param name="command" value="APPLY"/>
                            <c:param name="Jobid" value="${joblisting.jobid}"/>
                        </c:url>

                        <tr>
                            <td> ${joblisting.title} </td>
                            <td> ${joblisting.payPerHour} </td>
                            <td> <fmt:formatDate value="${joblisting.startDate}" pattern="dd-MM-yyyy" /> </td>
                            <td> <fmt:formatDate value="${joblisting.endDate}" pattern="dd-MM-yyyy" /> </td>
                            <td>
                                <a href="${link}">Apply</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <br></br>
        <div style="text-align:center">
            <a href="/ProjectOne/" class="btn btn-primary" role="button" >Home</a>
        </div>
    </div>
</body>
</html>