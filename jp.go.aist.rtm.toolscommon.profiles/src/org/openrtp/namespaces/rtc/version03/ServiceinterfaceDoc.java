
package org.openrtp.namespaces.rtc.version03;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for serviceinterface_doc complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="serviceinterface_doc">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.openrtp.org/namespaces/rtc}serviceinterface">
 *       &lt;sequence>
 *         &lt;element name="Doc" type="{http://www.openrtp.org/namespaces/rtc_doc}doc_serviceinterface" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serviceinterface_doc", namespace = "http://www.openrtp.org/namespaces/rtc_doc", propOrder = {
    "doc"
})
@XmlSeeAlso({
    ServiceinterfaceExt.class
})
public class ServiceinterfaceDoc
    extends Serviceinterface
{

    @XmlElement(name = "Doc")
    protected DocServiceinterface doc;

    /**
     * Gets the value of the doc property.
     * 
     * @return
     *     possible object is
     *     {@link DocServiceinterface }
     *     
     */
    public DocServiceinterface getDoc() {
        return doc;
    }

    /**
     * Sets the value of the doc property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocServiceinterface }
     *     
     */
    public void setDoc(DocServiceinterface value) {
        this.doc = value;
    }

}
