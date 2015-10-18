package es.unican.ps.impuestoCirculacion.businessLayer.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.*;

import es.unican.ps.impuestoCirculacion.businessLayer.*;
import es.unican.ps.impuestoCirculacion.domain.*;
import es.unican.ps.impuestoCirculacion.daoLayer.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GestionImpuestoCirculacionIntegracionTest {
	
	GestionImpuestoCirculacion gic;
	
	@Before
	public void setUp(){
		IContribuyentesDAO icd = new ImpuestosDAO();
		IVehiculosDAO ivd = new ImpuestosDAO();
		gic = new GestionImpuestoCirculacion(icd,ivd);
	}
	
	@Test
	public void testContribuyente(){
		//Contribuyente existente
		Contribuyente c = gic.contribuyente("71674631F");
		assertTrue(c.getDni().equals("71674631F"));
		assertTrue(c.getNombre().equals("Aitor"));
		assertTrue(c.getApellido1().equals("Menta"));
		assertTrue(c.getApellido2().equals("Perez"));
		//Contribuyente inexistente
		c = gic.contribuyente("00000000O");
		assertTrue(c == null);
	}
	
	@Test
	public void testTotalContribuyente(){
		//Contribuyente existente
		assertTrue(gic.totalContribuyente("72189333S")==212.04);
		//Contribuyente inexistente
		assertTrue(gic.totalContribuyente("00000000O") == 0.0);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testVehiculo(){
		//Vehiculo existente
		//Motocicleta
		Vehiculo v = gic.vehiculo("1237HTD");
		assertTrue(v.getMatricula().equals("1237HTD"));
		assertTrue(v.getCilindrada()==120);
		
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(new Date(2009-1900,3-1,11));
		c2.setTime(v.getFecha1Matriculacion());
		assertTrue(c1.compareTo(c2)==0);
		
		//Turismo
		v = gic.vehiculo("3247BBD");
		assertTrue(v.getMatricula().equals("3247BBD"));
		assertTrue(v.getPotencia()==22.0);
		
		c1 = Calendar.getInstance();
		c2 = Calendar.getInstance();
		c1.setTime(new Date(2005-1900,1-1,1));
		c2.setTime(v.getFecha1Matriculacion());
		assertTrue(c1.compareTo(c2)==0);
		
		//Furgoneta
		v = gic.vehiculo("3543DDF");
		assertTrue(v.getMatricula().equals("3543DDF"));
		assertTrue(v.getPotencia()==12.0);
		
		c1.setTime(new Date(2008-1900,6-1,1));
		c2.setTime(v.getFecha1Matriculacion());
		assertTrue(c1.compareTo(c2)==0);
		//Vehiculo inexistente
		v = gic.vehiculo("0000BBB");
		assertTrue(v == null);
	}
	
	@Test
	public void altaContribuyente(){
		//Alta normal
		Contribuyente c = new Contribuyente();
		c.setDni("11111111B");
		c.setNombre("Juan");
		c.setApellido1("Ete");
		c.setApellido2("Mikasa");
		c.setListaVehiculos(new ArrayList<Vehiculo>());
		Contribuyente resul = gic.altaContribuyente(c); 
		assertTrue(c.equals(resul));
		assertTrue(c.equals(gic.contribuyente("11111111B")));
		//Alta existente
		c = new Contribuyente();
		c.setDni("72189333S");
		c.setNombre("Víctor");
		c.setApellido1("Gómez");
		c.setApellido2("Cobo");
		c.setListaVehiculos(new ArrayList<Vehiculo>());
		resul = gic.altaContribuyente(c); 
		assertTrue(c.equals(resul));
	}
	
	@Test
	public void altaVehiculo(){
		//Contribuyente a anadir los vehiculos
		Contribuyente c = new Contribuyente();
		c.setDni("11111111B");
		c.setNombre("Juan");
		c.setApellido1("Ete");
		c.setApellido2("Mikasa");
		c.setListaVehiculos(new ArrayList<Vehiculo>());
		gic.altaContribuyente(c); 
		
		Vehiculo v = new Turismo();
		v.setMatricula("1111BBB");
		v.setFecha1Matriculacion(new Date(2010-1900,5-1,15));
		gic.altaVehiculo(v, c); 
		assertTrue(c.getListaVehiculos().contains(v));
		
		c = gic.contribuyente("72189333S");
		gic.altaVehiculo(v, c); 
		assertTrue(!c.getListaVehiculos().contains(v));
		gic.altaVehiculo(v, null); //Si no da excepcion, todo correcto
	}
	
	@Test
	public void bajaContribuyente(){
		gic.bajaContribuyente("71345631P");
		assertTrue(gic.contribuyente("71345631P")==null);
		
		gic.bajaContribuyente("00000000O"); //Si no da excepcion va bien
	}
	
	@Test
	public void bajaVehiculo(){
		gic.bajaVehiculo("3543DDF", gic.contribuyente("78493121S"));
		assertTrue(gic.vehiculo("3543DDF")==null);
		
	}
}

