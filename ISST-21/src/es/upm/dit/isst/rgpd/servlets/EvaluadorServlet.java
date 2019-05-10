package es.upm.dit.isst.rgpd.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

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
import es.upm.dit.isst.rgpd.dao.InvestigadorDAO;
import es.upm.dit.isst.rgpd.dao.InvestigadorDAOImplementation;
import es.upm.dit.isst.rgpd.model.Investigador;
import es.upm.dit.isst.rgpd.model.Solicitud;
import es.upm.dit.isst.rgpd.model.Evaluador;
import es.upm.dit.isst.rgpd.model.Evaluacion;


@WebServlet({ "/EvaluadorServlet"})
public class EvaluadorServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		
		int nEvTotales = 0;
		int nEvPendiente = 0;
		int nEvAmpliacion = 0;
		int nEvCerrada = 0;
		
		EvaluadorDAO edao = EvaluadorDAOImplementation.getInstance();		
		Evaluador evaluador = edao.read(email);
		
		Collection <Evaluacion> evaluaciones = evaluador.getEvaluaciones();
		Collection <Evaluacion> pendientes = new ArrayList <Evaluacion>(); 
		Collection <Evaluacion> faltaAmpliacion = new ArrayList <Evaluacion>();
		Collection <Evaluacion> cerradas = new ArrayList <Evaluacion>();
		
		req.getSession().setAttribute( "evaluador", evaluador);		
		req.getSession().setAttribute( "evaluaciones_list", evaluador.getEvaluaciones());
		
		for(Evaluacion e: evaluaciones) {
			nEvTotales++;
		}
		for (Evaluacion e: evaluaciones) {
			if ((e.getSolicitud().getEstado() == 4) || (e.getSolicitud().getEstado() == 6)) {
				nEvPendiente++;
				pendientes.add(e);
			}
			
			else if (e.getSolicitud().getEstado() == 5) {
				nEvAmpliacion++;
				faltaAmpliacion.add(e);
			}
			
			else if (e.isResultado().equals("Sin evaluar") && (e.getSolicitud().getEstado() == 7)) {
				nEvPendiente++;
				pendientes.add(e);
			}	
	
			else if ((e.isResultado().equals("Aprobado"))|| (e.isResultado().equals("Denegado") )) {
				nEvCerrada++;
				cerradas.add(e);
			}
			
		}
		
		req.getSession().setAttribute( "evaluaciones_totales", evaluaciones);
		req.getSession().setAttribute( "evaluaciones_pendientes", pendientes);
		req.getSession().setAttribute( "evaluaciones_ampliacion", faltaAmpliacion);
		req.getSession().setAttribute( "evaluaciones_cerradas", cerradas);
		req.getSession().setAttribute( "n_evaluaciones_totales", nEvTotales);
		req.getSession().setAttribute( "n_evaluaciones_pendientes", nEvPendiente);
		req.getSession().setAttribute( "n_evaluaciones_ampliacion", nEvAmpliacion);
		req.getSession().setAttribute( "n_evaluaciones_cerradas", nEvCerrada);
		
		if (req.getParameter("volverpendientes") != null) {
			getServletContext().getRequestDispatcher("/VerEvaluacionPendienteView.jsp").forward(req,resp);
		}
		else if (req.getParameter("volverampliacion") != null) {
			getServletContext().getRequestDispatcher("/VerEvaluacionAmpliacionView.jsp").forward(req,resp);
		}
		else if (req.getParameter("volvercerradas") != null) {
			getServletContext().getRequestDispatcher("/VerEvaluacionCerradaView.jsp").forward(req,resp);
		}
		else {
		getServletContext().getRequestDispatcher("/EvaluadorView.jsp").forward(req,resp);
		}
	}

}
