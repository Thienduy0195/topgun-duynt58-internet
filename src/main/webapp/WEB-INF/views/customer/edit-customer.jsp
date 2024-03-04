<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update customer</title>
<style type="text/css">
 <%@include file="/resources/css/bootstrap.min.css"%>
 <%@include file="/resources/css/create-customer.css"%>
</style>
</head>
<body style="background-color: #121212;">

	<%@ include file="../commons/header.jsp"%>

	<div class="container-fluid d-flex justify-content-center">
		<div class="login-form">
			<h3 class="text-center text-white">UPDATE CUSTOMER</h3>
			<form:form
				action="${pageContext.request.contextPath}/customer/${customer.customerId}"
				method="post" modelAttribute="customer">
				<div class="row my-4">
					<div class="hidden">
						<form:input path="customerId" type="text"
							class="form-control input" id="customerId" placeholder="Họ và tên"
							autocomplete="off" value="${customer.customerId}"
						/>
					</div>
					<div class="col-md-6" style="padding-right: 15px">
						<div class="form-floating textbox mb-4">

							<form:input path="name" type="text" class="form-control input"
								id="full-name" placeholder="Họ và tên" autocomplete="off"
								value="${customer.name}"
							onblur="validateByJS(this.id,'name',this.value)"
							/>
							<label for="full-name" style="color: white"> Full name <span
								class="text-danger">*</span>
							</label>
							<form:errors path="name" cssClass="text-danger" />
							<span id="name1" class="text-danger"></span>
						</div>
					</div>

					<div class="col-md-6" style="padding-right: 15px">
						<div class="form-floating textbox mb-4">
							<form:input path="address" type="text" class="form-control input"
								id="address" placeholder="Họ và tên" autocomplete="off"
								value="${customer.address }"
							onblur="validateByJS(this.id,'address',this.value)"
							/>
							<label for="address" style="color: white"> Address <span
								class="text-danger">*</span>
							</label>
							<form:errors path="address" cssClass="text-danger" />
							<span id="address1" class="text-danger"></span>
						</div>
					</div>
				</div>
				<div class="row my-4">

					<div class="col-md-6" style="padding-right: 15px">
						<div class="form-floating textbox mb-4">
							<form:input path="email" type="email" class="form-control input"
								id="email" placeholder="example@gmail.com" autocomplete="off"
								value="${customer.email }"
							onblur="validateByJS(this.id,'email',this.value)"
							/>
							<label for="email" style="color: white"> Email <span
								class="text-danger">*</span>
							</label>
							<form:errors path="email" cssClass="text-danger" />
							<span id="email1" class="text-danger"></span>
						</div>
					</div>
					<div class="col-md-6 " style="padding-right: 15px">
						<div class="form-floating textbox mb-4">
							<form:input path="phoneNumber" type="text"
								class="form-control input" id="phone-number"
								placeholder="Họ và tên" autocomplete="off"
								value="${customer.phoneNumber}"
							onblur="validateByJS(this.id,'phoneNumber',this.value)"
							/>
							<label for="phone-number" style="color: white"> Phone <span
								class="text-danger">*</span>
							</label>
							<form:errors path="phoneNumber" cssClass="text-danger" />
							<span id="phoneNumber1" class="text-danger"></span>
						</div>
					</div>
				</div>

				<div class="row my-4">
					<div class="col-md-4 my-4"></div>
					<div class="col-md-4" style="text-align: center; width: 100%">
						<button type="submit" name="button"
							class="btn btn-primary btn-responsive w-25" style="height: 40px">Update</button>

						<a href="${pageContext.request.contextPath}/customer/list">
							<button name="button"
								class="btn btn-outline-light btn-responsive w-25 ms-3"
								style="height: 40px">Cancel</button>
						</a>

					</div>
					<div class="col-md-4"></div>
				</div>
			</form:form>
		</div>
	</div>

	<%@ include file="../commons/footer.jsp"%>
</body>

<script>

	var valid = [];
	function validateByJS(id, nameInput, valueInput) {
		const index = valid.findIndex(obj => obj.hasOwnProperty(id));
		let checkVar = checkValidate(valueInput,nameInput)
		console.log(checkVar)
		if (checkVar !== "") {
			if (index !== -1) {
				valid[index][id]= true
				console.log(valid)
			}else {
				valid = [...valid, {[id]: true }];
				console.log(valid);
			}
			document.getElementById(id+"1").innerText= checkVar
		} else {
			console.log(index)
			if (index !== -1) {
				valid[index][id]= false
				document.getElementById(id+"1").innerText=""
				console.log(valid);
			}
		}
		let check = true
		valid.forEach(item=> {

			if(item[Object.keys(item)[0]]){
				document.getElementById("submitBtn").disabled = true
				check= false
			}

		})
		if(check) {
			document.getElementById("submitBtn").disabled = false
		}
	}

	function checkValidate(value, nameValidate) {

		const phoneRegex = /^(090|091|\(84\)\+90|\(84\)\+91)[0-9]{7}$/
		const customerCodeRegex = /^CUS\d{5}$/;
		const serviceCodeRegex = /^PRO\d{3}$/;
		const positiveIntegerRegex = /^[1-9]\d*$/;
		const time24hFormatRegex = /^([01]\d|2[0-3]):([0-5]\d)$/;

		if(value === "" || value === 0 ) {
			return "Required!";
		}

		if(nameValidate === 'customerId') {
			if(!value.match(customerCodeRegex)) {
				return "Wrong format CUSxxxxx";
			}
		}

		if(nameValidate === 'phoneNumber') {
			if(!value.match(phoneRegex)) {
				return "Wrong format 0(+84)90|91xxxxxxx";
			}
		}

		return "";

	}

</script>

</html>