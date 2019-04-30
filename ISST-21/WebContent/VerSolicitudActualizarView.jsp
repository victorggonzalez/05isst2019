<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Investigador View</title>
<link rel="stylesheet" href="assets/css/main.css">
</head>
<body>
<header id="header">
		<h1>INVESTIGADOR</h1>
		<nav id="nav">
		<ul><li><a href="InvestigadorServlet?email=${investigador.email}" class="button">Inicio</a></li>
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
	<h2><b>Información de tus solicitudes pendientes de ampliación</b></h2>
	</header>
	<div class="box">
	
		<table border="1">
			<tr>
				<th><h4><b>Título</b></h4></th>
				<th><h4><b>id</b></h4></th>
				<th><h4><b>Estado</b></h4></th>
				<th><h4><b>Formulario</b></h4></th>
				<th><h4><b>Memoria</b></h4></th>
				<th><h4><b>Ampliación</b></h4></th>
				<th><h4><b></b></h4></th>
			</tr>
				<c:forEach items="${solicitudes_actualizar}" var="solicitudi">
				<tr>
					
					<td>${solicitudi.titulo }</td>
					<td>${solicitudi.id }</td>
					<td><c:if test="${solicitudi.estado == 1}">
						Solicitud vacía
						</c:if>
						<c:if test="${solicitudi.estado == 2}">
						Falta memoria
						</c:if>
						<c:if test="${solicitudi.estado == 3}">
						Lista para enviar
						</c:if>
						<c:if test="${solicitudi.estado == 4}">
						Enviada
						</c:if>

						<c:if test="${solicitudi.estado == 5}">
						Falta ampliacion
						</c:if>
						<c:if test="${solicitudi.estado > 5 && solicitudi.estado < 8}">
						En evaluación
						</c:if>
						<c:if test="${solicitudi.estado == 8}">
							<c:if test="${solicitudi.evaluaciones[0].isResultado() == 'Aprobado'}">
								<c:if test="${solicitudi.evaluaciones[1].isResultado() == 'Aprobado'}">
									Aprobado
								</c:if>
							</c:if>
							<c:if test="${solicitudi.evaluaciones[0].isResultado() == 'Denegado' || solicitudi.evaluaciones[1].isResultado() == 'Denegado'}">
								Denegado
							</c:if>
						</c:if>
					</td>
					<td><c:if test="${solicitudi.estado > 1}">
						Formulario relleno
						</c:if>
					</td>
					<td><c:if test="${solicitudi.estado > 2}">
						<form action="ServeFileServlet">
						<input type="hidden" name="id" value="${solicitudi.id}" />
						<input type="hidden" name="tipoDocumento" value="memoria" />
						<button type="submit" class="button icon fa-download">Descargar memoria</button>
						</form>
						</c:if>
					</td>

					
					<td>
						
						<c:if test="${solicitudi.estado < 5}"> Ampliación no requerida </c:if>
						<c:if test="${solicitudi.estado == 5}"> 
						Ampliación requerida. 
						<p>Pulsa en Completar solicitud y envía la ampliación</p>
						</c:if>
						<c:if test="${solicitudi.estado > 5 && solicitudi.ampliacion != null}"> 
						<form action="ServeFileServlet">
						<input type="hidden" name="id" value="${solicitudi.id}" />
						<input type="hidden" name="tipoDocumento" value="ampliacion" />
						<button type="submit" class="button icon fa-download">Descargar ampliación</button>
						</form>
						</c:if>
						<c:if test="${solicitudi.estado > 5 && solicitudi.ampliacion == null}"> 
						 Ampliación no requerida 
						</c:if>
					</td>
					<td><c:if test="${solicitudi.estado < 8}"> 
						<form action="SolicitarServlet" method="get">
						<input type="hidden" name="id" value="${solicitudi.id}" />
						<input type="hidden" name="solicitudes_list" value="${solicitudes_list}" />
						<input type="hidden" name="email" value="${investigador.email}" />
						<button type="submit" class="button small">Completar solicitud</button>
						</form>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
<<<<<<< HEAD


=======
<hr>	

		<form action="InvestigadorServlet" method="get">
			<input type = "hidden" name = "email" value ="${investigador.email}" />	
			<p><button type="submit" class="button alt small">Inicio</button></p>
		</form>
		
>>>>>>> 1f66a443e348565321da34b8ce79e017850590e4
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