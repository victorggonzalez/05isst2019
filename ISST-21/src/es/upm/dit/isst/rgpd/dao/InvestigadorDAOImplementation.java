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
	public Investigador loginInvestigador(String email, String password) {
		Session session = SessionFactoryService.get().openSession();
		Investigador investigador = null;
		try {
			session.beginTransaction();
			investigador = (Investigador) session
					.createQuery("select t from Investigador t where t.email= :email and t.password = :password")
					.setParameter("email", email).setParameter("password", password).uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {

		} finally {
			session.close();
		}
		return investigador;
	}

	@Override
	public Collection<Investigador> readAllInvestigador() {
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
	public void createInvestigador(Investigador investigador) {
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
	public Investigador readInvestigador(String email) {
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
	public void updateInvestigador(Investigador investigador) {
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
	public void deleteInvestigador(Investigador investigador) {
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

	
	
	
	
	
	
	
