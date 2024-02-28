
package org.openrtp.namespaces.rtc.version03;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for basic_info complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="basic_info">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="componentType" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="STATIC"/>
 *             &lt;enumeration value="UNIQUE"/>
 *             &lt;enumeration value="COMMUTATIVE"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="activityType" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="PERIODIC"/>
 *             &lt;enumeration value="SPORADIC"/>
 *             &lt;enumeration value="EVENTDRIVEN"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="componentKind" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="rtcType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="category" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="executionRate" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="executionType" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="maxInstances" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="vendor" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="abstract" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="hardwareProfile" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="creationDate" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="updateDate" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "basic_info")
@XmlSeeAlso({
    BasicInfoDoc.class
})
public class BasicInfo {

    @XmlAttribute(name = "name", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String name;
    @XmlAttribute(name = "componentType", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String componentType;
    @XmlAttribute(name = "activityType", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String activityType;
    @XmlAttribute(name = "componentKind", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String componentKind;
    @XmlAttribute(name = "rtcType", namespace = "http://www.openrtp.org/namespaces/rtc")
    protected String rtcType;
    @XmlAttribute(name = "category", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String category;
    @XmlAttribute(name = "description", namespace = "http://www.openrtp.org/namespaces/rtc")
    protected String description;
    @XmlAttribute(name = "executionRate", namespace = "http://www.openrtp.org/namespaces/rtc")
    protected Double executionRate;
    @XmlAttribute(name = "executionType", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String executionType;
    @XmlAttribute(name = "maxInstances", namespace = "http://www.openrtp.org/namespaces/rtc")
    protected BigInteger maxInstances;
    @XmlAttribute(name = "vendor", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String vendor;
    @XmlAttribute(name = "version", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String version;
    @XmlAttribute(name = "abstract", namespace = "http://www.openrtp.org/namespaces/rtc")
    protected String _abstract;
    @XmlAttribute(name = "hardwareProfile", namespace = "http://www.openrtp.org/namespaces/rtc")
    protected String hardwareProfile;
    @XmlAttribute(name = "creationDate", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationDate;
    @XmlAttribute(name = "updateDate", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateDate;

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
     * Gets the value of the componentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComponentType() {
        return componentType;
    }

    /**
     * Sets the value of the componentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComponentType(String value) {
        this.componentType = value;
    }

    /**
     * Gets the value of the activityType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivityType() {
        return activityType;
    }

    /**
     * Sets the value of the activityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivityType(String value) {
        this.activityType = value;
    }

    /**
     * Gets the value of the componentKind property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComponentKind() {
        return componentKind;
    }

    /**
     * Sets the value of the componentKind property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComponentKind(String value) {
        this.componentKind = value;
    }

    /**
     * Gets the value of the rtcType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRtcType() {
        return rtcType;
    }

    /**
     * Sets the value of the rtcType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRtcType(String value) {
        this.rtcType = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

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
     * Gets the value of the executionRate property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getExecutionRate() {
        return executionRate;
    }

    /**
     * Sets the value of the executionRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setExecutionRate(Double value) {
        this.executionRate = value;
    }

    /**
     * Gets the value of the executionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExecutionType() {
        return executionType;
    }

    /**
     * Sets the value of the executionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExecutionType(String value) {
        this.executionType = value;
    }

    /**
     * Gets the value of the maxInstances property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxInstances() {
        return maxInstances;
    }

    /**
     * Sets the value of the maxInstances property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxInstances(BigInteger value) {
        this.maxInstances = value;
    }

    /**
     * Gets the value of the vendor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * Sets the value of the vendor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendor(String value) {
        this.vendor = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the abstract property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbstract() {
        return _abstract;
    }

    /**
     * Sets the value of the abstract property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbstract(String value) {
        this._abstract = value;
    }

    /**
     * Gets the value of the hardwareProfile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHardwareProfile() {
        return hardwareProfile;
    }

    /**
     * Sets the value of the hardwareProfile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHardwareProfile(String value) {
        this.hardwareProfile = value;
    }

    /**
     * Gets the value of the creationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the value of the creationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreationDate(XMLGregorianCalendar value) {
        this.creationDate = value;
    }

    /**
     * Gets the value of the updateDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateDate() {
        return updateDate;
    }

    /**
     * Sets the value of the updateDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateDate(XMLGregorianCalendar value) {
        this.updateDate = value;
    }

}
