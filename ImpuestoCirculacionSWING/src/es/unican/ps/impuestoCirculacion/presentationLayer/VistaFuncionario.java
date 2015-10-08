package es.unican.ps.impuestoCirculacion.presentationLayer;

import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import es.unican.ps.impuestoCirculacion.businessLayer.IGestionContribuyentes;
import es.unican.ps.impuestoCirculacion.businessLayer.IGestionVehiculos;

@SuppressWarnings("serial")
public class VistaFuncionario extends JFrame {
	
	private IGestionContribuyentes contribuyentes;
	private IGestionVehiculos vehiculos;
	
	public VistaFuncionario(IGestionContribuyentes contribuyentes,
			IGestionVehiculos vehiculos) {
		
		this.contribuyentes = contribuyentes;
		this.vehiculos = vehiculos;
		
	}

	
}
