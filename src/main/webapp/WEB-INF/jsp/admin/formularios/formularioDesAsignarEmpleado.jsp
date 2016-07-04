<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>


<script src="<c:url value='/static/jquery-ui-1.12.0/jquery-ui.js' />"></script>
<link rel="stylesheet"
	href="<c:url value='/static/jquery-ui-1.12.0/jquery-ui.min.css' />">



<script type="text/javascript">
	function getProyectosByEmpleado() {

		var nombreEmpleado = $('#nombreEmpleado').val();

		$
				.ajax({

					url : "obtenerProyectosPorEmpleado.htm",
					type : "GET",
					data : "ssoid=" + nombreEmpleado,
					success : function(data) {
						var respuesta = $.parseJSON(data);

						$('#tags').empty();
						$(respuesta)
								.each(
										function(index, proyecto) {

											$('#tags')
													.append(
															'<span class="tag label label-info" onclick="verProyecto('+ proyecto.id
																	+ ',this,\''+proyecto.nombre+'\')" ">'
																	+ proyecto.nombre
																	+ '<span data-role="remove"></span></span>');

										});

					}

				});

	}

	function verProyecto(idProyecto,labelers,nombreProyecto) {
		
		var error;
		
		$("#selectBox").html("Proyecto:"+nombreProyecto);
		$("#selectBox").dialog({
			resizable : false,
			height : 200,
			title : "Seguro que desea desasignar este empleado?",
			modal : true,
			buttons : {
				"Aceptar" : function() {

					var request = new Object();
					request.nombreEmpleado = $("#nombreEmpleado").val();
					request.idProyecto = idProyecto;
					var request = JSON.stringify(request);
					

					$.ajax({

						url : "doDesAsignarEmpleado.htm",
						type : "GET",
						data : "asocEmplProyecto=" + request,
						success : function(data) {
							$(labelers).hide();
						
							//error = data;
							
							if (data == 200) {
								
								$(".tag").empty();
								$("#nombreEmpleado").val("");
							} else {
								
								alert("ocurrio un error pase 500");

							}
							
							
	
						},

					});

					$(this).dialog("close");
			
					

				},
				"Cancelar" : function() {
					
					$(this).dialog("close");

				},

			}
		});
		
		
	
		

	}
</script>
</head>
<body>

<label id="selectBox" style="display:none;">Proyectos: </label>

	<form:form method="post" modelAttribute="asociacionEmpleadoProyecto"
		action="asignarEmpleado.htm" data-example-id="simple-input-groups"
		class="bs-example bs-example-form" id="formAsignarEmpleado">

		<label>Desasignar a: </label>
		<form:select onchange="getProyectosByEmpleado()" id="nombreEmpleado"
			class="form-control" path="nombreEmpleado">
			<form:option value=""></form:option>
			<c:forEach items="${empleados}" var="empleado">
				<form:option value="${empleado.ssoId}">${empleado.ssoId}</form:option>
			</c:forEach>
		</form:select>

		<br />
		<br />
		<br />

		<label id="labelProyectosA">Proyectos a los cuales esta
			asignado:</label>
		<div id="contenedorAsignacion"></div>
		<div class="bootstrap-tagsinput" id="tags"></div>

		<br />
		<br />
		<br />

		<!-- 		<input type="button" onclick="submitForm('formAsignarEmpleado','')" -->
		<!-- 			value="DesAsociar" class="form-control btn btn-primary" placeholder="Username" -->
		<!-- 			aria-describedby="basic-addon1"> -->

	</form:form>









</body>
</html>