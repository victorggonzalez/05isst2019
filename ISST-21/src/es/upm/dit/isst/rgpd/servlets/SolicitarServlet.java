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
		

		//Variable para comprobar la existencia de evaluadores suficientes
		int evaluadoresDelAreaPosibles = 0;

		
		//Comprueba los evaluadores disponibles del mismo area que el investigador
		for (int i=0; i<evaluadoresArray.length; i++) {
			if( ((Evaluador) evaluadoresArray[i]).getArea() ==  solicitud.getInvestigador().getArea()
					&&
					((Evaluador) evaluadoresArray[i]).getGrupo() != solicitud.getInvestigador().getGrupo()) {
				evaluadoresDelAreaPosibles++;
			}
		}
		
		//Comprueba que dentro de ese area los evaluadores son 2 o más
		if (evaluadoresDelAreaPosibles < 2) {
			req.getSession().setAttribute("no_suficientes_investigadores", true);
		}
		
		req.getSession().setAttribute("email", email);
		req.getSession().setAttribute( "solicitudes_list", investigador.getSolicitudesPropias());
		getServletContext().getRequestDispatcher( "/SolicitudView.jsp" ).forward( req, resp );

		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter( "emailInvestigador" );
		
		InvestigadorDAO idao = InvestigadorDAOImplementation.getInstance();
		Investigador investigador = idao.read(email);
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
		req.getSession().setAttribute("emailInvestigador", email);
		getServletContext().getRequestDispatcher( "/SolicitudView.jsp" ).forward( req, resp );
		
	}

}
