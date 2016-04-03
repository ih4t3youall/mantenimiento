<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>

<script type="text/javascript">


function getProyectos(){
	
	var idEmpresa = $('#comboEmpresas').val();
	$.ajax({
		
		
		url : "ajaxProyectosEmpresa.htm",
		type : "GET",
		data : "idEmpresa="+idEmpresa,
		success : function(response){
			data = $.parseJSON(response);
			$('#comboProyectos').empty();
				$('#comboProyectos').append('<option value=""></option>');
			$(data).each(function(index,item){
				$('#comboProyectos').append("<option value='"+item.id +"'>"+item.nombre+"</option>");
				
				
			});
			
		
		
	}
		
		
	});
	
}


function submitFormAsociar(){
	
	var nombreEmpleado = $('#nombreEmpleado').val().trim();
	var idProyecto = $('#comboProyectos').val();
	if(nombreEmpleado != "" && idProyecto != ""){
	$('#formIdProyecto').val(idProyecto);
	$('#formNombreEmpelado').val(nombreEmpleado);
	
	
	$('#formAsignarEmpleado').submit();
	}else{
		
		alert("debe completar los formularios");
		
	}
	
	
}


</script>


</head>
<body>

	

		<label>Asignar a: </label>
		<select class="form-control" id="nombreEmpleado">
			<option value=""></option>
			<c:forEach items="${empleados}" var="empleado">

				<option value="${empleado.ssoId} ">${empleado.ssoId}</option>

			</c:forEach>



		</select>

		<label> Empresa:</label>
		<select class="form-control" onchange="getProyectos()"
			id="comboEmpresas">
			<option value=""></option>
			<c:forEach items="${empresas}" var="empresa">

				<option value="${empresa.id}">${empresa.nombre}</option>

			</c:forEach>

		</select>


		<label>Seleccione Proyecto</label>
		<select class="form-control" id="comboProyectos">



		</select>




		<br />
		<br />
		<br />

		<input type="button" onclick="submitFormAsociar()"
			value="Asociar" class="form-control" placeholder="Username"
			aria-describedby="basic-addon1">



<form:form method="post" modelAttribute="asociacionEmpleadoProyecto"
		action="asignarEmpleado.htm" data-example-id="simple-input-groups"
		class="bs-example bs-example-form" id="formAsignarEmpleado" style="display: none;">
		<form:input path="idProyecto" id="formIdProyecto"/>
		<form:input path="nombreEmpleado" id="formNombreEmpelado"/>
		
	</form:form>







</body>
</html>