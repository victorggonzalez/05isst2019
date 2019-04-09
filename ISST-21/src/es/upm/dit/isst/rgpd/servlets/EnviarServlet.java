package es.upm.dit.isst.rgpd.servlets;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.rgpd.dao.SolicitudDAOImplementation;
import es.upm.dit.isst.rgpd.dao.EvaluadorDAO;
import es.upm.dit.isst.rgpd.dao.EvaluadorDAOImplementation;
import es.upm.dit.isst.rgpd.dao.SolicitudDAO;
import es.upm.dit.isst.rgpd.model.Evaluador;
import es.upm.dit.isst.rgpd.model.Solicitud;

@WebServlet( "/MemoriaServlet")
public class EnviarServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter( "id" );
		SolicitudDAO sdao = SolicitudDAOImplementation.getInstance();
		Solicitud solicitud = sdao.read(id);
		solicitud.setEstado(5);
		EvaluadorDAO edao = EvaluadorDAOImplementation.getInstance();
		Collection<Evaluador> evaluadores = edao.readAll();
		Evaluador[] evaluadoresArray = (Evaluador[]) evaluadores.toArray();
		solicitud.setEvaluador1(evaluadoresArray[0]);
		solicitud.setEvaluador2(evaluadoresArray[1]);
		sdao.update(solicitud);
		resp.sendRedirect( req.getContextPath() + "/InvestigaddorServlet?email=" + req.getParameter("emailInvestigador") );

	}

}
