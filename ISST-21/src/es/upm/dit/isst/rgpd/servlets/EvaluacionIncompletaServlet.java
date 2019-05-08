package es.upm.dit.isst.rgpd.servlets;


import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.Sha256Hash;

import es.upm.dit.isst.rgpd.model.Evaluacion;
import es.upm.dit.isst.rgpd.model.Solicitud;
import es.upm.dit.isst.rgpd.dao.EvaluacionDAO;
import es.upm.dit.isst.rgpd.dao.EvaluacionDAOImplementation;
import es.upm.dit.isst.rgpd.dao.SolicitudDAO;
	import es.upm.dit.isst.rgpd.dao.SolicitudDAOImplementation;
	
	import java.io.IOException;
	import java.util.Collection;
	import java.util.Objects;


	import org.apache.shiro.SecurityUtils;
	import org.apache.shiro.authc.UsernamePasswordToken;
	import org.apache.shiro.subject.Subject;


	import es.upm.dit.isst.rgpd.dao.InvestigadorDAO;
	import es.upm.dit.isst.rgpd.dao.InvestigadorDAOImplementation;
	
	import es.upm.dit.isst.rgpd.model.Evaluacion;
	import es.upm.dit.isst.rgpd.model.Investigador;
	import es.upm.dit.isst.rgpd.model.Solicitud;

	@WebServlet( "/EvaluacionIncompletaServlet")
	public class EvaluacionIncompletaServlet extends HttpServlet {
		
		private String host;
		private String port;
		private String user;
		private String pass;

		public void init() {
			// reads SMTP server setting from web.xml file
			ServletContext context = getServletContext();
			host = context.getInitParameter("host");
			port = context.getInitParameter("port");
			user = context.getInitParameter("user");
			pass = context.getInitParameter("pass");
		}
		
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		String datos = req.getParameter("faltandatos");
		
		String idstring = req.getParameter( "id" );
		Long id = Long.parseLong(idstring);
		EvaluacionDAO edao = EvaluacionDAOImplementation.getInstance();
		Evaluacion evaluacion = edao.read(id);
		
		SolicitudDAO sdao = SolicitudDAOImplementation.getInstance();
		Solicitud solicitud = evaluacion.getSolicitud();
	
		solicitud.setEstado(5);
		solicitud.setFaltanDatos(datos);
		sdao.update(solicitud);
		edao.update(evaluacion);
		
		String email = evaluacion.getEvaluador().getEmail();
		
		//Codigo para enviar email al investigador cuando se le pide una ampliación
		String recipient = solicitud.getInvestigador().getEmail();
		String subject = "[RGPD] Solicitud " +  solicitud.getTitulo() + " incompleta";
		String content = "Hola" + solicitud.getInvestigador().getName() + ".\r\n\r\n"
				+ "A la solicitud con id "+  req.getParameter("id") +" le faltan los siguientes datos:\r\n"
				+ req.getParameter("faltandatos") +"\r\n"
				+ "Acceda al portal web para completar su solicitud.\r\n\r\n"
				+ "-----------------------------------------------\r\n"
				+ "Este correo ha sido generado automáticamente.\r\n" 
				+"No responda a este correo, este buzón automático no es revisado.\r\n" 
				+"Para revisar sus solicitudes, por favor, revíselas vía web.";

		String resultMessage = "";
		try {
			EmailUtility.sendEmail(host, port, user, pass, recipient, subject, content);
			resultMessage = "The e-mail was sent successfully";
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMessage = "There were an error: " + ex.getMessage();
		} finally {
			req.setAttribute("Message", resultMessage);
			resp.sendRedirect(req.getContextPath() + "/InvestigadorServlet?email=" + req.getParameter("email"));
			//getServletContext().getRequestDispatcher("/Result.jsp").forward(req, resp);
		
			req.getSession().setAttribute("emailEvaluador", email);
		req.getSession().setAttribute("id", id);
		resp.sendRedirect(req.getContextPath() + "/EvaluadorServlet?email=" + email);
	
	}
}	

	}


