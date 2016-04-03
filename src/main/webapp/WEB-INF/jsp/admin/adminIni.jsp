<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu Operario</title>

<%-- <link href="<c:url value='/static/css/bootstrap.css' />" --%>
<!-- 	rel="stylesheet"></link> -->
<%-- <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link> --%>




<script src="<c:url value='/static/jquery/jquery-1.11.3.min.js' />"></script>



<!-- notify  -->

<script src="<c:url value='/static/bootstrap-notify/bootstrap-notify.js' />"></script>
<script src="<c:url value='/static/bootstrap-notify/bootstrap-notify.min.js' />"></script>
<link rel="stylesheet" href="<c:url value='/static/bootstrap-notify/animate.css' />">




<!-- fin nofity -->



<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="<c:url value='/static/bootstrap-3.3.6-dist/css/bootstrap.min.css' />">


<!-- Optional theme -->
<link rel="stylesheet"
	href="<c:url value='/static/bootstrap-3.3.6-dist/css/bootstrap-theme.min.css' />">

<!-- Latest compiled and minified JavaScript -->
<script
	src="<c:url value='/static/bootstrap-3.3.6-dist/js/bootstrap.min.js' />"></script>

<!-- kitten -->


<link rel="stylesheet"
	href="<c:url value='/static/image-picker/image-picker/image-picker.css' />">
<script
	src="<c:url value='/static/image-picker/image-picker/image-picker.js' />"></script>

