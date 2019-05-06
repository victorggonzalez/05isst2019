<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Evaluador View</title>
<link rel="stylesheet" href="assets/css/main.css">
</head>
<body>
<shiro:lacksRole name="evaluador">
	No tienes permiso para ver el contenido de esta página
</shiro:lacksRole>

<shiro:hasRole name="evaluador">
	
<header id="header">
		<h1>EVALUADOR</h1>
		<nav id="nav">
		<ul>
			<li><a href="EvaluadorServlet?email=${evaluador.email}" class="button">Inicio</a></li>
			<li><a href="LogoutServlet" class="button">Log out</a></li>
		</ul>
		</nav>
</header>



<section id="main" class="container medium">
	<header style="margin: 0 0 2em 0">
		<h2><b>Información de tus evaluaciones pendientes</b></h2>
	</header>
	<div class="box">
		<c:if test="${empty evaluaciones_pendientes}">
			<h3><b><center>No hay elementos para mostrar</center></b></h3>
		</c:if>
		<c:if test="${!empty evaluaciones_pendientes}">
		<table border="1">
			<tr>
				<th><h4><b>Título</b></h4></th>
				<th><h4><b>id</b></h4></th>
				<th><h4><b>Estado</b></h4></th>
				<th><h4><b>Formulario</b></h4></th>
				<th><h4><b>Memoria</b></h4></th>
				<th><h4><b>Ampliación</b></h4></th>
				<th><h4><b>Tu valoración</b></h4></th>
				<th><h4><b></b></h4></th>
			</tr>
				<c:forEach items="${evaluaciones_pendientes}" var="evaluacioni">
				<tr>
					
					
					<td>${evaluacioni.solicitud.titulo }</td>
					<td>${evaluacioni.solicitud.id }</td>
					<td><c:if test="${evaluacioni.solicitud.estado < 7}">
						En evaluación
						</c:if>
						<c:if test="${evaluacioni.solicitud.estado  > 6}">
						${evaluacioni.isResultado()}
						</c:if>
					</td>
					<td><c:if test="${evaluacioni.solicitud.estado > 2}">
						Formulario relleno
						</c:if>
					</td>
					<td><c:if test="${evaluacioni.solicitud.estado > 3}">
						<form action="ServeFileServlet">

						<input type="hidden" name="id" value="${evaluacioni.solicitud.id}" />
						<input type="hidden" name="tipoDocumento" value="memoria" />
						<button type="submit" class="button icon fa-download">Descargar</button>

						</form>
						</c:if>
					</td>
					<td><c:if test="${evaluacioni.solicitud.estado < 5}"> Ampliación no requerida </c:if>
						<c:if test="${evaluacioni.solicitud.estado == 5}"> 
						Esperando ampliación
						</c:if>
						<c:if test="${evaluacioni.solicitud.estado > 5 && evaluacioni.solicitud.ampliacion != null}"> 
						<form action="ServeFileServlet">
						<input type="hidden" name="id" value="${evaluacioni.solicitud.id}" />
						<input type="hidden" name="tipoDocumento" value="ampliacion" />
						<button type="submit" class="button icon fa-download">Descargar ampliación</button>
						</form>
						</c:if>
						<c:if test="${evaluacioni.solicitud.estado > 5 && evaluacioni.solicitud.ampliacion == null}"> 
						 Ampliación no requerida </c:if>
					</td>
					
					<td>${evaluacioni.isResultado()}</td>
					
					<td><c:if test="${evaluacioni.isResultado() == 'Sin evaluar'}">
						<form action="EvaluarServlet" method="post">
						<input type="hidden" name="id" value="${evaluacioni.id}" />
						<input type="hidden" name="email" value="${evaluador.email}" />
						<button type="submit" class="button small">Evaluar solicitud</button>
						</form>
						</c:if>

						<c:if test="${evaluacioni.isResultado() != 'Sin evaluar'}">Ya evaluado
						</c:if>
					</td>
						
				</tr>
			</c:forEach>
		</tbody>
		</table>
		</c:if>
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
