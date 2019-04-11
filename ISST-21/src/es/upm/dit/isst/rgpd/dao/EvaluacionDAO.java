package es.upm.dit.isst.rgpd.dao;


import es.upm.dit.isst.rgpd.model.Evaluacion;
import es.upm.dit.isst.rgpd.model.EvaluacionKey;

import java.util.Collection;

public interface EvaluacionDAO {

	
	public void create(Evaluacion evaluacion);
	//Crea la sesion con la base de datos para realizar operaciones sobre un evaluador
	
	public Evaluacion read(long id);
	//Lee el email, que es la clave primaria, de la tabla que sigue el modelo evaluador
	
	public void update(Evaluacion evaluacion);
	//Actualiza la tabla que sigue el modelo evaluador
	
	public void delete(Evaluacion evaluacion);
	//Borra (la tabla? una entrada?) que sigue el modelo evaluador
	
	public Collection<Evaluacion> readAll();
}