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
		
		String idString = req.getParameter( "id" );
		Long id = Long.parseLong(idString);
		SolicitudDAO sdao = SolicitudDAOImplementation.getInstance();
		Solicitud solicitud = sdao.read(id);
		
		//Comprobar si hay evaluadores suficientes
		EvaluadorDAO edao = EvaluadorDAOImplementation.getInstance();
		Collection<Evaluador> evaluadores = edao.readAll();
		Object[] evaluadoresArray = evaluadores.toArray();
		
		//Variable para comprobar la existencia de evaluadores suficientes
		int evaluadoresDelArea = 0;
		
		//Colección de evaluadores del mismo area
		Collection<Evaluador> evaluadoresPosibles = evaluadores;
		
		//Comprueba los evaluadores disponibles del mismo area que el investigador
		for (int i=0; i<evaluadoresArray.length; i++) {
			if( ((Evaluador) evaluadoresArray[i]).getArea() ==  solicitud.getInvestigador().getArea() 
					&&
					((Evaluador) evaluadoresArray[i]).getGrupo() != solicitud.getInvestigador().getGrupo()) {
				evaluadoresDelArea++;
			} else {
				evaluadoresPosibles.remove((Evaluador) evaluadoresArray[i]);
			}
		}
		
		//Comprueba que no son menos de 2
		if(evaluadoresDelArea < 2){
			req.getSession().setAttribute("id", id);
			req.getSession().setAttribute("solicitud", solicitud);
			req.getSession().setAttribute("no_suficientes_investigadores", true);
			getServletContext().getRequestDispatcher("/SolicitudView.jsp").forward(req, resp);
		}
		
		/*if (evaluadoresArray.length < 2) {
			req.getSession().setAttribute("id", id);
			req.getSession().setAttribute("solicitud", solicitud);
			req.getSession().setAttribute("no_suficientes_investigadores", true);
			getServletContext().getRequestDispatcher("/SolicitudView.jsp").forward(req, resp);

		} */
			
			else { 

			solicitud.setEstado(4);
			sdao.update(solicitud);
			
			//Selección del primer evaluador entre todos los posibles
			Object[] evaluadoresPosiblesArray = evaluadoresPosibles.toArray();
			Evaluador evaluadorConMenosCarga1 = (Evaluador) evaluadoresPosiblesArray[0];
			for (int i=0; i<evaluadoresPosiblesArray.length; i++) {
				if(((Evaluador) evaluadoresPosiblesArray[i]).getEvaluaciones().toArray().length < evaluadorConMenosCarga1.getEvaluaciones().toArray().length) {
					evaluadorConMenosCarga1 = (Evaluador) evaluadoresPosiblesArray[i];
				}
			}
			
			evaluadoresPosibles.remove(evaluadorConMenosCarga1);
			
			//Nuevo array de evaluadores posibles sin el seleccionado anteriormente
			Object[] evaluadoresPosiblesArray2 = evaluadoresPosibles.toArray();
			Evaluador evaluadorConMenosCarga2 = (Evaluador) evaluadoresPosiblesArray2[0];
			for (int i=0; i<evaluadoresPosiblesArray2.length; i++) {
				if(((Evaluador) evaluadoresPosiblesArray2[i]).getEvaluaciones().toArray().length < evaluadorConMenosCarga1.getEvaluaciones().toArray().length) {
					evaluadorConMenosCarga2 = (Evaluador) evaluadoresPosiblesArray2[i];
				}
			}
			
			
			
			//Asignación del primer evaluador
			  Evaluacion evaluacion1 = new Evaluacion();
			  evaluacion1.setEvaluador(evaluadorConMenosCarga1);
			  evaluacion1.setSolicitud(solicitud);
			  evaluacion1.setResultado("Sin evaluar");
			  
			//Asignación del segundo evaluador
			  Evaluacion evaluacion2 = new Evaluacion();
			  evaluacion2.setEvaluador(evaluadorConMenosCarga2);
			  evaluacion2.setSolicitud(solicitud);
			  evaluacion2.setResultado("Sin evaluar");
			  
			  
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
