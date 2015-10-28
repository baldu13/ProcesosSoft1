package es.unican.ps.impuestoCirculacion.businessLayer;


import es.unican.ps.common.ILifecycle;
import es.unican.ps.impuestoCirculacion.domain.*;

public interface IGestionVehiculos extends ILifecycle {
	
	public Vehiculo altaVehiculo(Vehiculo v, Contribuyente c);
	
	public Vehiculo bajaVehiculo(String matricula, Contribuyente c);
	
	public Vehiculo vehiculo(String matricula); 	

}
