package es.unican.ps.impuestoCirculacion.daoLayer;

import java.util.LinkedList;
import java.util.List;


import es.unican.ps.impuestoCirculacion.domain.Ayuntamiento;
import es.unican.ps.impuestoCirculacion.domain.Contribuyente;
import es.unican.ps.impuestoCirculacion.domain.Vehiculo;




public class ImpuestosDAO implements IContribuyentesDAO, IVehiculosDAO {
	
	private Ayuntamiento ayun;
	
	public ImpuestosDAO() {
		ayun = Ayuntamiento.creaAyuntamiento();
	}

	public Contribuyente nuevoContribuyente(Contribuyente c) {
		ayun.getContribuyentes().add(c);
		return c;
	}

	public Contribuyente datosContribuyente(String dni) {
		for (Contribuyente c: ayun.getContribuyentes()) {
			if (c.getDni().equals(dni)) {
				return c;
			}
		}
		return null;
	}

	public Contribuyente actualizaContribuyente(Contribuyente nuevo) {
		for (Contribuyente c: ayun.getContribuyentes()) {
			if (c.getDni().equals(nuevo.getDni())) {
				ayun.getContribuyentes().remove(c);
				ayun.getContribuyentes().add(nuevo);
			}
		}
		return null;
	}

	public Contribuyente eliminaContribuyente(String dni) {
		for (Contribuyente c: ayun.getContribuyentes()) {
			if (c.getDni().equals(dni) && c.getListaVehiculos().size()==0) {
				ayun.getContribuyentes().remove(c);
				return c;
			}
		}
		return null;
	}

	public List<Contribuyente> contribuyentes() {
		return ayun.getContribuyentes();
	}



	public Vehiculo creaVehiculo(Vehiculo v) {
		vehiculos().add(v);
		return v;
	}

	public Vehiculo eliminaVehiculo(String matricula) {
		for(Contribuyente c:ayun.getContribuyentes()){
			for(Vehiculo v: c.getListaVehiculos()){
				if(v.getMatricula().equals(matricula)){
					c.getListaVehiculos().remove(v);
				}
			}
		}
		return vehiculo(matricula);
	}

	public Vehiculo actualizaVehiculo(Vehiculo nuevo) {
		return nuevo;
	}

	public Vehiculo vehiculo(String matricula) {
		for (Contribuyente c:ayun.getContribuyentes()) {
			for (Vehiculo v: c.getListaVehiculos()) {
				if (v.getMatricula().equals(matricula)) {
					return v;
				}
			}
		}
		return null;
	}

	public List<Vehiculo> vehiculos() {
		List<Vehiculo> vehiculos = new LinkedList<Vehiculo>();
		for (Contribuyente c:ayun.getContribuyentes()) {
			vehiculos.addAll(c.getListaVehiculos());
		}
		return vehiculos;
	}

	public void finaliza() {
		Ayuntamiento.flush(ayun);
		
	}

}
