package es.unican.ps.impuestoCirculacion.businessLayer.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		Vehiculo v1 = mock(Turismo.class);
		Vehiculo v2 = mock(Furgoneta.class);
		Contribuyente c = mock(Contribuyente.class);
		
		IContribuyentesDAO icd = mock(IContribuyentesDAO.class);
		IVehiculosDAO ivd = mock(IVehiculosDAO.class);
		gic = new GestionImpuestoCirculacion(icd,ivd);

		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		vehiculos.add(v1);
		vehiculos.add(v2);
		
		when(icd.datosContribuyente("11111111B")).thenReturn(c);
		when(icd.datosContribuyente("00000000O")).thenReturn(null);

		when(ivd.vehiculo("1111BBB")).thenReturn(v1);
		when(ivd.vehiculo("0000OOO")).thenReturn(null);
		
		when(v1.getMatricula()).thenReturn("1111BBB");
		when(v1.getPotencia()).thenReturn(17.0);
		when(v1.getFecha1Matriculacion()).thenReturn(new Date(2000,10,5));
		when(v1.calculaPrecio()).thenReturn(179.22);

		when(v2.getMatricula()).thenReturn("2222CCC");
		when(v2.getPotencia()).thenReturn(9.0);
		when(v2.getFecha1Matriculacion()).thenReturn(new Date(2000,8,3));
		when(v2.calculaPrecio()).thenReturn(68.16);

		
		when(c.getDni()).thenReturn("11111111B");
		when(c.getNombre()).thenReturn("Mike");
		when(c.getApellido1()).thenReturn("Litoris");
		when(c.getApellido2()).thenReturn("Llama");
		when(c.getListaVehiculos()).thenReturn(vehiculos);
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
		//Contribuyente existente
		assertTrue(gic.totalContribuyente("11111111B")==247.38);
		//Contribuyente inexistente
		assertTrue(gic.totalContribuyente("00000000O") == 0.0);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testVehiculo(){
		//Vehiculo existente
		Vehiculo v = gic.vehiculo("1111BBB");
		assertTrue(v.getMatricula().equals("1111BBB"));
		assertTrue(v.getPotencia()==17.0);
		assertTrue(v.getFecha1Matriculacion().equals(new Date(2000,10,5)));
		
		//Vehiculo inexistente
		v = gic.vehiculo("0000BBB");
		assertTrue(v == null);
	}
}

