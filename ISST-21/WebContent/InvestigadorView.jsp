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
							<li><a href="LogoutServlet" class="button">Log Out</a></li>
						</ul>
					</nav>
				</header>
<shiro:user>
    Welcome back <shiro:principal/>! Click <a href="LogoutServlet">here</a> to logout.
</shiro:user>


<hr>
	<shiro:lacksRole name="investigador">
	No tienes permiso para ver el contenido de esta página
	</shiro:lacksRole>
	<shiro:hasRole name="investigador">
	<h2><b>&nbsp;¡Bienvenido <shiro:principal/>!</b></h2>
	
	<h3>&nbsp;Información de tus solicitudes:<br><br></h3>

		<table border="1">
			<tr>
				<th><h4><b>Título</b></h4></th>
				<th><h4><b>id</b></h4></th>
				<th><h4><b>Estado</b></h4></th>
				
				<th><h4><b>Formulario</b></h4></th>
				<th><h4><b>Memoria</b></h4></th>
				<th><h4><b>Ampliación</b></h4></th>

				<th><h4><b>Enviado</b></h4></th>
				<th><h4><b>Faltan datos</b></h4></th>
				<th><h4><b>Ver solicitud</b></h4></th>
			</tr>
				<c:forEach items="${solicitudes_list}" var="solicitudi">
				<tr>
					
					<td>${solicitudi.titulo }</td>
					<td>${solicitudi.id }</td>
					<td>${solicitudi.estado}</td>
					<td><c:if test="${solicitudi.estado > 1}">
						Formulario relleno
						</c:if>
					</td>
					<td><c:if test="${solicitudi.estado > 2}">
						<form action="ServeFileServlet">
						<input type="hidden" name="id" value="${solicitudi.id}" />
						<input type="hidden" name="tipoDocumento" value="memoria" />
						<button type="submit" class="button small">Descargar memoria</button>
						</form>
						</c:if>
					</td>
					<td><c:if test="${solicitudi.ampliacion != null}">
						<form action="ServeFileServlet">
						<input type="hidden" name="id" value="${solicitudi.id}" />
						<input type="hidden" name="tipoDocumento" value="ampliacion" />
						<button type="submit" class="button small">Descargar ampliación</button>
						</form>

					</c:if></td>
					<td>
						<c:if test="${solicitudi.estado > 3}"> Si </c:if>
						<c:if test="${solicitudi.estado < 4}"> No </c:if>
					</td>
					<td>
						<c:if test="${solicitudi.estado == 5}"> ${solicitudi.faltanDatos} </c:if>
						<c:if test="${solicitudi.estado < 4}"> No </c:if>
					</td>
					
					<td>
						<form action="SolicitarServlet" method="get">
						<input type="hidden" name="id" value="${solicitudi.id}" />
						<input type="hidden" name="solicitudes_list" value="${solicitudes_list}" />
						<input type="hidden" name="email" value="${investigador.email}" />
						<button type="submit" class="button small">Ver</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<h3><b>&nbsp;Crear una nueva solicitud</b></h3>
			<form action="SolicitarServlet" method="post">
				<input type="hidden" name="emailInvestigador" value="${investigador.email}" />
				<h3>&nbsp;Título: <input type="text" name="titulo" /></h3>
				<button type="submit" class="button">Crear solicitud</button>
			</form>
	
	
	
	
	</shiro:hasRole>

</html>