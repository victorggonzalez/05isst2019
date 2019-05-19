package es.upm.dit.isst.rgpd.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.upm.dit.isst.rgpd.dao.InvestigadorDAO;
import es.upm.dit.isst.rgpd.dao.InvestigadorDAOImplementation;
import es.upm.dit.isst.rgpd.model.Investigador;

class TestInvestigadorDAOImplementation {
	
	private Investigador investigador = new Investigador();
	
	@BeforeEach
	void setUp() throws Exception {
		investigador.setEmail("test@alumnos.upm.es");
		investigador.setName("victor");
		investigador.setPassword("pass");
		investigador.setArea("area");
		investigador.setGrupo("grupo");
	}

	@AfterEach
	void tearDown() throws Exception {
		InvestigadorDAOImplementation.getInstance().delete(investigador);
		investigador = null;
	}

	@Test
	void testReadAll() {
		InvestigadorDAO idao = InvestigadorDAOImplementation.getInstance();
		Collection <Investigador> investigadores = idao.readAll();
		int tamaño = idao.readAll().size();
		InvestigadorDAOImplementation.getInstance().create(investigador);
		assertEquals( tamaño+1 , idao.readAll().size(), "Error en metodo ReadAll");
	}

	@Test
	void testCreate() {
		InvestigadorDAO idao = InvestigadorDAOImplementation.getInstance();
		idao.create(investigador);
		Investigador investigadorTest = idao.read("test@alumnos.upm.es");
		assertEquals( "victor", investigadorTest.getName(), "Error en metodo Create");
		assertEquals( "pass", investigadorTest.getPassword(), "Error en metodo Create");
		assertEquals( "area", investigadorTest.getArea(), "Error en metodo Create");
		assertEquals( "grupo", investigadorTest.getGrupo(), "Error en metodo Create");
		
	}

	@Test
	void testRead() {
		InvestigadorDAO idao = InvestigadorDAOImplementation.getInstance();
		idao.create(investigador);
		Investigador investigadorTest = idao.read("test@alumnos.upm.es");
		assertEquals("victor", investigadorTest.getName());
		assertEquals("pass", investigadorTest.getPassword());
		assertEquals("area", investigadorTest.getArea());
		assertEquals("grupo", investigadorTest.getGrupo());
			}

	@Test
	void testUpdate() {
		InvestigadorDAO idao = InvestigadorDAOImplementation.getInstance();
		idao.create(investigador);
		investigador.setArea("area2");
		idao.update(investigador);
		assertEquals("area2", idao.read(investigador.getEmail()).getArea(), "Error en metodo Update");
	}

	@Test
	void testDelete() {
		InvestigadorDAO idao = InvestigadorDAOImplementation.getInstance();
		idao.create(investigador);
		idao.delete(investigador);
		Investigador investigadorBorrar = idao.read("test@alumnos.upm.es");
		assertNull(investigadorBorrar, "Error en metodo Delete");
	}

}
