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
<div class="container mt-10 w-20 mb-10 center-block">
        <div id="wrapper">
            <div id="header">
                <h3 class="text-center mb-3">Edit Profile</h3>
            </div>
        </div>
        
	<form action="/ProjectOne/jsp/member/saveprofile" method="post">
		
		 <div class="form-group">
            <label for="fname">First Name:</label>
            <input type="text" class="form-control" name="firstname" id="fname" value="${sessionScope.user.fname}">
            <c:if test="${not empty errorsReg['fnameError']}">
                <div class="alert alert-danger">
                   ${errorsReg['fnameError']}
                </div>
            </c:if>
            </div>
            <div class="form-group">
            <label for="lname">Last Name:</label>
            <input type="text" class="form-control" id="lname" name="lastname" value="${sessionScope.user.lname}" >
            <c:if test="${not empty errorsReg['lnameError']}">
                <div class="alert alert-danger">
                     ${errorsReg['lnameError']}
                </div>
            </c:if>
            </div>
            <div class="form-group">
            <label for="phone">Phone No:</label>
            <input type="text" class="form-control" id="phone" name="phoneno" value="${sessionScope.user.phone}" >
            <c:if test="${not empty errorsReg['phoneError']}">
                <div class="alert alert-danger">
                     ${errorsReg['phoneError']}
                </div>
            </c:if>
            </div>
            <div class="form-group">
            <label for="email">Email Id:</label>
            <input type="text" class="form-control" id="email" name="emailid" value="${sessionScope.user.email}"  readonly="readonly">
            <c:if test="${not empty errorsReg['emailError']}">
                <div class="alert alert-danger">
                   ${errorsReg['emailError']}
                </div>
            </c:if>
            </div>
            
            
            <c:choose>
                <c:when test="${sessionScope.MemberType == 'SITTER'}">
                    <div class="form-group">
                    <label for="exp">Experience:</label>
                    <input type="text" class="form-control" id="exp" name="experience" value="${sessionScope.user.yrExp}" >
                    <c:if test="${not empty errorsReg['yrExpError']}">
                        <div class="alert alert-danger">
                           ${errorsReg['yrExpError']}
                        </div>
                    </c:if>
                    </div>
                    <div class="form-group">
                    <label for="epay">Experience:</label>
                    <input type="text" class="form-control" id="epay" name="expectedPay" value="${sessionScope.user.epay}" >
                    <c:if test="${not empty errorsReg['payError']}">
                        <div class="alert alert-danger">
                           ${errorsReg['payError']}
                        </div>
                    </c:if>
                    </div>
                </c:when>
                <c:when test="${sessionScope.MemberType == 'SEEKER'}">
                    <div class="form-group">
                    <label for="totalchildren">Total Children:</label>
                    <input type="text" class="form-control" id="totalchildren" name="totalchildren"  value="${sessionScope.user.totalChildren}" >
                    <c:if test="${not empty errorsReg['numChildrenError']}">
                        <div class="alert alert-danger">
                            ${errorsReg['numChildrenError']}
                        </div>
                    </c:if>
                    </div>
                    <div class="form-group">
                    <label for="sname">Spouse Name:</label>
                    <input type="text" class="form-control" id="sname" name="spousename" value="${sessionScope.user.spouseName}" >
                    <c:if test="${not empty errorsReg['spouseNameError']}">
                        <div class="alert alert-danger">
                            ${errorsReg['spouseNameError']}
                        </div>
                    </c:if>
                    </div>
                </c:when>
            </c:choose>
            
            <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" class="form-control" id="address" name="address"  value="${sessionScope.user.addr}" >
            <c:if test="${not empty ProfileData.errors['address']}">
                <div class="alert alert-danger">
                   ${errorsReg['addrError']}
                </div>
            </c:if>
            </div>
            
            <div class="input-grp" style="text-align:center">
                <input type="submit" class="btn btn-default" value="Save"/>
            </div>
	</form>
</div>
</body>
</html>