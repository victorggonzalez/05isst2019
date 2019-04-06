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
               <c:forEach items = "${campos}" var = "campoi" >
					 <tr>
					  <td><input type="checkbox" path="campos" value="${campoi}" > 
						</td>
					</tr>
				</c:forEach >
				
          
            </tr>
            <tr>
                <td><input type="submit" name="submit" value="Submit"></td>
            </tr>
          
        </table>
    </form>
 
</body>
</html>