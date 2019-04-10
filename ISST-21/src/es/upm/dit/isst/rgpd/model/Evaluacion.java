package es.upm.dit.isst.rgpd.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "evaluacion")
public class Evaluacion implements Serializable{
	
	@EmbeddedId
    private EvaluacionKey id;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("emailevaluador")
    @JoinColumn(name = "emailevaluador")
    private Evaluador evaluador;
 
    @ManyToOne(fetch = FetchType.LAZY)
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((evaluador == null) ? 0 : evaluador.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (resultado ? 1231 : 1237);
		result = prime * result + ((solicitud == null) ? 0 : solicitud.hashCode());
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
		Evaluacion other = (Evaluacion) obj;
		if (evaluador == null) {
			if (other.evaluador != null)
				return false;
		} else if (!evaluador.equals(other.evaluador))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (resultado != other.resultado)
			return false;
		if (solicitud == null) {
			if (other.solicitud != null)
				return false;
		} else if (!solicitud.equals(other.solicitud))
			return false;
		return true;
	}
    
    
     

}
