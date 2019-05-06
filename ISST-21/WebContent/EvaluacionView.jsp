<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Evaluacion</title>
<link rel="stylesheet" href="assets/css/main.css">
</head>
<body>
<shiro:user>
	<header id="header">
					<h1>EVALUADOR</h1>
					<nav id="nav">
						<ul>	

						
						 <li>
						 	<form action="EvaluadorServlet" method="get">
								<input type = "hidden" name = "email" value ="${evaluacion.evaluador.email}" />	
								<input type = "hidden" name = "solicitudes_list" value ="${solicitudes_list}" />
								<button type="submit" class="button">Back</button>
							</form>	
						 </li>
						 <li><a href="LogoutServlet" class="button">Log out</a></li>	
						</ul>
						
					</nav>
				</header>
</shiro:user>				
<shiro:user>
     Pulsa <a href="LogoutServlet">aqui</a> para salir.
</shiro:user>



<shiro:lacksRole name="evaluador">
No tienes permiso para ver el contenido de esta página
</shiro:lacksRole>
<shiro:hasRole name="evaluador">

<section id="main" class="container medium">
	<header style="margin: 0 0 2em 0">
		<h2>Resumen de la solicitud</h2>
	</header>
	<div class="box">
<table border="1">
			<tr>
				<th><h2>Datos seleccionados en el formulario</h2></th>
			</tr>
			<tr>
			<td>
				<c:forEach items="${evaluacion.solicitud.formulario}" var="current">
					<p><c:out value="${current}" /></p>
				</c:forEach>
			</td>
			</tr>
</table>
<h2>Descarga de la memoria</h2>
	
	<form action = "DescargarMemoriaServlet" method = "post">	
		<center>	
		<button type = "submit" class="button icon fa-download">Descargar Memoria</button>
		</center>
	</form>
		
<tr>
	<td>
		<p></p><c:if test="${evaluacion.solicitud.estado == 4}">
			<form action="CompletarServlet" method = "post">
			Si crees que faltan datos para poder evaluar el proyecto, pulsa aquí
			<p></p>
			<center>
				<input type="hidden" name="id" value="${evaluacion.id}" />
				<button type="submit" class="button small">Completar</button>
			</center>
			</form>
		</c:if>
	</td>
	<td>
		
	</td>

	
	<td><h2>Realizar evaluación</h2>
		<c:if test="${evaluacion.solicitud.estado ==5}">
			<p>Esperando a la ampliación</p>
		</c:if>
		<c:if test="${evaluacion.solicitud.estado != 5 and evaluacion.isResultado() == 'Sin evaluar' }" >
			<p></p>
			<form action="AceptarServlet" method = "post">
			Si crees que el proyecto cumple el RGPD, pulsa aquí para aceptar el proyecto
			<p></p>
			<center>
				<input type="hidden" name="id" value="${evaluacion.id}" />
				<button type="submit" class="button small">Aceptar</button>
			</center>
			</form>
			</c:if>
	</td>
		<td>
		<c:if test="${evaluacion.solicitud.estado != 5 and evaluacion.isResultado() == 'Sin evaluar' }" >
			<p></p>
			<form action="DenegarServlet" method = "post">
			Si crees que el proyecto no cumple el RGPD, pulsa aquí para rechazar el proyecto
			<p></p>
			<center>
				<input type="hidden" name="id" value="${evaluacion.id}" />
				<button type="submit" class="button small">Rechazar</button>
			</center>
			</form>
		</c:if>	
	</td>
</tr>

</div>
</section>
		
 </shiro:hasRole>

</body>
</html>