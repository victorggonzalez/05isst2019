package es.upm.dit.isst.rgpd.model;

import java.io.Serializable;
import java.util.ArrayList;
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
	private int id;
	

	private String titulo;

	private int estado;
	
	

	private String [] formulario;

	
	@Lob
	private byte[] memoria;
	

	@Lob
	private byte[] ampliacion;
	
	
	@OneToMany(mappedBy = "solicitud")
    private Collection<Evaluacion> evaluaciones;
	
	/*
	@ManyToMany(cascade = CascadeType.ALL)
	private Collection<Evaluador> evaluadores;
	*/
	
	@ManyToOne
	private Investigador investigador;
	/*
	public void addEvaluador(Evaluador evaluador){
	        if(this.evaluadores == null){
	            this.evaluadores = new ArrayList<Evaluador>();
	        }
	        
	        this.evaluadores.add(evaluador);
	    }
	*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	
	
	

}
