<%@page import="org.springframework.ui.Model"%>
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
				<h2 class="text-center text-white">CUSTOMER MANAGEMENT</h2>
				<div class=" text-white mt-3">
					<!--        row search 1-->
					<div class="row" style="height: 80px">

						<div class="row pr-0">
							<div class="col-10">
								<form:form class="row"
									action="${pageContext.request.contextPath}/customer/search"
									method="get">
									<div class="col-4">
										<input type="hidden" name="page" value="${1}" /> <input
											id="nameCustomer" type="text" name="searchName"
											value="${searchName}" class="input-search form-control"
											placeholder="Enter name to search">
									</div>

									<div class="col-4">

										<input id="address" type="text" name="searchAddress"
											class="input-search form-control" value="${searchAddress }"
											placeholder="Enter address to search">
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
								<a href="${pageContext.request.contextPath}/customer/create">
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
							<th class="th-w15" scope="col">ID</th>
							<th class="th-w15" scope="col">FULL NAME</th>
							<th class="th-w15" scope="col">ADDRESS</th>
							<th class="th-w10" scope="col">PHONE</th>
							<th class="th-w20" scope="col">EMAIL</th>
							<th scope="col">ACTION</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${customers}" var="item" varStatus="status">
							<tr>
								<td class="text-center">${status.count}</td>
								<td>${item.customerId}</td>
								<td>${item.name}</td>
								<td>${item.address}</td>
								<td>${item.phoneNumber}</td>
								<td>${item.email}</td>
								<td><a
									href="${pageContext.request.contextPath}/customer/${item.customerId}">
										<button class="btn btn-outline-secondary color-icon">
											<i class="fa-solid fa-pen-to-square"></i>
										</button>
								</a>

									<button type="button" class="btn btn-outline-secondary"
										data-bs-toggle="modal" data-bs-target="#exampleModal"
										onclick="showModalDelete('${item.customerId}', '${item.name}')">
										<i class="fa-solid fa-trash"></i>
									</button> <a
									href="${pageContext.request.contextPath}/customer/history/${item.customerId}">
										<button type="button" class="btn btn-outline-secondary ">
											<i class="fa-solid fa-gears"></i>
										</button>
								</a></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>

				<!--No record in database-->
				<c:if test="${customers.size() ==0}">
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

			<c:if test="${customers.size() !=0}">
				<%--Pagination--%>
				<div class="d-flex justify-content-center">

					<table border="1" cellpadding="5">
						<tr class="d-flex justify-content-center">
							<td><c:if test="${currentPage!=1}">
									<a
										href="?page=${currentPage - 1}&searchName=${searchName}&searchAddress=${searchAddress}">Previous</a>
								</c:if> <c:if test="${currentPage==1}">
									<a class="text-secondary">Previous</a>
								</c:if></td>
							<c:forEach begin="1" end="${noOfPages}" var="i">
								<c:choose>
									<c:when test="${currentPage eq i}">
										<td class="text-white">${i}</td>
									</c:when>
									<c:otherwise>
										<td><a
											href="?page=${i}&searchName=${searchName}&searchAddress=${searchAddress}">${i}</a></td>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<td><c:if test="${currentPage!=noOfPages}">
									<a
										href="?page=${currentPage + 1}&searchName=${searchName}&searchAddress=${searchAddress}">Next</a>
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

	<input value="${message}" id="message" hidden="true">
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<form action="${pageContext.request.contextPath}/customer/delete"
				method="post">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Confirm</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body container-fluid">
						Do you want to delete <span id="delete_modal"></span> <input
							hidden="true" id="sendId" name="customerId"><span>?</span>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Cancel</button>
						<button type="submit" class="btn btn-danger"
							data-bs-dismiss="modal">Delete</button>

					</div>
				</div>
			</form>
		</div>
	</div>

	<!--begin thong bao sau khi delete -->
	<div class="modal fade" id="modalAlert" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div>
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Alert</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body container-fluid">
						<p id="modalAlertMes"></p>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary"
							data-bs-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--end thong bao sau khi delete -->

	<%@ include file="../commons/footer.jsp"%>



	<script>
		window.onload = function() {
			if ($('#message').val() != null && $('#message').val() != "") {
				$("#modalAlertMes").html($('#message').val());
				$(document).ready(function() {
					$('#modalAlert').modal("show")
				})
			}
		}
	</script>

	<script>
		function showModalDelete(a, b) {
			document.getElementById("delete_modal").innerText = b;
			document.getElementById("sendId").value = a;
		}
	</script>


	<!-- Javascript files-->
	<script src="/resources/js/bootstrap.min.js"></script>
	<script src="/resources/js/jquery3.6.js"></script>
</html>