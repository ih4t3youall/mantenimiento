<script type="text/javascript">
function submitImagen(){
	var flag = true;
	$(".validar").each(function(index,item){
		
		if($(item).val() ==""){
			
			flag = false;
		}
		
		
	});
	
	if(flag){
		
		$("#frmAlta").submit();
		
	}else {
		
		alert("debe completar todos los campos.");
		
	}
	
	
}


</script>
	<div class="form-container">


		<form action="subirArchivo.htm?${_csrf.parameterName}=${_csrf.token}"
			method="POST" name="frmAlta" id="frmAlta" class="frmABM" target=""
			enctype="multipart/form-data">



			<div class="row">
				<label class="col-md-3 col-md-offset-3 control-lable" for="file">Subir foto</label>
				<div class="col-md-7">
					<input type="file" id="file" name="file" class="form-control input-sm validar col-md-offset-3" /> 
					<label>Nombre:</label>
					<input 	type="text" id="nombre" name="nombre" class="form-control validar col-md-offset-3" />
					<label>Descripcion:</label> 
						<input type="text" id="descripcion" name="descripcion"	class="form-control validar col-md-offset-3" />
					<div class="has-error"></div>
				</div>
			
				</div>
					<div class="row">
					<div class="col-md-3 col-md-offset-2 form-actions floatRight">
						<input type="button" value="subir imagen"  onclick="submitImagen()" class="btn btn-primary btn-sm">
					</div>
			</div>

		</form>
	</div>
