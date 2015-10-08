package es.unican.ps.impuestoCirculacion.main;

import es.unican.ps.impuestoCirculacion.businessLayer.GestionImpuestoCirculacion;
import es.unican.ps.impuestoCirculacion.daoLayer.ImpuestosDAO;
import es.unican.ps.impuestoCirculacion.presentationLayer.VistaFuncionario;

public class Runner {

	public static void main(String[] args) {
		
		ImpuestosDAO datos = new ImpuestosDAO();
		GestionImpuestoCirculacion negocio = new GestionImpuestoCirculacion(datos, datos);
		VistaFuncionario vista = new VistaFuncionario(negocio, negocio);
		vista.setVisible(true);
	}

}
