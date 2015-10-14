package es.unican.ps.impuestoCirculacion.businessLayer.test;

import java.util.Date;

import org.junit.*;

import es.unican.ps.impuestoCirculacion.businessLayer.*;
import es.unican.ps.impuestoCirculacion.domain.*;
import es.unican.ps.impuestoCirculacion.daoLayer.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GestionImpuestoCirculacionTest {
	
	GestionImpuestoCirculacion gic;
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp(){
		//Mock
		Vehiculo v = mock(Turismo.class);
		Contribuyente c = mock(Contribuyente.class);
		
		IContribuyentesDAO icd = mock(IContribuyentesDAO.class);
		IVehiculosDAO ivd = mock(IVehiculosDAO.class);
		gic = new GestionImpuestoCirculacion(icd,ivd);

		
		when(icd.contribuyente("11111111B")).thenReturn(c);
		when(icd.contribuyente("00000000O")).thenReturn(null);
		when(ivd.vehiculo("1111BBB")).thenReturn(v);
		when(ivd.vehiculo("0000OOO")).thenReturn(null);
		
		when(v.getMatricula()).thenReturn("1111BBB");
		when(v.getPotencia()).thenReturn(59.0);
		when(v.getFecha1Matriculacion()).thenReturn(new Date(2000,10,5));

		
		when(c.getDni()).thenReturn("11111111B");
		when(c.getNombre()).thenReturn("Mike");
		when(c.getApellido1()).thenReturn("Litoris");
		when(c.getApellido2()).thenReturn("Llama");


		
	}
	
	@Test
	public void testContribuyente(){
		//Contribuyente existente
		Contribuyente c = gic.contribuyente("11111111B");
		assertTrue(c.getDni().equals("11111111B"));
		assertTrue(c.getNombre().equals("Mike"));
		assertTrue(c.getApellido1().equals("Litoris"));
		assertTrue(c.getApellido2().equals("Llama"));
		//Contribuyente inexistente
		c = gic.contribuyente("00000000O");
		assertTrue(c == null);
	}
	
	@Test
	public void testTotalContribuyente(){
		//TODO
		//Contribuyente existente
		
		//Contribuyente inexistente
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testVehiculo(){
		//Vehiculo existente
		Vehiculo v = gic.vehiculo("1111BBB");
		assertTrue(v.getMatricula().equals("1111BBB"));
		assertTrue(v.getPotencia()==59);
		assertTrue(v.getFecha1Matriculacion().equals(new Date(2000,10,5)));
		
		//Vehiculo inexistente
		v = gic.vehiculo("0000BBB");
		assertTrue(v == null);
	}
}

