<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.care.model.Member" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Member Registeration</title>
    <link type="text/css" rel="stylesheet" href="/HomeJobMarketplace/css/bootstrap.css">
    <script src="/HomeJobMarketplace/js/bootstrap.js"></script>
</head>
<body>
    <div class="container mt-5 w-25 mb-5 center-block">
        <div id="wrapper">
            <div id="header">
                <h3 class="text-center mb-3">Register</h3>
            </div>
        </div>
        <form action="/HomeJobMarketplace/visitor/register" method="post">
            <div class="form-group">
                <label for="fname">First Name:</label>
                <input type="text" class="form-control" name="firstname" id="fname" value="${formData.firstName}" />
                <c:if test="${not empty errors['firstName']}">
                    <div class="alert alert-danger">
                        ${errors["firstName"]}
                    </div>
                </c:if>
            </div>
            <div class="form-group">
            <label for="lname">Last Name:</label>
            <input type="text" class="form-control" id="lname" name="lastname" value=${formData.lastName}>
            <c:if test="${not empty errors['lastName']}">
                <div class="alert alert-danger">
                    ${errors["lastName"]}
                </div>
            </c:if>
            </div>
            <div class="form-group">
            <label for="phone">Phone No:</label>
            <input type="number" class="form-control" id="phone" name="phoneno" value=${formData.phoneNo}>
            <c:if test="${not empty errors['phoneNo']}">
                <div class="alert alert-danger">
                    ${errors["phoneNo"]}
                </div>
            </c:if>
            </div>
            <div class="form-group">
            <label for="email">Email Id:</label>
            <input type="email" class="form-control" id="email" name="emailid" value=${formData.emailId}>
            <c:if test="${not empty errors['emailId']}">
                <div class="alert alert-danger">
                    ${errors["emailId"]}
                </div>
            </c:if>
            </div>
            <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" id="password" name="password">
            <c:if test="${not empty errors['password']}">
                <div class="alert alert-danger">
                    ${errors["password"]}
                </div>
            </c:if>
            </div>
            <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" class="form-control" id="address" name="address" value=${formData.address}>
            <c:if test="${not empty errors['address']}">
                <div class="alert alert-danger">
                    ${errors["address"]}
                </div>
            </c:if>
            </div>
            <div class="form-group">
            <label for="pincode">Pincode:</label>
            <input type="number" class="form-control" id="pincode" name="pincode" value=${formData.pincode}>
            <c:if test="${not empty errors['pincode']}">
                <div class="alert alert-danger">
                    ${errors["pincode"]}
                </div>
            </c:if>
            </div>

            <c:choose>
                <c:when test="${sessionScope.MemberType == 'SITTER'}">
                    <div class="form-group">
                    <label for="exp">Experience:</label>
                    <input type="number" class="form-control" id="exp" name="experience" value=${formData.experience}>
                    <c:if test="${not empty errors['experience']}">
                        <div class="alert alert-danger">
                            ${errors["experience"]}
                        </div>
                    </c:if>
                    </div>
                </c:when>
                <c:when test="${sessionScope.MemberType == 'SEEKER'}">
                    <div class="form-group">
                    <label for="totalchildren">Total Children:</label>
                    <input type="number" class="form-control" id="totalchildren" name="totalchildren" value=${formData.totalChildren}>
                    <c:if test="${not empty errors['totalChildren']}">
                        <div class="alert alert-danger">
                            ${errors["totalChildren"]}
                        </div>
                    </c:if>
                    </div>
                    <div class="form-group">
                    <label for="sname">Spouse Name:</label>
                    <input type="text" class="form-control" id="sname" name="spousename" value=${formData.spouseName}>
                    <c:if test="${not empty errors['spouseName']}">
                        <div class="alert alert-danger">
                            ${errors["spouseName"]}
                        </div>
                    </c:if>
                    </div>
                </c:when>
                <c:otherwise>
                    Please put the blame on Abhay!
                </c:otherwise>
            </c:choose>
            <div style="text-align:center">
                <input type="submit" class="btn btn-default" value="Register"/>
            </div>
        </form>
    </div>
</body>
</html>