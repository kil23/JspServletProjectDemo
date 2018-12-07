<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>
<%@ page isELIgnored="false" %>

</head>
<body>
	<form action="${pageContext.request.contextPath}/visitor/register.do" method="post">
		<pre>
		First_Name: <input type="text" name="fname" value="${param.fname}"> ${errorsReg['fnameError']}
		
		Last_Name: <input type="text" name="lname" value="${param.lname}"> ${errorsReg['lnameError']}
		
		Phone_no: <input type="text" name="phone" value="${param.phone}"> ${errorsReg['phoneError']}
		
		E-mail: <input type="text" name="email" value="${param.email}"> ${errorsReg['emailError']}
		
		Type: 	<input type="radio" name="type" value="Sitter">Sitter	<input type="radio" name="type" value="Seeker">Seeker
		
		<jsp:element name="Sitter" >
		<label>
		Year_of_Experience: 	</label><input type="text" name="yearExp" value="${param.yearExp}">
		
		<label>
		Expected_pay: 		</label><input type="text" name="pay" value="${param.pay}">
		</jsp:element>
		<jsp:element name="Seeker" >
		<label>
		Children_in_Family: 	</label><input type="text" name="children" value="${param.children}">
		
		<label>
		Name_of_Spouse: 	</label><input type="text" name="spouse" value="${param.spouse}">
		</jsp:element>
		Address: 		<textarea rows="2" cols="40" name="addr" ><c:out value="${param.addr}" /></textarea> ${errorsReg['addrError']}
		
		Enter Password: 	<input type="password" name="password" value="${param.password}"> ${errorsReg['passwordError']}
		
		Confirm Password: 	<input type="password" name="Cpassword" value="${param.Cpassword}"> ${errorsReg['cpasswordError']}
		
		${errorsReg['passwordcompareError']}
		
		<input type="submit" value="Register">
		</pre>
	</form>
</body>
</html>