package es.upm.dit.isst.rgpd.servlets;

import java.io.IOException;

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

@WebServlet( "/AceptarServlet")
public class AceptarServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//String idsString = req.getParameter( "ids" );
		String ideString = req.getParameter("ide");
		Long ide = Long.parseLong(ideString);
		//Long ids = Long.parseLong(idsString);
		
		EvaluacionDAO edao = EvaluacionDAOImplementation.getInstance();
		Evaluacion evaluacion = edao.read(ide);
		//SolicitudDAO sdao = SolicitudDAOImplementation.getInstance();
		//Solicitud solicitud = sdao.read(ids);
		
		//primer evaluador
		if(evaluacion.getSolicitud().getEstado()==4 || evaluacion.getSolicitud().getEstado()==6) {
			evaluacion.getSolicitud().setEstado(7);
			evaluacion.setResultado("Aprobado");
		//segundo evaluador
		}else if(evaluacion.getSolicitud().getEstado()==7) {
			evaluacion.getSolicitud().setEstado(8);
			evaluacion.setResultado("Aprobado");
		//incompleto
		}else {
			evaluacion.getSolicitud().setEstado(evaluacion.getSolicitud().getEstado());
		}
		//actualizo las tablas
		//sdao.update(solicitud);
		edao.update(evaluacion);
		
		//mando datos que necesita la siguiente vista
		//req.getSession().setAttribute( "ids", ids );
		req.getSession().setAttribute( "ide", ide );		
		getServletContext().getRequestDispatcher( "/EvaluadorView.jsp" ).forward( req, resp );
	
	
	}
		
}
