<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>

<script type="text/javascript">


function cambiarContraseña(){	
	
	var password = $("#password").val();
	var password1 = $("#password2").val();
	
	if(password == password1){
		
		submitForm('formCambiarContrasenia');
	}else{
		
		$.notify({
			// options
			message: 'Las contraseñas no coinciden.' 
		},{
			// settings
			type: 'danger',
		});


	}
	
	
	
	
}


</script>

</head>
<body>

	<form:form method="post" modelAttribute="cambiarContraseniaDTO"
		action="doCambiarContrasenia.htm" data-example-id="simple-input-groups"
		class="bs-example bs-example-form" id="formCambiarContrasenia">


		
		<div class="input-group">
			<span id="basic-addon1" class="input-group-addon"></span>
			<form:input path="oldPassword" type="text"
				aria-describedby="basic-addon1" placeholder="Viejo password"
				class="form-control" />

		</div>
		<br/>
		<div class="input-group">	
			<span id="basic-addon1" class="input-group-addon"></span>
			<form:input path="newPassword" type="password"
				aria-describedby="basic-addon1" placeholder="Nuevo password"
				class="form-control" id="password" />

		</div>
		<br/>		
		<div class="input-group">
			<span id="basic-addon1" class="input-group-addon"></span>
			<input path="password" type="password"
				aria-describedby="basic-addon1" placeholder="Repetir nuevo password"
				class="form-control" id="password2" />

		</div>
		
		<br/>

		<input type="button" onclick="cambiarContraseña()"
			value="Crear" class="form-control btn btn-primary" placeholder="Username"
			aria-describedby="basic-addon1">

	</form:form>







</body>
</html>