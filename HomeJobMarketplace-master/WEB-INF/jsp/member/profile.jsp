<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.care.model.Member" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Profile</title>
    <link type="text/css" rel="stylesheet" href="/HomeJobMarketplace/css/bootstrap.css">
    <script src="/HomeJobMarketplace/js/bootstrap.js"></script>
</head>
<body>
    <div class="container mt-5 w-25 mb-5 center-block">
        <div id="wrapper">
            <div id="header">
                <h3 class="text-center mb-3">Profile</h3>
            </div>
        </div>

        <form action="/HomeJobMarketplace/member/edit-profile" method="post">
            <c:if test="${not empty requestScope.error}">
                <div class="alert alert-danger">
                  ${requestScope.error}
                </div>
            </c:if>
            <div class="form-group">
            <label for="fname">First Name:</label>
            <input type="text" class="form-control" name="firstname" id="fname" value="${requestScope.ProfileData.getFirstName()}" readonly="readonly">
            </div>
            <div class="form-group">
            <label for="lname">Last Name:</label>
            <input type="text" class="form-control" id="lname" name="lastname" value="${requestScope.ProfileData.getLastName()}" readonly="readonly">
            </div>
            <div class="form-group">
            <label for="phone">Phone No:</label>
            <input type="number" class="form-control" id="phone" name="phoneno" value="${requestScope.ProfileData.getPhoneNo()}" readonly="readonly">
            </div>
            <div class="form-group">
            <label for="email">Email Id:</label>
            <input type="email" class="form-control" id="email" name="emailid"  value="${requestScope.ProfileData.getEmailId()}" readonly="readonly">
            </div>
            <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" class="form-control" id="address" name="address" value="${requestScope.ProfileData.getAddress()}" readonly="readonly">
            </div>
            <div class="form-group">
            <label for="pincode">Pincode:</label>
            <input type="number" class="form-control" id="pincode" name="pincode" value="${requestScope.ProfileData.getPincode()}" readonly="readonly">
            </div>

            <c:choose>
                <c:when test="${sessionScope.MemberType == 'SITTER'}">
                    <div class="form-group">
                    <label for="exp">Experience:</label>
                    <input type="number" class="form-control" id="exp" name="experience" value="${requestScope.ProfileData.getExperience()}" readonly="readonly">
                    </div>
                </c:when>
                <c:when test="${sessionScope.MemberType == 'SEEKER'}">
                    <div class="form-group">
                    <label for="totalchildren">Total Children:</label>
                    <input type="number" class="form-control" id="totalchildren" name="totalchildren" value="${requestScope.ProfileData.getTotalChildren()}" readonly="readonly">
                    </div>
                    <div class="form-group">
                    <label for="sname">Spouse Name:</label>
                    <input type="text" class="form-control" id="sname" name="spousename" value="${requestScope.ProfileData.getSpouseName()}" readonly="readonly">
                    </div>
                </c:when>
                <c:otherwise>
                    Please put the blame on Abhay!1
                </c:otherwise>
            </c:choose>
            <div class="input-grp" style="text-align:center">
                <input type="submit" class="btn btn-default" value="Edit Profile"/>
                <a href="/HomeJobMarketplace/member/delete-profile" class="btn btn-danger" role="button" onclick="if(!(confirm('Sure you want to delete this profile?'))) return false">Delete Profile</a>
            </div>
        </form>
        </br></br>
        <div style="text-align:center">
            <a href="/HomeJobMarketplace/" class="btn btn-primary" role="button" >Home</a>
        </div>
    </div>
</body>
</html>