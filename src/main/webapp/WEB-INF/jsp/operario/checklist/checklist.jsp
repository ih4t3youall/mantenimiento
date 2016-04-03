<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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




<script
	src="<c:url value='/static/bootstrap-3.3.6-dist/js/bootstrap-datepicker.js'/>"></script>


<link rel="stylesheet"
	href="<c:url value='/static/bootstrap-3.3.6-dist/css/bootstrap-datepicker.css' />">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="<c:url value='/static/bootstrap-3.3.6-dist/css/bootstrap.min.css' />">


<!-- Optional theme -->
<link rel="stylesheet"
	href="<c:url value='/static/bootstrap-3.3.6-dist/css/bootstrap-theme.min.css' />">

<!-- Latest compiled and minified JavaScript -->
<script
	src="<c:url value='/static/bootstrap-3.3.6-dist/js/bootstrap.min.js' />"></script>



<!-- tabla -->
<script src="<c:url value='/static/jquery/jquery.dataTables.min.js'/>"></script>

<link rel="stylesheet"
	href="<c:url value='/static/css/jquery.dataTables.min.css' />">




<script type="text/javascript">
	$(document).ready(function() {
		$('#example').DataTable();
	});

	
	
	function acomodarFecha(){

		var fecha = $('#fechaProgramada').html();


		if(fecha != ""){
		var anio = $('#fechaProgramada').html().slice(0,4);
		var mes =  $('#fechaProgramada').html().slice(5,7);
		var dia =  $('#fechaProgramada').html().slice(8,10);


		$('#fechaProgramada').html(mes+"/"+dia+"/"+anio);
		}
	}
	
	$(document).ready(
			function() {

				$(document).ready(
						function() { // Script del Navegador
							//porque como hay integers me pisa el placeholder con un cero y en el placeholder esta el valor de cada campo
								
							
							acomodarFecha();				
							
							
							$('.form-control').each(function(index, item) {

								//para evitar que le saque el label al ultimo boton
								if (index != 7) {
									$(item).val("");
								}
							});

							$.fn.datepicker.dates['es'] = {
								days : [ "Domingo", "Lunes", "Martes",
										"Miercoles", "Jueves", "Viernes",
										"Sabado" ],
								daysShort : [ "Dom", "Lun", "Mar", "Mier",
										"Jue", "Vier", "sab" ],
								daysMin : [ "Do", "Lu", "Ma", "Mi", "Ju", "Vi",
										"Sa" ],
								months : [ "Enero", "Febrero", "Marzo",
										"Abril", "Mayo", "Junio", "Julio",
										"Agosto", "Septiembre", "Octubre",
										"Noviembre", "Diciembre" ],
								monthsShort : [ "En", "Feb", "Mar", "Abr",
										"May", "Jun", "Jul", "Aug", "Sep",
										"Oct", "Nov", "Dic" ],
								today : "Hoy",
								clear : "Clear",
								format : "dd/mm/yyyy",
								titleFormat : "MM yyyy", /* Leverages same syntax as 'format' */
								weekStart : 0,
								onClose : function() {
								}
							};

							$('.fecha').datepicker({
								language : 'es',

							});

							$('.fecha').change(function() {

								$('.datepicker').hide();

							});

						});

			});

	function submitForm() {

		var form = new Object();
		var maquina = new Object();
		var proyecto = new Object();
		var empresa = new Object();
		form.formItems = [];
		
		form.aptoServicio = $("#aptoServicio").val();
		maquina.id = parseInt($("#idMaquina").html());
		empresa.id = $("#idEmpresa").html();
		proyecto.id = $("#idProyecto").html();
		form.fechaRealizacion = $("#fechaRealizacion").val();
		form.fechaProgramada = $("#fechaProgramada").html();
		form.observaciones = $("#observaciones2").val();
		
		
		form.empresa =empresa;
		form.proyecto = proyecto;
		form.maquina = maquina;
		$(".fila").each(function(index, item) {

			var formItem = new Object();

			var combos = $(item).find("select");
			var observaciones = $(item).find("p");
			var label = $(item).find("label");

			formItem.label = $(label).html();
			formItem.posee = $(combos[0]).val();
			formItem.estado = $(combos[1]).val();
			formItem.observaciones = $(observaciones).val();

			form.formItems.push(formItem);

		});

		var sendable = JSON.stringify(form);

		$.ajax({

			url : "submitChecklist.htm",
			type : "GET",
			data : "form=" + sendable,
			success : function(data) {

				console.log("volvi");

			}

		});

	}

	var idModal = "";
	function modalObservaciones(idSecreto) {
		idModal = idSecreto;
		$("#observaciones").val("");
		$('#myModal').modal('show');

	}
	
	
	function modalPdf(){
		
		
		var idMaquina = $("#idMaquina").html();
		
		$.ajax({
			
			url :"generarPdf.htm",
			data : "idMaquina="+idMaquina,
			type : 'GET',
			success: function (response){
				

$("#modalPdf").empty();
$("#modalPdf").append(response);
var wWidth = $(window).width();
$("#pdfObject").width(wWidth-200);
				
			}
			
			
			
		});
		
		
		$('#modalPdf').modal('show');
		
	}
	
	

	function cerrarModalObservaciones(nose) {
		var observaciones = $("#observaciones").val();
		$("#" + idModal).val(observaciones);
		$('#myModal').modal('hide');

	}
