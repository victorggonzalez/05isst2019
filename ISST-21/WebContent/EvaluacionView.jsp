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

<h2>Resumen de la solicitud</h2>
<h3>Formulario de datos a tratar</h3>

	<c:forEach items="${solicitudi.formulario}" var="current">
		<tr><c:out value="${current}" /></tr>
	</c:forEach>

<h3>Descarga de la memoria</h3>
	<form action = "DescargarMemoriaServlet" method = "post">		
		<button type = "submit" >Descargar</button>
	</form>
		
<tr>
	<td>
		<c:if test="${solicitudi.estado != 6}">
			<form action="CompletarServlet">
				<input type="hidden" name="id" value="${solicitudi.id}" />
				<button type="submit">Descargar</button>
			</form>
		</c:if>
	</td>
	<td>
		<c:if test="${solicitudi.estado != 7 & }">
			<form action="Aceptar">
				<input type="hidden" name="id" value="${solicitudi.id}" />
				<button type="submit">Descargar</button>
			</form>
		</c:if>
	</td>
	
	
	
</tr>

	

 

</body>
</html>