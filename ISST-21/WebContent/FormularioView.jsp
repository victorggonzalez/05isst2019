<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<title>Formulario View</title>
</head>
 
<body>
    <h2>Rellene el formulario con los aspectos relacionados con su solicitud</h2>
 
    <form  action="FormularioServlet" method="post">

    
           	<h3>Marque los campos que considere:</h3>

            <p><input type="checkbox" name="seleccion" value="Datos personales reveladores " >¿Se tratan datos personales que revelen el origen étnico
             o racial, las opiniones políticas, las convicciones religiosas o filosóficas, o la afiliación sindical, y el 
             tratamiento de datos genéticos, datos biométricos dirigidos a identificar de manera unívoca a una persona
             física, datos relativos a la salud o datos relativos a la vida sexual o las orientaciones sexuales de una persona
             física?</p>
			<p><input type="checkbox" name="seleccion" value="Consentimiento de los individuos">¿Se ha obtenido el consentimiento explícito de los individuos
			cuyos datos arriba mencionados se tratan?</p>
            <p><input type="checkbox" name="seleccion" value="Datos publicos">¿El individuo cuyos datos se tratan ha hecho públicos 
            estos datos?</p>
            <p><input type="checkbox" name="seleccion" value="Datos de menores">¿Se tratan datos de menores de 16 años?</p>
            <p><input type="checkbox" name="seleccion" value="Consentimiento del tutor">En caso afirmativo, ¿se ha obtenido el consentimiento del tutor legal?</p>
            <p><input type="checkbox" name="seleccion" value="Datos relativos a condenas">¿Se hace uso de datos personales relativos a condenas e infracciones penales
            o medidas de seguridad?</p>
            <p><input type="checkbox" name="seleccion" value="Consentimiento de las autoridades">En caso afirmativo, ¿este tratamiento se realiza bajo las 
            supervisión de autoridades públicas o ha sido autorizado por el derecho de la unión?</p>
           
                
            <p><input type="submit" name="submit" value="Enviar"></p>


            <input type="hidden" name="id" value="${id}">
            <input type="hidden" name="solicitud" value="${solicitud}">
           


          

    </form>
 
</body>
</html>