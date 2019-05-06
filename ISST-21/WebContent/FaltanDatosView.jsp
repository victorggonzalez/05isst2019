<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Faltan Datos</title>
	<link rel="stylesheet" type="text/css" href="assets/css/main.css">
</head>
<body>
	<shiro:user>
	<header id="header">
		<h1>EVALUADOR</h1>
		<nav id="nav">
			<ul>						  
			   <li>
			  	<form action="EvaluarServlet" method="post">
					<input type="hidden" name="id" value="${id}"/>
					<input type = "hidden" name = "email" value ="${email}" />	
					<a class="button">Back</a>
				</form>
			  </li>
			  <li><a href="EvaluadorServlet?email=${evaluador.email}" class="button">Inicio</a></li>
			  <li><a href="LogoutServlet" class="button">Log out</a></li>

			</ul>
		</nav>
	</header>
	</shiro:user>

<hr>
	<shiro:lacksRole name="evaluador">
	No tienes permiso para ver el contenido de esta página
	</shiro:lacksRole>
	<shiro:hasRole name="evaluador">
	<section id="main" class="container medium">
		<header>
			<h2>Ha solicitado una ampliación de la solicitud: ${titulo}</h2>
			<p>Indique a continuación los datos que el investigador debe añadir</p>
		</header>
		<div class="box">
			<form action="EvaluacionIncompletaServlet" method="post">
				<textarea name="faltandatos" rows="10" cols="40" placeholder="Escribe aqui tus comentarios"></textarea>
				<input type="hidden" name="id" value="${id}"/>
				<p>
				<ul class="actions special">
					<button type="submit" class="button special big" style="width:150px; height:50px; font-size: 12pt;">Enviar</button>
				</ul>
				<hr>	
				<form action="EvaluarServlet" method="post">
					<input type="hidden" name="id" value="${id}"/>
					<input type = "hidden" name = "email" value ="${evaluador.email}" />	
					<p><button type="submit" class="button alt small">Atrás</button></p>
		</form>
			</form>	
		</div>
	</section>
	
	</shiro:hasRole>
	<footer id="footer">
		<ul class="copyright">
			<li>&copy; Proyecto RGPD. All rigths reserved.</li>
			<li>Design: Grupo 21</li>
		</ul>
	</footer>

</body>

</html>