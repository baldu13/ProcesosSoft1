
package es.unican.ps.impuestoCirculacion.domain;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.example.ss.impuestocirculacion package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.example.ss.impuestocirculacion
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Motocicleta }
     * 
     */
    public Motocicleta createMotocicleta() {
        return new Motocicleta();
    }

    /**
     * Create an instance of {@link Ayuntamiento }
     * 
     */
    public Ayuntamiento createAyuntamiento() {
        return new Ayuntamiento();
    }

    /**
     * Create an instance of {@link Contribuyente }
     * 
     */
    public Contribuyente createContribuyente() {
        return new Contribuyente();
    }


    /**
     * Create an instance of {@link Furgoneta }
     * 
     */
    public Furgoneta createFurgoneta() {
        return new Furgoneta();
    }

    /**
     * Create an instance of {@link Turismo }
     * 
     */
    public Turismo createTurismo() {
        return new Turismo();
    }

}
