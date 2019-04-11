package es.upm.dit.isst.rgpd.servlets;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.rgpd.model.*;
import es.upm.dit.isst.rgpd.dao.*;


@WebServlet( "/EvaluarServlet")

public class EvaluarServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		
		
		//String idsString = req.getParameter( "ids" );
		//Long ids = Long.parseLong(idsString);
		//SolicitudDAO soldao = SolicitudDAOImplementation.getInstance();
		//Solicitud solicitud = soldao.read(ids);

		///////////////////
		
		String idString = req.getParameter( "id" );
		Long id = Long.parseLong(idString);	
		EvaluacionDAO evlcdao = EvaluacionDAOImplementation.getInstance();
		Evaluacion evaluacion = evlcdao.read(id);
	
		///////////////////
		
		String emailEvaluador = req.getParameter("email");
		EvaluadorDAO edao = EvaluadorDAOImplementation.getInstance();
		Evaluador evaluador = edao.read(emailEvaluador);
		
		
		//req.getSession().setAttribute( "ids", ids );
		//req.getSession().setAttribute( "solicitud", solicitud );
		req.getSession().setAttribute( "evaluacion", evaluacion);
		req.getSession().setAttribute( "id", id );
		req.getSession().setAttribute( "emailEvaluador", emailEvaluador );


				
		
		getServletContext().getRequestDispatcher("/EvaluacionView.jsp").forward(req,resp);

	}


}



