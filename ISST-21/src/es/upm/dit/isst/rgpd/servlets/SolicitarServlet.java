package es.upm.dit.isst.rgpd.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import es.upm.dit.isst.rgpd.model.Solicitud;

@WebServlet( "/SolicitarServlet")

public class SolicitarServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String emailInvestigador = req.getParameter( "emailInvestigador" );
		String titulo = req.getParameter("titulo");
		Solicitud solicitud = new Solicitud();
		solicitud.setInvestigador(emailInvestigador);
		solicitud.setTitulo(titulo);
		solicitud.setEstado(1);
		SolicitudDAO sdao = SolicitudDAOImplementation.getInstance();
		sdao.create(solicitud);
		int id = solicitud.getId();
		req.setAttribute("id", id);
		req.setAttribute("emailInvestigador", emailInvestigador);
		getServletContext().getRequestDispatcher( "/SolicitudView.jsp" ).forward( req, resp );
		
	}

}
