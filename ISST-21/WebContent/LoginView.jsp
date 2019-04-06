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
	<link rel="stylesheet" type="text/css" href="ISST-21/WebContent/CSS/estilos.css">
</head>
<body>
	<shiro:guest>
		<h2>Login</h2>
		<form action="LoginServlet" method="post">
			Email: <input type="text" name="email" placeholder="Email" />
			Password: <input type="password" name="password" placeholder="Password" />
			<button type="submit">Login</button>
		</form>

		<h2>Register</h2>
		<form action="RegisterServlet" method="post">
			<p>
				Nombre: <input type="text" name="name" />
			</p>
			<p>
				Email: <input type="text" name="email" />
			</p>
			<p>
				Password: <input type="password" name="password" />
			</p>
			<p>
				Área de investigación: <input type="text" name="area" />
			</p>
			<p>
				Grupo de investigación: <input type="text" name="grupo" />
			</p>
			<p>
				Roll: <select name="roll" >
					<option value = "" disabled selected>Elija un roll </option>
<!-- 				A ver de donde saco los roles -->
					<c:forEach items="${roles}" var="rolli">
						<option value="${rolli}">
							${rolli}
						</option>
					</c:forEach>
					</select>
			</p>
			
			<button type="submit">Register</button>
		</form>

	</shiro:guest>

	<shiro:user>
    	Welcome back <shiro:principal />! Click <a href="LogoutServlet">here</a> to logout.
	</shiro:user>
</body>
</html>