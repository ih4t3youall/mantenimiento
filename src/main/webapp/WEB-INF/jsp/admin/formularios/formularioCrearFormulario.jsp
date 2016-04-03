<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>



<script 	src="<c:url value='/static/bootstrap-3.3.6-dist/js/bootstrap-datepicker.js'/>"></script>


<link rel="stylesheet"
	href="<c:url value='/static/bootstrap-3.3.6-dist/css/bootstrap-datepicker.css' />">

<script type="text/javascript">




$(document).ready(
		function() {

			$(document).ready(
					function() { // Script del Navegador
					//porque como hay integers me pisa el placeholder con un cero y en el placeholder esta el valor de cada campo
						$('.form-control').each(function(index, item) {

							//para evitar que le saque el label al ultimo boton
							if(index != 7){
							$(item).val("");
							}
						});
						
						$.fn.datepicker.dates['es'] = {
							    days: ["Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"],
							    daysShort: ["Dom", "Lun", "Mar", "Mier", "Jue", "Vier", "sab"],
							    daysMin: ["Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa"],
							    months: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
							    monthsShort: ["En", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dic"],
							    today: "Hoy",
							    clear: "Clear",
							    format: "dd/mm/yyyy",
							    titleFormat: "MM yyyy", /* Leverages same syntax as 'format' */
							    weekStart: 0,
							    onClose:function(){
							    	
							    }
							};
						
						$('.fecha').datepicker({
						    language: 'es',
						
						});
						
						
						$('.fecha').change(function() {
							
							$('.datepicker').hide();
							
						});
						
					});

		});






</script>
</head>
<body>


	<h3>Complete los campos para crear un nuevo ckeck list</h3>


	<form:form method="post" modelAttribute="formDTO"
		action="crearFormulario.htm" data-example-id="simple-input-groups"
		class="bs-example bs-example-form" id="formCrearFormulario">


		<div class="input-group">
			<span id="basic-addon1" class="input-group-addon">-></span>
			<form:input path="equipo" type="text" aria-describedby="basic-addon1"
				placeholder="Equipo" class="form-control" />

		</div>
		<br>

		<div class="input-group">
			<span id="basic-addon1" class="input-group-addon">-></span>
			<form:input path="fechaRealizacion" type="text"
				aria-describedby="basic-addon1" placeholder="fecha realizacion"
				class="form-control fecha" />

		</div>
<br>
		<div class="input-group">
			<span id="basic-addon1" class="input-group-addon">-></span>
			<form:input path="fechaProgramada"  type="text" aria-describedby="basic-addon1"
				placeholder="Fecha Programada" class="form-control fecha" />

		</div>

<br>
		<div class="input-group">
			<span id="basic-addon1" class="input-group-addon">-></span>
			<form:input path="nroInterno" type="text"
				aria-describedby="basic-addon1" placeholder="numeroInterno"
				class="form-control" />

		</div>
<br>
		<div class="input-group">
			<span id="basic-addon1" class="input-group-addon">-></span>
			<form:input path="nroOrden" type="text"
				aria-describedby="basic-addon1" placeholder="Numero Orden"
				class="form-control" />

		</div>
		<br>
		<div class="input-group">
			<span id="basic-addon1" class="input-group-addon">-></span>
			<form:input path="conclusion" type="text"
				aria-describedby="basic-addon1" placeholder="Conclusion"
				class="form-control" />

		</div>
<br>

		<div class="input-group">
			<span id="basic-addon1" class="input-group-addon">-></span>
			<form:input path="observaciones" type="text"
				aria-describedby="basic-addon1" placeholder="Observaciones"
				class="form-control" />

		</div>

<br>
		
		 <input type="button" onclick="submitForm('formCrearFormulario')" value="Crear" class="form-control" placeholder="Username" aria-describedby="basic-addon1">
		
	</form:form>






</body>
</html>