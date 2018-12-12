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
<%
    Cookie[] cookies=request.getCookies();
    String userName = "",rememberVal="";
    if (cookies != null) {
         for (Cookie cookie : cookies) {
           if(cookie.getName().equals("cookieUser")) {
             userName = cookie.getValue();
           }
           if(cookie.getName().equals("cookieRemember")){
             rememberVal = cookie.getValue();
           }
        }
    }
%>
	<form action="${pageContext.request.contextPath}/visitor/login.do" method="post">
		<h3>Login Credentials:</h3>
		<table style="with: 50%">
		<tr>
		<td>E-mail </td> 
		<td> <input type="text" name="email"  value="${param.email}"> ${errorsLogin['emailError']} </td>
		</tr>
		<tr>
		<td> Password  </td>
		<td> <input type="password" name="password" value="${param.password}"> ${errorsLogin['passwordError']} </td>
		</tr>
		<tr>
		<td> <input type="checkbox" name="rememberMe" value="1"> 
		Remember Me </td>
		</tr> 
		<tr> <td>${errorsLogin['loginError']} </td> </tr>
		</table>
		<input type="submit" value="Login">
	</form>
</body>
</html>