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
  <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script>
  <style type="text/css">
  
  ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  width: 25%;
  background-color: #f1f1f1;
  height: 100%; 
  position: fixed; 
  overflow: auto; 
} 
  </style>
</head>
<body>
 <pre> *** Job Posting *** </pre>
 <div>
 	<div>
		<ul>
  			<li><a href="homepage.jsp">Home</a></li>
  			<li><a href="profile.jsp">Profile</a></li>
  			<li><a href="postjob.jsp">Post-job</a></li>
  			<li><a href="editjob.jsp">Edit-job</a></li>
  			<li><a href="index.jsp">Close Account</a></li>
  			<li><a href="index.jsp">Logout</a></li>
  		</ul>
	</div>
	<div>
 		Title : <input type="text" name ="jobtitle" >
 		Posted_by :
 		Start-date : <input type="text" id="datepicker">
 		End-date : <input type="text" id="datepicker">
 	</div>
 </div>
</body>
</html>