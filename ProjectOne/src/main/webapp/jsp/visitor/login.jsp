<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link type="text/css" rel="stylesheet" href="/ProjectOne/css/bootstrap.css">
<script src="/ProjectOne/js/bootstrap.js"></script>
<title>Login Page</title>
</head>
<body id="LoginForm">
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
	<form action="${pageContext.request.contextPath}/visitor/login" method="post">
		 <div class="container"  >
        <div class="row">
            <div class="col-md-3 "></div>
                <div class="col-md-6  col-xs-12">
                    <div class="jumbotron">
                        <h1 class="text-center">Login Page</h1>
                        <br>
                        <div class="form-group">
                           <label for="email">Email address:</label>
							<input type="text" name="email" class="form-control " value="${param.email}"> 
		  					<label class="text-danger">${errorsLogin['emailError']}</label>  
                        </div>
                        <span class="glyphicon glyphicon-search"></span>aasd
                        <br>
                        <div class="form-group">
                            <label for="pwd">Password:</label>
		   					<input type="password" name="password" class="form-control " value="${param.password}"> 
		  					<label class="text-danger">${errorsLogin['passwordError']}</label> 
                        </div>
                        <div class="forgot">
						 	<a href="#" name="forgotPassword"> Forgot Password?</a>
						</div>
						<div class="remember">
						  	<input type="checkbox" name="rememberMe" value="1"> Remember Me  
						 </div>
						   <label class="text-danger">${errorsLogin['loginError']}</label>    
						<div style="text-align:center">
							<input type="submit" class="btn btn-primary  btn-md btn-block" value="Login">
						</div>
                    </div>
                </div>
            <div class="col-md-3"></div>
        </div>
    </div>
	</form>
</body>
</html>