package es.upm.dit.isst.rgpd.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import antlr.collections.List;
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
	private String host;
	private String port;
	private String user;
	private String pass;

	public void init() {
		// reads SMTP server setting from web.xml file
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		user = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
	}

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
		
		//ColecciÃ³n de evaluadores
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
			
			//Seleccion del primer evaluador entre todos los posibles
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
			
			
			
			//AsignaciÃ³n del primer evaluador
			  Evaluacion evaluacion1 = new Evaluacion();
			  evaluacion1.setEvaluador(evaluadorConMenosCarga1);
			  evaluacion1.setSolicitud(solicitud);
			  evaluacion1.setResultado("Sin evaluar");
			  
			//AsignaciÃ³n del segundo evaluador
			  Evaluacion evaluacion2 = new Evaluacion();
			  evaluacion2.setEvaluador(evaluadorConMenosCarga2);
			  evaluacion2.setSolicitud(solicitud);
			  evaluacion2.setResultado("Sin evaluar");
			  
			  //List lista = (List) new ArrayList<Evaluacion>();
			  //lista.add(evaluacion1);
			  //lista.add(evaluacion2);
			  //Collection<Evaluacion> evaluaciones = (Collection<Evaluacion>) lista;
			  //solicitud.setEvaluaciones(evaluaciones);
			  //sdao.update(solicitud);
			  
			  EvaluacionDAO evdao = EvaluacionDAOImplementation.getInstance();
			  evdao.create(evaluacion1); 
			  evdao.create(evaluacion2);
			 
			String email = req.getParameter("email");

			InvestigadorDAO idao = InvestigadorDAOImplementation.getInstance();
			Investigador investigador = idao.read(email);
			req.getSession().setAttribute("investigador", investigador);
			req.getSession().setAttribute("solicitudes_list", investigador.getSolicitudesPropias());
			
			
			//Codigo para enviar email al investigador
			String recipient = req.getParameter("email");
			String subject = "[RGPD] Solicitud creada: " +  solicitud.getTitulo();
			String content = "Hola investigador/a.\r\n\r\n"
					+ "La solicitud con id "+  req.getParameter("id") +" se ha abierto correctamente, y ha sido enviada para su evaluaciï¿½n.\r\n\r\n"
					+ "-----------------------------------------------\r\n"
					+ "Este correo ha sido generado automáticamente.\r\n" 
					+"No responda a este correo, este buzón automático no es revisado.\r\n" 
					+"Para revisar sus solicitudes, por favor, revíselas vía web.";

			
			//Codigo para enviar email a los evaluadores
			String recipient2 = evaluadorConMenosCarga1.getEmail();
			String recipient3 = evaluadorConMenosCarga2.getEmail();

			String subject2 = "[RGPD] Solicitud asignada: " +  solicitud.getTitulo();
			String content2 = "Hola evaluador/a.\r\n\r\n"
					+ "Se le ha asignado la solicitud con id "+  req.getParameter("id") +".\r\n"
					+ "Acceda al portal web para proceder con su evaluacion.\r\n\r\n"
					+ "-----------------------------------------------\r\n"
					+ "Este correo ha sido generado automáticamente.\r\n" 
					+"No responda a este correo, este buzón automático no es revisado.\r\n" 
					+"Para revisar sus solicitudes, por favor, revíselas vía web.";

			String resultMessage = "";
			try {
				EmailUtility.sendEmail(host, port, user, pass, recipient, subject, content);
				EmailUtility.sendEmail(host, port, user, pass, recipient2, subject2, content2);
				EmailUtility.sendEmail(host, port, user, pass, recipient3, subject2, content2);

				resultMessage = "The e-mail was sent successfully";
			} catch (Exception ex) {
				ex.printStackTrace();
				resultMessage = "There were an error: " + ex.getMessage();
			} finally {
				req.setAttribute("Message", resultMessage);
				resp.sendRedirect(req.getContextPath() + "/InvestigadorServlet?email=" + req.getParameter("email"));
				//getServletContext().getRequestDispatcher("/Result.jsp").forward(req, resp);
			}
			
		}
	}

}