</script>

<style type="text/css">
.borde {
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
}
</style>

</head>
<body>


	<!-- Modal -->
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Ingrese la observacion</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<textarea class="form-control" rows="5" id="observaciones"></textarea>
						<button class="btn btn-secondary" type="button"
							onClick="cerrarModalObservaciones()">Aceptar</button>
					</div>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>
<!-- MODAL PDF -->

<div class="modal fade" id="modalPdf" role="dialog">
	</div>
	
<!-- 	FIN MODAL PDF  -->


	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-3">
			<img width="150px" src="data:image/jpeg;base64,${empresa.urlImagen}" />
			<span id="idEmpresa" style="display:none;" >${empresa.id}</span>
		</div>
		<div class="col-md-3">
			<p>CHECK LIST DE MANTENIMIENTO PREVENTIVO ${maquina.nombre}, maquina id <span id="idMaquina">${maquina.id} </span>, proyecto id <span id="idProyecto">${proyecto.id}</span></p>

		</div>
		<div class="col-md-2">
			<img width="150px"
				src="<c:url value='/static/imagenes/logo/imasd.png' />" />

		</div>
		<div class="col-md-2">
			<p>aca va una fecha no se de que</p>
			<p>R:24.00 -- este numero tampoco se que es</p>

		</div>
		<div class="col-md-1"></div>
	</div>

	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-10">
			<p>Equipo: ${maquina.nombre}</p>
			<hr>
		</div>
		<div class="col-md-1"></div>

	</div>
	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-2">
			<label>Fecha realizacion:</label>
		</div>
		<div class="col-md-2">
			<input type="text" aria-describedby="basic-addon1"
				placeholder="Fecha realizacion" id="fechaRealizacion"
				class="form-control fecha" />
		</div>
		<div class="col-md-2">
			<label>Fecha Programada: </label>
		</div>
		<div class="col-md-2">
			<!-- FIXME -->
			<label id="fechaProgramada">${form.fechaProgramada}</label>
		</div>
		<div class="col-md-1"></div>
	</div>

	<!-- 	separador -->
	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-10">
			<hr>
		</div>
		<div class="col-md-1"></div>
	</div>

	<!-- 	fin separador -->
	
<!-- 	modal mostrar pdf -->
	
	<div class="row">
		<div class="col-md-1"></div>


		<div class="col-md-3"></div>
		<div class="col-md-4">
			<button class="btn btn-secondary btn-lg btn-block" type="button"
				onclick="modalPdf()">Mostrar Instrucciones</button>


		</div>

		<div class="col-md-2"></div>


		<div class="col-md-1"></div>



	</div>
