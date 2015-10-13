package es.unican.ps.impuestoCirculacion.presentationLayer.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.fest.swing.fixture.FrameFixture;

import es.unican.ps.impuestoCirculacion.businessLayer.*;
import es.unican.ps.impuestoCirculacion.domain.*;
import es.unican.ps.impuestoCirculacion.presentationLayer.VistaFuncionario;
import static org.mockito.Mockito.*;

public class VistaFuncionarioTest {

	private FrameFixture ff;
	
	@Before
	public void setUp(){
		//Mock
		IGestionContribuyentes igc = mock(IGestionContribuyentes.class);
		IGestionVehiculos igv = mock(IGestionVehiculos.class);
		//TODO: Prueba
		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		//
		when(igc.contribuyente("71674631F").getListaVehiculos()).thenReturn();
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
		
	}
}
