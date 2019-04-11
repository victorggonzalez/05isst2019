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
</head>
<body>
<shiro:user>
    Welcome back <shiro:principal />! Click <a href="LogoutServlet">here</a> to logout.
</shiro:user>


<hr>
	<shiro:lacksRole name="investigador">
	No tienes permiso para ver el contenido de esta página
	</shiro:lacksRole>
	<shiro:hasRole name="investigador">
	<h2>¡Bienvenido investigador!</h2>
	
	<h3>Información de tus solicitudes</h3>

		<table border="1">
			<tr>
				<th>Título</th>
				<th>id</th>
				<th>Estado</th>
				
				<th>Formulario</th>
				<th>Memoria</th>
				<th>Ampliación</th>
				<th>Enviado</th>
				<th>Ver solicitud</th>
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
						<button type="submit">Descargar memoria</button>
						</form>
						</c:if>
					</td>
					<td><c:if test="${solicitudi.ampliacion != null}">
						<form action="ServeFileServlet">
						<input type="hidden" name="id" value="${solicitudi.id}" />
						<input type="hidden" name="tipoDocumento" value="ampliacion" />
						<button type="submit">Descargar ampliación</button>
						</form>
					</c:if></td>
					<td>
						<c:if test="${solicitudi.estado > 3}"> Si </c:if>
						<c:if test="${solicitudi.estado < 4}"> No </c:if>
					</td>
					
					<td>
						<form action="SolicitarServlet" method="get">
						<input type="hidden" name="id" value="${solicitudi.id}" />
						<input type="hidden" name="solicitudes_list" value="${solicitudes_list}" />
						<input type="hidden" name="email" value="${investigador.email}" />
						<button type="submit">Ver</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<h3>Crear una nueva solicitud</h3>
			<form action="SolicitarServlet" method="post">
				<input type="hidden" name="emailInvestigador" value="${investigador.email}" />
				<p>Título: <input type="text" name="titulo" /></p>
				<button type="submit">Crear solicitud</button>
			</form>
	
	
	
	
	</shiro:hasRole>

</html>