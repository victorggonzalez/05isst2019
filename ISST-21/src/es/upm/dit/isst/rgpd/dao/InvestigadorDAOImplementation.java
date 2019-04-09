package es.upm.dit.isst.rgpd.dao;

import java.util.ArrayList;
import java.util.Collection;
import org.hibernate.Session;
import es.upm.dit.isst.rgpd.model.Investigador;

public class InvestigadorDAOImplementation implements InvestigadorDAO {

	private static InvestigadorDAOImplementation instance;
		
	private InvestigadorDAOImplementation() {
	};

	public static InvestigadorDAOImplementation getInstance() {
		if (null == instance) {
			instance = new InvestigadorDAOImplementation();
		}
		return instance;
}


	@Override
	public Collection<Investigador> readAll() {
		Session session = SessionFactoryService.get().openSession();
		Collection<Investigador> investigadores = new ArrayList<>();
		try {
			session.beginTransaction();
			investigadores.addAll(session.createQuery("select t from Investigador t").getResultList());
			session.getTransaction().commit();
		} catch (Exception e) {
		} finally {
			session.close();
		}
		return investigadores;
	}
	
	@Override
	public void create(Investigador investigador) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.save(investigador);
			session.getTransaction().commit();
		} catch (Exception e) {

		} finally {
			session.close();
		}
	}
	
	@Override
	public Investigador read(String email) {
		Investigador investigador = null;
		Session session = SessionFactoryService.get().openSession();

		try {
			session.beginTransaction();
			investigador = session.get(Investigador.class, email);
			session.getTransaction().commit();
		} catch (Exception e) {

		} finally {
			session.close();
		}
		return investigador;
	}

	@Override
	public void update(Investigador investigador) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(investigador);
			session.getTransaction().commit();
		} catch (Exception e) {
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(Investigador investigador) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(investigador);
			session.getTransaction().commit();
		} catch (Exception e) {

		} finally {
			session.close();
		}
	}
}

	
	
	
	
	
	
	
