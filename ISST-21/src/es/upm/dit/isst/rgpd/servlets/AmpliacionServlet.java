package es.upm.dit.isst.rgpd.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import es.upm.dit.isst.rgpd.dao.SolicitudDAO;
import es.upm.dit.isst.rgpd.dao.SolicitudDAOImplementation;
import es.upm.dit.isst.rgpd.model.Evaluacion;
import es.upm.dit.isst.rgpd.model.Evaluador;
import es.upm.dit.isst.rgpd.model.Solicitud;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig
@WebServlet( "/AmpliacionServlet")
public class AmpliacionServlet extends HttpServlet{

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
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Part filePart = req.getPart("ampliacion");
		InputStream fileContent = (InputStream) filePart.getInputStream();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[10240];
		for (int length = 0; (length = fileContent.read(buffer)) > 0;) output.write(buffer, 0, length);
	
		
		String idString = req.getParameter( "id" );
		Long id = Long.parseLong(idString);
		SolicitudDAO sdao = SolicitudDAOImplementation.getInstance();
		Solicitud solicitud = sdao.read(id);
		solicitud.setAmpliacion(output.toByteArray());
		solicitud.setEstado(6);
		sdao.update(solicitud);

		Subject currentUser = SecurityUtils.getSubject(); 
		req.getSession().setAttribute( "id", id );
		req.getSession().setAttribute( "solicitud", solicitud );
		req.getSession().setAttribute( "email", req.getParameter("email") );

		//Codigo para enviar email al investigador

		String recipient = solicitud.getInvestigador().getEmail();
		String subject = "[RGPD] Ampliacion enviada para la solicitud: " +  solicitud.getTitulo();

		String content = "Hola " + solicitud.getInvestigador().getName() + ".\r\n\r\n"

				+ "Has añadido a la solicitud con id "+  req.getParameter("id") +" la ampliacion requerida.\r\n\r\n"

				+ "-----------------------------------------------\r\n"
				+ "Este correo ha sido generado automáticamente.\r\n" 
				+"No responda a este correo, este buzón automático no es revisado.\r\n" 
				+"Para revisar sus solicitudes, por favor, revíselas vía web.";

		//CÃ³digo para enviar email a los evaluadores
		Collection<Evaluacion> evaluacionesSolicitud = solicitud.getEvaluaciones();
		Object[] evaluaciones = evaluacionesSolicitud.toArray();
		
		Evaluacion evaluacion1 = (Evaluacion) evaluaciones[0];
		Evaluador evaluador1 = evaluacion1.getEvaluador();
		Evaluacion evaluacion2 = (Evaluacion) evaluaciones[1];
		Evaluador evaluador2 = evaluacion2.getEvaluador();
		
		String recipientEv1 = evaluador1.getEmail();
		String recipientEv2 = evaluador2.getEmail();
		String subjectEv = "[RGPD] Ampliacion realizada: " +  solicitud.getTitulo();
		String contentEv = "Hola evaluador/a.\r\n\r\n"

				+ "Ha sido añadida a la solicitud con id "+  req.getParameter("id") +" la ampliación requerida.\r\n\r\n"

				+ "-----------------------------------------------\r\n"
				+ "Este correo ha sido generado automáticamente.\r\n" 
				+"No responda a este correo, este buzón automático no es revisado.\r\n" 
				+"Para revisar sus solicitudes, por favor, revíselas vía web.";
	
		System.out.println("email es " + recipientEv1);
		System.out.println(recipientEv2);
		System.out.println(recipient);
		String resultMessage = "";
		try {
			EmailUtility.sendEmail(host, port, user, pass, recipient, subject, content);
			EmailUtility.sendEmail(host, port, user, pass, recipientEv1, subjectEv, contentEv);
			EmailUtility.sendEmail(host, port, user, pass, recipientEv2, subjectEv, contentEv);
			resultMessage = "The e-mail was sent successfully";
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMessage = "There were an error: " + ex.getMessage();
		} finally {
			req.setAttribute("Message", resultMessage);
			resp.sendRedirect(req.getContextPath() + "/InvestigadorServlet?email=" + currentUser.getPrincipal());
			//getServletContext().getRequestDispatcher("/Result.jsp").forward(req, resp);
		}
		
	}

}
