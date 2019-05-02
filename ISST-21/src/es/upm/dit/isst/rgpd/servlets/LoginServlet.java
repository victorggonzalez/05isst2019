package es.upm.dit.isst.rgpd.servlets;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import es.upm.dit.isst.rgpd.dao.EvaluadorDAO;
import es.upm.dit.isst.rgpd.dao.EvaluadorDAOImplementation;


@WebServlet({ "/LoginServlet", "/" })
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Ver si se acaba de registrar
		String registrado = req.getParameter("registrado");
		if(Objects.equals(registrado, "true")) {
			System.out.println("Alerta: Te has registrado correctamente");
			//Cambiar por un alert de verdad
			req.getSession().setAttribute( "registrado", registrado);
		} else {
			req.getSession().setAttribute("registrado", null);
		}
		
		//Ver si ha puesto un usuario pass incorrecto
		String incorrecto = req.getParameter("incorrecto");
		if(Objects.equals(incorrecto, "true")) {
			req.getSession().setAttribute("incorrecto", incorrecto);
		} else {
			req.getSession().setAttribute("incorrecto", null);
		}
		
		getServletContext().getRequestDispatcher( "/LoginView.jsp" ).forward( req,resp );
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		  String email = req.getParameter( "email" ); 
		  String pass = req.getParameter("password" ); 
		  Subject currentUser = SecurityUtils.getSubject(); 
		  if (!currentUser.isAuthenticated() ) { 
			  UsernamePasswordToken token = new UsernamePasswordToken( email, pass ); 
			  try { 
				  currentUser.login( token ); 
				  if (currentUser.hasRole( "admin" ) ) 
					  resp.sendRedirect( req.getContextPath() +"/AdminServlet" ); 
				  else if ( currentUser.hasRole( "investigador" ) )
					  resp.sendRedirect( req.getContextPath() + "/InvestigadorServlet?email=" + currentUser.getPrincipal() ); 
				  else resp.sendRedirect( req.getContextPath() + "/EvaluadorServlet?email=" + currentUser.getPrincipal() );
				  } catch ( Exception e ){ 
					  boolean incorrecto = true;
					  resp.sendRedirect( req.getContextPath() + "/LoginServlet?incorrecto=" + incorrecto );
					  } 
			  } else { 
				  resp.sendRedirect( req.getContextPath() + "/LoginServlet");
			  }

	}
}