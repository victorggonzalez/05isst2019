package es.upm.dit.isst.rgpd.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Session;

import es.upm.dit.isst.rgpd.model.Evaluacion;
import es.upm.dit.isst.rgpd.model.EvaluacionKey;
import es.upm.dit.isst.rgpd.model.Evaluador;
import es.upm.dit.isst.rgpd.model.Investigador;

public class EvaluacionDAOImplementation implements EvaluacionDAO{
	
	private EvaluacionDAOImplementation() {} //private para que no se puedan crear objetos de esta clase
	
	private static EvaluacionDAOImplementation instancia = null;
	
	
	public static EvaluacionDAOImplementation getInstance() {
		if(null == instancia) {
				instancia = new EvaluacionDAOImplementation();
		}
		return instancia;
	}

	@Override
	public void create(Evaluacion evaluacion) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.save(evaluacion);
			session.getTransaction().commit();
		} catch (Exception e) {

		} finally {
			session.close();
		}
	}

	@Override
	public Evaluacion read(EvaluacionKey evaluacionKey) {
		Evaluacion evaluacion = null;
		Session session = SessionFactoryService.get().openSession();

		try {
			session.beginTransaction();
			evaluacion = session.get(Evaluacion.class, evaluacionKey);
			session.getTransaction().commit();
		} catch (Exception e) {

		} finally {
			session.close();
		}
		return evaluacion;
	}

	@Override
	public void update(Evaluacion evaluacion) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(evaluacion);
			session.getTransaction().commit();
		} catch (Exception e) {
		} finally {
			session.close();
		}
		
	}

	@Override
	public void delete(Evaluacion evaluacion) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(evaluacion);
			session.getTransaction().commit();
		} catch (Exception e) {

		} finally {
			session.close();
		}
		
	}

	@Override
	public Collection<Evaluacion> readAll() {
		Session session = SessionFactoryService.get().openSession();
		Collection<Evaluacion> evaluaciones = new ArrayList<>();
		try {
			session.beginTransaction();
			evaluaciones.addAll(session.createQuery("select t from Evaluacion t").getResultList());
			session.getTransaction().commit();
		} catch (Exception e) {
		} finally {
			session.close();
		}
		return evaluaciones;
	}

}
