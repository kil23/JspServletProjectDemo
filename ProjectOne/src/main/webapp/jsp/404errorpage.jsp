<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link type="text/css" rel="stylesheet" href="/ProjectOne/css/bootstrap.css">
<script src="/ProjectOne/js/bootstrap.js"></script>
<title>Insert title here</title>
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

        Go to <a href="/ProjectOne/jsp/"<c:out value="${sessionScope.type}" />"/homepage.jsp">homepage</a>.
    </div>
</body>
</html>