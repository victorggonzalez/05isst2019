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
</head>
<body>
<shiro:user>
    Welcome back <shiro:principal />! Click <a href="LogoutServlet">here</a> to logout.
</shiro:user>


<hr>
	<shiro:lacksRole name="evaluador">
	No tienes permiso para ver el contenido de esta página
	</shiro:lacksRole>
	<shiro:hasRole name="evaluador">
	<h2>¡Bienvenido evaluador!</h2>
	
	<h3>Información de tus evaluaciones</h3>

		<table border="1">
			<tr>
				<th>Título</th>
				<th>id</th>
				<th>Estado</th>
				
				<th>Formulario</th>
				<th>Memoria</th>
				<th>Ampliación</th>
				<th>Tu valoracion</th>
				<th>Valoración 2</th>
			</tr>
				<c:forEach items="${evaluaciones_list}" var="evaluacioni">
				<tr>
					
					<td>${evaluacioni.solicitud.titulo }</td>
					<td>${evaluacioni.solicitud.id }</td>
					<td>${evaluacioni.solicitud.estado}</td>
				
					
					
					<td><c:if test="${evaluacioni.solicitud.estado > 2}">
						Formulario relleno
						</c:if>
					</td>
					<td><c:if test="${evaluacioni.solicitud.estado > 3}">
						<form action="ServeFileServlet">
						<input type="hidden" name="id" value="${evaluacioni.id}" />
						<button type="submit">Descargar</button>
						</form>
						</c:if>
					</td>
					
					
					<td>Ampliación
					</td>
					<td>
					</td>
					<td>
					</td>
					
						
				</tr>
			</c:forEach>
		</table>
		
		<h3>EVALUACIÓN</h3>
			<form action="EvaluarServlet" method="post">
				<input type="hidden" name="emailEvaluador" value="${evaluador.email}" />
				<p>Título: <input type="text" name="titulo" /></p>
				<button type="submit">Evaluar solicitud</button>
			</form>
	
	
	
	
	</shiro:hasRole>
