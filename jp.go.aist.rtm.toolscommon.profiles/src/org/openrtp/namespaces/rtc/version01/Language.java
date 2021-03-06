//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.04.24 at 07:25:27 午前 GMT 
//


package org.openrtp.namespaces.rtc.version01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for language complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="language">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="Cxx" type="{http://www.openrtp.org/namespaces/rtc}cxxlang"/>
 *         &lt;element name="Python" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Java" type="{http://www.openrtp.org/namespaces/rtc}javalang"/>
 *         &lt;element name="Csharp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Ruby" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "language", propOrder = {
    "cxx",
    "python",
    "java",
    "csharp",
    "ruby"
})
public class Language {

    @XmlElement(name = "Cxx")
    protected Cxxlang cxx;
    @XmlElement(name = "Python")
    protected String python;
    @XmlElement(name = "Java")
    protected Javalang java;
    @XmlElement(name = "Csharp")
    protected String csharp;
    @XmlElement(name = "Ruby")
    protected String ruby;

    /**
     * Gets the value of the cxx property.
     * 
     * @return
     *     possible object is
     *     {@link Cxxlang }
     *     
     */
    public Cxxlang getCxx() {
        return cxx;
    }

    /**
     * Sets the value of the cxx property.
     * 
     * @param value
     *     allowed object is
     *     {@link Cxxlang }
     *     
     */
    public void setCxx(Cxxlang value) {
        this.cxx = value;
    }

    /**
     * Gets the value of the python property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPython() {
        return python;
    }

    /**
     * Sets the value of the python property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPython(String value) {
        this.python = value;
    }

    /**
     * Gets the value of the java property.
     * 
     * @return
     *     possible object is
     *     {@link Javalang }
     *     
     */
    public Javalang getJava() {
        return java;
    }

    /**
     * Sets the value of the java property.
     * 
     * @param value
     *     allowed object is
     *     {@link Javalang }
     *     
     */
    public void setJava(Javalang value) {
        this.java = value;
    }

    /**
     * Gets the value of the csharp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCsharp() {
        return csharp;
    }

    /**
     * Sets the value of the csharp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCsharp(String value) {
        this.csharp = value;
    }

    /**
     * Gets the value of the ruby property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuby() {
        return ruby;
    }

    /**
     * Sets the value of the ruby property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuby(String value) {
        this.ruby = value;
    }

}
