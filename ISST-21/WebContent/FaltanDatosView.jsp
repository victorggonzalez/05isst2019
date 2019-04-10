<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<title>Faltan Datos View</title>
</head>

<body>
	
	<h1>Indique los datos que el investigador debe aÒadir</h1>
	<form action="CompletarServlet" method="post">
		<input type="text" name="faltandatos">
		<input type="hidden" name="id" value="${id }"/>
		<input type="hidden" name="emailEvaluador" value="${email }" />
		<button type="submit">Enviar</button>
	</form>	
	
</body>

</html>