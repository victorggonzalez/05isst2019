package es.upm.dit.isst.rgpd.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Evaluacion implements Serializable{
	
	@EmbeddedId
    private EvaluacionKey id;
 
    @ManyToOne
    @MapsId("emailevaluador")
    @JoinColumn(name = "emailevaluador")
    private Evaluador evaluador;
 
    @ManyToOne
    @MapsId("idsolicitud")
    @JoinColumn(name = "idsolicitud")
    private Solicitud solicitud;
 
    private boolean resultado;

	public EvaluacionKey getId() {
		return id;
	}

	public void setId(EvaluacionKey id) {
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

	public boolean isResultado() {
		return resultado;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}
    
    
     

}
