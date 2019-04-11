package es.upm.dit.isst.rgpd.dao;


import es.upm.dit.isst.rgpd.model.Evaluador;
import java.util.Collection;

public interface EvaluadorDAO {
//Esto es un evaluador dao
	

	public void create(Evaluador evaluador);
	//Crea la sesion con la base de datos para realizar operaciones sobre un evaluador
	
	public Evaluador read(String email);
	//Lee el email, que es la clave primaria, de la tabla que sigue el modelo evaluador
	
	public void update(Evaluador evaluador);
	//Actualiza la tabla que sigue el modelo evaluador
	
	public void delete(Evaluador evaluador);
	//Borra (la tabla? una entrada?) que sigue el modelo evaluador
	
	public Collection<Evaluador> readAll();
}
