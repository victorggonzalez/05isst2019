<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Evaluacion</title>
</head>
<body>
<shiro:user>
    Welcome back <shiro:principal />! Click <a href="LogoutServlet">here</a> to logout.
</shiro:user>

<shiro:lacksRole name="evaluador">
No tienes permiso para ver el contenido de esta página
</shiro:lacksRole>
<shiro:hasRole name="evaluador">

<h2>Resumen de la solicitud</h2>
<h3>Formulario de datos a tratar</h3>

	<c:forEach items="${evaluacion.solicitud.formulario}" var="current">
		<p><tr><c:out value="${current}" /></tr></p>
	</c:forEach>

<h3>Descarga de la memoria</h3>
	<form action = "DescargarMemoriaServlet" method = "post">		
		<button type = "submit">Descargar Memoria</button>
	</form>
		
<tr>
	<td>
		<c:if test="${evaluacion.solicitud.estado != 6}">
			<p>Aquí puedes indicar que faltan datos en el proyecto</p>
			<form action="CompletarServlet" method = "post">
				<input type="hidden" name="id" value="${evaluacion.id}" />
				<p><button type="submit">Completar</button></p>
			</form>
		</c:if>
	</td>
	<td>
		<c:if test="${evaluacion.solicitud.estado != 5}">
			<p>Aquí puedes aceptar el proyecto</p>
			<form action="AceptarServlet" method = "post">
				<input type="hidden" name="id" value="${evaluacion.id}" />
				<p><button type="submit">Aceptar</button></p>
			</form>
			</c:if>
	</td>
		<td>
		<c:if test="${evaluacion.solicitud.estado != 5}">
		<p>Aquí puedes rechazar el proyecto</p>
			<form action="DenegarServlet" method = "post">
				<input type="hidden" name="id" value="${evaluacion.id}" />
				<p><button type="submit">Rechazar</button></p>
			</form>
		</c:if>	
	</td>
	
	
	
</tr>

	

 </shiro:hasRole>

</body>
</html>