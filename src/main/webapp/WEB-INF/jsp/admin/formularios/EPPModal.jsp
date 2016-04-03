<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	$(document).ready(function() {

		$("select").imagepicker({
			hide_select : true,
			show_label : true,
		});

	});
</script>
<div class="form-container">


	<select class="image-picker limit_callback show-html imagenes"
		id="multiple" multiple="multiple">

		<c:forEach items="${epps}" var="epp">


			<option  data-img-src="data:image/jpeg;base64,${epp.imagen}"
				value="${epp.idEpp}">${epp.nombre}</option>

		</c:forEach>
	</select>


	<div class="col-lg-6">
		<div class="input-group">
			<span class="input-group-addon"> <input type="checkbox"
				id="check" aria-label="Checkbox for following text input">
			</span> <input type="text" disabled="true"
				aria-label="Text input with checkbox" value="opcional"
				class="form-control">
		</div>
	</div>
</div>
