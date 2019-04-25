package es.upm.dit.isst.rgpd.servlets;

import java.io.IOException;

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
		// TODO Auto-generated method stub

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//String rol = req.getParameter("roll");
		String[] rol = req.getParameterValues( "rol" );

		if("evaluador".equals(rol[0])) {
			//Registrar a un evaluador
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String area = req.getParameter("area");
			String grupo = req.getParameter("grupo");
			
			
			//Aplicar logica
			Evaluador evaluador = new Evaluador();
			evaluador.setName(name); 
			evaluador.setEmail(email);
			evaluador.setArea(area); 
			evaluador.setGrupo(grupo); 
			evaluador.setPassword(new Sha256Hash( password ).toString());
			 
			//Persistir los datos
			EvaluadorDAO edao = EvaluadorDAOImplementation.getInstance();
			edao.create(evaluador);
			
			//Pensar a donde quiere ir esto
			resp.sendRedirect(req.getContextPath() + "/LoginServlet");
			
			
		}
		else {
			//Registrar a un investigador
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String area = req.getParameter("area");
			String grupo = req.getParameter("grupo");
			
			
			Investigador investigador = new Investigador();
			investigador.setName(name); investigador.setEmail(email);
			investigador.setArea(area); investigador.setGrupo(grupo);
			investigador.setPassword( new Sha256Hash( password ).toString());
			 
			
			InvestigadorDAO idao = InvestigadorDAOImplementation.getInstance();
			idao.create(investigador);
			
			//Pensar a donde quiere ir esto
			resp.sendRedirect(req.getContextPath() + "/LoginServlet");
			
		}		
	}
}
