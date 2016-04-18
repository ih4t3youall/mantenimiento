<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
	function getCheckList(maquinaId, proyectoId) {

		$('#maquinaId').val(maquinaId);
		$('#proyectoId').val(proyectoId);
		$('#checklist').submit();

	}
</script>



</head>
<body>


	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-6">

			<c:forEach items="${maquinas}" var="maquina">


				<div class="item">


					<div
						onclick="getCheckList('${maquina.id}','${maquinaProyectoIdDTO.proyectoId}')"
						class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">${maquina.nombre}</h3>
						</div>
						<div class="panel-body">${maquina.descripcion}</div>
					</div>

				</div>




			</c:forEach>
			<a href="inicio.htm"><input type="button" value="volver" /></a>
		</div>
		<div class="col-md-3"></div>

	</div>

	<form:form style="display: none;" method="post"
		modelAttribute="maquinaProyectoIdDTO" action="getCheckListById.htm"
		data-example-id="simple-input-groups"
		class="bs-example bs-example-form" id="checklist">


		<div class="input-group">
			<span id="basic-addon1" class="input-group-addon">Nom</span>
			<form:input path="maquinaId" id="maquinaId" type="text"
				aria-describedby="basic-addon1" placeholder="Nombre"
				class="form-control" />

		</div>
		<br>
		<div class="input-group">
			<span id="basic-addon1" class="input-group-addon">Desc</span>
			<form:input path="proyectoId" id="proyectoId" type="text"
				aria-describedby="basic-addon1" placeholder="Descripcion"
				class="form-control" />

		</div>
		<br>




		<input type="button" onclick="submitForm('formCrearMaquina')"
			value="Crear" class="form-control" placeholder="Username"
			aria-describedby="basic-addon1">

	</form:form>



</body>
</html>