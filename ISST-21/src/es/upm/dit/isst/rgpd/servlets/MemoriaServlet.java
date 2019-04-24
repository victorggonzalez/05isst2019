package es.upm.dit.isst.rgpd.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import es.upm.dit.isst.rgpd.dao.EvaluadorDAO;
import es.upm.dit.isst.rgpd.dao.EvaluadorDAOImplementation;
import es.upm.dit.isst.rgpd.dao.SolicitudDAO;
import es.upm.dit.isst.rgpd.dao.SolicitudDAOImplementation;
import es.upm.dit.isst.rgpd.model.Evaluador;
import es.upm.dit.isst.rgpd.model.Solicitud;
@MultipartConfig
@WebServlet( "/MemoriaServlet")
public class MemoriaServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Part filePart = req.getPart("file");
		InputStream fileContent = (InputStream) filePart.getInputStream();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[10240];
		for (int length = 0; (length = fileContent.read(buffer)) > 0;) output.write(buffer, 0, length);
	
		
		String idString = req.getParameter( "id" );
		Long id = Long.parseLong(idString);
		SolicitudDAO sdao = SolicitudDAOImplementation.getInstance();
		Solicitud solicitud = sdao.read(id);
		solicitud.setMemoria(output.toByteArray());
		solicitud.setEstado(3);
		sdao.update(solicitud);
		EvaluadorDAO edao = EvaluadorDAOImplementation.getInstance();
		Collection<Evaluador> evaluadores = edao.readAll();

		Object[] evaluadoresArray = evaluadores.toArray();

		if (evaluadoresArray.length < 2) {
			req.getSession().setAttribute("id", id);
			req.getSession().setAttribute("solicitud", solicitud);
			req.getSession().setAttribute("no_suficientes_investigadores", true);
			getServletContext().getRequestDispatcher("/SolicitudView.jsp").forward(req, resp);
		}
		req.getSession().setAttribute( "id", id );
		req.getSession().setAttribute( "solicitud", solicitud );
		req.getSession().setAttribute( "emailInvestigador", req.getParameter("emailInvestigador") );
		getServletContext().getRequestDispatcher( "/SolicitudView.jsp" ).forward( req, resp );

		
		
	}

}
