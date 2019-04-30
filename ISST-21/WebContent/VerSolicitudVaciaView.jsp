<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Investigador View</title>
<link rel="stylesheet" href="assets/css/main.css">
</head>
<body>
<header id="header">
		<h1>INVESTIGADOR</h1>
		<nav id="nav">
		<ul>
		<li><a href="InvestigadorServlet?email=${investigador.email}" class="button">Inicio</a></li>
			<li><a href="LogoutServlet" class="button">Log out</a></li>
		</ul>
		</nav>
</header>
<shiro:user>

</shiro:user>


	<shiro:lacksRole name="investigador">
	No tienes permiso para ver el contenido de esta página
	</shiro:lacksRole>
	<shiro:hasRole name="investigador">
	<!-- Main -->
	<section id="main" class="container">
	<header     style="margin: 0 0 2em 0">
	<h2><b>Información de tus solicitudes incompletas</b></h2>
	</header>
	<div class="box">
	
		<table border="1">
			<tr>
				<th><h4><b>Título</b></h4></th>
				<th><h4><b>id</b></h4></th>
				<th><h4><b>Estado</b></h4></th>
				<th><h4><b>Formulario</b></h4></th>
				<th><h4><b>Memoria</b></h4></th>
				<th><h4><b></b></h4></th>
			</tr>
				<c:forEach items="${solicitudes_vacias}" var="solicitudi">
				<tr>
					
					<td>${solicitudi.titulo }</td>
					<td>${solicitudi.id }</td>
					<td><c:if test="${solicitudi.estado == 1}">
						Solicitud vacía
						</c:if>
						<c:if test="${solicitudi.estado < 3 && solicitudi.estado > 1}">
						Solicitud incompleta
						</c:if>
						<c:if test="${solicitudi.estado == 3}">
						Lista para enviar
						</c:if>
						
					</td>
					<td>
					<c:if test="${solicitudi.estado == 1}">
						Formulario vacío
						</c:if>
					<c:if test="${solicitudi.estado > 1}">
						Formulario relleno
						</c:if>
					</td>
					<td>
					<c:if test="${solicitudi.estado < 3}">
					Memoria vacía
					</c:if>
					<c:if test="${solicitudi.estado > 2}">
						<form action="ServeFileServlet">
							<input type="hidden" name="id" value="${solicitudi.id}" />
							<input type="hidden" name="tipoDocumento" value="memoria" />
							<button type="submit" class="button icon fa-download">Descargar memoria</button>
						</form>
						</c:if>
					</td>

					
					
					
					<td><c:if test="${solicitudi.estado < 8}"> 
						<form action="SolicitarServlet" method="get">
							<input type="hidden" name="id" value="${solicitudi.id}" />
							<input type="hidden" name="solicitudes_list" value="${solicitudes_list}" />
							<input type="hidden" name="email" value="${investigador.email}" />
							<button type="submit" class="button small">Completar solicitud</button>
						</form>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
<hr>	

		
		
	</div>
	</section>
<!-- Footer -->
	<footer id="footer">
				<ul class="copyright">
					<li>&copy; Proyecto RGPD. All rights reserved.</li><li>Design: Grupo 21</li>
				</ul>
	</footer>
	
	

</shiro:hasRole>
</body>
</html>