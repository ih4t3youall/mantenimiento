<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>

</head>
<body>

	<form:form method="post" modelAttribute="maquinaDTO"
		action="crearMaquina.htm" data-example-id="simple-input-groups"
		class="bs-example bs-example-form" id="formCrearMaquina">


		<div class="input-group">
			<span id="basic-addon1" class="input-group-addon"></span>
			<form:input path="nombre" id="nombreMaquina" type="text" aria-describedby="basic-addon1"
				placeholder="Nombre" class="form-control" />

		</div>
		<br>
		<div class="input-group">
			<span id="basic-addon1" class="input-group-addon"></span>
			<form:input path="descripcion" id="descripcionMaquina" type="text" aria-describedby="basic-addon1"
				placeholder="Descripcion" class="form-control" />

		</div>
		<br>
		

	


		<input type="button" onclick="submitFormMaquinaModal()"
			value="Crear" class="form-control" placeholder="Username"
			aria-describedby="basic-addon1">

	</form:form>







</body>
</html>