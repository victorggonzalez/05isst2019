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
        <table>
     
            <tr>
                <td>Marque los campos que considere:</td>
            </tr>
            <tr>
            	<td><input type="checkbox" name="seleccion1" value="campo1" >Esto es Campo 1</td>
				<td><input type="checkbox" name="seleccion2" value="campo2">Esto es Campo 2</td>
              	<td><input type="checkbox" name="seleccion3" value="campo3">Esto es Campo 3</td>
              	<td><input type="checkbox" name="seleccion4" value="campo4">Esto es Campo 4</td>
              	<td><input type="checkbox" name="seleccion5" value="campo5">Esto es Campo 5</td>
            
             
             <!--   <c:forEach items = "${campos_list}" var = "campoi" >
					 
				 <td><input type="checkbox"  value="${campoi}" >
						</td>
				
			</c:forEach >-->
				
            
            </tr>
            <tr>
                <td><input type="submit" name="submit" value="Submit"></td>
            </tr>
          
        </table>
    </form>
 
</body>
</html>