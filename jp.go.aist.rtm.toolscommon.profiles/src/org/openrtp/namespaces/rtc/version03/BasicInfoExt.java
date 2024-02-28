
package org.openrtp.namespaces.rtc.version03;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for basic_info_ext complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="basic_info_ext">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.openrtp.org/namespaces/rtc_doc}basic_info_doc">
 *       &lt;sequence>
 *         &lt;element name="VersionUpLogs" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Properties" type="{http://www.openrtp.org/namespaces/rtc_ext}property" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="comment" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="saveProject" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "basic_info_ext", namespace = "http://www.openrtp.org/namespaces/rtc_ext", propOrder = {
    "versionUpLogs",
    "properties"
})
public class BasicInfoExt
    extends BasicInfoDoc
{

    @XmlElement(name = "VersionUpLogs")
    protected List<String> versionUpLogs;
    @XmlElement(name = "Properties")
    protected List<Property> properties;
    @XmlAttribute(name = "comment", namespace = "http://www.openrtp.org/namespaces/rtc_ext")
    protected String comment;
    @XmlAttribute(name = "saveProject", namespace = "http://www.openrtp.org/namespaces/rtc_ext")
    protected String saveProject;

    /**
     * Gets the value of the versionUpLogs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the versionUpLogs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVersionUpLogs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getVersionUpLogs() {
        if (versionUpLogs == null) {
            versionUpLogs = new ArrayList<String>();
        }
        return this.versionUpLogs;
    }

    /**
     * Gets the value of the properties property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the properties property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProperties().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Property }
     * 
     * 
     */
    public List<Property> getProperties() {
        if (properties == null) {
            properties = new ArrayList<Property>();
        }
        return this.properties;
    }

    /**
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * Gets the value of the saveProject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSaveProject() {
        return saveProject;
    }

    /**
     * Sets the value of the saveProject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSaveProject(String value) {
        this.saveProject = value;
    }

    public void setVersionUpLogs(List<String> list) {
    }
    public void setProperties(List<Property> list) {
    }
}
