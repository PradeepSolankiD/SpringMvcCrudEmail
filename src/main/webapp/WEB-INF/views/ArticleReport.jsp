<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./base.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<style>
.email-form {
	display: flex; /* Make the form a flex container */
	flex-wrap: nowrap;
	/* Don't allow elements to wrap onto multiple lines */
	align-items: center; /* Align elements vertically */
}

.email-form-group {
	margin-right: 1rem; /* Add some margin between groups */
}

.email-form-group:last-child {
	margin-right: 0; /* Remove margin for the last group */
}

/* Additional styles from previous examples can be included here */
</style>
</head>
<body>
	<div class="container mt-3">
		<h1>Article Report</h1>
		<a href="${pageContext.request.contextPath}/demo/add"
			class="btn btn-primary"> Add Article </a>
		<div class="row">
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Title</th>
						<th scope="col">Description</th>
						<th scope="col">Authors</th>
						<th scope="col">AccessCategory</th>
						<!-- <th scope="col">Public URL</th> -->
						<th scope="col">Article Date</th>
						<th scope="col">Free View Expiry</th>
						<th scope="col">Edit</th>
						<th scope="col">Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="article" items="${articles}">
						<tr>
							<td class="table-plus">${article.id}</td>
							<td>${article.title}</td>
							<td>${article.description}</td>
							<td><c:forEach var="author" items="${article.authors}"
									varStatus="status">
                                    ${author.name}<c:if
										test="${not status.last}">, </c:if>
								</c:forEach></td>
							<td><c:forEach var="accessCategory"
									items="${article.accessCategory}" varStatus="status">
                                    ${accessCategory.name}
                                    <c:if test="${not status.last}">, </c:if>
								</c:forEach></td>
							<%-- <td>${article.publicUrl}</td> --%>
							<td>${article.articleDate}</td>
							<td>${article.freeViewExpiry}</td>
							<td><a
								href="${pageContext.request.contextPath}/demo/edit/${article.id}"
								class="btn btn-warning">Edit</a></td>
							<td><a
								href="${pageContext.request.contextPath}/demo/delete/${article.id}"
								class="btn btn-danger">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- Email form -->
			<h2>Send Email</h2>
			<form action="${pageContext.request.contextPath}/sendEmail"
				method="post" class="email-form"
				style="display: flex; flex-wrap: nowrap; align-items: center;">

				<div class="email-form-group">
					<label for="to">To:</label> <input type="email" id="to" name="to"
						required>
				</div>
				<div class="email-form-group">
					<label for="subject">Subject:</label> <input type="text"
						id="subject" name="subject" required>
				</div>
				<div class="email-form-group">
					<label for="body">Body:</label>
					<textarea id="body" name="body" rows="5" cols="30" required></textarea>
				</div>
				<div class="email-form-actions">
					<button type="submit">Send Email</button>
				</div>
				<c:if test="${not empty message}">
					<div class="email-form-message">${message}</div>
				</c:if>
			</form>

		</div>
	</div>
</body>
</html>
