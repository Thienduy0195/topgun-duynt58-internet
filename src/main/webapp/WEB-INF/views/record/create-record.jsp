<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Using Computer</title>
<style type="text/css">
 <%@include file="/resources/css/bootstrap.min.css"%>
 <%@include file="/resources/css/create-customer.css"%>
</style>
</head>
<body style="background-color: #121212;">

	<%@ include file="../commons/header.jsp"%>

	<div class="container-fluid d-flex justify-content-center">
		<div class="login-form">
			<h3 class="text-center text-white">REGISTER TO USE COMPUTER</h3>
			<form:form action="${pageContext.request.contextPath}/record/create"
				method="post" modelAttribute="record">
				<div class="row my-4">
					<div class="col-md-6" style="padding-right: 15px">
						<div class="form-floating textbox mb-4">
							<form:select name="customers" path="customerId"
								class="form-control input text-white"
								style="background: #2b2b2b; border: none">
								<option value="">Choose customer</option>
								<c:forEach items="${customers}" var="item">
									<option value="${item.customerId}"
									${item.customerId == record.customerId ? 'selected' : ''}
									>${item.name}</option>
								</c:forEach>
							</form:select>
							<form:errors path="customerId" cssClass="text-danger" />
							<label for="customerId" style="color: white"> Customer
								Name <span class="text-danger">*</span>
							</label>
						</div>
					</div>

					<div class="col-md-6" style="padding-right: 15px">
						<div class="form-floating textbox mb-4">
							<form:select name="computers" path="computerId"
								class="form-control input text-white"
								style="background: #2b2b2b; border: none">
								<option value="">Choose computer</option>
								<c:forEach items="${computers}" var="item">
									<option value="${item.computerId}"
									${item.computerId == record.computerId ? 'selected' : ''}
									>${item.computerId}</option>
								</c:forEach>
							</form:select>
							<form:errors path="computerId" cssClass="text-danger" />
							<label for="computerId" style="color: white"> Computer
								Code <span class="text-danger">*</span>
							</label>
						</div>
					</div>
				</div>

				<div class="row my-4">

					<div class="col-md-4" style="padding-right: 15px">
						<div class="form-floating textbox mb-4">
							<form:input class="form-control input text-white"
								id="startingDate" style="background: #2b2b2b;"
								path="startingDate" type="date" />
							<label for="startingDate" style="color: white"> Starting
								Date <span class="text-danger">*</span>
							</label>
							<form:errors path="startingDate" cssClass="text-danger" />
						</div>
					</div>

					<div class="col-md-4" style="padding-right: 15px">
						<div class="form-floating textbox mb-4">
							<form:input class="form-control input text-white"
								id="startingHour" style="background: #2b2b2b;"
								path="startingHour" type="time" />
							<label for="startingHour" style="color: white"> Starting
								Hour<span class="text-danger">*</span>
							</label>
							<form:errors path="startingHour" cssClass="text-danger" />
						</div>
					</div>
					<div class="col-md-4 " style="padding-right: 15px">
						<div class="form-floating textbox mb-4">
							<form:input class="form-control input text-white" id="usingTime"
								style="background: #2b2b2b;" path="usingTime" />
							<label for="usingTime" style="color: white"> Using Time
								(minute) <span class="text-danger">*</span>
							</label>
							<form:errors path="usingTime" cssClass="text-danger" />
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

	<%@ include file="../commons/footer.jsp"%>
</body>

</html>