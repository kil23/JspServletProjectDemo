<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.care.context.MyApplicationContext"%>
<%
if(MyApplicationContext.get().getMember() != null) {
  request.getRequestDispatcher("/visitor/login").forward(request, response);
}
%>
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
                <h2 class="text-center mb-5">Home Job Marketplace</h2>
            </div>
        </div>

        <c:if test="${not empty requestScope.msg}">
            <div class="alert alert-success">
              ${requestScope.msg}
            </div>
        </c:if>

        <c:if test="${not empty requestScope.error}">
            <div class="alert alert-danger">
              ${requestScope.error}
            </div>
        </c:if>

       	<form action="/HomeJobMarketplace/visitor/welcome" method="get">
       	    <div class="row" style="margin-top:5em">
       	        <div class="col align-self-center" style="text-align:center">
                    <button type="submit" class="btn btn-default" name="type" value="sitter">Apply for a Job</button>
       	        </div>
       	        <div class="col align-self-center" style="text-align:center">
                    <button type="submit" class="btn btn-default" name="type" value="seeker">Post a Job</button>
                </div>
       	    </div>
        </form>
    </div>
</body>
</html>