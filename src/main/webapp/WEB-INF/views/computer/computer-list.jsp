<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Computer list</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/computer-list.css" />"
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
		<div class="row">
			<h2 class="text-center text-white">COMPUTER MANAGEMENT</h2>
		</div>
		<div class="row">
			<div class=" text-white mt-3">
				<div class="row" style="height: 80px">
					<div class="row">
						<div class="col-11">
							<form:form class="row"
								action="${pageContext.request.contextPath}/computer/search"
								method="get">

								<div class="col-3">
									<input type="hidden" name="page" value="${1}" /> <input
										id="searchId" type="text" class="input-search form-control"
										name="searchId" value="${searchId}"
										placeholder="Enter the computer id">
								</div>

								<div class="col-3">
									<input id="nameCustomer" type="text" name="searchLocation"
										value="${searchLocation}" class="input-search form-control"
										placeholder="Enter the location">
								</div>

								<div class="col-3">
									<select style="min-width: 100px; margin: 0; height: 38px"
										name="status" class="input-search form-select">
										<option value="">--- Choose status ---</option>
										<option value="Offline"
											${status == 'Offline' ? 'selected' : ''}>Offline</option>
										<option value="Online" ${status == 'Online' ? 'selected' : ''}>Online</option>
										<option value="Maintain" ${computer.status == 'Maintain' ? 'selected' : ''}>Maintain</option>
									</select>
								</div>
								<div class="col-3">
									<button type="submit"
										class="btn btn-outline-secondary classDisabled">
										Search</button>

									<button type="reset" style="margin-right: 0px"
										class="btn btn-outline-secondary classDisabled ">
										<i class="fa-solid fa-arrows-rotate"></i>
									</button>

								</div>

							</form:form>
						</div>
						<div class="col-1 p-0">
							<div>
								<a href="${pageContext.request.contextPath}/computer/create">
									<button class="btn btn-outline-secondary"
										style="margin-right: 0px">Add new</button>
								</a>
							</div>

						</div>

					</div>

				</div>
			</div>
		</div>
		<div class="row">
			<div class="container">
				<div style="height: 370px">
					<div style="min-height: 332px">
						<table class="table table-dark w-100 table-responsive">
							<thead>
								<tr>
									<th scope="col" class="text-center">#</th>
									<th scope="col">ID</th>
									<th scope="col">LOCATION</th>
									<th scope="col">STATUS</th>
									<th scope="col" class="text-center">ACTION</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${computers}" var="item" varStatus="status">
									<tr>
										<td class="text-center">${status.count}</td>
										<td>${item.computerId}</td>
										<td>${item.location}</td>
										<td>${item.status}</td>
										<td class="text-center"><a
											href="${pageContext.request.contextPath}/computer/${item.computerId}">
												<button class="btn btn-outline-secondary color-icon">
													<i class="fa-solid fa-pen-to-square"></i>
												</button>
										</a>

											<button type="button" class="btn btn-outline-secondary"
												data-bs-toggle="modal" data-bs-target="#exampleModal"
												onclick="showModalDelete('${item.computerId}')">
												<i class="fa-solid fa-trash"></i>
											</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

						<!--No record in database-->
						<c:if test="${computers.size() ==0}">
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

					<c:if test="${computers.size() !=0}">
						<div class="d-flex justify-content-center">
							<%--Pagination--%>
							<table border="1" cellpadding="5">
								<tr class="d-flex justify-content-center">
									<td><c:if test="${currentPage!=1}">
											<a
												href="?page=${currentPage - 1}&searchId=${searchId}&searchLocation=${searchLocation}&status=${status}">Previous</a>
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
													href="?page=${i}&searchId=${searchId}&searchLocation=${searchLocation}&status=${status}">${i}</a></td>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<td><c:if test="${currentPage!=noOfPages}">
											<a
												href="?page=${currentPage + 1}&searchId=${searchId}&searchLocation=${searchLocation}&status=${status}">Next</a>
										</c:if> <c:if test="${currentPage==noOfPages}">
											<a class="text-secondary">Next</a>
										</c:if></td>
								</tr>
							</table>
						</div>

					</c:if>
				</div>



			</div>
		</div>

	</div>

	<!--Start Delete Modal-->
	<input value="${message}" id="message" hidden="true">
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<form action="${pageContext.request.contextPath}/computer/delete"
				method="post">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Confirm</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body container-fluid">
						Do you want to delete <span id="delete_modal"></span> <input
							hidden="true" id="sendId" name="computerId"><span>?</span>
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
	<!--End Delete Modal-->
	
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
		function showModalDelete(computerId) {
			document.getElementById("delete_modal").innerText = computerId;
			document.getElementById("sendId").value = computerId;
		}
	</script>

	<!-- Javascript files-->
	<script src="/resources/js/bootstrap.min.js"></script>
	<script src="/resources/js/jquery3.6.js"></script>
</body>
</html>