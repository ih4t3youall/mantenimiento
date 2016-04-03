<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>


<script type="text/javascript">
	//FIXME
	$(document).ready(function() {
		// 		$("#boton").prop('disabled', true);
	});

	function agregarCampo() {

		var campo = '<div class="form-group"> 		<label for="">Ingrese Nombre Input checkbox:</label> <input type="text" class="form-control customInput campoTexto"	id="0" value="">	</div>';

		$('#campos').append(campo);

	}


</script>
</head>
<body>


	<h3>Editar nuevo checklist</h3>
	<h5>Maquina <span id="maquinaId">${form.id}</span></h5>
	<div id="campos">


		<c:forEach items="${form.formItems}" var="item">

			<div class="form-group">
				<label for="">Modificar:</label> <input
					type="text" class="form-control customInput campoTexto" id="${item.idformItem} " value="${item.label}">
			</div>


		</c:forEach>



		<div class="form-group">
			<label for="">Ingrese Nombre Input checkbox:</label> <input
				type="text" class="form-control customInput campoTexto" id="0">
		</div>


	</div>
	<button type="button" onclick="agregarCampo()"
		class="btn btn-default btn-sm">
		<span class=" glyphicon glyphicon-pencil" aria-hidden="true"></span> +
	</button>



		<input type="button" id="boton" onclick="editarFormularioSubmit()"
		value="Enviar Modificaciones" class="form-control"
		aria-describedby="basic-addon1">





</body>
</html>