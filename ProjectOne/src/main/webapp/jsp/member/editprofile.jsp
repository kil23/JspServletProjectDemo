<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="/ProjectOne/css/bootstrap.css">
<script src="/ProjectOne/js/bootstrap.js"></script>
<title>Edit Profile</title>
</head>
<body>

	<form action="${pageContext.request.contextPath}/saveprofile" method="post">
		<table style="with: 50%">
		<tr>
		<td>First Name</td>
		<td> <input type="text" name="fname" value="${sessionScope.user.fname}"> ${errorsReg['fnameError']}</td>
		</tr>
		<tr>
		<td>Last Name </td>
		<td> <input type="text" name="lname" value="${sessionScope.user.lname}"> ${errorsReg['lnameError']} </td>
		</tr>
		<tr>
		<td>Phone no </td>
		<td><input type="text" name="phone" value="${sessionScope.user.phone}"> ${errorsReg['phoneError']}</td>
		</tr>
		<tr>
		<td>E-mail </td>
		<td><input type="text" name="email" value="${sessionScope.user.email}"> ${errorsReg['emailError']}</td>
		</tr>
		<tr>
		
		<td>Type </td>
		<td><input type="radio" name="type" value="Sitter" >Sitter	<input type="radio" name="type" value="Seeker" >Seeker</td>
		</tr>
		<tr>
    	 	<td>Year of Experience: <input type="text"  name="yearExp" class="sitter" value="${sessionScope.user.yrExp}"/>${errorsReg['yrExpError']}</td>
   		 	<td>Expected Pay: <input type="text"  name="pay"  class="sitter" value="${sessionScope.user.epay}"/>${errorsReg['payError']}</td>
		</tr>
		<tr>
    	 	<td>Number of Children: <input type="text" name="children"  class="seeker" value="${sessionScope.user.totalChildren}"/>${errorsReg['numChildrenError']}</td>
   		 	<td>Name of Spouse: <input type="text" name="spouse"  class="seeker" value="${sessionScope.user.spouseName}"/>${errorsReg['spouseNameError']}</td> 	 
		</tr>
		<tr>
		<td>Address  </td>
		<td> <textarea rows="2" cols="40" name="addr" ><c:out value="${sessionScope.user.addr}" /></textarea> ${errorsReg['addrError']} </td>
		</tr>
		</table>
		<input type="submit" value="Save">
		
	</form>
</body>
</html>