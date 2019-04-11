package es.upm.dit.isst.rgpd.servlets;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.rgpd.dao.SolicitudDAOImplementation;
import es.upm.dit.isst.rgpd.dao.EvaluacionDAO;
import es.upm.dit.isst.rgpd.dao.EvaluacionDAOImplementation;
import es.upm.dit.isst.rgpd.dao.EvaluadorDAO;
import es.upm.dit.isst.rgpd.dao.EvaluadorDAOImplementation;
import es.upm.dit.isst.rgpd.dao.InvestigadorDAO;
import es.upm.dit.isst.rgpd.dao.InvestigadorDAOImplementation;
import es.upm.dit.isst.rgpd.dao.SolicitudDAO;
import es.upm.dit.isst.rgpd.model.Evaluacion;
import es.upm.dit.isst.rgpd.model.Evaluador;
import es.upm.dit.isst.rgpd.model.Investigador;
import es.upm.dit.isst.rgpd.model.Solicitud;

@WebServlet("/EnviarServlet")
public class EnviarServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String idString = req.getParameter("id");
		Long id = Long.parseLong(idString);
		SolicitudDAO sdao = SolicitudDAOImplementation.getInstance();
		Solicitud solicitud = sdao.read(id);

		EvaluadorDAO edao = EvaluadorDAOImplementation.getInstance();
		Collection<Evaluador> evaluadores = edao.readAll();
		Object[] evaluadoresArray = evaluadores.toArray();

		if (evaluadoresArray.length < 2) {
			req.getSession().setAttribute("id", id);
			req.getSession().setAttribute("solicitud", solicitud);
			req.getSession().setAttribute("no_suficientes_investigadores", true);
			getServletContext().getRequestDispatcher("/SolicitudView.jsp").forward(req, resp);

		} else {

			solicitud.setEstado(4);
			sdao.update(solicitud);
			
			  Evaluacion evaluacion1 = new Evaluacion();
			  evaluacion1.setEvaluador((Evaluador) evaluadoresArray[0]);
			  evaluacion1.setSolicitud(solicitud);
			  evaluacion1.setResultado("sinEvaluar");
			  
			  Evaluacion evaluacion2 = new Evaluacion();
			  evaluacion2.setEvaluador((Evaluador) evaluadoresArray[1]);
			  evaluacion2.setSolicitud(solicitud);
			  evaluacion2.setResultado("sinEvaluar");
			  
			  EvaluacionDAO evdao = EvaluacionDAOImplementation.getInstance();
			  evdao.create(evaluacion1); 
			  evdao.create(evaluacion2);
			 
			

			String email = req.getParameter("email");

			InvestigadorDAO idao = InvestigadorDAOImplementation.getInstance();
			Investigador investigador = idao.read(email);
			req.getSession().setAttribute("investigador", investigador);
			req.getSession().setAttribute("solicitudes_list", investigador.getSolicitudesPropias());

			resp.sendRedirect(req.getContextPath() + "/InvestigadorServlet?email=" + req.getParameter("email"));
		}
	}

}
