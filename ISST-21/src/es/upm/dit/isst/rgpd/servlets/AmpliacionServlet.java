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

		resp.sendRedirect( req.getContextPath() + "/InvestigadorServlet?email=" + currentUser.getPrincipal() );

		//Codigo para enviar email al investigador
		String recipient = req.getParameter("email");
		String subject = "[RGPD] Ampliación enviada para la solicitud: " +  solicitud.getTitulo();
		String content = "Hola investigador/a.\r\n\r\n"
				+ "Has añadido a solicitud con id "+  req.getParameter("id") +" la ampliación requerida.\r\n\r\n"
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
		}
		
	}

}
