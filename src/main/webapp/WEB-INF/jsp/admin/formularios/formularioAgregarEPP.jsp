
 

<form action="subirArchivo.htm?${_csrf.parameterName}=${_csrf.token}" method="POST" name="frmAlta" id="frmAlta"  class="frmABM" target="" enctype="multipart/form-data"> 
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="file">Subir Archivo</label>
					<div class="col-md-7">
					    <div class="input-group">
			            	<span id="basic-addon1" class="input-group-addon"></span>
							<input type="file" id="file" name="file" class="form-control input-sm" aria-describedby="basic-addon1"/>
						</div>
						<br>
						<div class="input-group">
			            	<span id="basic-addon1" class="input-group-addon"></span>
							<input type="text" id="nombre" name="nombre" class="form-control input-sm" placeholder="Nombre" aria-describedby="basic-addon1"/>
						</div>
						<br>
						<div class="input-group">
			            	<span id="basic-addon1" class="input-group-addon"></span>
							<input type="text" id="descripcion" name="descripcion" class="form-control input-sm" placeholder="Descripcion" aria-describedby="basic-addon1"/>
						</div>
						<div class="has-error">
						</div>
					</div>
				</div>
	       	</div>
	
			<div class="row">
				<div class="form-actions floatRight">
					<input type="submit" value="Subir" class="btn btn-primary btn-sm">
				</div>
			</div>
		</form>
