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
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<header id="header">
					<h1>INVESTIGADOR</h1>
					<nav id="nav">
						<ul>	
							<li><a href="LogoutServlet" class="button">Salir</a></li>
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
			
		<c:if test="${solicitud.estado < 3}">
		<h2><b>Siga los pasos para completar su solicitud</b></h2>
		</c:if>
		<c:if test="${solicitud.estado == 3}">
		<h2><b>Has completado la solicitud correctamente.</b></h2>
		</c:if>
		<c:if test="${solicitud.estado < 4 }">
		<h3>Rellena el siguiente formulario</h3>
			<c:if test="${solicitud.estado == 1}">
				<form action="FormularioServlet" method="get">
					<input type="hidden" name="id" value="${id}" />
					<button type="submit" class="button small">Rellenar Formulario</button>
				</form>
			</c:if>
			<c:if test="${solicitud.estado > 1 && solicitud.estado < 4 }">
				<button type="submit" class="button small" disabled>Rellenar Formulario</button>
				Ha rellenado el formulario correctamente
			</c:if>
		<h3>Sube la memoria de tu trabajo</h3>
			<c:if test="${solicitud.estado == 1}">
				<button type="submit" class="button small" disabled >Subir memoria</button>
			</c:if>
			<c:if test="${solicitud.estado == 2}">
				<form action="MemoriaServlet" method="post" enctype="multipart/form-data">
					<div>
						<label for="file"><b>Elige un archivo (PDF)</b></label> 
						<input type="file" name="file" id="file" accept=".pdf" class="inputfile" required/>
					</div>
					<input type="hidden" name="id" value="${id}" /> 
					<input type="hidden" name="emailInvestigador" value="${emailInvestigador}" />
					<div>
					<p></p>
					<p><button type="submit" class="button small">Subir memoria</button>
					</p></div>
				</form>
			</c:if>
			<c:if test="${solicitud.estado == 3}">
				<button type="submit" class="button small" disabled >Subir memoria</button> Ha entregado la memoria correctamente
			</c:if>
		
		<h3>Para terminar el proceso, envía tu solicitud al Comité de Ética.</h3>
				<c:if test="${solicitud.estado < 3}">
					<button type="submit" class="button small" disabled>Enviar solicitud</button>
				</c:if>
				<c:if test="${solicitud.estado == 3}">
					<c:if test="${!no_suficientes_investigadores}">
		
						<form action="EnviarServlet" method="post">
							<input type="hidden" name="id" value="${id}" /> 
							<input type="hidden" name="email" value="${solicitud.investigador.email}" />
							<input type="hidden" name="solicitudes_list" value="${solicitudes_list}" />
							<button type="submit" class="button">Enviar solicitud</button>
		
						</form>
					</c:if>
					<c:if test="${no_suficientes_investigadores}">
						<button type="submit" class="button small" disabled>Enviar solicitud</button>
						<p>Aún no hay suficientes evaluadores, inténtalo más tarde.</p>
					</c:if>
				</c:if>
			<p></p>
			<div class="w3-white w3-round-xlarge">
				<c:if test="${solicitud.estado == 1}">
				<div class="w3-container w3-grey w3-round-xlarge" style="width: 3%">0/2</div>
				</c:if>
				<c:if test="${solicitud.estado == 2}">
				<div class="w3-container w3-grey w3-round-xlarge" style="width: 50%">1/2</div>
				</c:if>
				<c:if test="${solicitud.estado == 3}">
				<div class="w3-container w3-grey w3-round-xlarge" style="width: 100%">2/2</div>
				</c:if>
			</div>
		</c:if>
	
		
		<c:if test="${solicitud.estado == 4}">
			<h2><b>Solicitud enviada para evaluar.</b></h2>
		</c:if>
		<c:if test="${solicitud.estado == 5}">
			<h2><b>Hay una petición de ampliación para esta solicitud</b></h2>
			<h4>Los datos solicitados se muestran a continuación:</h4>
			
			<p>${solicitud.faltanDatos} </p>
			<h4>Sube un archivo con los datos requeridos. </h4>
			<form action="AmpliacionServlet" method="post" enctype = "multipart/form-data">
				<input type = "file" name = "ampliacion" accept=".pdf"   required/>
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