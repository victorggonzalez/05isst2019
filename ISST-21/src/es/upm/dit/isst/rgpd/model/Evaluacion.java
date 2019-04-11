package es.upm.dit.isst.rgpd.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity

public class Evaluacion implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
 
    @ManyToOne(fetch = FetchType.EAGER)
    
    private Evaluador evaluador;
 
    @ManyToOne(fetch = FetchType.EAGER)
   
    private Solicitud solicitud;
 
    
    private String resultado;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Evaluador getEvaluador() {
		return evaluador;
	}

	public void setEvaluador(Evaluador evaluador) {
		this.evaluador = evaluador;
	}

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public String isResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	
    
    
     

}
