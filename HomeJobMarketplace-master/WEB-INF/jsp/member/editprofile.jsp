<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.care.model.Member" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Edit Profile</title>
    <link type="text/css" rel="stylesheet" href="/HomeJobMarketplace/css/bootstrap.css">
    <script src="/HomeJobMarketplace/js/bootstrap.js"></script>
</head>
<body>
    <div class="container mt-5 w-25 mb-5 center-block">
        <div id="wrapper">
            <div id="header">
                <h3 class="text-center mb-3">Edit Profile</h3>
            </div>
        </div>

        <form action="/HomeJobMarketplace/member/save-profile" method="post">
            <div class="form-group">
            <label for="fname">First Name:</label>
            <input type="text" class="form-control" name="firstname" id="fname" value="${requestScope.ProfileData.firstName}">
            <c:if test="${not empty ProfileData.errors['firstName']}">
                <div class="alert alert-danger">
                    ${ProfileData.errors["firstName"]}
                </div>
            </c:if>
            </div>
            <div class="form-group">
            <label for="lname">Last Name:</label>
            <input type="text" class="form-control" id="lname" name="lastname" value="${requestScope.ProfileData.lastName}" >
            <c:if test="${not empty ProfileData.errors['lastName']}">
                <div class="alert alert-danger">
                    ${ProfileData.errors["lastName"]}
                </div>
            </c:if>
            </div>
            <div class="form-group">
            <label for="phone">Phone No:</label>
            <input type="number" class="form-control" id="phone" name="phoneno" value="${requestScope.ProfileData.phoneNo}" >
            <c:if test="${not empty ProfileData.errors['phoneNo']}">
                <div class="alert alert-danger">
                    ${ProfileData.errors["phoneNo"]}
                </div>
            </c:if>
            </div>
            <div class="form-group">
            <label for="email">Email Id:</label>
            <input type="email" class="form-control" id="email" name="emailid"  value="${requestScope.ProfileData.emailId}"  readonly="readonly">
            <c:if test="${not empty ProfileData.errors['emailId']}">
                <div class="alert alert-danger">
                    ${ProfileData.errors["emailId"]}
                </div>
            </c:if>
            </div>
            <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" class="form-control" id="address" name="address" value="${requestScope.ProfileData.address}" >
            <c:if test="${not empty ProfileData.errors['address']}">
                <div class="alert alert-danger">
                    ${ProfileData.errors["address"]}
                </div>
            </c:if>
            </div>
            <div class="form-group">
            <label for="pincode">Pincode:</label>
            <input type="number" class="form-control" id="pincode" name="pincode" value="${requestScope.ProfileData.pincode}" >
            <c:if test="${not empty ProfileData.errors['pincode']}">
                <div class="alert alert-danger">
                    ${ProfileData.errors["pincode"]}
                </div>
            </c:if>
            </div>

            <c:choose>
                <c:when test="${sessionScope.MemberType == 'SITTER'}">
                    <div class="form-group">
                    <label for="exp">Experience:</label>
                    <input type="number" class="form-control" id="exp" name="experience" value="${requestScope.ProfileData.experience}" >
                    <c:if test="${not empty ProfileData.errors['experience']}">
                        <div class="alert alert-danger">
                            ${ProfileData.errors["experience"]}
                        </div>
                    </c:if>
                    </div>
                </c:when>
                <c:when test="${sessionScope.MemberType == 'SEEKER'}">
                    <div class="form-group">
                    <label for="totalchildren">Total Children:</label>
                    <input type="number" class="form-control" id="totalchildren" name="totalchildren" value="${requestScope.ProfileData.totalChildren}" >
                    <c:if test="${not empty ProfileData.errors['totalChildren']}">
                        <div class="alert alert-danger">
                            ${ProfileData.errors["totalChildren"]}
                        </div>
                    </c:if>
                    </div>
                    <div class="form-group">
                    <label for="sname">Spouse Name:</label>
                    <input type="text" class="form-control" id="sname" name="spousename" value="${requestScope.ProfileData.spouseName}" >
                    <c:if test="${not empty ProfileData.errors['spouseName']}">
                        <div class="alert alert-danger">
                            ${ProfileData.errors["spouseName"]}
                        </div>
                    </c:if>
                    </div>
                </c:when>
                <c:otherwise>
                    Please put the blame on Abhay!
                </c:otherwise>
            </c:choose>
            <div class="input-grp" style="text-align:center">
                <input type="submit" class="btn btn-default" value="Save"/>
            </div>
        </form>
        </br></br>
        <div style="text-align:center">
            <a href="/HomeJobMarketplace/" class="btn btn-primary" role="button" >Home</a>
        </div>
    </div>
</body>
</html>