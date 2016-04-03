<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>

<script type="text/javascript">
	

	function submitFormAsociar() {

		
		
		
		var idCliente = $('#idCliente').val().trim();
		var idEmpresa = $('#idEmpresa').val().trim();
		if (idCliente != "" && idEmpresa != "") {
			$('#formIdEmpresa').val(idEmpresa);
			$('#formIdCliente').val(idCliente);

			$('#formAsignarCliente').submit();
		} else {

			alert("debe completar los formularios");

		}

	}
</script>


</head>
<body>



	<label>Asignar a: </label>
	<select class="form-control" id="idCliente">
		<option value=""></option>
			<c:forEach items="${clientes}" var="cliente">

			<option value="${cliente.id}">${cliente.ssoId}</option>

		</c:forEach>




	</select>

	<label> Empresa:</label>
	<select class="form-control" 
		id="idEmpresa">
		<option value=""></option>
		<c:forEach items="${empresas}" var="empresa">

			<option value="${empresa.id}">${empresa.nombre}</option>

		</c:forEach>

	</select>






	<br />
	<br />
	<br />

	<input type="button" onclick="submitFormAsociar()" value="Asociar"
		class="form-control" placeholder="Username"
		aria-describedby="basic-addon1">



	<form:form method="post" modelAttribute="asociacionClienteEmpresa"
		action="asignarClienteEmpresa.htm" data-example-id="simple-input-groups"
		class="bs-example bs-example-form" id="formAsignarCliente"
		style="display: none;">
		<form:input path="idCliente" id="formIdCliente" />
		<form:input path="idEmpresa" id="formIdEmpresa" />

	</form:form>







</body>
</html>