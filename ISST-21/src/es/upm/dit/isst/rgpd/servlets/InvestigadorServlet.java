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
			} 
			else if(s.getEstado() == 4){
				nSolCurso++;
			}
			else if(s.getEstado() == 5){
				nSolActualiz++;
			}
			else if(s.getEstado() > 5 && s.getEstado() < 8){
				nSolCurso++;
			}
			else if(s.getEstado() == 8){
				nSolCerrado++;
			}
		}
		
		req.getSession().setAttribute( "solicitudes_list", sinRepetir);
		req.getSession().setAttribute( "solicitudes_vacias", nSolVacia);
		req.getSession().setAttribute( "solicitudes_encurso", nSolCurso);
		req.getSession().setAttribute( "solicitudes_actualizar", nSolActualiz);
		req.getSession().setAttribute( "solicitudes_cerradas", nSolCerrado);
		 
		
		getServletContext().getRequestDispatcher("/InvestigadorView.jsp").forward(req,resp);

	}

}