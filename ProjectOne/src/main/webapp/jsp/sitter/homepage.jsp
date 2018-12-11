<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" 
integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<style>

</style>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      Welcome <%= session.getAttribute("username") %>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="/ProjectOne/jsp/seeker/homepage.jsp">Home</a></li>
    </ul >
    <ul class="nav navbar-nav">
    	<li><a href="/ProjectOne/jsp/profile.jsp">Profile</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
          <li><a href=""><span class="glyphicon glyphicon-log-in"></span> Logout </a></li>
    </ul>
  </div>
</nav>
	
	<table >
		<tr>
			<td>
				<a href="CreateCourse.jsp">My Application</a>
			</td>
		</tr>
		<tr>
			<td>
				<a href="CreateCourse.jsp"> Apply to new job</a>
			</td>
		</tr>
		<tr>
			<td>
				<a href="CreateCourse.jsp">Edit Profile</a>
			</td>
		</tr>
		<tr>
			<td>
				<a href="CloseAccountServlet.java">Close Account</a>
			</td>
		</tr>
		
	</table>
 
</body>
</html>