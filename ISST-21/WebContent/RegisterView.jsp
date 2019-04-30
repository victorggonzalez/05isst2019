<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login View</title>
	<link rel="stylesheet" type="text/css" href="assets/css/main.css">
</head>
<body>
	<shiro:guest>
<!-- Header -->
	<header id="header">
		<h1><a>Proyecto RGPD</a> by Grupo 21</h1>
		<nav id="nav">
						<ul>
							<li style="white-space: nowrap;"><a href="LoginView.jsp" class="button">Back</a></li>
						</ul>
					</nav>
	</header>
<!-- Main -->
		<section id="main" class="container">
			<div class="box">
				<h2>Registro</h2>
				<form action="RegisterServlet" method="post">
					<p>
					Nombre: <input type="text" name="name" placeholder="Nombre" />
					</p>
					<p>
					Email: <input type="text" name="email" placeholder="Email" />
					</p>
					<p>
					Password: <input type="password" name="password" placeholder="Password" /> 
					</p>
					<p>
					Área de investigación: <input type="text" name="area" placeholder="Área" /> 
					</p>
					<p>
					Grupo de investigación: <input type="text" name="grupo" placeholder="Grupo" />
					</p>
					
					<p style="margin:0 0 0 0">
					Rol:
					</p>
					<div class="row gtr-uniform gtr-50" >
						<div class="col-6">
							<input type="radio" id="investigador" value="investigador" name="rol" checked>
							<label for="investigador">Investigador</label>
						</div>
						
						<div class="col-6">
							<input type="radio" id="evaluador" value="evaluador" name="rol" >
							<label for="evaluador">Evaluador</label>
						</div>
					</div>
					
					<button type="submit" class="button special" style="margin:1em 0 0 0">Registrar</button>
				</form>
			</div>
		</section>

		<!-- Footer -->
	<footer id="footer">
				<ul class="copyright">
					<li>&copy; Proyecto RGPD. All rights reserved.</li><li>Design: Grupo 21</li>
				</ul>
	</footer>

	</shiro:guest>

	<shiro:user>
    	Welcome back <shiro:principal />! Click <a href="LogoutServlet">here</a> to logout.
	</shiro:user>
</body>
</html>