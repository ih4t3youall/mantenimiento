<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script
	src="<c:url value='/static/bootstrap-3.3.6-dist/js/bootstrap-datepicker.js'/>"></script>


<link rel="stylesheet"
	href="<c:url value='/static/bootstrap-3.3.6-dist/css/bootstrap-datepicker.css' />">

<html>
<head>


<script type="text/javascript">
	//FIXME
	
	
	
		$(document).ready(
					function() { // Script del Navegador
					//porque como hay integers me pisa el placeholder con un cero y en el placeholder esta el valor de cada campo
						
						$.fn.datepicker.dates['es'] = {
							    days: ["Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"],
							    daysShort: ["Dom", "Lun", "Mar", "Mier", "Jue", "Vier", "sab"],
							    daysMin: ["Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa"],
							    months: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
							    monthsShort: ["En", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dic"],
							    today: "Hoy",
							    clear: "Clear",
							    //FIXME VOLVE A dd/mm/yyyy
							    format: "MM/dd/yyyy",
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
	
	
	
	$(document)
			.ready(
					function() {
						$("#closeModal")
								.click(
										function() {
											//$("option")
											var seleccion = [];
											$('#multiple :selected').each(	function(i, selected) {
												seleccion[i] = $(selected)	.val();
													});
											$(seleccion).each(function(index,item) {
																if ($("#check").is(':checked')) {
																	$("#contenedor").append("<input style='display: none;' class='eppOpcional' value='"+item+"'>");
																} else {
																	$("#contenedor").append("<input style='display: none;' class='eppObligatorio' value='"+item+"'>");
																}
															});
										});
					});
	function agregarCampo() {
		var campo = '<div class="form-group"> 		<label for="usr">Ingrese Nombre Input checkbox:</label> <input type="text" class="form-control customInput textos"	id="0">	</div>';
		$('#campos').append(campo);
	}
	function doSubmit() {
		var enviar = [];
		$(".textos").each(function(index, item) {
			var aux = new Object();
			aux.label = $(item).val();
			aux.idformItem = $(item).attr("id");
// FIXME esto pregunta por algo que obviamente existe
			if (aux != "") {
				enviar.push(aux);
			}
		});
		if (enviar.length > 0) {
			var maquina = $("#idMaquina").html();
			var eppOpcional = [];
			var eppObligatorio = [];
			$(".eppOpcional").each(function(index, item) {
				eppOpcional.push($(item).val());
			});
			$(".eppObligatorio").each(function(index, item) {
				eppObligatorio.push($(item).val());
			});
		  
			var fechaProgramada = $("#fechaProgramada").val();
		  
		var send = JSON.stringify(enviar);
		 
		  
		  
			$.ajax({
				url : "submitTemplateFormulario.htm",
				type : "GET",
				data : "camposFormulario=" + send + "&idMaquina=" + maquina
						+ "&eppOpcional=" + eppOpcional + "&eppObligatorio="
						+ eppObligatorio + "&fechaProgramada="
						+ fechaProgramada,
				success : function(response) {
					getForm('templateFormulario.htm');
				},
				error : function(error) {
					alert("Ocurrio un error");
				}
			});
		}
	}
	function asignarEPP() {
	}
	
	function uploadFormData(){
	    $('#result').html('');
	 
	  var oMyForm = new FormData();
	  oMyForm.append("file", file2.files[0]);
	 
	  $.ajax({
	    url: 'rest/cont/upload',
	    data: oMyForm,
	    dataType: 'text',
	    processData: false,
	    contentType: false,
	    type: 'POST',
	    success: function(data){
	      $('#result').html(data);
	    }
	  });
	}
	function modalEPP() {
		$.ajax({
			url : "eppModal.htm",
			type : "GET",
			success : function(data) {
				$('#modalEPP').modal('show');
				$(".modal-EPP-body").empty();
				$(".modal-EPP-body").append(data);
			}
		});
	}
</script>
</head>
<body>


	<!-- Modal  EPP-->
	<div class="modal fade" id="modalEPP" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-EPP-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-EPP-title"></h4>
				</div>
				<div class="modal-EPP-body"></div>
				<div class="modal-EPP-footer">
					<br />
					<hr>
					<button type="button" class="btn btn-default" id="closeModal"
						data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>


<!--  Form 2 -->
<h1> hola</h1>
<i>Uploading File With Ajax</i><br/>
<form id="form2" method="post" action="/spring-mvc-file-upload/rest/cont/upload" enctype="multipart/form-data">
  <!-- File input -->    
  <input name="file2" id="file2" type="file" /><br/>
</form>
 

<button value="Submit" onclick="uploadFormData()" >Upload</button><i>Using FormData Object</i>
 
<div id="result"></div>
</body>
</html>


	<h3>Crear nuevo checklist</h3>
	<label id="idMaquina">${idMaquina}</label>
	
		<div class="form-group">
			<label for="usr">Fecha programada:</label> <input
				type="text" class="form-control customInput  fecha" id="fechaProgramada">
		</div>
	
	
	
	<div id="campos">
		<div class="form-group">
			<label for="usr">Ingrese Nombre Input checkbox:</label> <input
				type="text" class="form-control customInput camposFormulario" id="usr">
		</div>


	</div>
	<button type="button" onclick="agregarCampo()"
		class="btn btn-default btn-sm">
		<span class=" glyphicon glyphicon-pencil" aria-hidden="true"></span> +
	</button>

	<button type="button" onclick="modalEPP()"
		class="btn btn-default btn-sm">
		<span class=" glyphicon glyphicon-pencil" aria-hidden="true"></span>
		agregar EPP
	</button>




	<input type="button" id="boton" onclick="doSubmit()"
		value="Crear Template" class="form-control"
		aria-describedby="basic-addon1">






</body>
</html>