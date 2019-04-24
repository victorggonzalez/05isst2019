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

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import es.upm.dit.isst.rgpd.dao.SolicitudDAO;
import es.upm.dit.isst.rgpd.dao.SolicitudDAOImplementation;
import es.upm.dit.isst.rgpd.model.Solicitud;


@MultipartConfig
@WebServlet( "/AmpliacionServlet")
public class AmpliacionServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Part filePart = req.getPart("ampliacion");
		InputStream fileContent = (InputStream) filePart.getInputStream();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[10240];
		for (int length = 0; (length = fileContent.read(buffer)) > 0;) output.write(buffer, 0, length);
	
		
		String idString = req.getParameter( "id" );
		Long id = Long.parseLong(idString);
		SolicitudDAO sdao = SolicitudDAOImplementation.getInstance();
		Solicitud solicitud = sdao.read(id);
		solicitud.setAmpliacion(output.toByteArray());
		solicitud.setEstado(6);
		sdao.update(solicitud);

		Subject currentUser = SecurityUtils.getSubject(); 
		req.getSession().setAttribute( "id", id );
		req.getSession().setAttribute( "solicitud", solicitud );
		req.getSession().setAttribute( "email", req.getParameter("email") );

		resp.sendRedirect( req.getContextPath() + "/InvestigadorServlet?email=" + currentUser.getPrincipal() );

		
		
	}

}
