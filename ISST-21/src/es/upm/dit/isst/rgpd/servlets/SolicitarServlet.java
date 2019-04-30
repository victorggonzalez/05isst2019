package es.upm.dit.isst.rgpd.servlets;

import java.io.IOException;
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
import es.upm.dit.isst.rgpd.model.Evaluador;
import es.upm.dit.isst.rgpd.model.Investigador;
import es.upm.dit.isst.rgpd.model.Solicitud;

@WebServlet( "/SolicitarServlet")

public class SolicitarServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SolicitudDAO sdao = SolicitudDAOImplementation.getInstance();
		String idString = req.getParameter("id");
		Long id = Long.parseLong(idString);
		Solicitud solicitud = sdao.read(id);
		req.getSession().setAttribute("solicitud",solicitud);
		req.getSession().setAttribute("id", id);	
		String email = req.getParameter("email");
		InvestigadorDAO idao = InvestigadorDAOImplementation.getInstance();
		Investigador investigador = idao.read(email);
		
		//Comprobar si hay evaluadores suficientes
		EvaluadorDAO edao = EvaluadorDAOImplementation.getInstance();
		Collection<Evaluador> evaluadores = edao.readAll();
		Object[] evaluadoresArray = evaluadores.toArray();
		if (evaluadoresArray.length < 2) {
			req.getSession().setAttribute("no_suficientes_investigadores", true);
		}
		
		req.getSession().setAttribute( "solicitudes_list", investigador.getSolicitudesPropias());
		getServletContext().getRequestDispatcher( "/SolicitudView.jsp" ).forward( req, resp );

		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String emailInvestigador = req.getParameter( "emailInvestigador" );
		
		InvestigadorDAO idao = InvestigadorDAOImplementation.getInstance();
		Investigador investigador = idao.read(emailInvestigador);
		String titulo = req.getParameter("titulo");
		
		//Aplicar logica
		Solicitud solicitud = new Solicitud();
		
		solicitud.setInvestigador(investigador);
		solicitud.setTitulo(titulo);
		solicitud.setEstado(1);
		
		//Persistir los datos
		SolicitudDAO sdao = SolicitudDAOImplementation.getInstance();
		sdao.create(solicitud);
		Long id = solicitud.getId();
		
		//Comprobar si hay evaluadores suficientes
		EvaluadorDAO edao = EvaluadorDAOImplementation.getInstance();
		Collection<Evaluador> evaluadores = edao.readAll();
		Object[] evaluadoresArray = evaluadores.toArray();
		if (evaluadoresArray.length < 2) {
			req.getSession().setAttribute("no_suficientes_investigadores", true);
		}
		
		req.getSession().setAttribute("id", id);
		req.getSession().setAttribute("solicitud", solicitud);
		req.getSession().setAttribute("emailInvestigador", emailInvestigador);
		getServletContext().getRequestDispatcher( "/SolicitudView.jsp" ).forward( req, resp );
		
	}

}
