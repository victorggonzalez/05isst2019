package es.upm.dit.isst.rgpd.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.*;

import es.upm.dit.isst.rgpd.model.Evaluador;
import es.upm.dit.isst.rgpd.model.Solicitud;

public class EvaluadorDAOImplementation implements EvaluadorDAO{


	private EvaluadorDAOImplementation() {} //private para que no se puedan crear objetos de esta clase
	

	@Override
	public Evaluador read(String email) {
		Evaluador Evaluador=null;
		Session session = SessionFactoryService.get().openSession();
		try {
		session.beginTransaction();
		session.get(Evaluador.class, email);
		session.getTransaction().commit();
		} catch(Exception e) {
			session.close();
		} finally {
			session.close();
		}
		return Evaluador;
	}

	@Override
	public void delete(Evaluador Evaluador) {
		Session session = SessionFactoryService.get().openSession();
		try {
		session.beginTransaction();
		session.delete(Evaluador);
		session.getTransaction().commit();
		} catch(Exception e) {
			session.close();
		} finally {
			session.close();
		}
	}
	
	@Override
	public void update(Evaluador Evaluador) {
		Session session =  SessionFactoryService.get().openSession();
		try {
		session.beginTransaction();
		session.saveOrUpdate(Evaluador);
		session.getTransaction().commit();
		} catch(Exception e) {
			session.close();
		} finally {
			session.close();
		}	
	}

	@Override
	public void createEvaluador(Evaluador Evaluador) {
		Session session = SessionFactoryService.get().openSession();
		
		try {
		session.beginTransaction();
		session.save(Evaluador);
		session.getTransaction().commit();
		} catch(Exception e) {
		session.close();
		} finally {
			session.close();
		}
	}
}
