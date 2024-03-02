<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create new product</title>
<style type="text/css">
 <%@include file="/resources/css/bootstrap.min.css"%>
 <%@include file="/resources/css/create-product.css"%>
</style>
</head>
<body style="background-color: #121212;">

	<%@ include file="../commons/header.jsp"%>

	<div class="container-fluid d-flex justify-content-center">
		<div class="login-form">
			<h3 class="text-center text-white">ADD NEW SERVICE</h3>
			<form:form action="${pageContext.request.contextPath}/product/create"
				method="post" modelAttribute="product">
				<div class="row my-4">
					<div class="col-md-4" style="padding-right: 15px">
						<div class="form-floating textbox mb-4">

							<form:input path="productId" type="text"
								class="form-control input" id="productId"
								placeholder="Họ và tên" autocomplete="off" />
							<label for="productId" style="color: white"> Service code (SV-xxxx)
								<span class="text-danger">*</span>
							</label>
							<form:errors path="productId" cssClass="text-danger" />
						</div>
					</div>
					<div class="col-md-4" style="padding-right: 15px">
                        <div class="form-floating textbox mb-4">

                            <form:input path="productName" type="text"
                                class="form-control input" id="full-name"
                                placeholder="Enter service name" autocomplete="off" />
                            <label for="full-name" style="color: white"> Service name
                                <span class="text-danger">*</span>
                            </label>
                              <form:errors path="productName" cssClass="text-danger" />
                        </div>
                    </div>
					<div class="col-md-4" style="padding-right: 15px">
						<div class="form-floating textbox mb-4">
							<form:input path="price" type="text" class="form-control input"
								id="address" placeholder="Enter price" autocomplete="off" />
							<label for="date-of-birth" style="color: white"> Price <span
								class="text-danger">*</span>
							</label>
							<form:errors path="price" cssClass="text-danger" />
						</div>
					</div>
				</div>
				<div class="row my-4">
					<div class="col-md-6" style="padding-right: 15px">
						<div class="form-floating textbox mb-4">
							<form:input path="unit" type="text" class="form-control input"
								id="unit" placeholder="Enter unit" autocomplete="off" />
							<label for="unit" style="color: white"> Unit <span
								class="text-danger">*</span>
							</label>
							<form:errors path="unit" cssClass="text-danger" />
						</div>
					</div>
					<div class="col-md-6 " style="padding-right: 15px">
						<div class="form-floating textbox mb-4">
							<form:input path="image" type="text" class="form-control input"
								id="image" placeholder="Họ và tên" autocomplete="off" />
							<label for="image" style="color: white"> Image url <span
								class="text-danger">*</span>
							</label>
							<form:errors path="image" cssClass="text-danger" />
						</div>
					</div>
				</div>

				<div class="row my-4">
					<div class="col-md-4 my-4"></div>
					<div class="col-md-4" style="text-align: center; width: 100%">
						<button name="button" class="btn btn-primary btn-responsive w-25"
							style="height: 40px">Add new</button>

						<a href="${pageContext.request.contextPath}/computer/list"
							class="btn btn-outline-light btn-responsive
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