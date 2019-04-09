<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Solicitud View</title>
	<link rel="stylesheet" type="text/css" href="ISST-21/WebContent/CSS/estilos.css">
</head>
<body>
	<shiro:user>
    	Welcome back <shiro:principal />! Click <a href="LogoutServlet">here</a> to logout.
		<h2>Solicitud</h2>
		<form action="FormularioServlet" method="post">
			<button type="submit">Rellenar Formulario</button>
		</form>
		<form action = "MemoriaServlet" method = "post" enctype = "multipart/form-data">
			<input type = "file" name = "file" />
			<input type = "hidden" name = "id" value ="${id}" />
			<input type = "hidden" name = "emailInvestigador" value ="${emailInvestigador}" />			
			<button type = "submit" > Subir memoria </button>
		</form>
		
		<form action="EnviarServlet" method="post">
			<input type = "hidden" name = "id" value ="${id}" />
			<input type = "hidden" name = "emailInvestigador" value ="${emailInvestigador}" />	
			<button type="submit">Enviar Solicitud</button>
		</form>
		
	</shiro:user>
</body>
</html>