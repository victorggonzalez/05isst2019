package es.upm.dit.isst.rgpd.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.upm.dit.isst.rgpd.dao.EvaluacionDAOImplementation;
import es.upm.dit.isst.rgpd.dao.InvestigadorDAO;
import es.upm.dit.isst.rgpd.dao.InvestigadorDAOImplementation;
import es.upm.dit.isst.rgpd.dao.SolicitudDAO;
import es.upm.dit.isst.rgpd.dao.SolicitudDAOImplementation;
import es.upm.dit.isst.rgpd.model.*;

class TestSolicitudDAOImplementation {

	private Solicitud solicitud= new Solicitud();
	
	@BeforeEach
	void setUp() throws Exception {
		solicitud.setTitulo("titulo");
		solicitud.setEstado(1);
		
		
	}

	@AfterEach
	void tearDown() throws Exception {
		if(solicitud!= null) {
			
		
		SolicitudDAOImplementation.getInstance().delete(solicitud);
		solicitud= null;
		}
	}

	@Test
	void testCreate() {

		SolicitudDAO sdao = SolicitudDAOImplementation.getInstance();
		sdao.create(solicitud);
		Long id = solicitud.getId();
		Solicitud solicitudTest = sdao.read(id);
		assertEquals( "titulo", solicitudTest.getTitulo(), "Error en metodo Create");
		assertEquals( 1, solicitudTest.getEstado(), "Error en metodo Create");
		assertNotNull(solicitudTest.getId(), "Error en metodo Create");
	}

	@Test
	void testDelete() {
		SolicitudDAO sdao = SolicitudDAOImplementation.getInstance();
		sdao.create(solicitud);
		Long id = solicitud.getId();
		sdao.delete(solicitud);
		Solicitud solicitudBorrar = sdao.read(id);
		assertNull(solicitudBorrar, "Error en metodo Delete");

	}

	@Test
	void testRead() {
		SolicitudDAO sdao = SolicitudDAOImplementation.getInstance();
		sdao.create(solicitud);
		Long id = solicitud.getId();
		Solicitud solicitudTest = sdao.read(id);
		assertEquals( "titulo", solicitudTest.getTitulo(), "Error en metodo Create");
		assertEquals( 1, solicitudTest.getEstado(), "Error en metodo Create");
		assertNotNull(solicitudTest.getId(), "Error en metodo Create");
	}

	@Test
	void testReadAll() {
		SolicitudDAO sdao = SolicitudDAOImplementation.getInstance();
		Collection <Solicitud> solicitudes = sdao.readAll();
		int tamaño = solicitudes.size();
		sdao.create(solicitud);
		assertEquals( tamaño+1 , sdao.readAll().size(), "Error en metodo ReadAll");
	}

	@Test
	void testUpdate() {
		SolicitudDAO sdao = SolicitudDAOImplementation.getInstance();
		sdao.create(solicitud);
		Long id = solicitud.getId();
		solicitud.setEstado(2);
		sdao.update(solicitud);
		assertEquals(2, sdao.read(id).getEstado(), "Error en metodo Update");
	}

}
