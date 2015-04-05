
package lab11;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the lab11 package. 
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

    private final static QName _IntMultiplier_QNAME = new QName("http://lab11/", "intMultiplier");
    private final static QName _IntMultiplierResponse_QNAME = new QName("http://lab11/", "intMultiplierResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: lab11
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IntMultiplierResponse }
     * 
     */
    public IntMultiplierResponse createIntMultiplierResponse() {
        return new IntMultiplierResponse();
    }

    /**
     * Create an instance of {@link IntMultiplier_Type }
     * 
     */
    public IntMultiplier_Type createIntMultiplier_Type() {
        return new IntMultiplier_Type();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IntMultiplier_Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab11/", name = "intMultiplier")
    public JAXBElement<IntMultiplier_Type> createIntMultiplier(IntMultiplier_Type value) {
        return new JAXBElement<IntMultiplier_Type>(_IntMultiplier_QNAME, IntMultiplier_Type.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IntMultiplierResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab11/", name = "intMultiplierResponse")
    public JAXBElement<IntMultiplierResponse> createIntMultiplierResponse(IntMultiplierResponse value) {
        return new JAXBElement<IntMultiplierResponse>(_IntMultiplierResponse_QNAME, IntMultiplierResponse.class, null, value);
    }

}
