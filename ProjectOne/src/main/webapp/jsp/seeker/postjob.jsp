<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Posting Job</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  
</head>
<body>
 <div>
 	<div>
		<ul>
  			<li><a href="homepage.jsp">Home</a></li>
  			<li><a href="profile.jsp">Profile</a></li>
  			<li><a href="postjob.jsp">Post-job</a></li>
  			<li><a href="index.jsp">Logout</a></li>
  		</ul>
	</div>
	 <div class="container mt-5 w-25 mb-5 center-block">
	<div id="wrapper">
            <div id="header">
                <h3 class="text-center mb-3">Post a Job</h3>
            </div>
        </div>
	 <form action="/ProjectOne/seeker/postjob" method="post">
            <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" class="form-control" name="title" id="title" value="${job.title}">
            ${errorsJob['titleError']}
            </div>
            <div class="form-group">
            <label for="start_date">Start Date:</label>
            <input type="date" class="form-control" id="start_date" name="startdate" value="${job.startDate}">
            ${errorsJob['startdateError']}
            </div>
            
            <div class="form-group">
            <label for="end_date">End Date:</label>
            <input type="date" class="form-control" id="end_date" name="enddate" value="${job.endDate}">
            ${errorsJob['enddateError']}
            </div>
            
            <div class="form-group">
            <label for="pay/hr">Pay Per Hour:</label>
            <input type="text" class="form-control" id="pay/hr" name="payperhr" value="${job.payPerHr}">
            ${errorsJob['payError']}
            </div>
            
            <div style="text-align:center">
                <input type="submit" class="btn btn-default" value="PostJob"/>
            </div>
        </form>
 </div>
 </div>
</body>
</html>