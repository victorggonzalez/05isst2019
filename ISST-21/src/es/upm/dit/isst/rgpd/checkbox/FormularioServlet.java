package es.upm.dit.isst.rgpd.checkbox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
import es.upm.dit.isst.rgpd.checkbox.model.Formulario;
import es.upm.dit.isst.rgpd.dao.SolicitudDAO;
import es.upm.dit.isst.rgpd.dao.SolicitudDAOImplementation;
import es.upm.dit.isst.rgpd.model.Solicitud;

@Controller
//@RequestMapping("/formulario.html")
@WebServlet ("/FormularioServlet")
public class FormularioServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		Formulario formulario = new Formulario();
	    //esto es para marcar por defecto algunos campos
	    //List<String> preCheckedVals = new ArrayList<String>();
	    // para pre-checkear valor preCheckedVals.add("campox");
	    //formulario.setCampos(preCheckedVals);
			//List<String> campos = new ArrayList<String>();
	    	/*campos.add("campo1");
	    	campos.add("campo2");
	    	campos.add("campo3");
	    	campos.add("campo4");
	    	campos.add("ampo5");*/
	    	req.getSession().setAttribute("formulario", formulario);

			getServletContext().getRequestDispatcher( "/FormularioView.jsp" ).forward( req, resp );
	}
	
	
	//@RequestMapping(method = RequestMethod.GET)
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

			String [] selecciones = req.getParameterValues( "seleccion" );
			Solicitud solicitud = new Solicitud();
			solicitud.setFormulario(selecciones);
			
			//SolicitudDAO sdao = SolicitudDAOImplementation.getInstance();
			//sdao.read(id);
			
	    	req.getSession().setAttribute("camposseleccionados", selecciones);
	    	
			getServletContext().getRequestDispatcher( "/SeleccionesFormularioView.jsp" ).forward( req, resp );
	}
 
   /* @RequestMapping(method = RequestMethod.POST)
    public String submitForm(Model model, Formulario formulario,
            BindingResult result) {
        model.addAttribute("formulario", formulario);
        return "successFormulario";
    }*/

}
