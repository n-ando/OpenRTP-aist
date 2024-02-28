
package org.openrtp.namespaces.rtc.version03;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for serviceinterface complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="serviceinterface">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="direction" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Provided"/>
 *             &lt;enumeration value="Required"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="instanceName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="idlFile" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serviceinterface")
@XmlSeeAlso({
    ServiceinterfaceDoc.class
})
public class Serviceinterface {

    @XmlAttribute(name = "name", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String name;
    @XmlAttribute(name = "direction", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String direction;
    @XmlAttribute(name = "instanceName", namespace = "http://www.openrtp.org/namespaces/rtc")
    protected String instanceName;
    @XmlAttribute(name = "idlFile", namespace = "http://www.openrtp.org/namespaces/rtc")
    protected String idlFile;
    @XmlAttribute(name = "type", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String type;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the direction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Sets the value of the direction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirection(String value) {
        this.direction = value;
    }

    /**
     * Gets the value of the instanceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstanceName() {
        return instanceName;
    }

    /**
     * Sets the value of the instanceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstanceName(String value) {
        this.instanceName = value;
    }

    /**
     * Gets the value of the idlFile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdlFile() {
        return idlFile;
    }

    /**
     * Sets the value of the idlFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdlFile(String value) {
        this.idlFile = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
