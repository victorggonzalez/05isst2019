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
<shiro:user> Pulsa <a href="LogoutServlet">aqui</a> para salir.
</shiro:user>

<shiro:lacksRole name="evaluador">
No tienes permiso para ver el contenido de esta página
</shiro:lacksRole>
<shiro:hasRole name="evaluador">
<hr>
<h2>Resumen de la solicitud</h2>
<table border="1">
			<tr>
				<th>Datos seleccionados en el formulario</th>
			</tr>
			<tr>
			<td>
				<c:forEach items="${evaluacion.solicitud.formulario}" var="current">
					<p><c:out value="${current}" /></p>
				</c:forEach>
			</td>
			</tr>
</table>
<h3>Descarga de la memoria</h3>
	<form action = "DescargarMemoriaServlet" method = "post">		
		<button type = "submit">Descargar Memoria</button>
	</form>
		
<tr>
	<td>
		<c:if test="${evaluacion.solicitud.estado == 4}">
			<p>Aquí puedes indicar que faltan datos en el proyecto</p>
			<form action="CompletarServlet" method = "post">
				<input type="hidden" name="id" value="${evaluacion.id}" />
				<p><button type="submit">Completar</button></p>
			</form>
		</c:if>
	</td>
	<td>
		<c:if test="${evaluacion.solicitud.estado ==5}">
			<p>Esperando a la ampliación</p>
		</c:if>
	</td>
	<td>
		<c:if test="${evaluacion.solicitud.estado != 5 and evaluacion.isResultado() == 'Sin evaluar' }" >
			<p></p>
			<form action="AceptarServlet" method = "post">
			Si crees que el proyecto cumple el RGPD, aquí puedes aceptar el proyecto
				<input type="hidden" name="id" value="${evaluacion.id}" />
				<button type="submit">Aceptar</button>
			</form>
			</c:if>
	</td>
		<td>
		<c:if test="${evaluacion.solicitud.estado != 5 and evaluacion.isResultado() == 'Sin evaluar' }" >
			<p></p>
			<form action="DenegarServlet" method = "post">
			Si crees que el proyecto no cumple el RGPD, aquí puedes rechazar el proyecto
				<input type="hidden" name="id" value="${evaluacion.id}" />
				<button type="submit">Rechazar</button>
			</form>
		</c:if>	
	</td>
</tr>
<hr>
		<form action="EvaluadorServlet" method="get">
			<input type = "hidden" name = "email" value ="${evaluacion.evaluador.email}" />	
			<input type = "hidden" name = "solicitudes_list" value ="${solicitudes_list}" />
			<p><button type="submit">Atrás</button></p>
		</form>
	

 </shiro:hasRole>

</body>
</html>