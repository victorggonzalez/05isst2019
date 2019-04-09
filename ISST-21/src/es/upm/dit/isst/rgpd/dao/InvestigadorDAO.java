package es.upm.dit.isst.rgpd.dao;

import java.util.Collection;
import es.upm.dit.isst.rgpd.model.Investigador;

public interface InvestigadorDAO {

	
	public Collection<Investigador> readAll();
	
	public void create(Investigador investigador);
	
	public Investigador read(String id);
	
	public void update(Investigador investigador);

	public void delete(Investigador investigador);


}
