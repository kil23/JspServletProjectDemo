<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="/ProjectOne/css/bootstrap.css">
<script src="/ProjectOne/js/bootstrap.js"></script>
<title>Job List</title>
</head>
<body>
	<div class="container mt-10 w-100">
		<div id="headcontainer">
			<div id="header">
				<h2 class="text-center mb-10">Jobs List</h2>
			</div>
		</div>
		
		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<th> Title |</th> 
						<th> Start-Date |</th> 
						<th> End-Date |</th> 
						<th> Status |</th> 
						<th> Pay-per-Hour |</th> 
						<th> Operations </th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="jobs" items="${JobList}">
						
						<c:url var="edit" value="/jsp/seeker/edit-job">
							<c:param name="JobId" value="${jobs.id}" />
						</c:url>
						
						<c:url var="delete" value="/jsp/seeker/delete-job">
							<c:param name="JobId" value="${jobs.id}" />
						</c:url>
						
						<c:url var="listApplication" value="/jsp/seeker/list-jobapplication">
							<c:param name="JobId" value="${jobs.id}" />
						</c:url>
						
						<tr>
							<td> <c:out value="${jobs.title}"/> </td>
							<td> <c:out value="${jobs.startdate}"/></td>
							<td> <c:out value="${jobs.enddate}"/></td>
							<td> <c:out value="${jobs.status}"/> </td>
							<td> <c:out value="${jobs.payperhr}"/> </td>
							<td>
								<c:choose>
									<c:when test="${jobs.status == 'Active'}">
										<a href="${edit}">Edit</a> | 
										<a href="${delete}" onclick ="if(!(confirm('Delete this job?'))) return false">Delete</a> | 
										<a href="${listApplication}">List Job Application</a>
									</c:when>
								</c:choose>
							</td>
							
						</tr>	
					</c:forEach>
				</tbody>
			</table>
		</div>
		 <div style="text-align:center">
            <a href="/ProjectOne/" class="btn btn-primary" role="button" >Home</a>
        </div>
	</div>
</body>
</html>