
package org.openrtp.namespaces.rtc.version03;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for doc_serviceport complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="doc_serviceport">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ifdescription" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "doc_serviceport", namespace = "http://www.openrtp.org/namespaces/rtc_doc")
public class DocServiceport {

    @XmlAttribute(name = "description", namespace = "http://www.openrtp.org/namespaces/rtc_doc")
    protected String description;
    @XmlAttribute(name = "ifdescription", namespace = "http://www.openrtp.org/namespaces/rtc_doc")
    protected String ifdescription;

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the ifdescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIfdescription() {
        return ifdescription;
    }

    /**
     * Sets the value of the ifdescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIfdescription(String value) {
        this.ifdescription = value;
    }

}
