
package org.openrtp.namespaces.rtc.version03;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rtc_profile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rtc_profile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BasicInfo" type="{http://www.openrtp.org/namespaces/rtc}basic_info"/>
 *         &lt;element name="Actions" type="{http://www.openrtp.org/namespaces/rtc}actions"/>
 *         &lt;element name="ConfigurationSet" type="{http://www.openrtp.org/namespaces/rtc}configuration_set" minOccurs="0"/>
 *         &lt;element name="DataPorts" type="{http://www.openrtp.org/namespaces/rtc}dataport" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ServicePorts" type="{http://www.openrtp.org/namespaces/rtc}serviceport" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Language" type="{http://www.openrtp.org/namespaces/rtc}language" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement(name="RtcProfile")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rtc_profile", propOrder = {
    "basicInfo",
    "actions",
    "configurationSet",
    "dataPorts",
    "servicePorts",
    "language"
})
public class RtcProfile {

    @XmlElement(name = "BasicInfo", required = true)
    protected BasicInfo basicInfo;
    @XmlElement(name = "Actions", required = true)
    protected Actions actions;
    @XmlElement(name = "ConfigurationSet")
    protected ConfigurationSet configurationSet;
    @XmlElement(name = "DataPorts")
    protected List<Dataport> dataPorts;
    @XmlElement(name = "ServicePorts")
    protected List<Serviceport> servicePorts;
    @XmlElement(name = "Language")
    protected Language language;
    @XmlAttribute(name = "id", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String id;
    @XmlAttribute(name = "version", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String version;

    /**
     * Gets the value of the basicInfo property.
     * 
     * @return
     *     possible object is
     *     {@link BasicInfo }
     *     
     */
    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    /**
     * Sets the value of the basicInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BasicInfo }
     *     
     */
    public void setBasicInfo(BasicInfo value) {
        this.basicInfo = value;
    }

    /**
     * Gets the value of the actions property.
     * 
     * @return
     *     possible object is
     *     {@link Actions }
     *     
     */
    public Actions getActions() {
        return actions;
    }

    /**
     * Sets the value of the actions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Actions }
     *     
     */
    public void setActions(Actions value) {
        this.actions = value;
    }

    /**
     * Gets the value of the configurationSet property.
     * 
     * @return
     *     possible object is
     *     {@link ConfigurationSet }
     *     
     */
    public ConfigurationSet getConfigurationSet() {
        if (configurationSet == null) {
        	configurationSet = new ConfigurationSet();
        }
        return configurationSet;
    }

    /**
     * Sets the value of the configurationSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfigurationSet }
     *     
     */
    public void setConfigurationSet(ConfigurationSet value) {
        this.configurationSet = value;
    }

    /**
     * Gets the value of the dataPorts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataPorts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataPorts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Dataport }
     * 
     * 
     */
    public List<Dataport> getDataPorts() {
        if (dataPorts == null) {
            dataPorts = new ArrayList<Dataport>();
        }
        return this.dataPorts;
    }

    /**
     * Gets the value of the servicePorts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the servicePorts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServicePorts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Serviceport }
     * 
     * 
     */
    public List<Serviceport> getServicePorts() {
        if (servicePorts == null) {
            servicePorts = new ArrayList<Serviceport>();
        }
        return this.servicePorts;
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link Language }
     *     
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link Language }
     *     
     */
    public void setLanguage(Language value) {
        this.language = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
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

    public void setDataPorts(List<Dataport> list) {
    }
    public void setServicePorts(List<Serviceport> list) {
    }
}
