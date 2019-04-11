package es.upm.dit.isst.rgpd.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Solicitud implements Serializable{

	public Solicitud() {
	}

	//id autogenerado
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	

	private String titulo;

	private int estado;
	
	

	private String [] formulario;

	
	@Lob
	private byte[] memoria;
	

	@Lob
	private byte[] ampliacion;
	
	
	@OneToMany(mappedBy = "solicitud", fetch = FetchType.EAGER, cascade =CascadeType.ALL)
    private Collection<Evaluacion> evaluaciones;
	
	
	@ManyToOne
	private Investigador investigador;


	


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}


	public String[] getFormulario() {
		return formulario;
	}


	public void setFormulario(String[] formulario) {
		this.formulario = formulario;
	}


	public byte[] getMemoria() {
		return memoria;
	}


	public void setMemoria(byte[] memoria) {
		this.memoria = memoria;
	}


	public byte[] getAmpliacion() {
		return ampliacion;
	}


	public void setAmpliacion(byte[] ampliacion) {
		this.ampliacion = ampliacion;
	}


	public Collection<Evaluacion> getEvaluaciones() {
		return evaluaciones;
	}


	public void setEvaluaciones(Collection<Evaluacion> evaluaciones) {
		this.evaluaciones = evaluaciones;
	}


	public Investigador getInvestigador() {
		return investigador;
	}


	public void setInvestigador(Investigador investigador) {
		this.investigador = investigador;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(ampliacion);
		result = prime * result + estado;
		result = prime * result + ((evaluaciones == null) ? 0 : evaluaciones.hashCode());
		result = prime * result + Arrays.hashCode(formulario);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((investigador == null) ? 0 : investigador.hashCode());
		result = prime * result + Arrays.hashCode(memoria);
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		Solicitud other = (Solicitud) obj;
		if (!Arrays.equals(ampliacion, other.ampliacion))
			return false;
		if (estado != other.estado)
			return false;
		if (evaluaciones == null) {
			if (other.evaluaciones != null)
				return false;
		} else if (!evaluaciones.equals(other.evaluaciones))
			return false;
		if (!Arrays.equals(formulario, other.formulario))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (investigador == null) {
			if (other.investigador != null)
				return false;
		} else if (!investigador.equals(other.investigador))
			return false;
		if (!Arrays.equals(memoria, other.memoria))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	
	

}
