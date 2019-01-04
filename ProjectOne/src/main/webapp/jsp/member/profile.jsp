<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
</head>
<body>
	<form action="/member/editprofile" method="get">
		<div class="form-group">
            <label for="firstname">First Name:</label>
            <input type="text" class="form-control" name="fname" id="firstname" value="${sessionScope.user.fname}" readonly="readonly">
        </div>
		<div class="form-group">
            <label for="lastname">Last Name:</label>
            <input type="text" class="form-control" id="lastname" name="lname" value="${sessionScope.user.lname}" readonly="readonly">
        </div>
		 <div class="form-group">
            <label for="phoneno">Phone No:</label>
            <input type="text" class="form-control" id="phoneno" name="phone" value="${sessionScope.user.phone}" readonly="readonly">
            </div>
            <div class="form-group">
            <label for="emailid">Email Id:</label>
            <input type="text" class="form-control" id="emailid" name="email"  value="${sessionScope.user.email}" readonly="readonly">
            </div>
		<c:choose>
                <c:when test="${sessionScope.type == 'SITTER'}">
                    <div class="form-group">
                    <label for="yrExp">Year of Experience:</label>
                    <input type="text" class="form-control" id="yrExp" name="yearExp" value="${sessionScope.user.yrExp}" readonly="readonly">
                    </div>
                    <div class="form-group">
   		 			<label for="epay">	Expected Pay: </label>
   		 			<input type="text"  name="pay" id="epay" class="form-control" value="${sessionScope.user.epay}" readonly="readonly"/>	
                    </div>
                </c:when>
                <c:when test="${sessionScope.type == 'SEEKER'}">
                    <div class="form-group">
                    <label for="numChildren">Total Children:</label>
                    <input type="text" class="form-control" id="numChildren" name="children" value="${sessionScope.user.totalChildren}" readonly="readonly">
                    </div>
                    <div class="form-group">
                    <label for="spouseName">Spouse Name:</label>
                    <input type="text" class="form-control" id="spouseName" name="spouse" value="${sessionScope.user.spouseName}" readonly="readonly">
                    </div>
                </c:when>
            </c:choose>
		 
		 <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" class="form-control" id="address" name="addr" value="${sessionScope.user.addr}"  readonly="readonly">
         </div>
         
		<div class="input-grp" style="text-align:center">
           <input type="submit" class="btn btn-primary" value="Edit"/>
           <a href="/ProjectOne/member/deleteprofile" class="btn btn-danger" role="button" onclick="if(!(confirm('Delete Profile?'))) return false">Delete</a>
        </div>
	</form>
	
	<div style="text-align:center">
            <a href="/ProjectOne/" class="btn btn-default" role="button" >Home</a>
        </div>
</body>
</html>