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
    Pulsa <a href="LogoutServlet">aqui</a> para salir.
</shiro:user>


<hr>
	<shiro:lacksRole name="evaluador">
	No tienes permiso para ver el contenido de esta página
	</shiro:lacksRole>
	<shiro:hasRole name="evaluador">
	<h2>¡Bienvenido evaluador <shiro:principal />!</h2>
	
	<h3>Información de tus evaluaciones</h3>

		<table border="1">
			<tr>
				<th>Título</th>
				<th>id</th>
				<th>Estado</th>
				
				<th>Formulario</th>
				<th>Memoria</th>
				<th>Ampliación</th>
				<th>Evaluar solicitud</th>
				<th>Tu valoración</th>
				
			</tr>
				<c:forEach items="${evaluaciones_list}" var="evaluacioni">
				<tr>
					
					<td>${evaluacioni.solicitud.titulo }</td>
					<td>${evaluacioni.solicitud.id }</td>
					<td><c:if test="${evaluacioni.solicitud.estado < 7}">
						En evaluación
						</c:if>
						<c:if test="${evaluacioni.solicitud.estado  > 6}">
						${evaluacioni.resultado}
						</c:if>
					</td>
					<td><c:if test="${evaluacioni.solicitud.estado > 2}">
						Formulario relleno
						</c:if>
					</td>
					<td><c:if test="${evaluacioni.solicitud.estado > 3}">
						<form action="ServeFileServlet">
						<input type="hidden" name="id" value="${evaluacioni.solicitud.id}" />
						<button type="submit">Descargar</button>
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
						<button type="submit">Descargar ampliacion</button>
						</form>
						</c:if>
					</td>
					
					
					<td><c:if test="${evaluacioni.isResultado() == 'Sin evaluar'}">
						<form action="EvaluarServlet" method="post">
						<input type="hidden" name="id" value="${evaluacioni.id}" />
						<input type="hidden" name="email" value="${evaluador.email}" />
						<button type="submit">Evaluar</button>
						</form>
						</c:if>
						
					</td>
					<td>${evaluacioni.isResultado()}</td>
						
				</tr>
			</c:forEach>
		</table>

	
	
	
	
	</shiro:hasRole>
