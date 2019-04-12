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
<shiro:user> Pulsa <a href="LogoutServlet">aqui</a> para salir.
</shiro:user>


<hr>
	<shiro:lacksRole name="investigador">
	No tienes permiso para ver el contenido de esta página
	</shiro:lacksRole>
	<shiro:hasRole name="investigador">
		<c:if test="${solicitud.estado == 1}">
		<h2>Sigue los pasos para completar tu solicitud</h2>
		<h3>1º Rellena el siguiente formulario</h3>
		<form action="FormularioServlet" method="get">
			<input type = "hidden" name = "id" value ="${id}" />
			<button type="submit">Rellenar Formulario</button>
		</form>
		
		</c:if>
		
		<c:if test="${solicitud.estado == 2}">
			<h2>Sigue los pasos para completar tu solicitud</h2>
			<h3>2º Sube la memoria de tu trabajo</h3>
			<form action = "MemoriaServlet" method = "post" enctype = "multipart/form-data">
				<input type = "file" name = "file" />
				<input type = "hidden" name = "id" value ="${id}" />
				<input type = "hidden" name = "emailInvestigador" value ="${emailInvestigador}" />			
				<button type = "submit" > Subir memoria </button>
			</form>
		</c:if>
		
		<c:if test="${solicitud.estado == 3}">

			<h2>Has completado la solicitud correctamente.</h2>
 			<c:if test="${!no_suficientes_investigadores}">
			<h3>Para terminar el proceso, envía tu solicitud al Comité de Ética.</h3>
				<form action="EnviarServlet" method="post">
					<input type = "hidden" name = "id" value ="${id}" />
					<input type = "hidden" name = "email" value ="${solicitud.investigador.email}" />	
					<input type = "hidden" name = "solicitudes_list" value ="${solicitudes_list}" />
					<p>Pulsa para enviar tu solicitud <button type="submit">Enviar Solicitud</button></p>
				</form>
			</c:if>
 			<c:if test="${no_suficientes_investigadores}">
 				<p>Aún no hay suficientes evaluadores, inténtalo más tarde.</p>		 
 			</c:if> 
 			
		</c:if>
		
		<c:if test="${solicitud.estado == 4}">
			<h2>Solicitud enviada para evaluar.</h2>
		</c:if>
		<c:if test="${solicitud.estado == 5}">
			<h4>Hay una petición de ampliación. Los datos solicitados se muestran a continuación:</h4>
			<p>${solicitud.faltanDatos} </p>
			<h4>Sube un archivo con los datos indicados. </h4>
			<form action="AmpliacionServlet" method="post" enctype = "multipart/form-data">
				<input type = "file" name = "ampliacion" />
				<input type="hidden" name="id" value="${id}" />
				<input type = "hidden" name = "emailInvestigador" value ="${emailInvestigador}" />	
				<button type="submit">Subir ampliación</button>
			</form>
		</c:if>
		<c:if test="${solicitud.estado > 5}">
			<h2>Has actualizado la solicitud correctamente.</h2>
		</c:if>
		
<hr>
		<form action="InvestigadorServlet" method="get">
			<input type = "hidden" name = "email" value ="${solicitud.investigador.email}" />	
			<input type = "hidden" name = "solicitudes_list" value ="${solicitudes_list}" />
			<p><button type="submit">Atrás</button></p>
		</form>
		
		</shiro:hasRole>
		</body>
</html>