<!-- fin kitten -->

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

	
	function editarFormularioSubmit() {

		var items = [];


		$(".campoTexto").each(function(index,data){

		  
		  
		  var item =  new Object();
		  item.label= $(data).val().trim();
		  item.idformItem=$(data).attr("id").trim();
		  if(item.label != ""){
		  items.push(item);
		  }
		  

		});
		
		 var toServer = JSON.stringify(items);
		
		$.ajax({
			
			url : "submitFormEditarFormulario.htm",
			type :"GET",
			data : "formItems="+toServer+"&maquinaId="+$("#maquinaId").html().trim(),
			success:function(response){
				
				
				$('#contenedor').empty();
				$('#contenedor').append(response);
			}
			
			
		});


		
	}
	
	
	function editarCheckList(){
		var idMaquina = $('#comboMaquinas').val();
		$.ajax({
			
			url : "formEditarFormulario.htm",
			type : "GET",
			data : "idMaquina="+idMaquina,
			success:function(data){
				
				$('#contenedor').empty();
				$('#contenedor').append(data);
				
			}
		
			
			
		});
		
		
		
	}
	
	
	function cambio() {

		var id = $('#nombreEmpleado').val();
		$.ajax({
			url : "obtenerProyectosPorEmpleado.htm",
			type : 'GET',
			data : "id=" + id,
			success : function(data) {

				data = $.parseJSON(data);
				var tagProyectos = "";
				$.each(data, function(i, item) {
					tagProyectos += "<span class='label label-primary'>"
							+ item.nombre + "</span>";

				});
				$("#contenedorAsignacion").empty();
				$("#contenedorAsignacion").append(tagProyectos);

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
				message: 'Todos los campos son obligatorios.' 
			},{
				// settings
				type: 'danger'
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

	function formCrearCheckList() {

		var idMaquina = $('#comboMaquinas').val();

		var flag = true;

		$(".check").each(function(index, item) {

			if ($(item).val() == "") {
				flag = false;
			}
		});

		if (flag) {

			$.ajax({

				url : "getTemplateFormulario.htm",
				type : "GET",
				data : "idMaquina="+idMaquina,
				success : function(response) {
					$('#contenedor').empty();
					$('#contenedor').append(response);

				}

			});
		} else {

			alert("debe completar todos los campos");

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
	<div tabindex="-1" id="content" class="bs-docs-header">
		<div class="container">
			<h1 id="titulo">Panel Administrador</h1>
			<p id="copete">cristina 2020</p>
			<div id="carbonads-container">
				<div class="carbonad">
					<div id="azcarbon"></div>
				</div>
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
						<li role="presentation">
							<button type="button" class="btn btn-default btn-sm">
								<span class=" glyphicon glyphicon-pencil" aria-hidden="true"></span>
								Cambiar Contraseña
							</button>


						</li>
						<li role="presentation"><a href="<c:url value='/logout' />"><button
									type="button" class="btn btn-default btn-sm">
									<span class="glyphicon glyphicon-off" aria-hidden="true"></span>
									Deslogearse
								</button></a></li>
					</ul></li>
				<li><a class="desplegable" href="#" title="Alquiler">Empleados</a>
					<ul class="subnavegador">
						<li role="presentation"><a href="#" title="Viviendas"
							onClick="getForm('formCrearEmpleado.htm')">Crear empleado</a></li>
						<li role="presentation"><a href="#" title="Viviendas"
							onClick="getForm('formAsignarEmpleado.htm')">Asignar empleado
						</a></li>
						<li role="presentation"><a href="#" title="Viviendas"
							onClick="getForm('formDesAsignarEmpleado.htm')">Des-asignar
								Empleado</a></li>

					</ul></li>
					
					
					<li><a class="desplegable" href="#" title="Alquiler">Clientes</a>
					<ul class="subnavegador">
						
						<li role="presentation"><a href="#" title="Viviendas"
							onClick="getForm('formAsignarCliente.htm')">Asignar cliente
						</a></li>
						<li role="presentation"><a href="#" title="Viviendas"
							onClick="getForm('formDesAsignarEmpleado.htm')">Des-asignar
								cliente</a></li>

					</ul></li>
					
					


				<li><a class="desplegable" href="#" title="Alquiler">Formularios</a>
					<ul class="subnavegador">
						<li role="presentation"><a
							onClick="formularioCrearFormulario()" href="#" title="Viviendas"
							onClick="formAsignarEmpleado()">Crear Formularios</a></li>
						<li role="presentation"><a href="#" title="Viviendas"
							onClick="getForm('templateFormulario.htm')">Crear check list
						</a></li>
						<li role="presentation"><a href="#" title="Viviendas"
							onClick="getForm('editarFormulario.htm')">Editar formulario
						</a></li>

					</ul></li>

				<li><a class="desplegable" href="#" title="Alquiler">Maquinas</a>
					<ul class="subnavegador">
						<li role="presentation"><a
							 href="#" title="Viviendas"
							onClick="getForm('formCrearMaquina.htm')">Crear Maquina</a></li>

					</ul></li>
					
					
					<li><a class="desplegable" href="#" title="Alquiler">Empresa</a>
					<ul class="subnavegador">
						<li role="presentation"><a
							 href="#" title="Viviendas"
							onClick="getForm('formCrearEmpresa.htm')">Crear Empresa</a></li>

					</ul></li>
					
					<li><a class="desplegable" href="#" title="Alquiler">Proyecto</a>
					<ul class="subnavegador">
						<li role="presentation"><a
							 href="#" title="Viviendas"
							onClick="getForm('formCrearProyecto.htm')">Crear Proyecto</a></li>

					</ul></li>
					
					
					
<!-- 					<li><a class="desplegable" href="#" title="Alquiler">EPP</a> -->
<!-- 					<ul class="subnavegador"> -->
<!-- 						<li role="presentation"><a -->
<!-- 							 href="#" title="Viviendas" -->
<!-- 							onClick="getForm('formAgregarEPP.htm')" -->
<!-- 							>Agregar EPP</a></li> -->

<!-- 					</ul></li> -->
	<li><a class="desplegable" href="#" title="Alquiler">EPP</a>
					<ul class="subnavegador">
						<li role="presentation"><a
							 href="#" title="Viviendas"
							onClick="getForm('formAgregarEPP.htm')">Agregar epp</a></li>

					</ul></li>
					



			</ul>





		</div>
		<div class="col-md-4">
			<div id="contenedor"></div>




		</div>
	</div>






</body>
</html>