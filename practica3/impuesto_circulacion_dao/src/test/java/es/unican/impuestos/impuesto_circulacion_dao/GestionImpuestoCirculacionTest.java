package es.unican.impuestos.impuesto_circulacion_dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.*;

import es.unican.ps.impuestoCirculacion.domain.*;
import es.unican.ps.impuestoCirculacion.daoLayer.*;
import static org.junit.Assert.*;

public class GestionImpuestoCirculacionTest {
	
	
	@Test
	public void testVehiculo(){
		//Creamos objeto de dao layer
		IContribuyentesDAO icd = new ImpuestosDAO();
		IVehiculosDAO ivd = new ImpuestosDAO();
		//Creamos objeto de domain
		Ayuntamiento a = new Ayuntamiento();
		Contribuyente c = new Contribuyente();
		Vehiculo v1 = new Furgoneta();
		Vehiculo v2 = new Motocicleta();
		Vehiculo v3 = new Turismo();
		assertTrue(true);
	}
}

