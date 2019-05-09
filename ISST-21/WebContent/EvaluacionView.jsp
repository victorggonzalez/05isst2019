<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Proyecto RGPD</title>
<link rel="stylesheet" href="assets/css/main.css">
</head>
<body>

<!-- Header -->
<header id="header">
					<h1>EVALUADOR</h1>
					<nav id="nav">
						<ul>	
						<li><a href="EvaluadorServlet?email=${evaluador.email}" class="button">Inicio</a></li>
						 <li><a href="LogoutServlet" class="button">Log out</a></li>	
						</ul>
						
					</nav>
</header>



<shiro:lacksRole name="evaluador">
No tienes permiso para ver el contenido de esta página
</shiro:lacksRole>
<shiro:hasRole name="evaluador">

<!-- Main -->	
<section id="main" class="container">
	<header style="margin: 0 0 2em 0">
		<h2><b>Va a evaluar la solicitud: ${evaluacion.solicitud.titulo}</b></h2>
	</header>
	<div class="box">
	<h2><b>Resumen de la solicitud a evaluar:</b></h2>
	<h3>Datos seleccionados por el investigador en el formulario</h3>
		<c:forEach items="${evaluacion.solicitud.formulario}" var="current">
			<ul>
			<li><c:out value="${current}" /></li>
			</ul>
		</c:forEach>
	<h3>Descarga de la memoria de la solicitud</h3>	
	<form action = "ServeFileServlet">	
	<input type="hidden" name="tipoDocumento" value="memoria" />
	<input type="hidden" name="id" value="${evaluacion.solicitud.id}" />	
		<button type = "submit" class="button icon fa-download">Descargar memoria</button>

	</form>
		</div>
	<div class = "box">
	<h2><b>Realizar evaluación</b></h2>
<c:if test="${evaluacion.solicitud.estado == 4}">
			<form action="CompletarServlet" method = "post">
			<h3>Si crees que faltan datos para poder evaluar el proyecto, pulsa Completar</h3>
				<input type="hidden" name="id" value="${evaluacion.id}" />
				<button type="submit" class="button small">Completar</button>
			</form>
</c:if>


		<c:if test="${evaluacion.solicitud.estado ==5}">
		<h3>Esperando a la ampliación</h3>
		</c:if>
		<c:if test="${evaluacion.solicitud.estado != 5 and evaluacion.isResultado() == 'Sin evaluar' }" >

			<form action="AceptarServlet" method = "post">
			<h3>Si crees que el proyecto cumple el RGPD, pulsa aquí para aceptar el proyecto</h3>

				<input type="hidden" name="id" value="${evaluacion.id}" />
				<button type="submit" class="button small">Aceptar</button>

			</form>
			</c:if>

		<c:if test="${evaluacion.solicitud.estado != 5 and evaluacion.isResultado() == 'Sin evaluar' }" >

			<form action="DenegarServlet" method = "post">
			<h3>Si crees que el proyecto no cumple el RGPD, pulsa aquí para rechazar el proyecto</h3>

				<input type="hidden" name="id" value="${evaluacion.id}" />
				<button type="submit" class="button small">Rechazar</button>

			</form>
		</c:if>	
<hr>
<form action="EvaluadorServlet" method="get">
			<input type = "hidden" name = "email" value ="${evaluacion.evaluador.email}" />
			<input type = "hidden" name = "evaluaciones_list" value ="${evaluaciones_list}" />
			<c:if test="${evaluacion.solicitud.estado == 4 || evaluacion.solicitud.estado > 5}">	
				<input type = "hidden" name = "volverpendientes" value ="true" />
			</c:if>
			<c:if test="${evaluacion.solicitud.estado == 5}">	
				<input type = "hidden" name = "volverampliacion" value ="true" />
			</c:if>

			<c:if test="${evaluacion.solicitud.estado == 8 }">	
				<input type = "hidden" name = "volvercerradas" value ="true" />
			</c:if>
			<p><button type="submit" class="button alt small">Atrás</button></p>
		</form>
</div>
</section>

	<footer id="footer">
				<ul class="copyright">
					<li>&copy; Proyecto RGPD. All rights reserved.</li><li>Design: Grupo 21</li>
				</ul>
	</footer>
	
		
 </shiro:hasRole>

</body>
</html>