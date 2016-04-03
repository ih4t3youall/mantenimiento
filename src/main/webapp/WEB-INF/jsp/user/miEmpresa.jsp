<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="<c:url value='/static/bootstrap-3.3.6-dist/css/bootstrap.min.css' />">


<!-- Optional theme -->
<link rel="stylesheet"
	href="<c:url value='/static/bootstrap-3.3.6-dist/css/bootstrap-theme.min.css' />">

<!-- Latest compiled and minified JavaScript -->
<script
	src="<c:url value='/static/bootstrap-3.3.6-dist/js/bootstrap.min.js' />"></script>



<!-- owl -->
<link rel="stylesheet"
	href="<c:url value='/static/owl-carousel/owl-carousel/owl.carousel.css' />">
<link rel="stylesheet"
	href="<c:url value='/static/owl-carousel/owl-carousel/owl.theme.css'/>">


<script
	src="<c:url value='/static/owl-carousel/owl-carousel/owl.carousel.js'/>"></script>
<!-- end owl -->


<script type="text/javascript">
	
</script>

<style type="text/css">
</style>

</head>
<body>


	<div class="container">
		<h1 id="titulo">Mi empresa</h1>
		<p id="copete">${empresa.nombre}</p>
		<div id="carbonads-container">
			<div class="carbonad">
				<div id="azcarbon"></div>
			</div>
		</div>
	</div>

<img height="200px" width="200px" src="${empresa.urlImagen}" />



</body>
</html>