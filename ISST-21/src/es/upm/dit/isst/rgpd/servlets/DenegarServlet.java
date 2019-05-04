package es.upm.dit.isst.rgpd.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import es.upm.dit.isst.rgpd.dao.EvaluacionDAO;
import es.upm.dit.isst.rgpd.dao.EvaluacionDAOImplementation;
import es.upm.dit.isst.rgpd.dao.InvestigadorDAO;
import es.upm.dit.isst.rgpd.dao.InvestigadorDAOImplementation;
import es.upm.dit.isst.rgpd.dao.SolicitudDAO;
import es.upm.dit.isst.rgpd.dao.SolicitudDAOImplementation;
import es.upm.dit.isst.rgpd.model.Evaluacion;
import es.upm.dit.isst.rgpd.model.Investigador;
import es.upm.dit.isst.rgpd.model.Solicitud;
import es.upm.dit.isst.rgpd.model.EvaluacionKey;

@WebServlet( "/DenegarServlet")

public class DenegarServlet extends HttpServlet{
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
	
		
		String idString = req.getParameter("id");
		Long id = Long.parseLong(idString);
		
		EvaluacionDAO edao = EvaluacionDAOImplementation.getInstance();
		Evaluacion evaluacion = edao.read(id);
		SolicitudDAO sdao = SolicitudDAOImplementation.getInstance();
		Solicitud solicitud = evaluacion.getSolicitud();
	
		
		//primer evaluador
		if(evaluacion.getSolicitud().getEstado()==4 || evaluacion.getSolicitud().getEstado()==6) {
			evaluacion.getSolicitud().setEstado(7);
			solicitud.setEstado(7);
			evaluacion.setResultado("Denegado");
		//segundo evaluador
		}else if(evaluacion.getSolicitud().getEstado()==7) {
			evaluacion.getSolicitud().setEstado(8);
			solicitud.setEstado(8);
			evaluacion.setResultado("Denegado");
			
			//Correo ha sido denegado
			String recipient = solicitud.getInvestigador().getEmail();
			String subject = "[RGPD] Solicitud Denegada: " + solicitud.getTitulo();
			String content = "Hola " + solicitud.getInvestigador().getName() + ".\r\n\r\n"
					+ "La solicitud con id "+  solicitud.getId() +" y título \"" +solicitud.getTitulo() 
					+"\" no ha sido aceptada por los miembros del tribunal de Ética.\r\n\r\n"
					+ "-----------------------------------------------\r\n"
					+ "Este correo ha sido generado automáticamente.\r\n" 
					+"No responda a este correo, este buzón automático no es revisado.\r\n" 
					+"Para revisar sus solicitudes, por favor, revíselas vía web.";
			String resultMessage = "";
			try {
				EmailUtility.sendEmail(host, port, user, pass, recipient, subject, content);
				resultMessage = "The e-mail was sent successfully";
			} catch  (Exception ex) {
				ex.printStackTrace();
				resultMessage = "There were an error: " + ex.getMessage();
			}
		//incompleto
		}else {
			evaluacion.getSolicitud().setEstado(evaluacion.getSolicitud().getEstado());
		}
		//actualizo las tablas
		edao.update(evaluacion);
		sdao.update(solicitud);	

		String email = evaluacion.getEvaluador().getEmail();
		
		//mando datos que necesita la siguiente vista
		req.getSession().setAttribute( "id", id );
		resp.sendRedirect(req.getContextPath() + "/EvaluadorServlet?email=" + email);
	}
		
}
