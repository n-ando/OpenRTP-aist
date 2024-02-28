
package org.openrtp.namespaces.rtc.version03;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for configuration_doc complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="configuration_doc">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.openrtp.org/namespaces/rtc}configuration">
 *       &lt;sequence>
 *         &lt;element name="Doc" type="{http://www.openrtp.org/namespaces/rtc_doc}doc_configuration" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "configuration_doc", namespace = "http://www.openrtp.org/namespaces/rtc_doc", propOrder = {
    "doc"
})
@XmlSeeAlso({
    ConfigurationExt.class
})
public class ConfigurationDoc
    extends Configuration
{

    @XmlElement(name = "Doc")
    protected DocConfiguration doc;

    /**
     * Gets the value of the doc property.
     * 
     * @return
     *     possible object is
     *     {@link DocConfiguration }
     *     
     */
    public DocConfiguration getDoc() {
        return doc;
    }

    /**
     * Sets the value of the doc property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocConfiguration }
     *     
     */
    public void setDoc(DocConfiguration value) {
        this.doc = value;
    }

}
