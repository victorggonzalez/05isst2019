package es.upm.dit.isst.rgpd.checkbox.model;


import java.util.ArrayList;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.ui.Model;

@Entity
public class Formulario {
	
	//private boolean nuevoFormulario;
	
	private List<String> campos;
	//private String [] campos;
	
	/*public String initForm(Model model) {
		Formulario formulario = new Formulario();
    //esto es para marcar por defecto algunos campos
    //List<String> preCheckedVals = new ArrayList<String>();
    // para pre-checkear valor preCheckedVals.add("campox");
    //formulario.setCampos(preCheckedVals);
		model.addAttribute("formulario", formulario);
		List<String> campos = new ArrayList<String>();
    	campos.add("Campo1");
    	campos.add("Campo2");
    	campos.add("Campo3");
    	campos.add("Campo4");
    	campos.add("Campo5");
    	model.addAttribute("campos", campos);
    	return "formulario";
	}*/
	

	/*public boolean isNuevoFormulario() {
		return nuevoFormulario;
	}



	public void setNuevoFormulario(boolean nuevoFormulario) {
		this.nuevoFormulario = nuevoFormulario;
	}*/


/*
	public void setCampos(String[] campos) {
		this.campos = campos;
	}

	public String[] getCampos() {
		return campos;
	}*/



	public List<String> getCampos() {
		return campos;
	}



	public void setCampos(List<String> campos) {
		this.campos = campos;
	}

}
