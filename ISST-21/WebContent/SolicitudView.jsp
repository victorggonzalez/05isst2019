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
	<link rel="stylesheet" href="assets/css/w3.css">
</head>
<body>
<!-- Header -->
<header id="header">
		<h1>INVESTIGADOR</h1>
		<nav id="nav">
		<ul>
		<li><a href="InvestigadorServlet?email=${investigador.email}" class="button">Inicio</a></li>
			<li><a href="LogoutServlet" class="button">Log out</a></li>

		</ul>
		</nav>
</header>
<shiro:user>
</shiro:user>

	<shiro:lacksRole name="investigador">
	No tienes permiso para ver el contenido de esta página
	</shiro:lacksRole>
	<shiro:hasRole name="investigador">
		
	<!-- Main -->	
	<section id="main" class="container">
	<header     style="margin: 0 0 2em 0">
		<c:if test="${solicitud.estado < 4}">
		<h2><b>Siga los pasos para completar su solicitud</b></h2>
		</c:if>
		<c:if test="${solicitud.estado == 5}">
			<h2><b>Hay una petición de ampliación para esta solicitud</b></h2>
		</c:if>
		
	</header>
	<div class="box">
		<c:if test="${solicitud.estado < 4 }">
		<h3><b>Rellene el siguiente formulario</b></h3>
			<c:if test="${solicitud.estado == 1}">
				<form action="FormularioServlet" method="get" style="margin:0">
					<input type="hidden" name="id" value="${id}" />
					<button type="submit" class="button small">Rellenar formulario</button>
				</form>
			</c:if>
			<c:if test="${solicitud.estado > 1 && solicitud.estado < 4 }">
				<button type="submit" class="button small" disabled>Rellenar formulario</button>
				Ha rellenado el formulario correctamente
			</c:if>
		<h3><b>Suba la memoria de su trabajo</b></h3>
			<c:if test="${solicitud.estado == 1}">
				<button type="submit" class="button small" disabled >Subir memoria</button>
			</c:if>
			<c:if test="${solicitud.estado == 2}">
				<form action="MemoriaServlet" method="post" enctype="multipart/form-data"  style="margin:0">
					<input type="hidden" name="id" value="${id}" /> 
					<input type="hidden" name="emailInvestigador" value="${emailInvestigador}" />
					
					
					<button type="submit" class="button small">Subir memoria</button>
					
						<input type="file" name="file" id="file" accept=".pdf" class="inputfile" required/>
					
				</form>
			</c:if>
			<c:if test="${solicitud.estado == 3}">
				<button type="submit" class="button small" disabled >Subir memoria</button> Ha entregado la memoria correctamente
			</c:if>
		
		<h3><b>Para terminar el proceso, envíe su solicitud al Comité de Ética.</b></h3>
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
						Aún no hay suficientes evaluadores, inténtalo más tarde.
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
			<h3><b>Los datos solicitados se muestran a continuación:</b></h3>
			
			<p>${solicitud.faltanDatos} </p>
			<h3><b>Sube un archivo con los datos requeridos.</b></h3>
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
			<c:if test="${solicitud.estado < 4}">	
				<input type = "hidden" name = "volverincompletas" value ="true" />
			</c:if>
			<c:if test="${solicitud.estado == 4}">	
				<input type = "hidden" name = "volverencurso" value ="true" />
			</c:if>
			<c:if test="${solicitud.estado == 5}">	
				<input type = "hidden" name = "volverampliacion" value ="true" />
			</c:if>
			<c:if test="${solicitud.estado > 5 && solicitud.estado < 8}">	
				<input type = "hidden" name = "volverencurso" value ="true" />
			</c:if>
			<p><button type="submit" class="button alt small">Atrás</button></p>
		</form>

		</div>
	</section>
		<!-- Footer -->
	<footer id="footer">
				<ul class="copyright">
					<li>&copy; Proyecto RGPD. All rights reserved.</li><li>Design: Grupo 21</li>
				</ul>
	</footer>
		</shiro:hasRole>
		</body>
</html>