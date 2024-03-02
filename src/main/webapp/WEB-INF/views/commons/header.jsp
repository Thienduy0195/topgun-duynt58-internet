<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">
a {
	text-decoration: none;
}

li {
	color: #a8a8a8;
	padding-left: 5px;
}

.hover-transfer:hover {
	background-color: green;
}
</style>
</head>
<body>
	<div class="container-fluid"
		style="position: sticky; top: 0; z-index: 99; background: black;">
		<!--    header-->
		<div class="row header">

			<nav class="navbar navbar-expand-lg navbar-light">
				<div class="container-fluid">
					<button class="navbar-toggler" type="button"
						data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03"
						aria-controls="navbarTogglerDemo03" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>

					<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0">

							<li class="nav-item dropdown"><a class="nav-link"
								href="${pageContext.request.contextPath}/"
								id="navbarDropdownCustomer" role="button" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false"
								style="color: #a8a8a8"> Home</a></li>

							<li class="nav-item dropdown">
								<div>
									<a class="nav-link"
										href="${pageContext.request.contextPath}/customer/list"
										id="navbarDropdownComputer" role="button"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false" style="color: #a8a8a8"> Customer |</a>
								</div>
							</li>


							<li class="nav-item dropdown">
								<div>
									<a class="nav-link"
										href="${pageContext.request.contextPath}/computer/list"
										id="navbarDropdownComputer" role="button"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false" style="color: #a8a8a8"> Computer |</a>
								</div>
							</li>
							<li class="nav-item dropdown">
								<div>
									<a class="nav-link"
										href="${pageContext.request.contextPath}/product/list"
										id="navbarDropdownProduct" role="button"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false" style="color: #a8a8a8">Service |</a>
								</div>
							</li>

							<li class="nav-item dropdown">
								<div>
									<a class="nav-link"
										href="${pageContext.request.contextPath}/record/list"
										id="navbarDropdownProduct" role="button"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false" style="color: #a8a8a8">Record |</a>
								</div>
							</li>

							<li class="nav-item dropdown">
								<div>
									<a class="nav-link"
										href="${pageContext.request.contextPath}/payment/list"
										id="navbarDropdownProduct" role="button"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false" style="color: #a8a8a8">Payment </a>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
	</div>

</body>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function() {
		$('.dropdown-toggle').click(function() {
			$('.dropdown-menu').toggleClass('show');
		});
	});
</script>
</html>