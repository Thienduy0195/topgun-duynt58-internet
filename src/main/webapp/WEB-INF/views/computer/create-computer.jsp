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
						autocomplete="off" value="${computer.computerId}"
						onblur="validateByJS(this.id,'computerId',this.value)"
					/>
					<form:errors path="computerId" cssClass="text-danger" />
					<span id="computerId1" class="text-danger"></span>
				</div>

				<div class="textbox mb-4">
					<label for="location" style="color: white"> LOCATION <span
						class="text-danger">*</span>
					</label>
					<form:input path="location" type="text" class="form-control input"
						id="location" placeholder="Enter the location"
						style="padding-top: 10px" autocomplete="off"
						value="${computer.location}"
						onblur="validateByJS(this.id,'location',this.value)"
					/>
					<form:errors path="location" cssClass="text-danger" />
					<span id="location1" class="text-danger"></span>
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

		const computerCodeRegex = /^PRO\d{3}$/;

		if(value === "" || value === 0 ) {
			return "Required!";
		}

		if(nameValidate === 'computerId') {
			if(!value.match(computerCodeRegex)) {
				return "Wrong format COMxxx";
			}
		}

		return "";

	}

</script>
</html>