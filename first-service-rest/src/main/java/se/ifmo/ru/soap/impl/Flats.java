
package se.ifmo.ru.soap.impl;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "flats", targetNamespace = "http://impl.web.ru.ifmo.se/", wsdlLocation = "http://localhost:8080/first-service-soap/flats?wsdl")
public class Flats
    extends Service
{

    private final static URL FLATS_WSDL_LOCATION;
    private final static WebServiceException FLATS_EXCEPTION;
    private final static QName FLATS_QNAME = new QName("http://impl.web.ru.ifmo.se/", "flats");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://first-service-soap:8080/first-service-soap/flats?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        FLATS_WSDL_LOCATION = url;
        FLATS_EXCEPTION = e;
    }

    public Flats() {
        super(__getWsdlLocation(), FLATS_QNAME);
    }

    public Flats(WebServiceFeature... features) {
        super(__getWsdlLocation(), FLATS_QNAME, features);
    }

    public Flats(URL wsdlLocation) {
        super(wsdlLocation, FLATS_QNAME);
    }

    public Flats(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, FLATS_QNAME, features);
    }

    public Flats(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Flats(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns FlatsWebService
     */
    @WebEndpoint(name = "FlatsWebServiceImplPort")
    public FlatsWebService getFlatsWebServiceImplPort() {
        return super.getPort(new QName("http://impl.web.ru.ifmo.se/", "FlatsWebServiceImplPort"), FlatsWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns FlatsWebService
     */
    @WebEndpoint(name = "FlatsWebServiceImplPort")
    public FlatsWebService getFlatsWebServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://impl.web.ru.ifmo.se/", "FlatsWebServiceImplPort"), FlatsWebService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (FLATS_EXCEPTION!= null) {
            throw FLATS_EXCEPTION;
        }
        return FLATS_WSDL_LOCATION;
    }

}
