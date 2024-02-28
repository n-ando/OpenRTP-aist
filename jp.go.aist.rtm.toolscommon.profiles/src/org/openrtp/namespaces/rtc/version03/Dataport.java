
package org.openrtp.namespaces.rtc.version03;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import org.openrtp.namespaces.rtc.version02.ConstraintType;

/**
 * <p>Java class for dataport complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataport">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Constraint" type="{http://www.openrtp.org/namespaces/rtc}constraint_type" minOccurs="0"/>
 *         &lt;element name="Event" type="{http://www.openrtp.org/namespaces/rtc}event" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="portType" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="DataInPort"/>
 *             &lt;enumeration value="DataOutPort"/>
 *             &lt;enumeration value="EventPort"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="idlFile" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="interfaceType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="dataflowType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="subscriptionType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="unit" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataport", propOrder = {
    "constraint",
    "event"
})
@XmlSeeAlso({
    DataportDoc.class
})
public class Dataport {

    @XmlElement(name = "Constraint")
    protected ConstraintType constraint;
    @XmlElement(name = "Event")
    protected List<Event> event;
    @XmlAttribute(name = "portType", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String portType;
    @XmlAttribute(name = "name", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String name;
    @XmlAttribute(name = "type", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String type;
    @XmlAttribute(name = "idlFile", namespace = "http://www.openrtp.org/namespaces/rtc")
    protected String idlFile;
    @XmlAttribute(name = "interfaceType", namespace = "http://www.openrtp.org/namespaces/rtc")
    protected String interfaceType;
    @XmlAttribute(name = "dataflowType", namespace = "http://www.openrtp.org/namespaces/rtc")
    protected String dataflowType;
    @XmlAttribute(name = "subscriptionType", namespace = "http://www.openrtp.org/namespaces/rtc")
    protected String subscriptionType;
    @XmlAttribute(name = "unit", namespace = "http://www.openrtp.org/namespaces/rtc")
    protected String unit;

    /**
     * Gets the value of the constraint property.
     * 
     * @return
     *     possible object is
     *     {@link ConstraintType }
     *     
     */
    public ConstraintType getConstraint() {
        return constraint;
    }

    /**
     * Sets the value of the constraint property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConstraintType }
     *     
     */
    public void setConstraint(ConstraintType value) {
        this.constraint = value;
    }

    /**
     * Gets the value of the event property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the event property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEvent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Event }
     * 
     * 
     */
    public List<Event> getEvent() {
        if (event == null) {
            event = new ArrayList<Event>();
        }
        return this.event;
    }

    /**
     * Gets the value of the portType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortType() {
        return portType;
    }

    /**
     * Sets the value of the portType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortType(String value) {
        this.portType = value;
    }

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
     * Gets the value of the interfaceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterfaceType() {
        return interfaceType;
    }

    /**
     * Sets the value of the interfaceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterfaceType(String value) {
        this.interfaceType = value;
    }

    /**
     * Gets the value of the dataflowType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataflowType() {
        return dataflowType;
    }

    /**
     * Sets the value of the dataflowType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataflowType(String value) {
        this.dataflowType = value;
    }

    /**
     * Gets the value of the subscriptionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriptionType() {
        return subscriptionType;
    }

    /**
     * Sets the value of the subscriptionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriptionType(String value) {
        this.subscriptionType = value;
    }

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnit(String value) {
        this.unit = value;
    }

    public void setEvent(List<Event> list) {
    }
}
