package es.upm.dit.isst.rgpd.servlets;

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;

import es.upm.dit.isst.rgpd.dao.EvaluadorDAO;
import es.upm.dit.isst.rgpd.dao.EvaluadorDAOImplementation;
import es.upm.dit.isst.rgpd.dao.InvestigadorDAO;
import es.upm.dit.isst.rgpd.dao.InvestigadorDAOImplementation;
import es.upm.dit.isst.rgpd.model.Evaluador;
import es.upm.dit.isst.rgpd.model.Investigador;

@WebServlet({ "/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String repetido = req.getParameter("repetido");
		if(Objects.equals(repetido, "true")) {
			System.out.println("Alerta: ese email está repetido");
			//Cambiar por un alert de verdad
			req.getSession().setAttribute( "repetido", repetido);
		} else {
			req.getSession().setAttribute("repetido", null);
		}
		getServletContext().getRequestDispatcher( "/RegisterView.jsp" ).forward( req,resp );
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String[] rol = req.getParameterValues( "rol" );
		EvaluadorDAO edao = EvaluadorDAOImplementation.getInstance();
		InvestigadorDAO idao = InvestigadorDAOImplementation.getInstance();
		Collection<Evaluador> evaluadores = edao.readAll();
		Collection<Investigador> investigadores = idao.readAll();
		
		if("evaluador".equals(rol[0])) {
			//Registrar a un evaluador
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String area = req.getParameter("area");
			String grupo = req.getParameter("grupo");
			
			//Ver si no está registrado el email			
			Boolean repetido = false;
			for(Evaluador e: evaluadores) {
				if (Objects.equals(email, e.getEmail())) {
					repetido = true;
				}
			}
			for(Investigador i: investigadores) {
				if (Objects.equals(email, i.getEmail())) {
					repetido = true;
				}
			}
			if (!repetido) {
				//Aplicar logica
				Evaluador evaluador = new Evaluador();
				evaluador.setName(name); 
				evaluador.setEmail(email);
				evaluador.setArea(area); 
				evaluador.setGrupo(grupo); 
				evaluador.setPassword(new Sha256Hash( password ).toString());
				//Persistir los datos
				edao.create(evaluador);
				//Enviar alerta de que se ha registrado bien
				resp.sendRedirect(req.getContextPath() + "/LoginServlet?registrado=" + !repetido);
				System.out.println("Se supone que no está repetido");
			}
			else {
				//Enviar alerta de que el email está repetido
				System.out.println("Se supone que  está repetido");
				resp.sendRedirect(req.getContextPath() + "/RegisterServlet?repetido=" + repetido);

			}
			
			
		}
		else {
			//Registrar a un investigador
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String area = req.getParameter("area");
			String grupo = req.getParameter("grupo");
			
			//Ver si eesta registrado			
			Boolean repetido = false;
			for(Evaluador e: evaluadores) {
				if (Objects.equals(email, e.getEmail())) {
					repetido = true;
				}
			}
			for(Investigador i: investigadores) {
				if (Objects.equals(email, i.getEmail())) {
					repetido = true;
				}
			}
			
			if (!repetido) {
				Investigador investigador = new Investigador();
				investigador.setName(name); investigador.setEmail(email);
				investigador.setArea(area); investigador.setGrupo(grupo);
				investigador.setPassword( new Sha256Hash( password ).toString());
				idao.create(investigador);
				System.out.println("Se supone que no está repetido");
				//Enviar alerta de que se ha registrado bien
				resp.sendRedirect(req.getContextPath() + "/LoginServlet?registrado=" + !repetido);
			} else {
				//Enviar alerta de que el email está repetido
				System.out.println("Se supone que  está repetido");
				resp.sendRedirect(req.getContextPath() + "/RegisterServlet?repetido=" + repetido);			
			}
			
			
		}		
	}
}
