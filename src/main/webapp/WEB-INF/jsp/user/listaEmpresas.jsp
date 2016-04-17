<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<div class="col-md-4">
			<c:forEach items="${columnaA}" var="empresa">

				<div style="cursor: pointer; cursor: hand"
					class="panel panel-primary">
					<div class="panel-heading">${empresa.nombre}</div>
					<div class="panel-body">
						<img onclick="submit('${empresa.id}')" height="125px" width="200px" alt=""
							src="data:image/jpeg;base64,${empresa.urlImagen}">
					</div>
				</div>

			</c:forEach>



		</div>

		<div class="col-md-4">
			<c:forEach items="${columnaB}" var="empresa">

				<div style="cursor: pointer; cursor: hand"
					class="panel panel-primary">
					<div class="panel-heading">${empresa.nombre}</div>
					<div class="panel-body">
						<img onclick="submit('${empresa.id}')"
							height="125px" width="200px" alt="" src="${empresa.urlImagen}">
					</div>
				</div>

			</c:forEach>



		</div>