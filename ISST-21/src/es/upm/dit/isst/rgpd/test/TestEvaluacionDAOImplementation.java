package es.upm.dit.isst.rgpd.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.upm.dit.isst.rgpd.dao.EvaluacionDAO;
import es.upm.dit.isst.rgpd.dao.EvaluacionDAOImplementation;
import es.upm.dit.isst.rgpd.dao.EvaluadorDAO;
import es.upm.dit.isst.rgpd.dao.EvaluadorDAOImplementation;
import es.upm.dit.isst.rgpd.model.Evaluacion;
import es.upm.dit.isst.rgpd.model.Evaluador;
import es.upm.dit.isst.rgpd.model.Investigador;
import es.upm.dit.isst.rgpd.model.Solicitud;

class TestEvaluacionDAOImplementation {

	private Evaluacion evaluacion= new Evaluacion();
	private Evaluador evaluador = new Evaluador();
	private Solicitud solicitud= new Solicitud();
	private Investigador investigador = new Investigador();


	@BeforeEach
	void setUp() throws Exception {
		evaluacion.setResultado("Sin evaluar");
		
		
	}

	@AfterEach
	void tearDown() throws Exception {
		EvaluacionDAOImplementation.getInstance().delete(evaluacion);
		evaluacion= null;
	}

	@Test
	void testCreate() {
		EvaluacionDAO edao = EvaluacionDAOImplementation.getInstance();
		edao.create(evaluacion);
		long id = evaluacion.getId();
		Evaluacion evaluacionTest = edao.read(id);
		assertEquals("Sin evaluar", evaluacionTest.isResultado(), "Error en metodo Create");
		assertNotNull(evaluacionTest.getId(), "Error en metodo Create");
		}

	@Test
	void testRead() {
		EvaluacionDAO edao = EvaluacionDAOImplementation.getInstance();
		edao.create(evaluacion);
		long id = evaluacion.getId();
		assertEquals("Sin evaluar", edao.read(id).isResultado(), "Error en metodo Read");
		assertNotNull(edao.read(id).getId(), "Error en metodo Read");
		
	}

	@Test
	void testUpdate() {
		EvaluacionDAO edao = EvaluacionDAOImplementation.getInstance();
		edao.create(evaluacion);
		evaluacion.setResultado("Aprobado");
		edao.update(evaluacion);
		Long id = evaluacion.getId();
		Evaluacion evaluacionTest = edao.read(id);
		assertEquals("Aprobado", evaluacionTest.isResultado(), "Error en metodo Update");
	}

	@Test
	void testDelete() {
		EvaluacionDAO edao = EvaluacionDAOImplementation.getInstance();
		edao.create(evaluacion);
		Long id = evaluacion.getId();
		edao.delete(evaluacion);
		Evaluacion evaluacionBorrar = edao.read(id);
		assertNull(evaluacionBorrar, "Error en metodo Delete");

	}

	@Test
	void testReadAll() {
		EvaluacionDAO edao = EvaluacionDAOImplementation.getInstance();
		Collection <Evaluacion> evaluaciones = edao.readAll();
		int tamaño = evaluaciones.size();
		EvaluacionDAOImplementation.getInstance().create(evaluacion);
		assertEquals( tamaño+1 , edao.readAll().size(), "Error en metodo ReadAll");
	
	}

}
