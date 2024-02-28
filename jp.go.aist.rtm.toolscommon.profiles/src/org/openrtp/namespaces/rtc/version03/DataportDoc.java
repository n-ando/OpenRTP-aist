
package org.openrtp.namespaces.rtc.version03;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataport_doc complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataport_doc">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.openrtp.org/namespaces/rtc}dataport">
 *       &lt;sequence>
 *         &lt;element name="Doc" type="{http://www.openrtp.org/namespaces/rtc_doc}doc_dataport" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataport_doc", namespace = "http://www.openrtp.org/namespaces/rtc_doc", propOrder = {
    "doc"
})
@XmlSeeAlso({
    DataportExt.class
})
public class DataportDoc
    extends Dataport
{

    @XmlElement(name = "Doc")
    protected DocDataport doc;

    /**
     * Gets the value of the doc property.
     * 
     * @return
     *     possible object is
     *     {@link DocDataport }
     *     
     */
    public DocDataport getDoc() {
        return doc;
    }

    /**
     * Sets the value of the doc property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocDataport }
     *     
     */
    public void setDoc(DocDataport value) {
        this.doc = value;
    }

}
