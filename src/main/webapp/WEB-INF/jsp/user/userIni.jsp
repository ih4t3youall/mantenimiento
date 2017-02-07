<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu Operario</title>

<%-- <link href="<c:url value='/static/css/bootstrap.css' />" --%>
<!-- 	rel="stylesheet"></link> -->
<%-- <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link> --%>



<script src="<c:url value='/static/jquery/jquery-1.11.3.min.js' />"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="<c:url value='/static/bootstrap-3.3.6-dist/css/bootstrap.min.css' />">


<!-- Optional theme -->
<link rel="stylesheet"
	href="<c:url value='/static/bootstrap-3.3.6-dist/css/bootstrap-theme.min.css' />">

<!-- Latest compiled and minified JavaScript -->
<script
	src="<c:url value='/static/bootstrap-3.3.6-dist/js/bootstrap.min.js' />"></script>



<!-- owl -->
<link rel="stylesheet"
	href="<c:url value='/static/owl-carousel/owl-carousel/owl.carousel.css' />">
<link rel="stylesheet"
	href="<c:url value='/static/owl-carousel/owl-carousel/owl.theme.css'/>">


<script
	src="<c:url value='/static/owl-carousel/owl-carousel/owl.carousel.js'/>"></script>
<!-- end owl -->


<script type="text/javascript">
	$(document).ready(
			function() {

				$(document).ready(
						function() { // Script del Navegador
							$("ul.subnavegador").not('.selected').hide();
							$("a.desplegable").click(
									function(e) {
										var desplegable = $(this).parent()
												.find("ul.subnavegador");
										$('.desplegable').parent().find(
												"ul.subnavegador").not(
												desplegable).slideUp('slow');
										desplegable.slideToggle('slow');
										e.preventDefault();
									})

						});

			});

	$(document).ready(function() {
		getForm("getEmpresas.htm");

	});

	function getForm(url) {

		$.ajax({
			url : url,
			type : 'GET',
			success : function(response) {
				$('#contenedor').empty();
				$('#contenedor').append(response);

			},
			error : function() {
				alert("Ha ocurrido un error");
			}
		});

	}

	function validarCamposNoVacios() {

		var flag = true;

		$('.form-control').each(function(index, item) {

			if ($(item).val() == "") {

				flag = false;
			}

		});

		return flag;

	}

	function submitForm(form) {

		var validado = validarCamposNoVacios();

		if (validado) {
			$("#" + form).submit();

		} else {

			//alert('debe completar todos los campos');
			$.notify({
				// options
				message : 'Todos los campos son obligatorios.'
			}, {
				// settings
				type : 'danger'
			});
		}

	}

	function getForm(url) {

		$.ajax({
			url : url,
			type : 'GET',
			success : function(response) {
				$('#contenedor').empty();
				$('#contenedor').append(response);

			},
			error : function() {
				alert("Ha ocurrido un error");
			}
		});

	}

	function validarCamposNoVacios() {

		var flag = true;

		$('.form-control').each(function(index, item) {

			if ($(item).val() == "") {

				flag = false;
			}

		});

		return flag;

	}

	function submitForm(form) {

		var validado = validarCamposNoVacios();

		if (validado) {
			$("#" + form).submit();

		} else {

			//alert('debe completar todos los campos');
			$.notify({
				// options
				message : 'Todos los campos son obligatorios.'
			}, {
				// settings
				type : 'danger'
			});
		}

	}

	function submit(id) {

		var empresaId = $("#acceso").html();
		//controla que el usuario tenga acceso a esa empresa , tambien se controla del lado del servidor
		if (empresaId == id) {
			$('#id').val(id);
			$('#form').submit();
		} else {

			alert("no tiene permisos para ver esa empresa.");

		}

	}
</script>

<style type="text/css">
li {
	list-style: none;
}
</style>

</head>
<body>
	<p style="display: none;" id="acceso">${empresa_id}</p>

	<div class="container">
		<h1 id="titulo">Clientes</h1>
		<p id="copete">Seleccione su empresa</p>
		<div id="carbonads-container">
			<div class="carbonad">
				<div id="azcarbon"></div>
			</div>
		</div>
	</div>


	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-2">

			<ul class="nav nav-pills nav-stacked navegador">
				<li role="presentation" class="active "><a href="#"
					class="desplegable" title="Venta">Usuario</a>
					<ul class="subnavegador ">
						<!-- 						<li role="presentation" class="active"><a href="#" title="Aparcamientos">Cambiar Contraseña</a></li> -->
						<li role="presentation"><a href="#"
							onClick="getForm('formCambiarContrasenia.htm')"> Cambiar
								Contraseña</a></li>
						<li role="presentation"><a href="<c:url value='/logout' />">
								Deslogearse </a></li>
					</ul></li>
				<li><a class="desplegable" href="#" title="Alquiler">Mas
						Opciones</a>
					<ul class="subnavegador">
						<li role="presentation"><a href="#" title="Viviendas">Todavia
								mas</a></li>
					</ul></li>
			</ul>



		</div>

		<div class="col-md-9">

			<div id="contenedor"></div>
		</div>

		<!-- 		<div class="col-md-4"> -->
		<%-- 			<c:forEach items="${columnaA}" var="empresa"> --%>

		<!-- 				<div style="cursor: pointer; cursor: hand" -->
		<!-- 					class="panel panel-primary"> -->
		<%-- 					<div class="panel-heading">${empresa.nombre}</div> --%>
		<!-- 					<div class="panel-body"> -->
		<%-- 						<img onclick="submit('${empresa.id}')" height="125px" width="200px" alt="" --%>
		<%-- 							src="data:image/jpeg;base64,${empresa.urlImagen}"> --%>
		<!-- 					</div> -->
		<!-- 				</div> -->

		<%-- 			</c:forEach> --%>



		<!-- 		</div> -->

		<!-- 		<div class="col-md-4"> -->
		<%-- 			<c:forEach items="${columnaB}" var="empresa"> --%>

		<!-- 				<div style="cursor: pointer; cursor: hand" -->
		<!-- 					class="panel panel-primary"> -->
		<%-- 					<div class="panel-heading">${empresa.nombre}</div> --%>
		<!-- 					<div class="panel-body"> -->
		<%-- 						<img onclick="submit('${empresa.id}')" --%>
		<%-- 							height="125px" width="200px" alt="" src="${empresa.urlImagen}"> --%>
		<!-- 					</div> -->
		<!-- 				</div> -->

		<%-- 			</c:forEach> --%>



		<!-- 		</div> -->
		<!-- 		<div class="col-md-1"></div> -->


	</div>

	<p id="usuarioId" style="display: none;"></p>

	<form:form action="miEmpresa.htm" id="form" method="post"
		style="display: none;" modelAttribute="empresa">
		<table border="0">
			<tr>
				<td>id:</td>
				<td><form:input id="id" path="id" /></td>
			</tr>
		</table>
	</form:form>



</body>
</html>