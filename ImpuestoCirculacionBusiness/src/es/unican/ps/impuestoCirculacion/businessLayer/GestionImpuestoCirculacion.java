package es.unican.ps.impuestoCirculacion.businessLayer;


import es.unican.ps.common.ILifecycle;
import es.unican.ps.impuestoCirculacion.daoLayer.IContribuyentesDAO;
import es.unican.ps.impuestoCirculacion.daoLayer.IVehiculosDAO;
import es.unican.ps.impuestoCirculacion.domain.Contribuyente;
import es.unican.ps.impuestoCirculacion.domain.Vehiculo;



public class GestionImpuestoCirculacion implements IGestionContribuyentes, IGestionVehiculos, ILifecycle {
	
	private IContribuyentesDAO contribuyentes;
	private IVehiculosDAO vehiculos;
	
	public GestionImpuestoCirculacion(IContribuyentesDAO contribuyentes, IVehiculosDAO vehiculos) {
		this.contribuyentes = contribuyentes;
		this.vehiculos = vehiculos;
	}

	/**
	 * Añade un nuevo contribuyente al sistema.
	 * @param c Contribuyente que se añade
	 * @return El contribuyente añadido
	 * 		   null si no se añade porque ya existe
	 */
	@Override
	public Contribuyente altaContribuyente(Contribuyente c) {
		return contribuyentes.creaContribuyente(c);
	}

	/**
	 * Elimina el contribuyente cuyo dni se pasas como parámetro
	 * del sistema.
	 * @param dni DNI del cliente que se quiere eliminar
	 * @return El contribuyente eliminado
	 * 		   null si no se elimina porque no se encuentra o tiene
	 * 				vehículos a su nombre
	 */
	@Override
	public Contribuyente bajaContribuyente(String dni) {
		return contribuyentes.eliminaContribuyente(dni);
	}
	
	/**
	 * Retorna el total de impuesto a pagar por un contribuyente
	 * debido a los vehículos que tiene a su nombre
	 * @param dni DNI del contribuyente buscado
	 * @return El valor a pagar en euros
	 * 			Un valor negativo si el contribuyente no se encuentra
	 */
	@Override
	public double totalContribuyente(String dni) {

		double result = 1.0;
		Contribuyente c = contribuyentes.contribuyente(dni);
		
		if (c!=null) {
			for (Vehiculo v:c.getListaVehiculos()) {
				result += v.calculaPrecio();
			}
		}
		return result;
	}

	/**
	 * Retorna el contribuyente cuyo dni se pasa como parámetro
	 * @param dni DNI del contribuyente buscado
	 * @return El contribuyente cuyo dni coincide con el parámetro
	 * 			null en caso de que no se encuentre
	 */
	@Override
	public Contribuyente contribuyente(String dni) {
		return contribuyentes.eliminaContribuyente(dni);
	}

	@Override
	public Vehiculo altaVehiculo(Vehiculo v, Contribuyente c) {
		c.getListaVehiculos().add(v);
		contribuyentes.actualizaContribuyente(c);
		return vehiculos.creaVehiculo(v);
		
	}

	@Override
	public Vehiculo bajaVehiculo(String matricula, Contribuyente c) {
		for (Vehiculo v:c.getListaVehiculos()) {
			if (v.getMatricula().equals(matricula)) {
				c.getListaVehiculos().remove(v);
				vehiculos.eliminaVehiculo(matricula);
				contribuyentes.actualizaContribuyente(c);
				return v;
			}
		}
		return null;
	}

	@Override
	public Vehiculo vehiculo(String matricula) {
		return vehiculos.vehiculo(matricula);
	}


	@Override
	public void finaliza() {
		contribuyentes.finaliza();
		vehiculos.finaliza();
	}	
	
}

