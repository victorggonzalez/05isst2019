package es.upm.dit.isst.rgpd.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
	
	private int estado;
	private boolean evaluacion1;
	private boolean evaluacion2;
	
	
	@Lob
	private byte[] memoria;
	
	
	@ManyToOne
	private Evaluador evaluador;
	
	@ManyToOne
	private Investigador investigador;
	

}
