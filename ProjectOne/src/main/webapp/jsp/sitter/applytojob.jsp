<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Apply to Job</title>
</head>
<body>

	<div class="container mt-5 w-25 mb-5 center-block">
        <div id="wrapper">
            <div id="header">
                <h3 class="text-center mb-3">${requestScope.Title}</h3>
            </div>
        </div>
        <form action="/ProjectOne/jsp/sitter/applyjob" method="post">
            <input type="hidden" name="jobid" value="${JobId}" />

            <div class="form-group">
                <label for="expPay">Expected Pay:</label>
                <input type="number" class="form-control" name="expectedpay" id="expPay" step="any" value="${jobApplication.expectedPay}">
                <c:if test="${not empty jobApplication.errors['expectedPay']}">
                    <div class="alert alert-danger">
                        ${jobApplication.errors['expectedPay']}
                    </div>
                </c:if>
            </div>

            <div style="text-align:center">
                <input type="submit" class="btn btn-default" value="Apply"/>
            </div>
        </form>
        </br></br>
        <div style="text-align:center">
            <a href="/ProjectOne/" class="btn btn-primary" role="button" >Home</a>
        </div>
    </div>
	
	Title: 
	
	Start Date:
	
	End Date:
	
	Pay per Hour: 
	
	Expected_pay (in $ per hour) : <input type="text" name="expectedpay" >
	
	<input type="submit" value="Apply">
</body>
</html>