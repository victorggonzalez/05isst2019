package es.upm.dit.isst.rgpd.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Evaluador implements Serializable{

	public Evaluador() {
	}

	@Id
	private String email;
	
	private String password;
	private String name;
	private String area;
	private String grupo;
	
	
	@ManyToMany
	private Collection<Solicitud> solicitudesEvaluadas;


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}


	public String getGrupo() {
		return grupo;
	}


	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}


	public Collection<Solicitud> getSolicitudesEvaluadas() {
		return solicitudesEvaluadas;
	}


	public void setSolicitudesEvaluadas(Collection<Solicitud> solicitudesEvaluadas) {
		this.solicitudesEvaluadas = solicitudesEvaluadas;
	}

	
	

	
	

}
