package es.upm.dit.isst.rgpd.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.rgpd.model.Solicitud;

@WebServlet( "/MemoriaServlet")
public class EnviarServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter( "id" );
		Solicitud sdao = SolicitudDAOImplementation.getInstance();
		Solicitud solicitud = sdao.read(id);
		solicitud.setStatus(4);
		//solicitud.setEvaluador1();
		//solicitud.setEvaluador2();
		sdao.update(solicitud);
		resp.sendRedirect( req.getContextPath() + "/InvestigaddorServlet?email=" + req.getParameter("emailInvestigador") );

	}

}
