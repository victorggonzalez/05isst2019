package es.upm.dit.isst.rgpd.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.rgpd.dao.SolicitudDAO;
import es.upm.dit.isst.rgpd.dao.SolicitudDAOImplementation;
import es.upm.dit.isst.rgpd.model.Solicitud;


@WebServlet("/ServeFileServlet")
public class ServeFileServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SolicitudDAO sdao = SolicitudDAOImplementation.getInstance();
		String idString = req.getParameter("id");
		Long id = Long.parseLong(idString);
		Solicitud solicitud = sdao.read(id);
		
		String tipoDocumento = req.getParameter("tipoDocumento");
		if (tipoDocumento.equals("memoria")) {
			resp.setContentLength(solicitud.getMemoria().length );
			resp.getOutputStream().write(solicitud.getMemoria());
		}
		else if (tipoDocumento.equals("ampliacion")){
			resp.setContentLength(solicitud.getAmpliacion().length );
			resp.getOutputStream().write(solicitud.getAmpliacion());
		}
		
	}

}
