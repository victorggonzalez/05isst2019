package es.upm.dit.isst.rgpd.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.util.Collection;

import org.hibernate.*;

import es.upm.dit.isst.rgpd.model.Evaluador;
import es.upm.dit.isst.rgpd.model.Solicitud;

public class SolicitudDAOImplementation implements SolicitudDAO{
	
	private static SolicitudDAOImplementation instancia = null;
	
	private SolicitudDAOImplementation() {
		// TODO Auto-generated constructor stub
	}

	public static SolicitudDAOImplementation getInstance() {
		if(null == instancia)
				instancia = new SolicitudDAOImplementation();
		return instancia;
	}
	
	
	
	@Override 
	public void create(Solicitud solicitud) { 
		Session session = SessionFactoryService.get().openSession(); 
		session.beginTransaction(); session.save( solicitud ); 
		session.getTransaction().commit(); 
		session.close(); 
	}
	
	@Override 
	public void delete(Solicitud solicitud) { 
		Session session = SessionFactoryService.get().openSession(); 
		session.beginTransaction(); 
		session.delete( solicitud ); 
		session.getTransaction().commit(); 
		session.close(); 
	}
	
	@Override 
	public Solicitud read(Long id) { 
		Session session = SessionFactoryService.get().openSession(); 
		session.beginTransaction(); 
		Solicitud solicitud = session.load( Solicitud.class, id ); 
		session.getTransaction().commit(); 
		session.close(); 
		return solicitud; 
	}
	
	@SuppressWarnings("unchecked") 
	@Override 
	public Collection<Solicitud> readAll() { 
		Session session = SessionFactoryService.get().openSession(); 
		session.beginTransaction(); 
		Collection<Solicitud> solicitud = session.createQuery( "from Solicitud" ).list(); 
		session.getTransaction().commit(); 
		session.close(); 
		return solicitud; 
	}
	
	@Override public void update(Solicitud solicitud) { 
		Session session = SessionFactoryService.get().openSession(); 
		session.beginTransaction(); 
		session.saveOrUpdate( solicitud ); 
		session.getTransaction().commit(); 
		session.close(); 
	}
	
	
}
