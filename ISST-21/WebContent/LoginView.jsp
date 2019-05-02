<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Proyecto RGPD</title>
	<link rel="stylesheet" type="text/css" href="assets/css/main.css">

</head>

<body>
	<shiro:guest>

<!-- Header -->
	<header id="header">
		<h1><a href="index.html">Proyecto RGPD</a> by Grupo 21</h1>
	</header>
<!-- Main -->
		
		<section id="main" class="container medium">
			<header     style="margin: 0 0 2em 0">
			
			<img src="images/logoUpm.jpg" alt="" style="border-radius:8px">
			<h2>Reglamento General de Protección de Datos</h2>
			</header>
			
			<div class="box">
				<h3>Login</h3>
				<c:if test="${registrado != null}">
					<h7 style = "color: #47AD62 ;">Te has registrado correctamente</h7>
				</c:if>
				<c:if test="${incorrecto != null}">
					<h7 style = "color: #FC8080 ;">Usuario o Password incorrecto</h7>
				</c:if>
				<form action="LoginServlet" method="post">
					<p>
					Email: <input type="text" name="email" placeholder="Email" required />
					</p>
					<p>
					Password: <input type="password" name="password"
						placeholder="Password"  required/>
					</p>
					<button type="submit" class="button special" style="margin:1em">Login</button>
					<a href="RegisterView.jsp" >Pincha aqui si no estás registrado.</a>
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