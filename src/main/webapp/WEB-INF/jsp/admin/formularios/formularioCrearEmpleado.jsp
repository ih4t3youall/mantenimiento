<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>

</head>
<body>

	<form:form method="post" modelAttribute="empleadoDTO"
		action="agregarEmpleado.htm" data-example-id="simple-input-groups"
		class="bs-example bs-example-form" id="formCrearEmpleado">


		<div class="input-group">
			<span id="basic-addon1" class="input-group-addon"></span>
			<form:input path="ssoId" type="text" aria-describedby="basic-addon1"
				placeholder="Nombre Usuario" class="form-control" />

		</div>
		<br>

		<div class="input-group">
			<span id="basic-addon1" class="input-group-addon"></span>
			<form:input path="password" type="text"
				aria-describedby="basic-addon1" placeholder="Apellido"
				class="form-control" />

		</div>
		<br>
		<div class="input-group">
			<span id="basic-addon1" class="input-group-addon"></span>
			<form:input path="firstName" type="text"
				aria-describedby="basic-addon1" placeholder="Nombre Persona"
				class="form-control" />

		</div>

		<br>
		<div class="input-group">
			<span id="basic-addon1" class="input-group-addon"></span>
			<form:input path="lastName" type="text"
				aria-describedby="basic-addon1" placeholder="Apellido"
				class="form-control" />

		</div>
		<br>
		<div class="input-group">
			<span id="basic-addon1" class="input-group-addon"></span>
			<form:input path="email" type="text" aria-describedby="basic-addon1"
				placeholder="Email" class="form-control" />

		</div>
		<br>


		<form:select class="form-control" path="type">

			<c:forEach items="${types}" var="type">

				<form:option value="${type} ">${type}</form:option>

			</c:forEach>



		</form:select>



		<br>

		<input type="button" onclick="submitForm('formCrearEmpleado')"
			value="Crear" class="form-control" placeholder="Username"
			aria-describedby="basic-addon1">

	</form:form>







</body>
</html>