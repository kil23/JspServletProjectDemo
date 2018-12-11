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
                <h2 class="text-center mb-5">Job: ${jobTitle}</h2>
            </div>
        </div>

        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Applicant Name</th>
                        <th>Application Status</th>
                        <th>Expected Pay</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="tempJobApplication" items="${jobApplications}">
                        <tr>
                            <td> ${tempJobApplication.sitterName} </td>
                            <td> ${tempJobApplication.status} </td>
                            <td> ${tempJobApplication.expectedPay} </td>
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