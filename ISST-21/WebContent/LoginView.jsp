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
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link href="CSS/estilos.css" rel="stylesheet" type="text/css">
<link href="CSS/login.css" rel="stylesheet" type="text/css">

</head>

<body>
	<shiro:guest>
	<div class="bgimg">
		<h1>Reglamento General de Protección de Datos</h1>
		<hr>
		<div class="row">
			<div class="column" >
			<h2>Login</h2>
			<form action="LoginServlet" method="post">
				<p><input type="text" name="email" placeholder="Email" /></p>
				<p><input type="password" name="password"
					placeholder="Password" /></p>
				<button type="submit">Login</button>
			</form>
			</div>
			<div class="column" >
				<div class="login-main-text row">
					<h2 class="col-8">Register</h2>
				</div>
				<div class="login-form">
					<form action="RegisterServlet" method="post">
						<div class="form-group"><p>Nombre: <input type="text" name="name" />
						</p></div>
						<div class="form-group"><p>Email: <input type="text" name="email" />
						</p></div>
						<div class="form-group"><p>Password: <input type="password" name="password" />
						</p></div>
						<div class="form-group"><p>Área de investigación: <input type="text" name="area" />
						</p></div>
						<div class="form-group"><p>Grupo de investigación: <input type="text" name="grupo" />
						</p></div>
						<div class="form-group"><p>Rol: <select name="roll">
								<option value="" disabled selected>Elija un rol</option>
								<option value="investigador">Investigador</option>
								<option value="evaluador">Evaluador</option>
							</select>
						</p></div>
						<button type="submit" class="col-12 btn btn-black">Registrar</button>
						<span class="col-12"></span>
					</form>
				</div>
			</div>
		</div>
	</div>	
	</shiro:guest>

	<shiro:user>
    	Welcome back <shiro:principal />! Click <a href="LogoutServlet">here</a> to logout.
	</shiro:user>
</body>
</html>