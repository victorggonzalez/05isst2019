package es.upm.dit.isst.rgpd.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	/*
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	*/
	
	
	private String password;
	private String name;
	private String area;
	private String grupo;
	
	
	
	@OneToMany(mappedBy = "evaluador", fetch = FetchType.EAGER)
	private Collection<Evaluacion> evaluaciones;



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



	public Collection<Evaluacion> getEvaluaciones() {
		return evaluaciones;
	}



	public void setEvaluaciones(Collection<Evaluacion> evaluaciones) {
		this.evaluaciones = evaluaciones;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((evaluaciones == null) ? 0 : evaluaciones.hashCode());
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evaluador other = (Evaluador) obj;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (evaluaciones == null) {
			if (other.evaluaciones != null)
				return false;
		} else if (!evaluaciones.equals(other.evaluaciones))
			return false;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		} else if (!grupo.equals(other.grupo))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}


/*
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}
	
	*/
	
	
	

}
