<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h2>Sorry, there was an error processing your request:</h2>
    <h3>${errorMessage}</h3>
    <a href="${pageContext.request.contextPath}/demo/add" class="btn btn-primary">Back</a>
</body>
</html>
