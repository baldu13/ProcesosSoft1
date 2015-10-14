package es.unican.ps.impuestoCirculacion.presentationLayer.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.fest.swing.fixture.FrameFixture;

import es.unican.ps.impuestoCirculacion.businessLayer.*;
import es.unican.ps.impuestoCirculacion.domain.*;
import es.unican.ps.impuestoCirculacion.presentationLayer.VistaFuncionario;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class VistaFuncionarioTest {

	private FrameFixture ff;
	
	@Before
	public void setUp(){
		//Mock
		Vehiculo v1 = mock(Turismo.class);
		Vehiculo v2 = mock(Motocicleta.class);
		Vehiculo v3 = mock(Turismo.class);
		Contribuyente c = mock(Contribuyente.class);
		IGestionContribuyentes igc = mock(IGestionContribuyentes.class);
		IGestionVehiculos igv = mock(IGestionVehiculos.class);
		//Cargar los mock
		when(igc.contribuyente("71674631F")).thenReturn(c);
		when(igc.contribuyente("00000000O")).thenReturn(null);
		
		when(v1.getMatricula()).thenReturn("7847GGD");
		when(v2.getMatricula()).thenReturn("9147HDD");
		when(v3.getMatricula()).thenReturn("4893DBD");
		
		when(c.calculaPrecioTotal()).thenReturn(234.76);
		when(c.getDni()).thenReturn("71674631F");
		when(c.getNombre()).thenReturn("Aitor");
		when(c.getApellido1()).thenReturn("Menta");
		when(c.getApellido2()).thenReturn("Perez");
		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		vehiculos.add(v1);
		vehiculos.add(v2);
		vehiculos.add(v3);
		when(c.getListaVehiculos()).thenReturn(vehiculos);
		//
		VistaFuncionario vf = new VistaFuncionario(igc,igv);
		ff = new FrameFixture(vf);
		vf.setVisible(true);
	}
	
	@After
	public void shutDown(){
		ff.cleanUp();
	}
	
	@Test
	public void test(){
		//Probamos el aspecto inicial
		ff.label("lblDniContribuyente").requireText("DNI Contribuyente");
		ff.textBox("txtDniContribuyente").requireText("");
		ff.label("lblDatosContribuyente").requireText("Datos Contribuyente");
		ff.label("lblNombreContribuyente").requireText("Nombre");
		ff.textBox("txtNombreContribuyente").requireText("");
		ff.label("lblTotalContribuyente").requireText("Total A Pagar");
		ff.list("listVehiculos").requireItemCount(0);
		ff.textBox("txtTotalContribuyente").requireText("");
		//Prueba 1
		ff.textBox("txtDniContribuyente").enterText("71674631F\n");
		ff.textBox("txtTotalContribuyente").requireText(Double.toString(234.76));
		ff.textBox("txtNombreContribuyente").requireText("Aitor Menta Perez");
		assertTrue(ff.list("listVehiculos").item(0).value().equals("7847GGD"));
		assertTrue(ff.list("listVehiculos").item(1).value().equals("9147HDD"));
		assertTrue(ff.list("listVehiculos").item(2).value().equals("4893DBD"));

		
		//Prueba 2
		ff.textBox("txtDniContribuyente").deleteText();
		ff.textBox("txtDniContribuyente").enterText("00000000O\n");
		ff.textBox("txtTotalContribuyente").requireText("");
		ff.textBox("txtNombreContribuyente").requireText("DNI No Válido");
		ff.list("listVehiculos").requireItemCount(0);


	}
}
