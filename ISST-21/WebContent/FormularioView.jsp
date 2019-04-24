<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulario View</title>
<link rel="stylesheet" href="assets/css/main.css">
</head>
 
<body>
<<<<<<< HEAD
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
		<h2><b>Rellene el formulario con los aspectos relacionados con su
			solicitud</b></h2>

		<form action="FormularioServlet" method="post">


			<h3>Marque los campos que considere:</h3>

			<p><input type="checkbox" id="seleccion1" name="seleccion" value="Datos personales reveladores ">
			<label for="seleccion1">¿Se tratan datos
				personales que revelen el origen étnico o racial, las opiniones
				políticas, las convicciones religiosas o filosóficas, o la
				afiliación sindical, y el tratamiento de datos genéticos, datos
				biométricos dirigidos a identificar de manera unívoca a una persona
				física, datos relativos a la salud o datos relativos a la vida
				sexual o las orientaciones sexuales de una persona física?</label>
			</p>
			<p>
				<input type="checkbox" id="seleccion2" name="seleccion"
					value="Consentimiento de los individuos">
				<label for="seleccion2">¿Se ha obtenido el
				consentimiento explícito de los individuos cuyos datos arriba
				mencionados se tratan?</label>
			</p>
			<p>
				<input type="checkbox" id="seleccion3" name="seleccion" value="Datos publicos">
				<label for="seleccion3">¿El
				individuo cuyos datos se tratan ha hecho públicos estos datos?</label>
			</p>
			<p>
				<input type="checkbox" id="seleccion4" name="seleccion" value="Datos de menores">
				<label for="seleccion4">¿Se
				tratan datos de menores de 16 años?</label>
			</p>
			<p>
				<input type="checkbox" id="seleccion5" name="seleccion"
					value="Consentimiento del tutor">
					<label for="seleccion5">En caso afirmativo, ¿se ha
				obtenido el consentimiento del tutor legal?</label>
			</p>
			<p>
				<input type="checkbox" id="seleccion6" name="seleccion"
					value="Datos relativos a condenas">
					<label for="seleccion6">¿Se hace uso de datos
				personales relativos a condenas e infracciones penales o medidas de
				seguridad?</label>
			</p>
			<p>
				<input type="checkbox" id="seleccion7" name="seleccion"
					value="Consentimiento de las autoridades">
					<label for="seleccion7">En caso
				afirmativo, ¿este tratamiento se realiza bajo las supervisión de
				autoridades públicas o ha sido autorizado por el derecho de la
				unión?</label>
			</p>
			<p>
				<input type="submit" name="submit" value="Enviar">
			</p>


			<input type="hidden" name="id" value="${id}"> 
			<input type="hidden" name="solicitud" value="${solicitud}">





		</form>
	</shiro:hasRole>

</body>
</html>