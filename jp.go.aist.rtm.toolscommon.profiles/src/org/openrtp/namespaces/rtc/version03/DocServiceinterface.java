
package org.openrtp.namespaces.rtc.version03;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for doc_serviceinterface complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="doc_serviceinterface">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="docArgument" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="docReturn" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="docException" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="docPreCondition" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="docPostCondition" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "doc_serviceinterface", namespace = "http://www.openrtp.org/namespaces/rtc_doc")
public class DocServiceinterface {

    @XmlAttribute(name = "description", namespace = "http://www.openrtp.org/namespaces/rtc_doc")
    protected String description;
    @XmlAttribute(name = "docArgument", namespace = "http://www.openrtp.org/namespaces/rtc_doc")
    protected String docArgument;
    @XmlAttribute(name = "docReturn", namespace = "http://www.openrtp.org/namespaces/rtc_doc")
    protected String docReturn;
    @XmlAttribute(name = "docException", namespace = "http://www.openrtp.org/namespaces/rtc_doc")
    protected String docException;
    @XmlAttribute(name = "docPreCondition", namespace = "http://www.openrtp.org/namespaces/rtc_doc")
    protected String docPreCondition;
    @XmlAttribute(name = "docPostCondition", namespace = "http://www.openrtp.org/namespaces/rtc_doc")
    protected String docPostCondition;

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
     * Gets the value of the docArgument property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocArgument() {
        return docArgument;
    }

    /**
     * Sets the value of the docArgument property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocArgument(String value) {
        this.docArgument = value;
    }

    /**
     * Gets the value of the docReturn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocReturn() {
        return docReturn;
    }

    /**
     * Sets the value of the docReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocReturn(String value) {
        this.docReturn = value;
    }

    /**
     * Gets the value of the docException property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocException() {
        return docException;
    }

    /**
     * Sets the value of the docException property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocException(String value) {
        this.docException = value;
    }

    /**
     * Gets the value of the docPreCondition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocPreCondition() {
        return docPreCondition;
    }

    /**
     * Sets the value of the docPreCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocPreCondition(String value) {
        this.docPreCondition = value;
    }

    /**
     * Gets the value of the docPostCondition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocPostCondition() {
        return docPostCondition;
    }

    /**
     * Sets the value of the docPostCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocPostCondition(String value) {
        this.docPostCondition = value;
    }

}
