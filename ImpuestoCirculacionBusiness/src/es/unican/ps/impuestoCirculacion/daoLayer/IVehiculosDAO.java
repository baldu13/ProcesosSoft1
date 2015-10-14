package es.unican.ps.impuestoCirculacion.daoLayer;

import java.util.List;

import es.unican.ps.common.ILifecycle;
import es.unican.ps.impuestoCirculacion.domain.Vehiculo;


public interface IVehiculosDAO extends ILifecycle {
	public Vehiculo creaVehiculo(Vehiculo v);
	public Vehiculo eliminaVehiculo(String matricula);
	public Vehiculo actualizaVehiculo(Vehiculo nuevo);
	public Vehiculo vehiculo(String matricula);
	public List<Vehiculo> vehiculos();
}

