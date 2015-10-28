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
	 * A�ade un nuevo contribuyente al sistema.
	 * @param c Contribuyente que se a�ade
	 * @return El contribuyente a�adido
	 * 		   null si no se a�ade porque ya existe
	 */
	public Contribuyente altaContribuyente(Contribuyente c) {
		return contribuyentes.nuevoContribuyente(c);
	}

	/**
	 * Elimina el contribuyente cuyo dni se pasas como par�metro
	 * del sistema.
	 * @param dni DNI del cliente que se quiere eliminar
	 * @return El contribuyente eliminado
	 * 		   null si no se elimina porque no se encuentra o tiene
	 * 				veh�culos a su nombre
	 */
	public Contribuyente bajaContribuyente(String dni) {
		return contribuyentes.eliminaContribuyente(dni);
	}
	
	/**
	 * Retorna el total de impuesto a pagar por un contribuyente
	 * debido a los veh�culos que tiene a su nombre
	 * @param dni DNI del contribuyente buscado
	 * @return El valor a pagar en euros
	 * 			Un valor negativo si el contribuyente no se encuentra
	 */
	public double totalContribuyente(String dni) {

		double result = 0.0;
		Contribuyente c = contribuyentes.datosContribuyente(dni);
		
		if (c!=null) {
			for (Vehiculo v:c.getListaVehiculos()) {
				result += v.calculaPrecio();
			}
		}
		return result;
	}

	/**
	 * Retorna el contribuyente cuyo dni se pasa como par�metro
	 * @param dni DNI del contribuyente buscado
	 * @return El contribuyente cuyo dni coincide con el par�metro
	 * 			null en caso de que no se encuentre
	 */
	public Contribuyente contribuyente(String dni) {
		return contribuyentes.datosContribuyente(dni);
	}

	public Vehiculo altaVehiculo(Vehiculo v, Contribuyente c) {
		for(Contribuyente co: contribuyentes.contribuyentes()){
			for(Vehiculo ve: co.getListaVehiculos()){
				if(ve.getMatricula().equals(v.getMatricula())){
					return null;
				}
			}
		}
		if(c!=null)
			c.getListaVehiculos().add(v);
		return v;
		
	}

	public Vehiculo bajaVehiculo(String matricula, Contribuyente c) {
		if(c!=null){
			for (Vehiculo v:c.getListaVehiculos()) {
				if (v.getMatricula().equals(matricula)) {
					c.getListaVehiculos().remove(v);
					vehiculos.eliminaVehiculo(matricula);
					contribuyentes.actualizaContribuyente(c);
					return v;
				}
			}
		}
		return null;
	}

	public Vehiculo vehiculo(String matricula) {
		return vehiculos.vehiculo(matricula);
	}


	public void finaliza() {
		contribuyentes.finaliza();
		vehiculos.finaliza();
	}	
	
}

