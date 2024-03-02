<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Using Service</title>
<style type="text/css">
 <%@include file="/resources/css/bootstrap.min.css"%>
 <%@include file="/resources/css/create-customer.css"%>
</style>
</head>
<body style="background-color: #121212;">

	<%@ include file="../commons/header.jsp"%>

	<div class="container-fluid d-flex justify-content-center">
		<div class="login-form">
			<h3 class="text-center text-white">REGISTER TO USE SERVICE</h3>
			<form:form action="${pageContext.request.contextPath}/payment/create"
				method="post" modelAttribute="paymentDTO">
				<div class="row my-4">
					<div class="col-md-6" style="padding-right: 15px">
						<div class="form-floating textbox mb-4">
							<form:select name="customers" path="customerId"
								class="form-control input text-white"
								style="background: #2b2b2b; border: none">
								<option value="">Choose customer</option>
								<c:forEach items="${customers}" var="item">
									<option value="${item.customerId}">${item.name}</option>
								</c:forEach>
							</form:select>
							
							<label for="customerId" style="color: white"> Customer
								Name <span class="text-danger">*</span>
							</label>
							
							<form:errors path="customerId" cssClass="text-danger" />
						</div>
					</div>

					<div class="col-md-6" style="padding-right: 15px">
						<div class="form-floating textbox mb-4">
							<form:select path="productId"
								class="form-control input text-white"
								style="background: #2b2b2b; border: none">
								<option value="">Choose service</option>
								<c:forEach items="${products}" var="item">
									<option value="${item.productId}">${item.productName}</option>
								</c:forEach>
							</form:select>
							
							<label for="productId" style="color: white"> Service Name
								<span class="text-danger">*</span>
							</label>
							
							<form:errors path="productId" cssClass="text-danger" />
						</div>
					</div>
				</div>

				<div class="row my-4">

					<div class="col-md-4" style="padding-right: 15px">
						<div class="form-floating textbox mb-4">
							<form:input class="form-control input text-white" id="usingDate"
								style="background: #2b2b2b;" path="usingDate" type="date" />
							
							<label for="usingDate" style="color: white"> Using Date <span
								class="text-danger">*</span>
							</label>
							
							<form:errors path="usingDate" cssClass="text-danger" />
						</div>
					</div>

					<div class="col-md-4" style="padding-right: 15px">
						<div class="form-floating textbox mb-4">
							<form:input class="form-control input text-white" id="usingHour"
								style="background: #2b2b2b;" path="usingHour" type="time" />
							
							<label for="usingHour" style="color: white"> Using time <span
								class="text-danger">*</span>
							</label>
							
							<form:errors path="usingHour" cssClass="text-danger" />

						</div>
					</div>
					<div class="col-md-4 " style="padding-right: 15px">
						<div class="form-floating textbox mb-4">
							<form:input class="form-control input text-white" id="amount"
								style="background: #2b2b2b;" path="amount" />
							
							<label for="amount" style="color: white"> Amount <span
								class="text-danger">*</span>
							</label>
							
							<form:errors path="amount" cssClass="text-danger" style="color: white;"/>

						</div>
					</div>
				</div>



				<div class="row my-4">
					<div class="col-md-4 my-4"></div>
					<div class="col-md-4" style="text-align: center; width: 100%">
						<button type="submit" name="button"
							class="btn btn-primary btn-responsive w-25" style="height: 40px">Add
							new</button>

						<a href="${pageContext.request.contextPath}/customer/list"
							class="w-25 btn btn-outline-light btn-responsive
        w-25 ms-3"
							style="height: 40px"> Cancel </a>

					</div>
					<div class="col-md-4"></div>
				</div>
			</form:form>
		</div>
	</div>

	<button id="myButton">click!</button>

	<%@ include file="../commons/footer.jsp"%>
</body>

<script>
	$(function() {

		$("#dialog").dialog({
			autoOpen : false,
			modal : true
		});

		$("#myButton").on("click", function(e) {
			e.preventDefault();
			$("#dialog").dialog("open");
		});

	});
</script>
</html>