<!-- fin modal mostrar pdf -->

	<!-- 	separador -->
	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-10">
			<hr>
		</div>
		<div class="col-md-1"></div>
	</div>

	<!-- 	fin separador -->
	
	
	


	<div class="row">
		<div class="col-md-1"></div>


		<div class="col-md-3">
			<label>Numero de interno</label>

		</div>
		<div class="col-md-2">aca se escribe nro interno</div>

		<div class="col-md-3">
			<label>Numero de Orden</label>
		</div>
		<div class="col-md-2">
			<label>aca se escribe el nuemro de orden</label>
		</div>





		<div class="col-md-1"></div>



	</div>
	<!-- 	separador -->
	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-10">
			<hr>
		</div>
		<div class="col-md-1"></div>
	</div>

	<!-- 	fin separador -->

	<div class="row">

		<div class="col-md-1"></div>

		<div class="col-md-2">
			<label>EPP Obligatorio durante el mantenimiento</label>

		</div>


		<div class="col-md-8">

			<c:forEach items="${eppObligatorio}" var="epp">


				<img src="data:image/jpeg;base64,${epp.imagen}" value="${epp.idEpp}" />

			</c:forEach>
		</div>

		<div class="col-md-1"></div>



	</div>
	<!-- 	separador -->
	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-10">
			<hr>
		</div>
		<div class="col-md-1"></div>
	</div>

	<!-- 	fin separador -->

	<div class="row">

		<div class="col-md-1"></div>

		<div class="col-md-2">
			<label>EPP adicionales durante el mantenimiento</label>

		</div>


		<div class="col-md-8">

			<c:forEach items="${eppOpcional}" var="epp">


				<img src="data:image/jpeg;base64,${epp.imagen}" value="${epp.idEpp}" />

			</c:forEach>
		</div>

		<div class="col-md-1"></div>



	</div>

	<!-- 	separador -->
	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-10">
			<hr>
		</div>
		<div class="col-md-1"></div>
	</div>

	<!-- 	fin separador -->
	<!-- aca arranca la tabla -->
	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-10">

			<table width="100%" cellspacing="0" class="display dataTable"
				id="example" role="grid" aria-describedby="example_info"
				style="width: 100%;">
				<thead>
					<tr role="row">
						<th class="sorting_asc" tabindex="0" aria-controls="example"
							rowspan="1" colspan="1" style="width: 136px;"
							aria-sort="ascending"
							aria-label="Name: activate to sort column descending">Condiciones
							Generales</th>
						<th class="sorting" tabindex="0" aria-controls="example"
							rowspan="1" colspan="1" style="width: 218px;"
							aria-label="Position: activate to sort column ascending">Posee</th>
						<th class="sorting" tabindex="0" aria-controls="example"
							rowspan="1" colspan="1" style="width: 102px;"
							aria-label="Office: activate to sort column ascending">Estado</th>
						<th class="sorting" tabindex="0" aria-controls="example"
							rowspan="1" colspan="1" style="width: 43px;"
							aria-label="Age: activate to sort column ascending">Observaciones</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th rowspan="1" colspan="1">Condiciones Generales</th>
						<th rowspan="1" colspan="1">Posee</th>
						<th rowspan="1" colspan="1">Estado</th>
						<th rowspan="1" colspan="1">Observaciones</th>
					</tr>
				</tfoot>
				<tbody>


					<c:forEach items="${form.formItems}" var="item">

						<tr role="row" class="odd fila">
							<td class="sorting_1"><label>${item.label}</label></td>
							<td><select>
									<option>si</option>
									<option>no</option>
									<option>N/C</option>

							</select></td>
							<td><select>
									<option>B</option>
									<option>M</option>
									<option>N/C</option>
							</select></td>
							<td>
								<button class="btn btn-secondary" type="button"
									onClick="modalObservaciones('${item.label}secret')">Observaciones</button>
								<p id="${item.label}secret" style="display: none;"></p>

							</td>
						</tr>


					</c:forEach>




				</tbody>
			</table>
		</div>



		<div class="col-md-1"></div>

	</div>
	<!-- 	separador -->
	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-10">
			<hr>
		</div>
		<div class="col-md-1"></div>
	</div>

	<!-- 	fin separador -->
	<div class="row">

		<div class="col-md-1"></div>
		<div class="col-md-10">
			<label>Observaciones: </label>
			<textarea class="form-control" rows="5" id="observaciones2"></textarea>

		</div>


		<div class="col-md-1"></div>

	</div>

	<!-- 	separador -->
	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-10">
			<hr>
		</div>
		<div class="col-md-1"></div>
	</div>

	<!-- 	fin separador -->
	<div class="row">

		<div class="col-md-1"></div>

		<div class="col-md-10">

			<label>NOTA : Cada vez que en la lista de verificacion se
				genere un NO, esto debe ser acompañado de su correspondiente NO
				CONFORMIDAD.</label>
		</div>

		<div class="col-md-1"></div>

	</div>
	<!-- 	separador -->
	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-10">
			<hr>
		</div>
		<div class="col-md-1"></div>
	</div>

	<!-- 	fin separador -->

	<div class="row">
		<div class="col-md-1"></div>


		<div class="col-md-4">
			<label>CONCLUCION:</label>


		</div>
		<div class="col-md-4">
			<label>APTO PARA SERVICIO </label>


		</div>

		<div class="col-md-2">

			<select id="aptoServicio">
				<option value="true">si</option>
				<option value="false">no</option>

			</select>

		</div>


		<div class="col-md-1"></div>



	</div>

	<!-- 	boton enviar -->

	<div class="row">
		<div class="col-md-1"></div>


		<div class="col-md-3"></div>
		<div class="col-md-4">
			<button class="btn btn-secondary btn-lg btn-block" type="button"
				onclick="submitForm()">SUBMIT</button>


		</div>

		<div class="col-md-2"></div>


		<div class="col-md-1"></div>



	</div>


	<!-- 	separador -->
	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-10">
			<hr>
		</div>
		<div class="col-md-1"></div>
	</div>

	<!-- 	fin separador -->


	<a href="inicio.htm"><input type="button" value="volver" /></a>

</body>
</html>