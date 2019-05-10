<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Evaluador View</title>
<link rel="stylesheet" href="assets/css/main.css">
</head>
<body>

	<shiro:lacksRole name="evaluador">
	No tienes permiso para ver el contenido de esta página
	</shiro:lacksRole>
	<shiro:hasRole name="evaluador">

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
		<h2><b>¡Bienvenido evaluador ${evaluador.name}!</b></h2>
		</header>

		<div class="box">
		<h2><b>Tus evaluaciones:</b></h2>
				<table border="1">

			<tr>
				<th><h3><b>Evaluaciones</b></h3></th>
				<th><h3><b>Cantidad</b></h3></th>
			</tr>
			<tr>
				<td><a href="VerEvaluacionPendienteView.jsp">Pendientes de evaluar</a></td>
				<td>${n_evaluaciones_pendientes}</td>
			</tr>
			<tr>
				<td><a href="VerEvaluacionAmpliacionView.jsp">Pendiente de ampliación</a></td>
				<td>${n_evaluaciones_ampliacion}</td>
			</tr>
			<tr>
				<td><a href="VerEvaluacionCerradaView.jsp">Cerradas</a></td>
				<td>${n_evaluaciones_cerradas}</td>
			</tr>
		</table>
			
	
	</div>
	</section>

	<footer id="footer">
				<ul class="copyright">
					<li>&copy; Proyecto RGPD. All rights reserved.</li><li>Design: Grupo 21</li>
				</ul>
	</footer>
	
		
	</shiro:hasRole>
</body>
</html>