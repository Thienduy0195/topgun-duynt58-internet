<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View history using computers and services</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">


<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<link href="<c:url value="/resources/css/computer-list.css" />"
	rel="stylesheet">

<style type="text/css">
a {
	text-decoration: none
}
</style>


</head>
<body>
	<%@ include file="../commons/header.jsp"%>
	<div class="container mt-5" id="table-computer">
		<div>
			<div class="row">
				<h2 class="text-center text-white">USING COMPUTER MANAGEMENT</h2>
				<div class=" text-white mt-3">
					<!--        row search 1-->
					<div class="row" style="height: 80px">

						<div class="row pr-0">
							<div class="col-10">
								<form:form class="row"
									action="${pageContext.request.contextPath}/record/search"
									method="get">
									<div class="col-4">
										<input type="hidden" name="page" value="${1}" /> <input
											id="customerId" type="text" name="customerId"
											value="${customerId}" class="input-search form-control"
											placeholder="Enter customer code to search">
									</div>

									<div class="col-4">

										<input id="computerId" type="text" name="computerId"
											class="input-search form-control" value="${computerId}"
											placeholder="Enter computer code to search">
									</div>
									<div class="col-4">
										<button type="submit"
											class="btn btn-outline-secondary w-20 classDisabled">
											Search</button>
										<button type="reset" style="margin-left: 15px"
											class="btn btn-outline-secondary w-20 classDisabled ">
											<i class="fa-solid fa-arrows-rotate"></i>
										</button>
									</div>
								</form:form>
							</div>
							<div class="col-2 d-flex justify-content-end">
								<a href="${pageContext.request.contextPath}/record/create">
									<button class="btn btn-outline-secondary">Add new</button>
								</a>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
		<!--  table-->
		<div style="height: 370px">
			<div style="min-height: 332px">
				<table class="table table-dark w-100">
					<thead>
						<tr>
							<th class="text-center">#</th>
							<th class="th-w15" scope="col">CUSTOMER ID</th>
							<th class="th-w15" scope="col">COMPUTER ID</th>
							<th class="th-w15" scope="col">STARTING DATE</th>
							<th class="th-w10" scope="col">STARTING HOUR</th>
							<th class="th-w20" scope="col">USING TIME</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${records}" var="item" varStatus="status">
							<tr>
								<td class="text-center">${status.count}</td>
								<td>${item.recordId.customerId}</td>
								<td>${item.recordId.computerId}</td>
								<td>${item.recordId.startingDate}</td>
								<td>${item.recordId.startingHour}</td>
								<td>${item.usingTime}</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>

				<!--No record in database-->
				<c:if test="${records.size() ==0}">
					<div class="container">
						<div class="row justify-content-center">
							<div class="col-6">
								<img
									src="https://i.giphy.com/media/HTSsuRrErs54g1Tqr5/giphy.webp">
								<div class="text-center">
									<p class="text-white">No result!</p>
								</div>
							</div>
						</div>
					</div>
				</c:if>
			</div>

			<c:if test="${records.size() !=0}">
				<%--Pagination--%>
				<div class="d-flex justify-content-center">

					<table border="1" cellpadding="5">
						<tr class="d-flex justify-content-center">
							<td><c:if test="${currentPage!=1}">
									<a
										href="?page=${currentPage - 1}&customerId=${customerId}&computerId=${computerId}">Previous</a>
								</c:if> <c:if test="${currentPage==1}">
									<a class="text-secondary">Previous</a>
								</c:if></td>
							<c:forEach begin="1" end="${noOfPages}" var="i">
								<c:choose>
									<c:when test="${currentPage eq i}">
										<td class="text-white">${i}</td>
									</c:when>
									<c:otherwise>
										<td><a href="?page=${i}">${i}</a></td>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<td><c:if test="${currentPage!=noOfPages}">
									<a
										href="?page=${currentPage + 1}&customerId=${customerId}&computerId=${computerId}">Next</a>
								</c:if> <c:if test="${currentPage==noOfPages}">
									<a class="text-secondary">Next</a>
								</c:if></td>
						</tr>
					</table>

				</div>

			</c:if>
		</div>

		<br>
	</div>

	<%@ include file="../commons/footer.jsp"%>
</html>