<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Faltan Datos</title>
	<link rel="stylesheet" type="text/css" href="ISST-21/WebContent/CSS/estilos.css">
</head>
<body>
<shiro:user> Pulsa <a href="LogoutServlet">aqui</a> para salir.
</shiro:user>
<hr>
	<shiro:lacksRole name="evaluador">
	No tienes permiso para ver el contenido de esta página
	</shiro:lacksRole>
	<shiro:hasRole name="evaluador">
	
	<h2>Has solicitado una ampliación de la solicitud: ${titulo}</h2>
	<h3>Indique a continuación los datos que el investigador debe añadir</h3>
	<form action="EvaluacionIncompletaServlet" method="post">
		<textarea name="faltandatos" rows="10" cols="40" placeholder="Escribe aqui tus comentarios"></textarea>
		<input type="hidden" name="id" value="${id}"/>
		<p><button type="submit">Enviar</button></p>
	</form>	
	
	</shiro:hasRole>
</body>

</html>