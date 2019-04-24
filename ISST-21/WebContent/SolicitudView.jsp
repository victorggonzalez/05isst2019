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
	<link rel="stylesheet" href="assets/css/main.css">
</head>
<body>
<header id="header">
					<h1>INVESTIGADOR</h1>
					<nav id="nav">
						<ul>	
							<li><a href="LogoutServlet" class="button">Log Out</a></li>
						</ul>
					</nav>
				</header>
<shiro:user>
Pulsa <a href="LogoutServlet">aqui</a> para salir.
</shiro:user>

<hr>
	<shiro:lacksRole name="investigador">
	No tienes permiso para ver el contenido de esta página
	</shiro:lacksRole>
	<shiro:hasRole name="investigador">
			
		<c:if test="${solicitud.estado == 1}">
		<h2><b>Sigue los pasos para completar tu solicitud</b></h2>
		<h3>Rellena el siguiente formulario</h3>

		<form action="FormularioServlet" method="get">
			<input type = "hidden" name = "id" value ="${id}" />
			<button type="submit" class="button small">Rellenar Formulario</button>
		</form>
		<h3>Sube la memoria de tu trabajo</h3>
		<button type = "submit" disabled class="button small"> Subir memoria </button>
		</c:if>

		
		
		<c:if test="${solicitud.estado == 2}">
		<h2><b>Sigue los pasos para completar tu solicitud</b></h2>
		<h3>Formulario relleno</h3>
		<button type = "submit" class="button alt icon fa-check"> Formulario relleno</button>
		<a href="#" class="button icon fa-download">Icon</a>
		<h3>Sube la memoria de tu trabajo</h3>

			<form action = "MemoriaServlet" method = "post" enctype = "multipart/form-data">
				<div>
					<label for="file">Elige un archivo (PDF)</label> 
					<input type="file"
						name="file" accept=".pdf" />

				</div>
				<input type = "hidden" name = "id" value ="${id}" />
				<input type = "hidden" name = "emailInvestigador" value ="${emailInvestigador}" />
				<div>			
				<p><button type = "submit" class="button small"> Subir memoria </button></p>
				</div>
			</form>
		</c:if>
		
		<c:if test="${solicitud.estado == 3}">

			<h2><b>Has completado la solicitud correctamente.</b></h2>
 			<c:if test="${!no_suficientes_investigadores}">	

			<h3>Para terminar el proceso, envía tu solicitud al Comité de Ética.</h3>
				<form action="EnviarServlet" method="post">
					<input type = "hidden" name = "id" value ="${id}" />
					<input type = "hidden" name = "email" value ="${solicitud.investigador.email}" />	
					<input type = "hidden" name = "solicitudes_list" value ="${solicitudes_list}" />
					<button type="submit" class="button small">Enviar solicitud</button>

				</form>
			</c:if>
 			<c:if test="${no_suficientes_investigadores}">
 				<p>Aún no hay suficientes evaluadores, inténtalo más tarde.</p>		 
 			</c:if> 
 			
		</c:if>
		
		<c:if test="${solicitud.estado == 4}">
<<<<<<< HEAD
			<h2><b>Solicitud enviada para evaluar.</b></h2>
		</c:if>
		<c:if test="${solicitud.estado == 5}">
			<h2><b>Hay una petición de ampliación para esta solicitud</b></h2>
			<h4>Los datos solicitados se muestran a continuación:</h4>
			
			<p>${solicitud.faltanDatos} </p>
			<h4>Sube un archivo con los datos requeridos. </h4>
			<form action="AmpliacionServlet" method="post" enctype = "multipart/form-data">
				<input type = "file" name = "ampliacion" class="button small"/>
				<input type="hidden" name="id" value="${id}" />
				<input type = "hidden" name = "emailInvestigador" value ="${emailInvestigador}" />	
				<button type="submit" class="button small">Subir ampliación</button>
			</form>
		</c:if>
		<c:if test="${solicitud.estado > 5}">
			<h2><b>Has actualizado la solicitud correctamente.</b></h2>
		</c:if>
		
<hr>
		<form action="InvestigadorServlet" method="get">
			<input type = "hidden" name = "email" value ="${solicitud.investigador.email}" />	
			<input type = "hidden" name = "solicitudes_list" value ="${solicitudes_list}" />
			<p><button type="submit" class="button alt small">Atrás</button></p>

		</form>
		
		</shiro:hasRole>
		</body>
</html>