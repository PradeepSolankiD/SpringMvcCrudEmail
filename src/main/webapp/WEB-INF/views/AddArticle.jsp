<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./base.jsp"%>
</head>
<body>

	<div class="container mt-3">

		<h1>Add/Update UI</h1>
		<h3>Add Article</h3>
		<form action="${pageContext.request.contextPath}/demo/add"
			method="post" enctype="multipart/form-data">

			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="name">Title</label> <input type="text"
							class="form-control" id="title" name="title"
							placeholder="Enter Title">
					</div>
				</div>
				<div class="row">

					<div class="col">
						<div class="form-group">
							<label for="date">Article Date</label> <input type="date"
								class="form-control" id="articleDate" name="articleDate">
						</div>
					</div>
				</div>
				<div class="col">
					<div class="form-group">
						<label for="freeViewExpiry">Free View Expiry</label> <input
							type="date" class="form-control" id="freeViewExpiry"
							name="freeViewExpiry">
					</div>
				</div>
				<div class="col">
					<div class="form-group">
						<label for="authors">Authors</label> <select class="form-control"
							id="authors" name="authorNames" multiple>
							<option value="Author 1">Author One</option>
							<option value="Author 2">Author Two</option>
							<option value="Author 3">Author Three</option>
						</select>
					</div>
				</div>
				<div class="col">
					<div class="form-group">
						<label for="accessCategory">AccessCategory</label> <select
							class="form-control" id="accessCategory"
							name="accessCategoryNames" multiple>
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
							rows="5" placeholder="Enter Description"> </textarea>
					</div>
				</div>

			</div>
			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="publicUrl">Public URL</label> <input type="text"
							class="form-control" id="publicUrl" name="publicUrl"
							placeholder="Enter Public URL">
					</div>
				</div>
				<div class="row">
					<div class="col">
						<div class="form-group">
							<label for="imageFile">Upload Image</label> <input type="file"
								class="form-control" name="imageFile" id="imageFile">
						</div>
					</div>
				</div>
			</div>


			<a href="${pageContext.request.contextPath}/" class="btn btn-warning">
				Back </a>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>

	</div>

</body>
</html>