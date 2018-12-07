<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/visitor/login.do" method="post">
		<pre>
		Login Credentials:
		
		Email: <input type="text" name="email"  value="${param.email}"> ${errorsLogin['emailError']}
		
		Password: <input type="password" name="password" value="${param.password}"> ${errorsLogin['passwordError']}
		
		<input type="submit" value="Login">
		<input type="checkbox" name="rememberMe"> Remember Me 
		${errorsLogin['loginError']}
		</pre>
	</form>
</body>
</html>