<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update computer</title>

<style type="text/css">
 <%@include file="/resources/css/bootstrap.min.css"%>
 <%@include file="/resources/css/create-computer.css"%>
</style>
</head>
<body>
	<%@ include file="../commons/header.jsp"%>

	<div class="container-fluid d-flex justify-content-center mb-4">
		<div class="login-form" style="width: 500px">
			<h6 class="text-center mt2">UPDATE COMPUTER</h6>
			<form:form
				action="${pageContext.request.contextPath}/computer/${computer.computerId}"
				method="post" modelAttribute="computer">


				<div class="form-floating textbox mb-4">

					<form:input path="computerId" type="text"
					 style="background: #2b2b2b;"
						class="form-control input" id="computerId" placeholder="Họ và tên"
						autocomplete="off" readonly="${true}" />
					<label for="computerId" style="color: white"> ID (Read only)
						<span class="text-danger">*</span>
					</label>
					<form:errors path="computerId" cssClass="text-danger" />
				</div>

				<div class="form-floating textbox mb-4">

					<form:input path="location" type="text" class="form-control input"
						id="location" placeholder="Họ và tên" autocomplete="off" />
					<label for="location" style="color: white"> Location <span
						class="text-danger">*</span>
					</label>
					<form:errors path="location" cssClass="text-danger" />
				</div>

				<div class="form-floating textbox mb-4">
					<form:select style="min-width: 100px; background: #2b2b2b; border: none"
						path="status" name="status" class="form-control input text-white">
						
						<option value="Offline" ${computer.status == 'Offline' ? 'selected' : ''}>Offline</option>
						<option value="Online" ${computer.status == 'Online' ? 'selected' : ''}>Online</option>
						<option value="Maintain" ${computer.status == 'Maintain' ? 'selected' : ''}>Maintain</option>
					</form:select>
					<label for="status" style="color: white"> Status <span
						class="text-danger">*</span>
					</label>
					<form:errors path="status" cssClass="text-danger" />
				</div>

				<div class="" style="padding-right: 37px">
					<div class="row mb-8 mt-4 justify-content-center row">

						<button type="submit" class="btn btn-primary w-25"
							style="margin-right: 10px">Update</button>


						<a href="${pageContext.request.contextPath}/computer/list"
							class="w-25">
							<button class="btn btn-outline-light">Cancel</button>
						</a>

					</div>
				</div>
			</form:form>
		</div>
	</div>
	<%@ include file="../commons/footer.jsp"%>
</body>
</html>