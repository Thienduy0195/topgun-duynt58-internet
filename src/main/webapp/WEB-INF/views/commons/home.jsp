<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HOME</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">


<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<style type="text/css">
body {
	background: #121212;
}
</style>

</head>
<body>
	<%@ include file="../commons/header.jsp"%>
	<div class="home-body h-100 mb-3">
		<div id="carouselExampleIndicators" class="carousel slide"
			data-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img
						src="https://xbox-store-checker.com/assets/upload/game/2021/06/optimize/9nmghp3dp44r-background.jpg"
						class="d-block w-100">
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../commons/footer.jsp"%>
</body>
</html>