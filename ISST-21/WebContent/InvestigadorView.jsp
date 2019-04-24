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

	<h2><b>&nbsp;¡Bienvenido investigador <shiro:principal/>!</b></h2>
	
	<h3>&nbsp;Tus solicitudes:<br><br></h3>
		<table border="1">
			<tr>
				<th>Solicitudes</th>
				<th>Cantidad</th>
			</tr>
			<tr>
					<td>Incompletas</td>
					<td>${solicitudes_vacias}</td>
				</tr>
				<tr>
					<td>En evaluación</td>
					<td>${solicitudes_encurso}</td>
				</tr>
				<tr>
					<td>Pendiente de ampliación</td>
					<td>${solicitudes_actualizar}</td>
				</tr>
				<tr>
					<td>Cerradas</td>
					<td>${solicitudes_cerradas}</td>
				</tr>
		</table>
		
	<h3>Información de tus solicitudes</h3>

		<table border="1">
			<tr>
				<th><h4><b>Título</b></h4></th>
				<th><h4><b>id</b></h4></th>
				<th><h4><b>Estado</b></h4></th>
				<th><h4><b>Formulario</b></h4></th>
				<th><h4><b>Memoria</b></h4></th>
				<th><h4><b>Enviado</b></h4></th>
				<th><h4><b>Faltan datos</b></h4></th>
				<th><h4><b>Ampliación</b></h4></th>
				<th><h4><b>Ver solicitud</b></h4></th>
			</tr>
				<c:forEach items="${solicitudes_list}" var="solicitudi">
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
						<c:if test="${solicitudi.estado > 3}"> Si </c:if>
						<c:if test="${solicitudi.estado < 4}"> No </c:if>
					</td>
					<td>
						<c:if test="${solicitudi.estado < 5}"> No </c:if>
						<c:if test="${solicitudi.estado == 5}"> Si</c:if>
						<c:if test="${solicitudi.estado > 5 && solicitudi.ampliacion != null}"> Datos actualizados </c:if>
						<c:if test="${solicitudi.estado > 5 && solicitudi.ampliacion == null}"> No</c:if>
						
					</td>
					<td>
						
						<c:if test="${solicitudi.estado < 5}"> Ampliación no requerida </c:if>
						<c:if test="${solicitudi.estado == 5}"> 
						Ampliación requerida. Pulsa en VER para enviar tu ampliación
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
						<button type="submit" class="button small">Ver</button>
						</form>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<h3><b>&nbsp;Crear una nueva solicitud</b></h3>
			<form action="SolicitarServlet" method="post">
				<input type="hidden" name="emailInvestigador" value="${investigador.email}" />

				<h3>&nbsp;Título: <input type="text" name="titulo" /></h3>
				<button type="submit" class="button">Crear solicitud</button>
			</form>
	
	
	
	
	</shiro:hasRole>

</html>