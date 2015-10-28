//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaciï¿½n de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderï¿½n si se vuelve a compilar el esquema de origen. 
// Generado el: 2014.10.08 a las 09:44:31 AM CEST 
//

package es.unican.ps.impuestoCirculacion.domain;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase Java para anonymous complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que
 * haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contribuyente" type="{http://www.example.org/ss/ImpuestoCirculacion}Contribuyente" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "contribuyente" })
@XmlRootElement(name = "ayuntamiento")
public class Ayuntamiento {

	@XmlElement(required = true)
	private List<Contribuyente> contribuyente;

	/**
	 * Gets the value of the contribuyente property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the contribuyente property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getContribuyente().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link Contribuyente }
	 * 
	 * 
	 */
	public List<Contribuyente> getContribuyentes() {
		if (contribuyente == null) {
			contribuyente = new ArrayList<Contribuyente>();
		}
		return this.contribuyente;
	}

	public static void flush(Ayuntamiento ayun) {
		try {
			JAXBContext jaxbctx = JAXBContext.newInstance(ObjectFactory.class);
			// Volcar la ifnoramción a un fichero
			Marshaller marshaller = jaxbctx.createMarshaller();
			marshaller.marshal(ayun, new File("D:/tmp/ayuntamiento.xml"));
		} catch (JAXBException e) {
			System.out.println("No se puede volcar la información al fichero");
			System.exit(0);
		}
	}

	public static Ayuntamiento creaAyuntamiento() {
		JAXBContext jaxbctx;
		try {
			jaxbctx = JAXBContext.newInstance(ObjectFactory.class);
			// Procesamos el documento (Unmarshall)
			javax.xml.bind.Unmarshaller unmarshaller = jaxbctx
					.createUnmarshaller();

			return (Ayuntamiento) unmarshaller.unmarshal(new File(
					"D:/tmp/ayuntamiento.xml"));

		} catch (JAXBException j) {
			System.out.println("Error del JAXB");
			System.out.println(j);
		}
		return null;
	}
}
