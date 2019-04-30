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
import es.upm.dit.isst.rgpd.dao.SolicitudDAO;
import es.upm.dit.isst.rgpd.dao.SolicitudDAOImplementation;
import es.upm.dit.isst.rgpd.model.Investigador;
import es.upm.dit.isst.rgpd.model.Solicitud;



@WebServlet({ "/InvestigadorServlet"})
public class InvestigadorServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		int nSolVacia = 0;
		int nSolCurso= 0;
		int nSolActualiz = 0;
		int nSolCerrado= 0;

		InvestigadorDAO idao = InvestigadorDAOImplementation.getInstance();
		Investigador investigador = idao.read(email);
		req.getSession().setAttribute( "investigador", investigador);	
		
		Collection <Solicitud> solicitudesPropias = investigador.getSolicitudesPropias(); 
		Collection <Solicitud> sinRepetir = new ArrayList <Solicitud>(); 
		Collection <Solicitud> vacias = new ArrayList <Solicitud>(); 
		Collection <Solicitud> curso = new ArrayList <Solicitud>(); 
		Collection <Solicitud> ampliacion = new ArrayList <Solicitud>(); 
		Collection <Solicitud> cerradas = new ArrayList <Solicitud>(); 
		for(Solicitud s : solicitudesPropias) {
			if(s != null) { 
				if (!sinRepetir.contains(s)) { 
					sinRepetir.add(s);
					} 
				} 
			}
		
		for(Solicitud s : sinRepetir) {
			if(s.getEstado()  < 4) { 
				nSolVacia++;
				vacias.add(s);
			} 
			else if(s.getEstado() == 4){
				nSolCurso++;
				curso.add(s);
			}
			else if(s.getEstado() == 5){
				nSolActualiz++;
				ampliacion.add(s);
			}
			else if(s.getEstado() > 5 && s.getEstado() < 8){
				nSolCurso++;
				curso.add(s);
			}
			else if(s.getEstado() == 8){
				nSolCerrado++;
				cerradas.add(s);
			}
		}
		
		req.getSession().setAttribute( "solicitudes_list", sinRepetir);
		req.getSession().setAttribute( "solicitudes_vacias", vacias);
		req.getSession().setAttribute( "solicitudes_encurso", curso);
		req.getSession().setAttribute( "solicitudes_actualizar", ampliacion);
		req.getSession().setAttribute( "solicitudes_cerradas", cerradas);
		req.getSession().setAttribute( "n_solicitudes_vacias", nSolVacia);
		req.getSession().setAttribute( "n_solicitudes_encurso", nSolCurso);
		req.getSession().setAttribute( "n_solicitudes_actualizar", nSolActualiz);
		req.getSession().setAttribute( "n_solicitudes_cerradas", nSolCerrado);
		
		if (req.getParameter("volverincompletas") != null) {
			getServletContext().getRequestDispatcher("/VerSolicitudVaciaView.jsp").forward(req,resp);
		}
		else if (req.getParameter("volverencurso") != null) {
			getServletContext().getRequestDispatcher("/VerSolicitudCursoView.jsp").forward(req,resp);
		}
		else if (req.getParameter("volverampliacion") != null) {
			getServletContext().getRequestDispatcher("/VerSolicitudActualizarView.jsp").forward(req,resp);
		}
		else if (req.getParameter("volvercerradas") != null) {
			getServletContext().getRequestDispatcher("/VerSolicitudCerradaView.jsp").forward(req,resp);
		}
		else {
		getServletContext().getRequestDispatcher("/InvestigadorView.jsp").forward(req,resp);
		}
	}

}