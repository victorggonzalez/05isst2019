package es.upm.dit.isst.rgpd.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private boolean evaluacion1;
	private boolean evaluacion2;
	

	private String [] formulario;

	
	@Lob
	private byte[] memoria;
	

	@Lob
	private byte[] ampliacion;
	

	@ManyToMany //(mappedBy = "evaluador" , fetch = FetchType.EAGER)
	private Collection<Evaluador> evaluadores;

	@ManyToOne
	private Investigador investigador;

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

	public boolean isEvaluacion1() {
		return evaluacion1;
	}

	public void setEvaluacion1(boolean evaluacion1) {
		this.evaluacion1 = evaluacion1;
	}

	public boolean isEvaluacion2() {
		return evaluacion2;
	}

	public void setEvaluacion2(boolean evaluacion2) {
		this.evaluacion2 = evaluacion2;
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

	public Collection<Evaluador> getEvaluadores() {
		return evaluadores;
	}

	public void setEvaluadores(Collection<Evaluador> evaluadores) {
		this.evaluadores = evaluadores;
	}

	public Investigador getInvestigador() {
		return investigador;
	}

	public void setInvestigador(Investigador investigador) {
		this.investigador = investigador;
	}

	

	
	

}
