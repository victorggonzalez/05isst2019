package es.upm.dit.isst.rgpd.test;

// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import es.upm.dit.isst.rgpd.dao.EvaluacionDAO;
import es.upm.dit.isst.rgpd.dao.EvaluacionDAOImplementation;
import es.upm.dit.isst.rgpd.dao.EvaluadorDAO;
import es.upm.dit.isst.rgpd.dao.EvaluadorDAOImplementation;
import es.upm.dit.isst.rgpd.dao.InvestigadorDAO;
import es.upm.dit.isst.rgpd.dao.InvestigadorDAOImplementation;
import es.upm.dit.isst.rgpd.dao.SolicitudDAO;
import es.upm.dit.isst.rgpd.dao.SolicitudDAOImplementation;
import es.upm.dit.isst.rgpd.model.Evaluacion;
import es.upm.dit.isst.rgpd.model.Evaluador;
import es.upm.dit.isst.rgpd.model.Investigador;
import es.upm.dit.isst.rgpd.model.Solicitud;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class EvaluacionDenegada {
	  private WebDriver driver;
	  private Map<String, Object> vars;
	  JavascriptExecutor js;
@Before
public void setUp() {
	System.setProperty( "webdriver.chrome.driver", "chromedriver.exe");
	driver = new ChromeDriver();
	js = (JavascriptExecutor) driver;
	vars = new HashMap<String, Object>();
	
	InvestigadorDAO idao = InvestigadorDAOImplementation.getInstance();
	Investigador i = new Investigador();
	i.setName("investigador");
	i.setEmail("i@upm.es");
	i.setArea("area1");
	i.setGrupo("grupo1");
	i.setPassword("d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1");
	idao.create(i);
	
	EvaluadorDAO edao = EvaluadorDAOImplementation.getInstance();
	Evaluador e1 = new Evaluador();
	e1.setName("evaluador1");
	e1.setEmail("e1@upm.es");
	e1.setArea("area1");
	e1.setGrupo("grupo2");
	e1.setPassword("d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1");
	edao.create(e1);
	Evaluador e2 = new Evaluador();
	e2.setName("evaluador2");
	e2.setEmail("e2@upm.es");
	e2.setArea("area1");
	e2.setGrupo("grupo2");
	e2.setPassword("d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1");
	edao.create(e2);
	
	SolicitudDAO sdao = SolicitudDAOImplementation.getInstance();
	Solicitud s = new Solicitud();
	s.setId((long) 1);
	s.setAmpliacion(null);
	s.setEstado(4);
	s.setFaltanDatos(null);
	s.setFormulario(null);
	s.setMemoria(null);
	s.setTitulo("Nueva solicitud");
	s.setInvestigador(i);
	sdao.create(s);
	
	EvaluacionDAO ecdao = EvaluacionDAOImplementation.getInstance();
	Evaluacion ec1 = new Evaluacion();
	ec1.setId((long) 1);
	ec1.setResultado("Sin evaluar");
	ec1.setEvaluador(e2);
	ec1.setSolicitud(s);
	ecdao.create(ec1);
	Evaluacion ec2 = new Evaluacion();
	ec2.setId((long) 2);
	ec2.setResultado("Sin evaluar");
	ec2.setEvaluador(e1);
	ec2.setSolicitud(s);
	ecdao.create(ec2);
}
@After
public void tearDown() {
	EvaluacionDAO ecdao = EvaluacionDAOImplementation.getInstance();
	  Collection <Evaluacion> e = ecdao.readAll();
	  for(Evaluacion e1: e) {
		  ecdao.delete(e1);
	  }
	  
	  SolicitudDAO sdao = SolicitudDAOImplementation.getInstance();
	  Collection<Solicitud> s = sdao.readAll();
	  for(Solicitud s1: s) {
		  sdao.delete(s1);
	  }
	  
	  EvaluadorDAO edao = EvaluadorDAOImplementation.getInstance();
	  Collection <Evaluador> ed = edao.readAll();
	  for(Evaluador e1: ed) {
		  edao.delete(e1);
	  }
	  
	  InvestigadorDAO idao = InvestigadorDAOImplementation.getInstance();
	  Collection <Investigador> i = idao.readAll();
	  for(Investigador i1: i) {
		  idao.delete(i1);
	  }
 driver.quit();
}
@Test
public void EvaluacionDenegada() {

 driver.get("http://localhost:8080/ISST2019/");
 driver.manage().window().setSize(new Dimension(1280, 680));
 driver.findElement(By.name("email")).click();
 driver.findElement(By.name("email")).sendKeys("e1@upm.es");
 driver.findElement(By.name("password")).sendKeys("pass");
 driver.findElement(By.cssSelector(".button")).click();
 driver.findElement(By.linkText("Pendientes de evaluar")).click();
 driver.findElement(By.cssSelector(".small")).click();
 driver.findElement(By.cssSelector("form:nth-child(3) > .button")).click();
 driver.findElement(By.linkText("Log out")).click();
 driver.findElement(By.name("email")).click();
 driver.findElement(By.name("email")).sendKeys("e2@upm.es");
 driver.findElement(By.name("password")).sendKeys("pass");
 driver.findElement(By.cssSelector(".button")).click();
 driver.findElement(By.linkText("Pendientes de evaluar")).click();
 driver.findElement(By.cssSelector(".small")).click();
 driver.findElement(By.cssSelector("form:nth-child(3) > .button")).click();
 driver.findElement(By.linkText("Log out")).click();
 driver.findElement(By.name("email")).click();
 driver.findElement(By.name("email")).sendKeys("i@upm.es");
 driver.findElement(By.name("password")).sendKeys("pass");
 driver.findElement(By.cssSelector(".button")).click();
 driver.findElement(By.linkText("Cerradas")).click();
 driver.findElement(By.cssSelector("td:nth-child(3)")).click();
 assertThat(driver.findElement(By.cssSelector("td:nth-child(3)")).getText(), is("Denegado"));
 driver.findElement(By.linkText("Log out")).click();
}
}
