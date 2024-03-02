<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.util.Locale"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer list</title>

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
	<div class="container mt-4" id="table-computer">
		<div>
			<div class="row mb-3">
				<h3 class="text-center text-white">USING COMPUTER HISTORY</h3>

			</div>
		</div>
		<!--  table-->
		<div class="mb-3">
			<div>
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
			</div>

			<!--No record in database-->
			<c:if test="${records.size() ==0}">
				<div class="container">
					<div class="row">
						<div class="col-3"></div>
						<div class="col-6 mx-5">
							<img
								src="https://i.giphy.com/media/HTSsuRrErs54g1Tqr5/giphy.webp">
						</div>
						<div class="col-3"></div>
					</div>
				</div>
			</c:if>

		</div>
		<div>
			<div class="row mb-3">
				<h3 class="text-center text-white">USING SERVICE HISTORY</h3>

			</div>
		</div>
		<div class="mb-3">
			<div>
				<table class="table table-dark w-100">
					<thead>
						<tr>
							<th class="text-center">#</th>
							<th class="th-w15" scope="col">CUSTOMER ID</th>
							<th class="th-w15" scope="col">SERVICE ID</th>
							<th class="th-w15" scope="col">SERVICE NAME</th>
							<th class="th-w15" scope="col">USING DATE</th>
							<th class="th-w10" scope="col">USING TIME</th>
							<th class="th-w20" scope="col">AMOUNT</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${payments}" var="item" varStatus="status">
							<tr>
								<td class="text-center">${status.count}</td>
								<td>${item.paymentId.customerId}</td>
								<td>${item.paymentId.productId}</td>
								<td>${item.product.productName}</td>
								<td>${item.paymentId.usingDate}</td>
								<td>${item.paymentId.usingHour}</td>
								<td>${item.amount}</td>
							</tr>
						</c:forEach>
						<c:if test="${payments.size() !=0}">
							<tr>
								<td colspan="6" class="text-bold">TOTAL ($)</td>
								<td class="text-bold">${totalDouble}</td>
							</tr>
						</c:if>

					</tbody>
				</table>


			</div>

			<!--No record in database-->
			<c:if test="${payments.size() ==0}">
				<div class="container">
					<div class="row">
						<div class="col-3"></div>
						<div class="col-6 mx-5">
							<img
								src="https://i.giphy.com/media/HTSsuRrErs54g1Tqr5/giphy.webp">
						</div>
						<div class="col-3"></div>
					</div>
				</div>
			</c:if>
		</div>
	</div>

	<div class="modal fade" id="deleteModal" tabindex="-1"
		aria-labelledby="deleteModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="deleteModalLabel">Confirm Delete</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p>Are you sure you want to delete this item?</p>
					<input type="hidden" id="deleteItemId" name="itemId">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-danger" onclick="deleteItem()">Delete</button>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="deleteModal1" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content object">
				<div class="modal-header">
					<h5 class="modal-title text-white" id="exampleModalLabel">CONFIRM
						TO DELETE</h5>
				</div>
				<div class="modal-body text-white">
					<h6>Do you want to delete this customer?</h6>
					<table style="width: 100%">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">Name</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-body text-white">
					<h6>Bạn có muốn xóa khách hàng</h6>
					<table style="width: 100%">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">Tên</th>
							</tr>
						</thead>
						<tbody>

						</tbody>
					</table>
				</div>
				<div class="modal-footer">

					<button type="button" class="btn btn-outline-secondary"
						data-bs-dismiss="modal">Đóng</button>
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal">Xóa</button>
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal">Xóa</button>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../commons/footer.jsp"%>

	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Modal Title</h4>
				</div>
				<div class="modal-body">
					<p>Modal content goes here.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>


</body>





<div class="modal fade" id="deleteModal" tabindex="-1"
	aria-labelledby="deleteModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="deleteModalLabel">Confirm Delete</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<p>Are you sure you want to delete this item?</p>
				<input type="hidden" id="deleteItemId" name="itemId">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Cancel</button>
				<button type="button" class="btn btn-danger" onclick="deleteItem()">Delete</button>
			</div>
		</div>
	</div>
</div>

<script>
	function deleteItem() {
		var itemId = document.getElementById("deleteItemId").value;
		// Send an AJAX request to delete the item
		// Redirect to the JSP page that displays the updated list
		window.location.href = "deleteItem?itemId=" + itemId;
	}

	$('#deleteModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var itemId = button.data('item-id');
		var modal = $(this);
		modal.find('#deleteItemId').val(itemId);
	});
</script>

</html>