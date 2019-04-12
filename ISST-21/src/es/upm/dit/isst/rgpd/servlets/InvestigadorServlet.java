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
		req.getSession().setAttribute( "solicitudes_list", sinRepetir);
		 
		
		getServletContext().getRequestDispatcher("/InvestigadorView.jsp").forward(req,resp);

	}

}