package es.upm.dit.isst.rgpd.servlets;


	import java.io.IOException;
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

	@WebServlet( "/EvaluacionIncompletaServlet")
	public class EvaluacionIncompletaServlet extends HttpServlet {
		
		
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
		
		req.getSession().setAttribute("emailEvaluador", email);
		req.getSession().setAttribute("id", id);
		resp.sendRedirect(req.getContextPath() + "/EvaluadorServlet?email=" + email);
	
	}
}	


