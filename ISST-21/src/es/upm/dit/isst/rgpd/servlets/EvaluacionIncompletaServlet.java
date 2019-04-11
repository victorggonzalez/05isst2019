package es.upm.dit.isst.rgpd.servlets;


	import java.io.IOException;
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	import org.apache.shiro.crypto.hash.Sha256Hash;
	
	import es.upm.dit.isst.rgpd.model.Solicitud;
	import es.upm.dit.isst.rgpd.dao.SolicitudDAO;
	import es.upm.dit.isst.rgpd.dao.SolicitudDAOImplementation;

	@WebServlet( "/EvaluacionIncompletaServlet")
	public class EvaluacionIncompletaServlet extends HttpServlet {
		
		
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		String datos = req.getParameter("faltandatos");

		String idstring = req.getParameter( "id" );
		Long id = Long.parseLong(idstring);
		SolicitudDAO sdao = SolicitudDAOImplementation.getInstance();
		Solicitud solicitud = sdao.read(id);
		solicitud.setEstado(5);	
		sdao.update(solicitud);
		
		String email = req.getParameter("emailEvaluador");
		req.getSession().setAttribute("emailEvaluador", email);
		getServletContext().getRequestDispatcher("/EvaluadorView.jsp").forward(req, resp);
	}
}	


