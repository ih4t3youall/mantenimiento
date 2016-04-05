<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<script type="text/javascript">



function getProyectosByEmpleado(){
	
	var nombreEmpleado = $('#nombreEmpleado').val();
	
	
	$.ajax({
		
		url : "obtenerProyectosPorEmpleado.htm",
		type : "GET",
		data : "ssoid="+nombreEmpleado,
		success: function(data){
			var respuesta = $.parseJSON(data);
			
			
			$('#tags').empty();
			$(respuesta).each(function(index,proyecto){
				
			$('#tags').append('<span class="tag label label-info" onclick="verProyecto('+proyecto.id+')" ">'+proyecto.nombre+'<span data-role="remove"></span></span>');
				
				
			});
			
			
		}
		
	});
	
	
	
	
}

function verProyecto(idProyecto){
	
	//FIXME 
	//tiene que mandar a una pantalla que muestre el proyecto, puede ser un modal
	
	
}


</script>
</head>
<body>

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
		<div class="bootstrap-tagsinput" id="tags">
			
		</div>

		<br />
		<br />
		<br />

		<input type="button" onclick="submitForm('formAsignarEmpleado','')"
			value="DesAsociar" class="form-control btn btn-primary" placeholder="Username"
			aria-describedby="basic-addon1">

	</form:form>









</body>
</html>