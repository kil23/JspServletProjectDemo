<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Edit Job Application </title>
</head>
<body>
	 <div class="container mt-10 w-20 mb-5 center-block">
        <div id="wrapper">
            <div id="head">
                <h3 class="text-center mb-5">Edit Job: ${requestScope.title} Application</h3>
            </div>
        </div>
        <form action="/ProjectOne/jsp/sitter/editjobapplication" method="post">
            <input type="hidden" name="jobappid" value="${jobApplicationid}" />

            <div class="form-group">
                <label for="expPay">Expected Pay:</label>
                <input type="number" class="form-control" name="expectedpay" id="expPay" step="any" value="${requestScope.ExpectedPay}">
            </div>

            <div style="text-align:center">
                <input type="submit" class="btn btn-default" value="Update"/>
            </div>
        </form>
        </br>
        
        <div style="text-align:center">
            <a href="/ProjectOne/" class="btn btn-primary" role="button" >Home</a>
        </div>
    </div>
</body>
</html>