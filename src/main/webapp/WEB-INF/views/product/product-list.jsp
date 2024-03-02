<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pro list</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/product-list.css" />"
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

	<div class="container mt-5">
		<div>
			<h2 class="text-center text-title text-white" style="font-size: 2rem">SERVICE
				MANAGEMENT</h2>
		</div>

		<div class="d-flex justify-content-between row mt-4"
			style="display: flex; justify-content: space-between">

			<div class="col-md-1"></div>
			<div class="col-6">
				<div class="d-flex justify-content-between">
					<form:form
						action="${pageContext.request.contextPath}/product/search"
						method="get">
						<div class="w-100 d-flex px-0">
							<input type="search" class="input-search form-control"
								name="searchName" style="width: 250px"
								placeholder="Enter service name" id="productName">
							<button type="submit" class="btn btn-outline-secondary mx-4">Search</button>
						</div>
					</form:form>
				</div>
			</div>
			<div class="col-4 d-flex justify-content-end">
				<div class="">
					<a href="${pageContext.request.contextPath}/product/create">
						<button class="btn btn-outline-secondary">Add new</button>
					</a>
				</div>
			</div>
			<div class="col-md-1"></div>
		</div>
	</div>

	<!--  Validate cho ô tìm kiếm-->
	<div class="container">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<div class="text-danger">&nbsp;</div>
			</div>
		</div>
		<div class="col-md-1"></div>
	</div>
	
	<c:if test="${products.size() !=0}">

        <!-- Pagination-->
        <div class="d-flex justify-content-center">
            <%--Pagination--%>
            <table border="1" cellpadding="5">
                <tr class="d-flex justify-content-center">
                    <td><c:if test="${currentPage!=1}">
                            <a href="?page=${currentPage - 1}&searchName=${searchName}">Previous</a>
                        </c:if> <c:if test="${currentPage==1}">
                            <a class="text-secondary">Previous</a>
                        </c:if></td>
                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <td class="text-white">${i}</td>
                            </c:when>
                            <c:otherwise>
                                <td><a href="?page=${i}&searchName=${searchName}">${i}</a></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <td><c:if test="${currentPage!=noOfPages}">
                            <a href="?page=${currentPage + 1}&searchName=${searchName}">Next</a>
                        </c:if> <c:if test="${currentPage==noOfPages}">
                            <a class="text-secondary">Next</a>
                        </c:if></td>
                </tr>
            </table>
        </div>

    </c:if>
	

	<c:if test="${products.size() !=0}">
		<div class="container">
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-10">
					<div class="row" style="min-height: 862px">
						<c:forEach items="${products}" var="product" varStatus="status">
							<div class="col-md-6 col-lg-6 col-xl-4 col-xxl-3">

								<div class="w-90 bg-card mt-5">
									<img src="${product.image}" class="card-img-top" alt="...">
									<div class="card-body">
										<div>
											<h5 class="hiddenText text-uppercase" data-toggle="tooltip"
												style="color: white; text-align: center"
												title="${product.productName}">${product.productName}</h5>
										</div>
										<div>

											<span class="card-detail-badge" style="font-size: 16px">
												<fmt:formatNumber value="${product.price}" type="number" />
												<sup>$</sup>
											</span>

											<button type="button" class="btn btn-outline-secondary"
												data-bs-toggle="modal" data-bs-target="#exampleModal"
												style="float: right; padding: 0 0.375rem"
												onclick="showModalDelete('${product.productId}', '${product.productName}')">

												<i class="fa-sharp fa-solid fa-trash"></i>
											</button>
											<a
												href="${pageContext.request.contextPath}/product/${product.productId}">
												<button class="btn btn-outline-secondary"
													style="float: right; margin-right: 5px; padding: 0 0.375rem">
													<i style="font-size: 1rem" class="fa-solid fa-pencil"></i>
												</button>
											</a>

										</div>

									</div>
								</div>

							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="col-md-1"></div>
		</div>
	</c:if>


	<!-- Khi không có sản phẩm nào-->

	<c:if test="${products.size() ==0}">
		<div class="row justify-content-center">
			<div class="col-6">
				<img src="https://i.giphy.com/media/HTSsuRrErs54g1Tqr5/giphy.webp">
				<div class="text-center">
					<p class="text-white">No result!</p>
				</div>
			</div>
		</div>
	</c:if>

	<br>
	
	<!-- Modal delete-->
	<input value="${message}" id="message" hidden="true">
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<form action="${pageContext.request.contextPath}/product/delete"
				method="post">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Confirm</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body container-fluid">
						Do you want to delete <span id="delete_modal"></span> <input
							hidden="true" id="sendId" name="productId"><span>?</span>
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
</body>
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
</html>