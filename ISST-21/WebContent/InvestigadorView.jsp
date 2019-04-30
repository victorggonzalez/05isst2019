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
<shiro:lacksRole name="investigador">
No tienes permiso para ver el contenido de esta página
</shiro:lacksRole>

<shiro:hasRole name="investigador">
<header id="header">
	<h1><a href="index.html">Proyecto RGPD</a> by Grupo 21</h1>
				<nav id="nav">
					<ul>	
						<li><a href="LogoutServlet" class="button">Log out</a></li>
					</ul>
				</nav>
			</header>
	
	<!-- Main -->
		<section id="main" class="container medium" style = "padding-bottom:0px">
		<header     style="margin: 0 0 2em 0">
		<h2><b>¡Bienvenido investigador <shiro:principal/>!</b></h2>
		</header>
		<div class="box">
		<h2><b>Tus solicitudes:</b></h2>
		
		<table border="1">
			<tr>
				<th><h3><b>Solicitudes</b></h3></th>
				<th><h3><b>Cantidad</b></h3></th>
			</tr>
			<tr>
				<td><a href="VerSolicitudVaciaView.jsp">Incompletas</a></td>
				<td>${n_solicitudes_vacias}</td>
			</tr>
			<tr> 
				<td><a href="VerSolicitudCursoView.jsp">En evaluación</a></td>
				<td>${n_solicitudes_encurso}</td>
			</tr>
			<tr>
				<td><a href="VerSolicitudActualizarView.jsp">Pendiente de ampliación</a></td>
				<td>${n_solicitudes_actualizar}</td>
			</tr>
			<tr>
				<td><a href="VerSolicitudCerradaView.jsp">Cerradas</a></td>
				<td>${n_solicitudes_cerradas}</td>
			</tr>
		</table>
		</div>
	</section>
	<section id="second" class="container medium">
		<div class="box">
		<h2><b>Crear una nueva solicitud</b></h2>
			<form action="SolicitarServlet" method="post">
				<input type="hidden" name="emailInvestigador" value="${investigador.email}" />
				<h3>Título: <input type="text" name="titulo" required/></h3>
				<button type="submit" class="button">Crear solicitud</button>
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

</html>