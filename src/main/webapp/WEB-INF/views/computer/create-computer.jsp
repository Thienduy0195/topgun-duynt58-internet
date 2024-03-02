<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create computer</title>

<style type="text/css">
 <%@include file="/resources/css/bootstrap.min.css"%>
 <%@include file="/resources/css/create-computer.css"%>
</style>
</head>
<body>
	<%@ include file="../commons/header.jsp"%>

	<div class="container-fluid d-flex justify-content-center">
		<div class="login-form">
			<h6 class="text-center mt-4">NEW COMPUTER</h6>
			<form:form
				action="${pageContext.request.contextPath}/computer/create"
				method="post" modelAttribute="computer">
				<div class="textbox mb-4">
					<label for="computerId" style="color: white"> COMPUTER CODE
						(example: COM-002) <span class="text-danger">*</span>
					</label>
					<form:input path="computerId" type="text"
						class="form-control input" id="computerId"
						placeholder="Enter computer code" style="padding-top: 10px"
						autocomplete="off" value="${computer.computerId}" />
					<form:errors path="computerId" cssClass="text-danger" />
				</div>

				<div class="textbox mb-4">
					<label for="location" style="color: white"> LOCATION <span
						class="text-danger">*</span>
					</label>
					<form:input path="location" type="text" class="form-control input"
						id="location" placeholder="Enter the location"
						style="padding-top: 10px" autocomplete="off"
						value="${computer.location}" />
					<form:errors path="location" cssClass="text-danger" />
					<div></div>

				</div>

				<div class="" style="padding-right: 37px">
					<div class="row mb-8 mt-4 justify-content-center">
						<button type="submit" class="btn btn-primary w-25"
							style="margin-right: 10px">Add new</button>

						<a href="${pageContext.request.contextPath}/computer/list"
							class="w-25 btn btn-outline-light"> Cancel </a>

					</div>
				</div>
			</form:form>
		</div>
	</div>
	<%@ include file="../commons/footer.jsp"%>
</body>
</html>