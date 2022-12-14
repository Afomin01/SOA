
package se.ifmo.ru.soap.impl;

import javax.xml.ws.WebFault;
import se.ifmo.ru.soap.api.VerificationFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "VerificationFault", targetNamespace = "http://api.web.ru.ifmo.se/")
public class VerificationException
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private VerificationFault faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public VerificationException(String message, VerificationFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public VerificationException(String message, VerificationFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: se.ifmo.ru.web.api.VerificationFault
     */
    public VerificationFault getFaultInfo() {
        return faultInfo;
    }

}
