<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.demo.com/functions" prefix="fnc"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./base.jsp"%>
</head>
<body>

	<div class="container mt-3">
		<h1>Edit Article</h1>
		<form
			action="${pageContext.request.contextPath}/demo/edit/${article.id}"
			method="post" enctype="multipart/form-data">
			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="id">Id</label> <input type="text"
							value="${article.id}" class="form-control" id="id" name="id"
							readonly="readonly">
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="title">Title</label> <input type="text"
							value="${article.title}" class="form-control" id="title"
							name="title" placeholder="Enter Title">
					</div>
				</div>
				<div class="col">
					<div class="form-group">
						<label for="articleDate">Date</label> <input type="date"
							value="${article.articleDate}" class="form-control"
							id="articleDate" name="articleDate">
					</div>
				</div>
				<div class="col">
					<div class="form-group">
						<label for="authors">Authors</label> <select name="authorNames"
							multiple="multiple" class="form-control" id="articleAuthors">
							<c:forEach items="${article.authors}" var="author">
								<option value="${author.name}" selected="selected">${author.name}</option>
							</c:forEach>
							<option value="author1">Author 1</option>
							<option value="author2">Author 2</option>
							<option value="author3">Author 3</option>
						</select>
					</div>
				</div>
				<div class="col">
					<div class="form-group">
						<label for="accessCategories">Access Categories</label> <select
							name="accessCategoryNames" multiple="multiple"
							class="form-control" id="articleAccessCategories">
							<c:forEach items="${article.accessCategory}" var="accessCategory">
								<option value="${accessCategory.name}" selected="selected">${accessCategory.name}</option>
							</c:forEach>
							<option value="Free">Free</option>
							<option value="Month">Month</option>
							<option value="Year">Year</option>
						</select>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="description">Description</label>
						<textarea class="form-control" id="description" name="description"
							rows="5" placeholder="Enter Description">${article.description}</textarea>
					</div>
				</div>
				<div class="col">
					<div class="form-group">
						<label for="publicUrl">Public URL</label> <input type="text"
							class="form-control" id="publicUrl" value="${article.publicUrl}"
							name="publicUrl" placeholder="Enter Public URL">
					</div>
				</div>
				<div class="col">
					<div class="form-group">
						<label for="freeViewExpiry">Free View Expiry</label> <input
							type="date" class="form-control" id="freeViewExpiry"
							value="${article.freeViewExpiry}" name="freeViewExpiry">
					</div>
				</div>
			</div>	
			<div class="row">	
				<div class="col">
					<div class="form-group">
						<label for="currentImage">Current Image:</label>
						<c:if test="${not empty article.imagePath}">
							<!-- Debugging output -->
							<p>Image Path: ${article.imagePath}</p>
							<img
								src="${pageContext.request.contextPath}/${article.imagePath}"
								alt="Current Image">
						</c:if>
					</div>
					<div class="form-group">
						<div>
							<label for="imageFile">Upload New Image:</label> <input
								type="file" name="imageFile" id="imageFile">
						</div>
					</div>
				</div>
			</div>


			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>

</body>
</html>
