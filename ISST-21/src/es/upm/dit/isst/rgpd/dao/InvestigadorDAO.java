package es.upm.dit.isst.rgpd.dao;

import java.util.Collection;
import es.upm.dit.isst.rgpd.model.Investigador;

public interface InvestigadorDAO {

	public Investigador loginInvestigador(String email, String password);
	
	public Collection<Investigador> readAllInvestigador();
	
	public void createInvestigador(Investigador investigador);
	
	public Investigador readInvestigador(String id);
	
	public void updateInvestigador(Investigador investigador);

	public void deleteInvestigador(Investigador investigador);


}
