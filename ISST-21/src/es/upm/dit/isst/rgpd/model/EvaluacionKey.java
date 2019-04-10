package es.upm.dit.isst.rgpd.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EvaluacionKey implements Serializable{
	
	@Column(name = "emailevaluador")
   	private String emailEvaluador;
 
    @Column(name = "idsolicitud")
    private int idSolicitud;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailEvaluador == null) ? 0 : emailEvaluador.hashCode());
		result = prime * result + idSolicitud;
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
		EvaluacionKey other = (EvaluacionKey) obj;
		if (emailEvaluador == null) {
			if (other.emailEvaluador != null)
				return false;
		} else if (!emailEvaluador.equals(other.emailEvaluador))
			return false;
		if (idSolicitud != other.idSolicitud)
			return false;
		return true;
	}

	
}
