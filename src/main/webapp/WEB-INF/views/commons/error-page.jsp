<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">


<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<link href="<c:url value="/resources/css/error-page.css" />"
	rel="stylesheet">
</head>
<body>
	<div id="app" style="min-height: 550px">
		<div>ERROR</div>
		<div class="txt">
			Please try again!<span class="blink">_</span>
		</div>
		<div class="justify-content-center">
			<a href="${pageContext.request.contextPath}/">
				<button class="btn btn-success">Back to Home</button>
			</a>
		</div>
	</div>

</body>
</html>