<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>

</head>
<body>

	<form:form method="post" modelAttribute="crearProyectoDTO"
		action="crearProyecto.htm" data-example-id="simple-input-groups"
		class="bs-example bs-example-form" id="formCrearProyecto">

		<label>Seleccione una empresa para adjudicarle un proyecto: </label>
		<form:select class="form-control" path="empresaId">

			<c:forEach items="${empresas}" var="empresa">

				<form:option value="${empresa.id} ">${empresa.nombre}</form:option>

			</c:forEach>



		</form:select>


		<br>
		<div class="input-group">
			<span id="basic-addon1" class="input-group-addon"></span>
			<form:input path="nombreProyecto" type="text" aria-describedby="basic-addon1"
				placeholder="Nombre" class="form-control" />

		</div>

		<br>
		<div class="input-group">
			<span id="basic-addon1" class="input-group-addon"></span>
			<form:input path="descripcionProyecto" type="text"
				aria-describedby="basic-addon1" placeholder="Descripcion"
				class="form-control" />

		</div>

		<br />
		<br />
		<br />

		<input type="button" onclick="submitForm('formCrearProyecto','')"
			value="Asociar" class="form-control" placeholder="Username"
			aria-describedby="basic-addon1">

	</form:form>







</body>
</html>