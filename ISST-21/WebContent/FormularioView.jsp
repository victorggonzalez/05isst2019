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

            <p><input type="checkbox" name="seleccion" value="campo1" >Esto es Campo 1</p>
			<p><input type="checkbox" name="seleccion" value="campo2">Esto es Campo 2</p>
            <p><input type="checkbox" name="seleccion" value="campo3">Esto es Campo 3</p>
            <p><input type="checkbox" name="seleccion" value="campo4">Esto es Campo 4</p>
            <p><input type="checkbox" name="seleccion" value="campo5">Esto es Campo 5</p>
     
            <p><input type="submit" name="submit" value="Submit"></p>

          

    </form>
 
</body>
</html>