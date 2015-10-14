package es.unican.ps.impuestoCirculacion.daoLayer;

import java.util.List;

import es.unican.ps.common.ILifecycle;
import es.unican.ps.impuestoCirculacion.domain.Contribuyente;


public interface IContribuyentesDAO extends ILifecycle {
	
	public Contribuyente creaContribuyente(Contribuyente c);
	public Contribuyente contribuyente(String dni);
	public Contribuyente actualizaContribuyente(Contribuyente nuevo);
	public Contribuyente eliminaContribuyente(String dni);
	public List<Contribuyente> contribuyentes();
	

}
