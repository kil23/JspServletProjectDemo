<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>
<%@ page isELIgnored="false" %>
<link type="text/css" rel="stylesheet" href="/ProjectOne/css/bootstrap.css">
<script src="/ProjectOne/js/bootstrap.js"></script>
<script type="text/javascript" src="ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/visitor/register" method="post">
		<table style="with: 50%">
		<tr>
		<td>First Name</td>
		<td> <input type="text" name="fname" value="${param.fname}"> ${errorsReg['fnameError']}</td>
		</tr>
		<tr>
		<td>Last Name </td>
		<td> <input type="text" name="lname" value="${param.lname}"> ${errorsReg['lnameError']} </td>
		</tr>
		<tr>
		<td>Phone no </td>
		<td><input type="text" name="phone" value="${param.phone}"> ${errorsReg['phoneError']}</td>
		</tr>
		<tr>
		<td>E-mail </td>
		<td><input type="text" name="email" value="${param.email}"> ${errorsReg['emailError']}</td>
		</tr>
		<tr>
		
		<td>Type </td>
		<td><input type="radio" name="type" value="Sitter" >Sitter	<input type="radio" name="type" value="Seeker" >Seeker</td>
		</tr>
		<tr>
    	 	<td>Year of Experience: <input type="text"  name="yearExp" class="sitter"/></td>
   		 	<td>Expected Pay: <input type="text"  name="pay"  class="sitter"/></td>
		</tr>
		<tr>
    	 	<td>Number of Children: <input type="text" name="children"  class="seeker"/></td>
   		 	<td>Name of Spouse: <input type="text" name="spouse"  class="seeker"/></td> 	 
		</tr>
		<tr>
		<td>Address  </td>
		<td> <textarea rows="2" cols="40" name="addr" ><c:out value="${param.addr}" /></textarea> ${errorsReg['addrError']} </td>
		</tr>
		<tr>
		<td>Enter Password </td>
		<td><input type="password" name="password" value="${param.password}"> ${errorsReg['passwordError']} </td>
		</tr>
		<tr>
		<td>Confirm Password </td>
		<td> 	<input type="password" name="Cpassword" value="${param.Cpassword}"> ${errorsReg['cpasswordError']} </td>
		</tr>
		<tr>
		<td>${errorsReg['passwordcompareError']}</td>
		</tr>
		</table>
		<input type="submit" value="Register">
		
	</form>
</body>
</html>