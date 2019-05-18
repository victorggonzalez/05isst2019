package es.upm.dit.isst.rgpd.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.upm.dit.isst.rgpd.dao.EvaluadorDAO;
import es.upm.dit.isst.rgpd.dao.EvaluadorDAOImplementation;
import es.upm.dit.isst.rgpd.dao.InvestigadorDAO;
import es.upm.dit.isst.rgpd.dao.InvestigadorDAOImplementation;
import es.upm.dit.isst.rgpd.model.Evaluacion;
import es.upm.dit.isst.rgpd.model.Evaluador;
import es.upm.dit.isst.rgpd.model.Investigador;

class TestEvaluadorDAOImplementation {
	private Evaluador evaluador = new Evaluador();
	private Collection<Evaluacion> evaluaciones;


	@BeforeEach
	void setUp() throws Exception {
		evaluador.setEmail("test2@alumnos.upm.es");
		evaluador.setName("victor");
		evaluador.setPassword("pass");
		evaluador.setArea("area");
		evaluador.setGrupo("grupo");
		evaluador.setEvaluaciones(evaluaciones);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		EvaluadorDAOImplementation.getInstance().delete(evaluador);
		evaluador = null;
	}

	@Test
	void testRead() {
		EvaluadorDAO edao = EvaluadorDAOImplementation.getInstance();
		edao.create(evaluador);
		Evaluador evaluadorTest = edao.read("test2@alumnos.upm.es");
		assertEquals("victor", evaluadorTest.getName(), "Error en metodo Read");
		assertEquals("pass", evaluadorTest.getPassword(), "Error en metodo Read");
		assertEquals("area", evaluadorTest.getArea(), "Error en metodo Read");
		assertEquals("grupo", evaluadorTest.getGrupo(), "Error en metodo Read");
	}

	@Test
	void testDelete() {
		EvaluadorDAO edao = EvaluadorDAOImplementation.getInstance();
		edao.create(evaluador);
		edao.delete(evaluador);
		Evaluador evaluadorBorrar = edao.read("test2@alumnos.upm.es");
		assertNull(evaluadorBorrar, "Error en metodo Delete");

	}

	@Test
	void testUpdate() {
		EvaluadorDAO edao = EvaluadorDAOImplementation.getInstance();
		edao.create(evaluador);
		evaluador.setArea("area2");
		edao.update(evaluador);
		assertEquals("area2", edao.read(evaluador.getEmail()).getArea(), "Error en metodo Update");
	}

	@Test
	void testCreate() {
		EvaluadorDAO edao = EvaluadorDAOImplementation.getInstance();
		edao.create(evaluador);
		Evaluador evaluadorTest = edao.read("test2@alumnos.upm.es");
		assertEquals("victor", evaluadorTest.getName(), "Error en metodo Create");
		assertEquals("pass", evaluadorTest.getPassword(), "Error en metodo Create");
		assertEquals("area", evaluadorTest.getArea(), "Error en metodo Create");
		assertEquals("grupo", evaluadorTest.getGrupo(), "Error en metodo Create");
	}

	@Test
	void testReadAll() {
		EvaluadorDAO edao = EvaluadorDAOImplementation.getInstance();
		Collection <Evaluador> evaluadores = edao.readAll();
		int tamaño = edao.readAll().size();
		edao.create(evaluador);
		assertEquals( tamaño+1 , edao.readAll().size(), "Error en metodo ReadAll");
	
	}

}
