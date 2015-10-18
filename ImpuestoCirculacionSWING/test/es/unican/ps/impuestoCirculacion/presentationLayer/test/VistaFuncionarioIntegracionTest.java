package es.unican.ps.impuestoCirculacion.presentationLayer.test;

import org.junit.*;
import org.fest.swing.fixture.FrameFixture;

import es.unican.ps.impuestoCirculacion.businessLayer.*;
import es.unican.ps.impuestoCirculacion.daoLayer.*;
import es.unican.ps.impuestoCirculacion.presentationLayer.*;
import static org.junit.Assert.*;

public class VistaFuncionarioIntegracionTest {

	private FrameFixture ff;
	
	@Before
	public void setUp(){
		IVehiculosDAO ivd = new ImpuestosDAO();
		IContribuyentesDAO icd = new ImpuestosDAO();
		IGestionVehiculos igv = new GestionImpuestoCirculacion(icd,ivd);
		IGestionContribuyentes igc = new GestionImpuestoCirculacion(icd,ivd);
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
