package es.upm.dit.isst.rgpd.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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
	
		
		String id = req.getParameter( "id" );
		Solicitud sdao = SolicitudDAOImplementation.getInstance();
		Solicitud solicitud = sdao.read(id);
		solicitud.setMemoria(output.toByteArray());
		solicitud.setStatus(3);
		sdao.update(solicitud);
		req.getSession().setAttribute( "emailInvestigador", req.getParameter("emailInvestigador") );
		getServletContext().getRequestDispatcher( "/SolicitudView.jsp" ).forward( req, resp );

		
		
	}

}
